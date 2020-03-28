package com.yuelei.service;

import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.bean.MyOrderItem;
import com.yuelei.entity.bean.PageResult;

import java.util.List;

public interface OrderManagerService {
    PageResult<OrderEntity> getAllOnGoingOrder(Integer page,Integer rows);

    List<String> getAllOnGoingOrderStatus();

    PageResult<MyOrderItem> getMyOrderInfo(String username, Integer page, Integer rows);
}
