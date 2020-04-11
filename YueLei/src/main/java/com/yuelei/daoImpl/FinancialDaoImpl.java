package com.yuelei.daoImpl;

import com.yuelei.dao.FinancialDao;
import com.yuelei.entity.FinancialManagerEntity;
import com.yuelei.entity.OrderEntity;
import com.yuelei.util.Constants;
import com.yuelei.util.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancialDaoImpl implements FinancialDao {

    @Override
    public boolean createFinancialManagerEntity(OrderEntity orderEntity, String otherCost) {
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            FinancialManagerEntity financialManagerEntity = new FinancialManagerEntity();
            financialManagerEntity.setOrderId(orderEntity.getOrderId());
            financialManagerEntity.setRoomType(orderEntity.getRoomType());
            financialManagerEntity.setRoomPrice(orderEntity.getRoomPrice());
            financialManagerEntity.setStartDate(orderEntity.getStartDate());
            financialManagerEntity.setEndDate(orderEntity.getEndDate());
            financialManagerEntity.setDays(orderEntity.getDays());
            financialManagerEntity.setRoomCharge(String.valueOf(Integer.parseInt(orderEntity.getDays()) * Integer.parseInt(orderEntity.getRoomPrice())));
            if(!orderEntity.getRemarks().isEmpty() && !orderEntity.getRemarks().equals(Constants.REMARKS_ORDER_OVERDUE)){
                int index = orderEntity.getRemarks().lastIndexOf(":");
                if(index > 0){
                    financialManagerEntity.setPriceDifference(orderEntity.getRemarks().substring(index+1));
                }
            }
            if(financialManagerEntity.getPriceDifference().isEmpty()){
                financialManagerEntity.setPriceDifference("0");
            }
            if(otherCost.isEmpty()){
                financialManagerEntity.setOtherCost("0");
            }else {
                financialManagerEntity.setOtherCost(otherCost);
            }

            session.save(financialManagerEntity);
            transaction.commit();
            session.close();
            return true;
        }catch (HibernateException e){
            System.out.println("创建账单失败");
            return false;
        }
    }

    @Override
    public List<FinancialManagerEntity> getAllFinancialManagerEntity() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        List<FinancialManagerEntity> financialManagerEntityList = new ArrayList<>();
        String hql = "from FinancialManagerEntity";
        Query<FinancialManagerEntity> query = session.createQuery(hql,FinancialManagerEntity.class);
        if(query.getResultList()!=null){
            financialManagerEntityList = query.getResultList();
        }
        transaction.commit();
        session.close();
        return financialManagerEntityList;
    }

    @Override
    public List<FinancialManagerEntity> getFinancialManagerEntityListByCondition(String startDate, String endDate, String roomType) {
        Object[] pram=new Object[3];
        int i=0;
        try {
            Session session = HibernateUtils.openSession();
            Transaction transaction = session.beginTransaction();
            List<FinancialManagerEntity> financialManagerEntityList = new ArrayList<>();
            String hql = "from FinancialManagerEntity FM where 1=1";
            if(!startDate.isEmpty()){
                hql+=" and end_date >=?"+i;
                pram[i++]=startDate;
            }
            if(!endDate.isEmpty()){
                hql+=" and end_date <=?"+i;
                pram[i++]=endDate;
            }
            if(!roomType.isEmpty()){
                hql+=" and room_type =?"+i;
                pram[i++]=roomType;
            }

            Query<FinancialManagerEntity> query = session.createQuery(hql,FinancialManagerEntity.class);
            for(int j=0;j<i;j++){
                query.setParameter(j,pram[j]);
            }
            if(query.getResultList()!=null){
                financialManagerEntityList = query.getResultList();
            }
            transaction.commit();
            session.close();
            return financialManagerEntityList;
        } catch (HibernateException e){
            System.out.println("查询失败");
            return null;
        }
    }
}
