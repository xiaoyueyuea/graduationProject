package com.yuelei.controller;

import com.yuelei.entity.UserEntity;
import com.yuelei.entity.bean.DataGridResult;
import com.yuelei.service.LoginService;
import com.yuelei.service.UserManagerService;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(path = "/userManager")
public class UserManagerController {

    @Autowired(required = false)
    private UserManagerService userManagerService;

    @Autowired(required = false)
    private LoginService loginService;

    @RequestMapping(path = "/showEmployeeList")
    public String showAllEmployee(){
        return "userManager/employeeManagerDetail";
    }

    @RequestMapping(path = "/getEmployeeList")
    public @ResponseBody
    WebJsonResult getEmployeeList(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(userManagerService.getAllEmployee(page,rows).getContent());
        dataGridResult.setTotal(userManagerService.getAllEmployee(page,rows).getTotal());
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/showConsumeList")
    public String showAllConsume(){
        return "userManager/consumeManagerDetail";
    }

    @RequestMapping(path = "/getConsumeList")
    public @ResponseBody WebJsonResult getConsumeList(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(userManagerService.getAllConsume(page,rows).getContent());
        dataGridResult.setTotal(userManagerService.getAllConsume(page,rows).getTotal());
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/consumeRegister_{username}_{password}_{sex}_{phone}")
    public @ResponseBody String insertUser(@PathVariable String username,@PathVariable String password,@PathVariable String sex,@PathVariable String phone){
        if(loginService.getUser(username) != null){
            return "NameIsExit";
        }
        if(userManagerService.registerConsume(username,password,sex,phone)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/updateEmployeeInfo_{name}_{sex}_{phone}_{admin}")
    public @ResponseBody
    String editEmployeeInfo(@PathVariable String name, @PathVariable String sex, @PathVariable String phone, @PathVariable int admin, HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        if(user.getAdmin()!=0){
            return "noPermission";
        }

        if(userManagerService.editEmployeeInfo(name,sex,phone,admin)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/getConsumeByCondition_{username}_{phone}")
    public @ResponseBody
    WebJsonResult getConsumeByCondition(@PathVariable String username,@PathVariable String phone){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(userManagerService.getConsumeListByCondition(username,phone));
        dataGridResult.setTotal(userManagerService.getConsumeListByCondition(username,phone).size());
        if(dataGridResult.getTotal()==0){
            return WebJsonResult.newFailure("没有此用户");
        }
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/getEmployeeByCondition_{number}_{username}_{phone}")
    public @ResponseBody
    WebJsonResult getEmployeeByCondition(@PathVariable Integer number,@PathVariable String username,@PathVariable String phone){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(userManagerService.getEmployeeListByCondition(number,username,phone));
        dataGridResult.setTotal(userManagerService.getEmployeeListByCondition(number,username,phone).size());
        if(dataGridResult.getTotal()==0){
            return WebJsonResult.newFailure("没有此用户");
        }
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/addEmployee_{name}_{password}_{sex}_{tel}_{admin}")
    public @ResponseBody String addEmployee(@PathVariable String name,@PathVariable String password,@PathVariable String sex,@PathVariable String tel,@PathVariable int admin){
        if(userManagerService.addEmployee(name,password,sex,tel,admin)){
            return "success";
        }
        return "fail";
    }
}