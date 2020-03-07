package com.yuelei.dao;

import com.yuelei.entity.OrderEntity;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
    boolean createOrder(String roomNo, String roomType, String roomPrice, String roomArea, String currentUsername, String realName, String phone, String idCard, Date startTime, Date endTime, String days, String remarks);

    List<OrderEntity> getAllOrderList();
}
