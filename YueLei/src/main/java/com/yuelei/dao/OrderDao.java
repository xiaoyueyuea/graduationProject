package com.yuelei.dao;

import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.bean.MyOrderItem;
import com.yuelei.entity.bean.PageResult;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
    boolean createOrder(String roomNo, String roomType, String roomPrice, String roomArea, String currentUsername, String realName, String phone, String idCard, Date startTime, Date endTime, String days, String remarks);

    PageResult<OrderEntity> getAllOnGoingOrderList(Integer page, Integer rows);

    List<String> getAllOnGoingOrderStatus();

    List<OrderEntity> getOnGoingOrderByCondition(String customerName,String customerPhone,String roomType,String orderStatus);

    PageResult<OrderEntity> getFinishedOrders(Integer page, Integer rows);

    List<OrderEntity> getFinishedOrderByCondition(String customerName,String customerPhone,String roomType);

    boolean updateOrderStatus(String roomNo,String status);

    boolean addOrderRemarks(String roomNo,String remarks);

    boolean delayOrderEndDate(String roomNo,Date delayDate,String days);

    PageResult<MyOrderItem> getOrderByCustomer(String username,Integer page,Integer rows);

    boolean updateOrderByRoomNoForChangeRoom(String oldRoomNo,String newRoomNo,String priceDifference);

    OrderEntity getOnGoingOrderByRoomNo(String roomNo);

    boolean deleteOrder(int orderId);
}
