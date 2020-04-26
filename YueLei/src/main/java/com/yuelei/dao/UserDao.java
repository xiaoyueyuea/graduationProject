package com.yuelei.dao;

import com.yuelei.entity.UserEntity;
import com.yuelei.entity.bean.PageResult;

import java.util.List;

public interface UserDao {
    boolean findUser(String username,String password);

    UserEntity getUserByName(String username);

    void modifyPassword(String username,String newPassword);

    boolean insertConsume(String username,String password,String sex,String phone);

    PageResult<UserEntity> getEmployeeList(Integer page, Integer rows);

    PageResult<UserEntity> getConsumeList(Integer page, Integer rows);

    boolean updateEmployeeInfo(String username,String sex,String phone,int admin);

    List<UserEntity> getConsumeByCondition(String username,String phone);

    List<UserEntity> getEmployeeByCondition(Integer number,String username,String phone);

    boolean addEmployee(String name,String password,String sex,String tel,int admin);
}