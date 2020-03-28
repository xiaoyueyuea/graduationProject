package com.yuelei.daoImpl;

import com.yuelei.dao.MessageDao;
import com.yuelei.entity.CustomerMessageEntity;
import com.yuelei.entity.UserEntity;
import com.yuelei.util.Constants;
import com.yuelei.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class MessageDaoImpl implements MessageDao {

    @Override
    public boolean createMessage(UserEntity userEntity, String message) {
        try{
            if(userEntity!=null){
                Session session = HibernateUtils.openSession();
                Transaction transaction = session.beginTransaction();
                CustomerMessageEntity customermessageEntity = new CustomerMessageEntity();
                Date date = new Date();// 获取当前时间
                Timestamp ts = new Timestamp(date.getTime());
                customermessageEntity.setUsername(userEntity.getUsername());
                customermessageEntity.setSex(userEntity.getSex());
                customermessageEntity.setPhone(userEntity.getPhone());
                customermessageEntity.setMessageDate(ts);
                customermessageEntity.setMessageContent(message);
                customermessageEntity.setStatus(Constants.STATUS_MESSAGE_UNTREATED);
                session.save(customermessageEntity);
                transaction.commit();
                session.close();
                return true;
            }
            return false;
        }catch (HibernateException e){
            System.out.println("留言失败");
            return false;
        }
    }

    @Override
    public List<CustomerMessageEntity> getAllMessage() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<CustomerMessageEntity> customerMessageEntityList = new ArrayList<>();
        String hql ="from CustomerMessageEntity CM";
        Query<CustomerMessageEntity> query = session.createQuery(hql,CustomerMessageEntity.class);
        if(query.getResultList()!=null){
            customerMessageEntityList=query.getResultList();
        }
        transaction.commit();
        session.close();
        return customerMessageEntityList;
    }

    @Override
    public boolean getCurrentMessageAndHandle(String username, long messageDate) {
        try{
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from CustomerMessageEntity CM where username =:Username and messageDate =:MessageDate";
            CustomerMessageEntity customerMessageEntity = null;
            Query<CustomerMessageEntity> query =session.createQuery(hql,CustomerMessageEntity.class);
            query.setParameter("Username",username);
            Timestamp ts = new Timestamp(messageDate);
            query.setParameter("MessageDate",ts);
            if(query.uniqueResult()!=null){
                customerMessageEntity=query.getSingleResult();
                customerMessageEntity.setStatus(Constants.STATUS_MESSAGE_TREATED);
                session.saveOrUpdate(customerMessageEntity);
            }
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            System.out.println("操作失败");
            return false;
        }
    }

    @Override
    public List<CustomerMessageEntity> getMessageEntityByCondition(String status) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from CustomerMessageEntity CM where status =:Status";
        List<CustomerMessageEntity> customerMessageEntityList = new ArrayList<>();
        Query<CustomerMessageEntity> query = session.createQuery(hql,CustomerMessageEntity.class);
        query.setParameter("Status",status);
        if(query.getResultList()!=null){
            customerMessageEntityList=query.getResultList();
        }
        transaction.commit();
        session.close();
        return customerMessageEntityList;
    }


}
