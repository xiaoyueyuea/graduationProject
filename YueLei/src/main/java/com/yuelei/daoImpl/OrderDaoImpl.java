package com.yuelei.daoImpl;

import com.yuelei.dao.OrderDao;
import com.yuelei.entity.OrderEntity;
import com.yuelei.util.Constants;
import com.yuelei.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
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
    public List<OrderEntity> getAllOrderList() {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<OrderEntity> orderEntityList = new ArrayList<>();
            String hql ="from OrderEntity";
            Query<OrderEntity> query = session.createQuery(hql,OrderEntity.class);
            if(query.getResultList()!=null){
                orderEntityList=query.getResultList();
            }
            transaction.commit();
            session.close();
            return orderEntityList;
        } catch (HibernateException e){
            System.out.println("查询订单失败");
            return null;
        }
    }
}
