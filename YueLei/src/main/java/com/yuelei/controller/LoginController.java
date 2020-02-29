package com.yuelei.controller;

import com.yuelei.entity.UserEntity;
import com.yuelei.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired(required = false)
    private LoginService loginService;

    @RequestMapping(path = "/loginAction_{username}_{password}")
    public @ResponseBody String checkUser(@PathVariable String username, @PathVariable String password,HttpSession session){
        session.setAttribute("user",loginService.getUser(username));
        return loginService.checkUser(username,password);
    }

    @RequestMapping(path = "/home")
    public String loginIn(Model model,HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        model.addAttribute("identity",user.getAdmin());
        model.addAttribute("username",user.getUsername());
        return "home";
    }

}