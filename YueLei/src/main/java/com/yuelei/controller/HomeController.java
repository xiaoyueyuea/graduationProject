package com.yuelei.controller;

import com.yuelei.entity.UserEntity;
import com.yuelei.service.HomeService;
import com.yuelei.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired(required = false)
    private LoginService loginService;

    @Autowired(required = false)
    private HomeService homeService;

    @RequestMapping(path = "checkOldPassword_{oldPassword}_{newPassword}")
    public @ResponseBody String modifyPassword(@PathVariable String oldPassword,@PathVariable String newPassword, HttpSession session){
        UserEntity user=(UserEntity) session.getAttribute("user");
        String username=user.getUsername();
        if(loginService.checkUser(username,oldPassword).equals("true")){
            homeService.modifyPassword(username,newPassword);
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }

    @RequestMapping(path = "/checkPermission")
    public @ResponseBody String checkPermission(HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("user");
        int admin=user.getAdmin();
        if(admin==2){
            return "consumer";
        }
        return "employee";
    }
}
