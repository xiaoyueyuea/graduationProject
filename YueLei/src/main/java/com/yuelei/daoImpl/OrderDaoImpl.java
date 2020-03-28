package com.yuelei.daoImpl;

import com.yuelei.dao.OrderDao;
import com.yuelei.dao.RoomDao;
import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.bean.MyOrderItem;
import com.yuelei.entity.bean.PageResult;
import com.yuelei.util.Constants;
import com.yuelei.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private RoomDao roomDao;

    @Override
    public boolean createOrder(String roomNo, String roomType, String roomPrice, String roomArea, String currentUsername, String realName, String phone, String idCard, Date startTime, Date endTime, String days, String remarks) {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            OrderEntity orderEntity =new OrderEntity();
            orderEntity.setRoomNo(roomNo);
            orderEntity.setRoomType(roomType);
            orderEntity.setRoomPrice(roomPrice);
            orderEntity.setRoomArea(roomArea);
            orderEntity.setCustomerNickname(currentUsername);
            orderEntity.setCustomerRealname(realName);
            orderEntity.setPhone(phone);
            orderEntity.setIdCard(idCard);
            orderEntity.setStartDate(startTime);
            orderEntity.setEndDate(endTime);
            orderEntity.setDays(days);
            orderEntity.setRemarks(remarks);
            orderEntity.setStatus(Constants.STATUS_ORDER_UNCHECKED);
            session.save(orderEntity);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e){
            System.out.println("############失败失败");
            return false;
        }
    }

    @Override
    public PageResult<OrderEntity> getAllOnGoingOrderList(Integer page, Integer rows) {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<OrderEntity> orderEntityList = new ArrayList<>();
            int currentPage = (page == null || page == 0) ? 1: page;//第几页
            int pageSize = (rows == null || rows == 0) ? 20: rows;//每页多少行
            int total=0;
            PageResult<OrderEntity> orderEntityPageResult = new PageResult<>();
            String hql ="from OrderEntity where status !=:Status";
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.getResultList()!=null){
                total=query.getResultList().size();
                orderEntityList=query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
            }
            orderEntityPageResult.setTotal(total);
            orderEntityPageResult.setContent(orderEntityList);
            transaction.commit();
            session.close();
            return orderEntityPageResult;
        } catch (HibernateException e){
            System.out.println("查询订单失败");
            return null;
        }
    }

    @Override
    public List<String> getAllOnGoingOrderStatus() {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<String> status = new ArrayList<>();
            String hql="select status from OrderEntity O where status !=:Status group by status";
            Query<String> query = session.createQuery(hql);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.getResultList()!=null){
                status = query.getResultList();
            }
            transaction.commit();
            session.close();
            return status;
        } catch (HibernateException e){
            System.out.println("查询失败");
            return null;
        }
    }

    @Override
    public List<OrderEntity> getOnGoingOrderByCondition(String customerName, String customerPhone, String roomType, String orderStatus) {
        String[] pram=new String[5];
        int i=0;
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<OrderEntity> orderEntityList = new ArrayList<>();
            String hql = "from OrderEntity O where status !=?"+i;
            pram[i++]=Constants.STATUS_ORDER_FINISHED;
            if(!customerName.isEmpty()){
                hql+=" and customerRealname =?"+i;
                pram[i++]=customerName;
            }
            if(!customerPhone.isEmpty()){
                hql+=" and phone =?"+i;
                pram[i++]=customerPhone;
            }
            if(!roomType.isEmpty()){
                hql+=" and roomType =?"+i;
                pram[i++]=roomType;
            }
            if(!orderStatus.isEmpty()){
                hql+=" and status =?"+i;
                pram[i++]=orderStatus;
            }


            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);

            for(int j=0;j<i;j++){
                query.setParameter(j,pram[j]);
            }

            if(query.getResultList()!=null){
                orderEntityList=query.getResultList();
            }
            transaction.commit();
            session.close();
            return orderEntityList;
        }catch (HibernateException e){
            System.out.println("查询失败");
            return null;
        }
    }

    @Override
    public PageResult<OrderEntity> getFinishedOrders(Integer page, Integer rows) {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<OrderEntity> orderEntityList = new ArrayList<>();
            int currentPage = (page == null || page == 0) ? 1: page;//第几页
            int pageSize = (rows == null || rows == 0) ? 20: rows;//每页多少行
            int total=0;
            PageResult<OrderEntity> orderEntityPageResult = new PageResult<>();
            String hql ="from OrderEntity where status =:Status";
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.getResultList()!=null){
                total=query.getResultList().size();
                orderEntityList=query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
            }
            orderEntityPageResult.setContent(orderEntityList);
            orderEntityPageResult.setTotal(total);
            transaction.commit();
            session.close();
            return orderEntityPageResult;
        } catch (HibernateException e){
            System.out.println("查询订单失败");
            return null;
        }
    }

    @Override
    public List<OrderEntity> getFinishedOrderByCondition(String customerName, String customerPhone, String roomType) {
        String[] pram=new String[4];
        int i=0;
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<OrderEntity> orderEntityList = new ArrayList<>();
            String hql = "from OrderEntity O where status =?"+i;
            pram[i++]=Constants.STATUS_ORDER_FINISHED;

            if(!customerName.isEmpty()){
                hql+=" and customerRealname =?"+i;
                pram[i++]=customerName;
            }
            if(!customerPhone.isEmpty()){
                hql+=" and phone =?"+i;
                pram[i++]=customerPhone;
            }
            if(!roomType.isEmpty()){
                hql+=" and roomType =?"+i;
                pram[i++]=roomType;
            }

            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);

            for(int j=0;j<i;j++){
                query.setParameter(j,pram[j]);
            }
            if(query.getResultList()!=null){
                orderEntityList=query.getResultList();
            }
            transaction.commit();
            session.close();
            return orderEntityList;
        }catch (HibernateException e){
            System.out.println("查询失败");
            return null;
        }
    }

    @Override
    public boolean updateOrderStatus(String roomNo, String status) {
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            OrderEntity orderEntity = new OrderEntity();
            String hql = "from OrderEntity O where roomNo =:RoomNo and status !=:Status";
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            query.setParameter("RoomNo",roomNo);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.uniqueResult()!=null){
                orderEntity=query.getSingleResult();
            }
            orderEntity.setStatus(status);
            session.saveOrUpdate(orderEntity);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public boolean addOrderRemarks(String roomNo, String remarks) {
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            OrderEntity orderEntity = new OrderEntity();
            String hql = "from OrderEntity O where roomNo =:RoomNo and status !=:Status";
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            query.setParameter("RoomNo",roomNo);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.uniqueResult()!=null){
                orderEntity=query.getSingleResult();
            }
            orderEntity.setRemarks(remarks);
            session.saveOrUpdate(orderEntity);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            System.out.println("添加订单备注失败");
            return false;
        }
    }

    @Override
    public boolean delayOrderEndDate(String roomNo, Date delayDate,String days) {
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            OrderEntity orderEntity = new OrderEntity();
            String hql = "from OrderEntity O where roomNo =:RoomNo and status !=:Status";
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            query.setParameter("RoomNo",roomNo);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.uniqueResult()!=null){
                orderEntity=query.getSingleResult();
            }
            orderEntity.setEndDate(delayDate);
            String totalDays=String.valueOf(Integer.parseInt(orderEntity.getDays())+Integer.parseInt(days));
            orderEntity.setDays(totalDays);
            session.saveOrUpdate(orderEntity);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            System.out.println("延期失败");
            return false;
        }
    }

    @Override
    public PageResult<MyOrderItem> getOrderByCustomer(String username, Integer page, Integer rows) {
        if(!username.isEmpty()){
            List<OrderEntity> orderEntityList = new ArrayList<>();
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from OrderEntity where customerNickname =:Username";
            int currentPage = (page == null || page == 0) ? 1: page;//第几页
            int pageSize = (rows == null || rows == 0) ? 20: rows;//每页多少行
            int total =0;
            PageResult<MyOrderItem> myOrderItemPageResult = new PageResult<>();
            List<MyOrderItem> myOrderItemList = new ArrayList<>();
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            query.setParameter("Username",username);
            if(query.getResultList()!=null){
                total = query.getResultList().size();
                orderEntityList=query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
                for (OrderEntity orderEntity : orderEntityList){
                    MyOrderItem myOrderItem = new MyOrderItem();
                    myOrderItem.setRoomNo(orderEntity.getRoomNo());
                    myOrderItem.setRoomType(orderEntity.getRoomType());
                    myOrderItem.setRoomPrice(orderEntity.getRoomPrice());
                    myOrderItem.setStartDate(orderEntity.getStartDate());
                    myOrderItem.setEndDate(orderEntity.getEndDate());
                    myOrderItem.setDays(orderEntity.getDays());
                    myOrderItem.setStatus(orderEntity.getStatus());
                    myOrderItemList.add(myOrderItem);
                }
            }
            myOrderItemPageResult.setContent(myOrderItemList);
            myOrderItemPageResult.setTotal(total);
            transaction.commit();
            session.close();
            return myOrderItemPageResult;
        }
        return null;
    }

    @Override
    public boolean updateOrderByRoomNoForChangeRoom(String oldRoomNo, String newRoomNo, String priceDifference) {
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from OrderEntity O where roomNo =:RoomNo and status !=:Status";
            Query<OrderEntity> query = session.createQuery(hql, OrderEntity.class);
            query.setParameter("RoomNo",oldRoomNo);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            OrderEntity orderEntity = query.getSingleResult();
            RoomEntity roomEntity = roomDao.getRoomByRoomNo(newRoomNo);
            orderEntity.setRoomNo(roomEntity.getRoomNo());
            orderEntity.setRoomType(roomEntity.getType());
            orderEntity.setRoomArea(roomEntity.getArea());
            orderEntity.setRemarks("由"+oldRoomNo+"房转到"+newRoomNo+"房");
            if(!priceDifference.isEmpty()){
                orderEntity.setRemarks(orderEntity.getRemarks()+",补差价:"+priceDifference);
            }
            session.saveOrUpdate(orderEntity);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public OrderEntity getOnGoingOrderByRoomNo(String roomNo) {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            OrderEntity orderEntity =null;
            String hql = "from OrderEntity O where roomNo =:RoomNo and status !=:Status";
            Query<OrderEntity> query = session.createQuery(hql, OrderEntity.class);
            query.setParameter("RoomNo",roomNo);
            query.setParameter("Status",Constants.STATUS_ORDER_FINISHED);
            if(query.uniqueResult()!=null){
                orderEntity = query.getSingleResult();
                transaction.commit();
                session.close();
                return orderEntity;
            }
            transaction.commit();
            session.close();
            return null;
        }catch (HibernateException e){
            System.out.println("未查到符合条件的订单");
            return null;
        }
    }

    @Override
    public boolean deleteOrder(int orderId) {
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "delete OrderEntity O where O.orderId =:Id";
            Query query = session.createQuery(hql);
            query.setParameter("Id",orderId);
            query.executeUpdate();
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

}
