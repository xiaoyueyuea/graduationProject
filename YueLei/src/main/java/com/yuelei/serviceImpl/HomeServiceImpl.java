package com.yuelei.serviceImpl;

import com.yuelei.dao.UserDao;
import com.yuelei.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDao userDao;

    @Override
    public void modifyPassword(String username, String newPassword) {
        userDao.modifyPassword(username,newPassword);
    }
}
