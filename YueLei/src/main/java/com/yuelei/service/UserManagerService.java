package com.yuelei.service;

import com.yuelei.entity.UserItem;

import java.util.List;

public interface UserManagerService {

    List<UserItem> getAllEmployee();

    List<UserItem> getAllConsume();

    boolean registerConsume(String username, String password, String sex, String phone);

    boolean editEmployeeInfo(String username,String sex,String phone,int admin);

    List<UserItem> getConsumeListByCondition(String username,String phone);

    List<UserItem> getEmployeeListByCondition(int number,String username,String phone);

    boolean addEmployee(String name,String password,String sex,String tel,int admin);
}
