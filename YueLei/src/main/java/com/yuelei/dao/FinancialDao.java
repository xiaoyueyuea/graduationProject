package com.yuelei.dao;

import com.yuelei.entity.FinancialManagerEntity;
import com.yuelei.entity.OrderEntity;

import java.util.List;

public interface FinancialDao {

    boolean createFinancialManagerEntity(OrderEntity orderEntity,String otherCost);

    List<FinancialManagerEntity> getAllFinancialManagerEntity();

    List<FinancialManagerEntity> getFinancialManagerEntityListByCondition(String startDate,String endDate,String roomType);
}
