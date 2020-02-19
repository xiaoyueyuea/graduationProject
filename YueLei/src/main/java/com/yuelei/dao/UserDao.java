package com.yuelei.dao;

import com.yuelei.entity.UserEntity;

import java.util.List;

public interface UserDao {
    boolean findUser(String username,String password);

    UserEntity getUserByName(String username);

    void modifyPassword(String username,String newPassword);

    boolean insertConsume(String username,String password,String sex,String phone);

    List<UserEntity> getEmployeeList();

    List<UserEntity> getConsumeList();

    boolean updateEmployeeInfo(String username,String sex,String phone,int admin);

    List<UserEntity> getConsumeByCondition(String username,String phone);

    List<UserEntity> getEmployeeByCondition(int number,String username,String phone);

    boolean addEmployee(String name,String password,String sex,String tel,int admin);
}
