package com.yuelei.controller.orderManagerController;

import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.item.DataGridResult;
import com.yuelei.service.OrderManagerService;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/orderManager")
public class OrderManagerController {

    @Autowired(required = false)
    private OrderManagerService orderManagerService;

    @RequestMapping(path = "/checkOrder")
    public String checkOrder(){
        return "orderManager/checkOrder";
    }

    @RequestMapping(path = "/getOrderList")
    public @ResponseBody
    WebJsonResult getOrderList(){
        List<OrderEntity> orderEntityList = orderManagerService.getAllOrder();
        System.out.println(orderEntityList.get(0).getEndDate()+"##########");
        DataGridResult dataGridResult = new DataGridResult();
        if(orderEntityList!=null){
            dataGridResult.setRows(orderEntityList);
            dataGridResult.setTotal(orderEntityList.size());
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("获取订单失败");
    }

    @RequestMapping(path = "/getOrderListByCondition_{roomNo}_{roomType}_{orderStatus}")
    public @ResponseBody WebJsonResult getOrderByCondition(@PathVariable String roomNo,@PathVariable String roomType,@PathVariable String orderStatus){
        return WebJsonResult.newFailure("没有符合条件的订单");
    }
}
