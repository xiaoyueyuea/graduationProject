package com.yuelei.daoImpl;

import com.yuelei.dao.UserDao;
import com.yuelei.entity.UserEntity;
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
    public List<UserEntity> getEmployeeList() {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from UserEntity u where admin= 0 or admin=1";
        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        List<UserEntity> employeeList = query.getResultList();
        transaction.commit();
        session.close();
        return employeeList;
    }

    @Override
    public List<UserEntity> getConsumeList() {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from UserEntity u where admin= :Admin";
        Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
        query.setParameter("Admin",2);
        List<UserEntity> consumeList = query.getResultList();
        transaction.commit();
        session.close();
        return consumeList;
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
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql="from UserEntity u where phone= :PHONE";
        List<UserEntity> userEntityList=new ArrayList<>();
        if(!username.isEmpty()){
            UserEntity user=getUserByName(username);
            if(user!=null){
                userEntityList.add(user);
            }
        }else {
            Query<UserEntity> query = session.createQuery(hql,UserEntity.class);
            query.setParameter("PHONE",phone);
            userEntityList=query.getResultList();
        }

        transaction.commit();
        session.close();
        return userEntityList;
    }

    @Override
    public List<UserEntity> getEmployeeByCondition(int number, String username, String phone) {
        Session session=HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql1="from UserEntity u where userId= :ID";
        String hql2="from UserEntity u where phone= :PHONE";
        List<UserEntity> userEntityList=new ArrayList<>();
        if(!String.valueOf(number).isEmpty()){
            Query<UserEntity> query = session.createQuery(hql1,UserEntity.class);
            query.setParameter("ID",number);
            userEntityList=query.getResultList();
        }else if(!username.isEmpty()){
            UserEntity user=getUserByName(username);
            if(user!=null){
                userEntityList.add(user);
            }
        }else {
            Query<UserEntity> query = session.createQuery(hql2,UserEntity.class);
            query.setParameter("PHONE",phone);
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