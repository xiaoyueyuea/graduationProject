package com.yuelei.serviceImpl;

import com.yuelei.dao.UserDao;
import com.yuelei.entity.UserEntity;
import com.yuelei.entity.bean.PageResult;
import com.yuelei.entity.bean.UserItem;
import com.yuelei.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageResult<UserEntity> getAllEmployee(Integer page, Integer rows) {
        PageResult<UserEntity> userEntityPageResult = new PageResult<>();
        userEntityPageResult.setContent(userDao.getEmployeeList(page,rows).getContent());
        userEntityPageResult.setTotal(userDao.getEmployeeList(page,rows).getTotal());
        userEntityPageResult.setSize(rows);
        userEntityPageResult.setPage(page);
        return userEntityPageResult;
    }

    @Override
    public PageResult<UserEntity> getAllConsume(Integer page, Integer rows) {
        PageResult<UserEntity> userEntityPageResult = new PageResult<>();
        userEntityPageResult.setContent(userDao.getConsumeList(page,rows).getContent());
        userEntityPageResult.setTotal(userDao.getConsumeList(page,rows).getTotal());
        userEntityPageResult.setSize(rows);
        userEntityPageResult.setPage(page);
        return userEntityPageResult;
    }

    @Override
    public boolean registerConsume(String username, String password, String sex, String phone) {
        return userDao.insertConsume(username,password,sex,phone);
    }

    @Override
    public boolean editEmployeeInfo(String username, String sex, String phone, int admin) {
        return userDao.updateEmployeeInfo(username,sex,phone,admin);
    }

    @Override
    public List<UserItem> getConsumeListByCondition(String username, String phone) {
        List<UserEntity> userEntityList = userDao.getConsumeByCondition(username,phone);
        List<UserItem> userItemList =new ArrayList<>();
        if(userEntityList!=null){
            for(UserEntity user : userEntityList){
                UserItem userItem=new UserItem();
                userItem.setUserId(user.getUserId());
                userItem.setUsername(user.getUsername());
                userItem.setSex(user.getSex());
                userItem.setPhone(user.getPhone());
                userItem.setAdmin(user.getAdmin());
                userItemList.add(userItem);
            }
        }
        return userItemList;
    }

    @Override
    public List<UserItem> getEmployeeListByCondition(Integer number, String username, String phone) {
        List<UserEntity> userEntityList=userDao.getEmployeeByCondition(number,username,phone);
        List<UserItem> userItemList =new ArrayList<>();
        if(userEntityList!=null){
            for(UserEntity user : userEntityList){
                UserItem userItem=new UserItem();
                userItem.setUserId(user.getUserId());
                userItem.setUsername(user.getUsername());
                userItem.setSex(user.getSex());
                userItem.setPhone(user.getPhone());
                userItem.setAdmin(user.getAdmin());
                userItemList.add(userItem);
            }
        }
        return userItemList;
    }

    @Override
    public boolean addEmployee(String name, String password, String sex, String tel, int admin) {
        return userDao.addEmployee(name,password,sex,tel,admin);
    }
}