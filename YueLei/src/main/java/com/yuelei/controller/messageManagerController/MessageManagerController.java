package com.yuelei.controller.messageManagerController;

import com.yuelei.dao.MessageDao;
import com.yuelei.entity.CustomerMessageEntity;
import com.yuelei.entity.UserEntity;
import com.yuelei.entity.bean.DataGridResult;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/messageManager")
public class MessageManagerController {

    @Autowired(required = false)
    private MessageDao messageDao;

    @RequestMapping(path = "/getCustomerMessage")
    public String getCustomerMessage(){
        return "messageManager/customerMessageManager";
    }

    @RequestMapping(path = "/customerMessageHandle_{message}")
    public @ResponseBody String customerMessageHandle(@PathVariable String message, HttpSession session){
        UserEntity user=(UserEntity) session.getAttribute("user");
        if(messageDao.createMessage(user,message)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/getAllMessage")
    public @ResponseBody
    WebJsonResult getAllMessage(){
        List<CustomerMessageEntity> customerMessageEntityList = messageDao.getAllMessage();
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(customerMessageEntityList);
        dataGridResult.setTotal(customerMessageEntityList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("没有留言");
    }

    @RequestMapping(path = "/unTreatedMessageHandle_{username}_{messageDate}")
    public @ResponseBody String unTreatedMessageHandle(@PathVariable String username,@PathVariable long messageDate){
        if(messageDao.getCurrentMessageAndHandle(username,messageDate)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/getMessageByCondition_{status}")
    public @ResponseBody WebJsonResult getMessageByCondition(@PathVariable String status){
        List<CustomerMessageEntity> customerMessageEntityList = messageDao.getMessageEntityByCondition(status);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(customerMessageEntityList);
        dataGridResult.setTotal(customerMessageEntityList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("查无此结果");
    }
}
