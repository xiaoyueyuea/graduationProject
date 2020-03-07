package com.yuelei.serviceImpl;

import com.yuelei.dao.OrderDao;
import com.yuelei.entity.OrderEntity;
import com.yuelei.service.OrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderManagerServiceImpl implements OrderManagerService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderEntity> getAllOrder() {
        return orderDao.getAllOrderList();
    }
}
