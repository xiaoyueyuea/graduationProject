package com.yuelei.service;

import com.yuelei.entity.UserEntity;

public interface LoginService {
    String checkUser(String username,String password);

    UserEntity getUser(String username);
}
