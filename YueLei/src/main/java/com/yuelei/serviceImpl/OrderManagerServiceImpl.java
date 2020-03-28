package com.yuelei.serviceImpl;

import com.yuelei.dao.OrderDao;
import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.bean.MyOrderItem;
import com.yuelei.entity.bean.PageResult;
import com.yuelei.service.OrderManagerService;
import com.yuelei.service.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManagerServiceImpl implements OrderManagerService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RoomBookService roomBookService;

    @Override
    public PageResult<OrderEntity> getAllOnGoingOrder(Integer page,Integer rows) {
        return orderDao.getAllOnGoingOrderList(page,rows);
    }

    @Override
    public List<String> getAllOnGoingOrderStatus() {
        return orderDao.getAllOnGoingOrderStatus();
    }

    @Override
    public PageResult<MyOrderItem> getMyOrderInfo(String username,Integer page,Integer rows) {
        PageResult<MyOrderItem> myOrderItemPageResult = orderDao.getOrderByCustomer(username,page,rows);
        for(MyOrderItem myOrderItem : myOrderItemPageResult.getContent()){
            String picture = roomBookService.getRoomCoverPicture(myOrderItem.getRoomType());
            myOrderItem.setPicture(picture);
        }
        return myOrderItemPageResult;
    }
}
