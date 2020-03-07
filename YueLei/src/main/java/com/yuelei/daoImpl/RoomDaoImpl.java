package com.yuelei.daoImpl;

import com.yuelei.dao.RoomDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao {
    @Override
    public List<RoomEntity> getAllEmptyRoom() {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        List<RoomEntity> roomEntityList =new ArrayList<>();
        String hql="from RoomEntity R where status =:Status ";
        Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
        query.setParameter("Status","空闲");
        if(query.getResultList()!=null){
            roomEntityList=query.list();
        }
        transaction.commit();
        session.close();
        return roomEntityList;
    }

    @Override
    public List<String> getAllRoomType() {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        List<String> roomType = new ArrayList<>();
        String hql="select type from RoomEntity R group by type";
        Query query = session.createQuery(hql);
        if(query.getResultList()!=null){
            roomType=query.list();
        }
        transaction.commit();
        session.close();
        return roomType;
    }

    @Override
    public RoomEntity getFirstRoomByType(String type) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        RoomEntity roomEntity = null;
        String hql="from RoomEntity R where type =:Type";
        Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
        query.setParameter("Type",type);
        if(query.getResultList()!=null){
            roomEntity=query.list().get(0);
        }
        transaction.commit();
        session.close();
        return roomEntity;
    }

    @Override
    public RoompictureEntity getRoomAlbum(String type) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        RoompictureEntity roompictureEntity = null;
        String hql="from RoompictureEntity RP where type =:Type";
        Query<RoompictureEntity> query = session.createQuery(hql,RoompictureEntity.class);
        query.setParameter("Type",type);
        if(query.getResultList()!=null){
            roompictureEntity=query.list().get(0);
        }
        transaction.commit();
        session.close();
        return roompictureEntity;
    }

    @Override
    public boolean checkRoomIsExit(String roomNo) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        boolean isExit=false;
        String hql="from RoomEntity R where roomNo =:RoomNo";
        Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
        query.setParameter("RoomNo",roomNo);
        if(query.uniqueResult()!=null){
            isExit=true;
        }
        transaction.commit();
        session.close();
        return isExit;
    }

    @Override
    public boolean addRoom(String roomNO, String type, String price, String area, String remarks) {
        try{
            Session session =HibernateUtils.openSession();
            Transaction transaction=session.beginTransaction();
            RoomEntity roomEntity = new RoomEntity();
            roomEntity.setRoomNo(roomNO);
            roomEntity.setType(type);
            roomEntity.setPrice(price);
            roomEntity.setArea(area);
            roomEntity.setRemarks(remarks);
            roomEntity.setStatus("空闲");
            session.saveOrUpdate(roomEntity);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e){
            return false;
        }
    }

    @Override
    public boolean addOrEditRoomPictureByType(String type) {
        return false;
    }

    @Override
    public boolean addOrEditRoomTypePicture(String type,String picture1Address,String picture2Address,String picture3Address,String picture4Address) {
        try{
            Session session =HibernateUtils.openSession();
            Transaction transaction=session.beginTransaction();
            RoompictureEntity roompictureEntity = getRoomAlbum(type);
            if(!picture1Address.isEmpty()){
                roompictureEntity.setPicture1(picture1Address);
            }
            if(!picture2Address.isEmpty()){
                roompictureEntity.setPicture2(picture2Address);
            }
            if(!picture3Address.isEmpty()){
                roompictureEntity.setPicture3(picture3Address);
            }
            if(!picture4Address.isEmpty()){
                roompictureEntity.setPicture4(picture4Address);
            }
            session.saveOrUpdate(roompictureEntity);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public void createNewRoomType(String type) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        RoompictureEntity roompictureEntity = new RoompictureEntity();
        roompictureEntity.setType(type);
        session.saveOrUpdate(roompictureEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public List<RoomEntity> getAllRoom() {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        List<RoomEntity> roomEntityList = null;
        String hql="from RoomEntity";
        Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
        if(query.getResultList()!=null){
            roomEntityList=query.list();
        }
        transaction.commit();
        session.close();
        return roomEntityList;
    }

    @Override
    public boolean editRoomInfo(String roomNo,String type,String price,String area,String remarks) {
        try{
            Session session =HibernateUtils.openSession();
            Transaction transaction=session.beginTransaction();
            RoomEntity roomEntity = null;
            String hql="from RoomEntity where roomNo =:RoomNo";
            Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
            query.setParameter("RoomNo",roomNo);
            if(query.uniqueResult()!=null){
                roomEntity=query.uniqueResult();
                roomEntity.setType(type);
                roomEntity.setPrice(price);
                roomEntity.setArea(area);
                roomEntity.setRemarks(remarks);
                session.saveOrUpdate(roomEntity);
            }
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public List<RoomEntity> getRoomListByCondition(String roomNo, String roomType) {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from RoomEntity R where type= :RoomType";
        List<RoomEntity> roomEntityList=new ArrayList<>();
        if(!roomNo.isEmpty()){
            RoomEntity roomEntity=getRoomByRoomNo(roomNo);
            if(roomEntity!=null){
                roomEntityList.add(roomEntity);
            }
        }else {
            Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
            query.setParameter("RoomType",roomType);
            roomEntityList=query.getResultList();
        }

        transaction.commit();
        session.close();
        return roomEntityList;
    }

    @Override
    public RoomEntity getRoomByRoomNo(String roomNo) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        RoomEntity roomEntity = null;
        String hql="from RoomEntity R where roomNo =:RoomNo";
        Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
        query.setParameter("RoomNo",roomNo);
        if(query.uniqueResult()!=null){
            roomEntity=query.uniqueResult();
        }
        transaction.commit();
        session.close();
        return roomEntity;
    }

    @Override
    public List<RoompictureEntity> getAllRoomPicture() {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        List<RoompictureEntity> roompictureEntityList = new ArrayList<>();
        String hql = "from RoompictureEntity";
        Query<RoompictureEntity> query = session.createQuery(hql,RoompictureEntity.class);
        if(query.getResultList()!=null){
            roompictureEntityList = query.getResultList();
        }
        transaction.commit();
        session.close();
        return roompictureEntityList;
    }

    @Override
    public RoompictureEntity getRoompictureEntityByType(String type) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        String hql = "from RoompictureEntity rp where type =:Type";
        RoompictureEntity roompictureEntity = null;
        Query<RoompictureEntity> query = session.createQuery(hql,RoompictureEntity.class);
        query.setParameter("Type",type);
        if(query.uniqueResult()!=null){
            roompictureEntity=query.getSingleResult();
        }
        transaction.commit();
        session.close();
        return roompictureEntity;
    }

    @Override
    public RoomEntity getFirstRoomToOrder(String type) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        String hql = "from RoomEntity R where type =:Type and status =:Status";
        RoomEntity roomEntity = null;
        Query<RoomEntity> query = session.createQuery(hql,RoomEntity.class);
        query.setParameter("Type",type);
        query.setParameter("Status","空闲");
        if(query.getResultList()!=null){
            roomEntity=query.getResultList().get(0);
        }
        transaction.commit();
        session.close();
        return roomEntity;
    }

    @Override
    public void updateRoomStatus(String roomNo, String status) {
        Session session =HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        RoomEntity roomEntity = getRoomByRoomNo(roomNo);
        roomEntity.setStatus(status);
        session.saveOrUpdate(roomEntity);
        transaction.commit();
        session.close();
    }
}
