package com.yuelei.daoImpl;

import com.yuelei.dao.UserDao;
import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.UserEntity;
import com.yuelei.entity.bean.PageResult;
import com.yuelei.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public boolean findUser(String username, String password) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        String hql="from UserEntity U where username= :UserName and password= :Password";
        boolean isExit=false;
        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        query.setParameter("UserName",username);
        query.setParameter("Password",password);
        if(query.uniqueResult()!=null){isExit=true;}
        transaction.commit();
        session.close();
        return isExit;
    }

    @Override
    public UserEntity getUserByName(String username) {
        Session session= HibernateUtils.openSession();
        Transaction transaction=session.beginTransaction();
        String hql="from UserEntity U where username= :UserName";
        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        query.setParameter("UserName",username);
        UserEntity user=null;
        if(query.uniqueResult()!=null){
            user=query.list().get(0);
        }
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void modifyPassword(String username,String newPassword) {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        UserEntity user=getUserByName(username);
        user.setPassword(newPassword);
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean insertConsume(String username, String password, String sex, String phone) {
        try{
            Session session=HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            UserEntity user=new UserEntity();
            user.setUsername(username);
            user.setPassword(password);
            user.setSex(sex);
            user.setPhone(phone);
            user.setAdmin(2);
            session.saveOrUpdate(user);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e){
            return false;
        }
    }


    @Override
    public PageResult<UserEntity> getEmployeeList(Integer page, Integer rows) {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        int currentPage = (page == null || page == 0) ? 1: page;//第几页
        int pageSize = (rows == null || rows == 0) ? 20: rows;//每页多少行
        PageResult<UserEntity> userEntityPageResult = new PageResult<>();
        String hql="from UserEntity u where admin= 0 or admin=1";
        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        int total = query.getResultList().size();
        List<UserEntity> employeeList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        userEntityPageResult.setPage(page);
        userEntityPageResult.setSize(rows);
        userEntityPageResult.setTotal(total);
        userEntityPageResult.setContent(employeeList);
        transaction.commit();
        session.close();
        return userEntityPageResult;
    }

    @Override
    public PageResult<UserEntity> getConsumeList(Integer page, Integer rows) {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        int currentPage = (page == null || page == 0) ? 1: page;//第几页
        int pageSize = (rows == null || rows == 0) ? 20: rows;//每页多少行
        PageResult<UserEntity> userEntityPageResult = new PageResult<>();
        String hql="from UserEntity u where admin= :Admin";
        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        query.setParameter("Admin",2);
        int total = query.getResultList().size();
        List<UserEntity> consumeList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        userEntityPageResult.setContent(consumeList);
        userEntityPageResult.setTotal(total);
        userEntityPageResult.setPage(page);
        userEntityPageResult.setSize(rows);
        transaction.commit();
        session.close();
        return userEntityPageResult;
    }

    @Override
    public boolean updateEmployeeInfo(String username,String sex, String phone, int admin) {
        try{
            Session session=HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            UserEntity user=getUserByName(username);
            user.setSex(sex);
            user.setPhone(phone);
            user.setAdmin(admin);
            session.saveOrUpdate(user);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public List<UserEntity> getConsumeByCondition(String username, String phone) {
        String[] pram=new String[2];
        int i=0;
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from UserEntity u where admin =2";
        List<UserEntity> userEntityList=new ArrayList<>();
        if(!username.isEmpty()){
            hql+=" and username =?"+i;
            pram[i++]=username;
        }
        if(!phone.isEmpty()){
            hql+=" and phone =?"+i;
            pram[i++]=phone;
        }

        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        for(int j=0;j<i;j++){
            query.setParameter(j,pram[j]);
        }

        if(query.getResultList()!=null){
            userEntityList=query.getResultList();
        }
        transaction.commit();
        session.close();
        return userEntityList;
    }

    @Override
    public List<UserEntity> getEmployeeByCondition(Integer number, String username, String phone) {
        String[] pram=new String[2];
        int i=0;
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from UserEntity u where admin != 2";
        List<UserEntity> userEntityList=new ArrayList<>();
        if(number != null){
            hql+=" and userId ="+number;
        }
        if(!username.isEmpty()){
            hql+=" and username =?"+i;
            pram[i++]=username;
        }
        if(!phone.isEmpty()){
            hql+=" and phone =?"+i;
            pram[i++]=phone;
        }

        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        for(int j=0;j<i;j++){
            query.setParameter(j,pram[j]);
        }

        if(query.getResultList()!=null){
            userEntityList=query.getResultList();
        }
        transaction.commit();
        session.close();
        return userEntityList;
    }

    @Override
    public boolean addEmployee(String name, String password, String sex, String tel, int admin) {
        try{
            Session session=HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            UserEntity user=new UserEntity();
            user.setUsername(name);
            user.setPassword(password);
            user.setSex(sex);
            user.setPhone(tel);
            user.setAdmin(admin);
            session.saveOrUpdate(user);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            return false;
        }
    }
}