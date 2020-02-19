package com.yuelei.serviceImpl;

import com.yuelei.dao.UserDao;
import com.yuelei.entity.UserEntity;
import com.yuelei.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public String checkUser(String username, String password) {
        boolean result=userDao.findUser(username,password);
        return String.valueOf(result);
    }

    @Override
    public UserEntity getUser(String username) {
        return userDao.getUserByName(username);
    }
}
