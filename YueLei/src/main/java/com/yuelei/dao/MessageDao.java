package com.yuelei.dao;

import com.yuelei.entity.CustomerMessageEntity;
import com.yuelei.entity.UserEntity;

import java.util.List;

public interface MessageDao {

    boolean createMessage(UserEntity userEntity,String message);

    List<CustomerMessageEntity> getAllMessage();

    boolean getCurrentMessageAndHandle(String username, long messageDate);

    List<CustomerMessageEntity> getMessageEntityByCondition(String status);
}
