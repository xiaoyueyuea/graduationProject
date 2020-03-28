package com.yuelei.controller.orderManagerController;

import com.yuelei.dao.FinancialDao;
import com.yuelei.dao.OrderDao;
import com.yuelei.dao.RoomDao;
import com.yuelei.entity.OrderEntity;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.bean.DataGridResult;
import com.yuelei.entity.bean.MyOrderItem;
import com.yuelei.service.OrderManagerService;
import com.yuelei.service.RoomBookService;
import com.yuelei.util.Constants;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/orderManager")
public class OrderManagerController {

    @Autowired(required = false)
    private OrderManagerService orderManagerService;

    @Autowired(required = false)
    private RoomBookService roomBookService;

    @Autowired(required = false)
    private OrderDao orderDao;

    @Autowired(required = false)
    private RoomDao roomDao;

    @Autowired(required = false)
    private FinancialDao financialDao;

    @RequestMapping(path = "/checkOnGoingOrder")
    public String checkOrder(Model model){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        List<String> types=new ArrayList<>();
        for(RoomEntity roomEntity : roomEntityList){
            types.add(roomEntity.getType());
        }
        model.asMap().put("types_checkOnGoingOrder",types);
        model.asMap().put("status_checkOnGoingOrder",orderManagerService.getAllOnGoingOrderStatus());
        return "orderManager/checkOnGoingOrder";
    }

    @RequestMapping(path = "/checkFinishedOrder")
    public String getFinishedOrders(Model model){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        List<String> types=new ArrayList<>();
        for(RoomEntity roomEntity : roomEntityList){
            types.add(roomEntity.getType());
        }
        model.asMap().put("types_checkFinishedOrder",types);
        return "orderManager/checkFinishedOrder";
    }

    @RequestMapping(path = "/getOnGoingOrderList")
    public @ResponseBody
    WebJsonResult getOrderList(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        List<OrderEntity> orderEntityList = orderManagerService.getAllOnGoingOrder(page,rows).getContent();
        DataGridResult dataGridResult = new DataGridResult();
        if(orderEntityList!=null){
            dataGridResult.setRows(orderEntityList);
            dataGridResult.setTotal(orderManagerService.getAllOnGoingOrder(page,rows).getTotal());
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("获取订单失败");
    }

    @RequestMapping(path = "/getOnGoingOrderListByCondition_{customerName}_{customerPhone}_{roomType}_{orderStatus}")
    public @ResponseBody WebJsonResult getOrderByCondition(@PathVariable String customerName,@PathVariable String customerPhone,@PathVariable String roomType,@PathVariable String orderStatus){
        List<OrderEntity> orderEntityList = orderDao.getOnGoingOrderByCondition(customerName,customerPhone,roomType,orderStatus);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(orderEntityList);
        dataGridResult.setTotal(orderEntityList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("没有符合条件的订单");
    }

    @RequestMapping(path = "/getFinishedOrderList")
    public @ResponseBody WebJsonResult getFinishedOrdersList(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        List<OrderEntity> orderEntityList = orderDao.getFinishedOrders(page,rows).getContent();
        DataGridResult dataGridResult = new DataGridResult();;
        dataGridResult.setRows(orderEntityList);
        dataGridResult.setTotal(orderDao.getFinishedOrders(page,rows).getTotal());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("没有符合条件的订单");
    }

    @RequestMapping(path = "/getFinishedOrderListByCondition_{customerName}_{customerPhone}_{roomType}")
    public @ResponseBody WebJsonResult getFinishedOrdersByCondition(@PathVariable String customerName,@PathVariable String customerPhone,@PathVariable String roomType){
        List<OrderEntity> orderEntityList = orderDao.getFinishedOrderByCondition(customerName,customerPhone,roomType);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(orderEntityList);
        dataGridResult.setTotal(orderEntityList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("没有符合条件的订单");
    }

    @RequestMapping(path = "/checkIn_{roomNo}")
    public @ResponseBody String checkIn(@PathVariable String roomNo){
        if(roomDao.updateRoomStatus(roomNo, Constants.STATUS_ROOM_APPLY) && orderDao.updateOrderStatus(roomNo,Constants.STATUS_ORDER_CHECKED)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/checkOut_{roomNo}_{otherCost}")
    public @ResponseBody String checkOut(@PathVariable String roomNo,@PathVariable String otherCost){
        OrderEntity orderEntity = orderDao.getOnGoingOrderByRoomNo(roomNo);
        if(roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_EMPTY) && orderDao.updateOrderStatus(roomNo,Constants.STATUS_ORDER_FINISHED) && financialDao.createFinancialManagerEntity(orderEntity,otherCost) ){
            return "success";
        }else {
            roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_APPLY);
            orderDao.updateOrderStatus(roomNo,Constants.STATUS_ORDER_CHECKED);
            return "fail";
        }
    }

    @RequestMapping(path = "/overdueHandle_{roomNo}")
    public @ResponseBody String overdueHandle(@PathVariable String roomNo){
        if(roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_EMPTY) && orderDao.updateOrderStatus(roomNo,Constants.STATUS_ORDER_FINISHED) && orderDao.addOrderRemarks(roomNo,Constants.REMARKS_ORDER_OVERDUE)){
            return "success";
        }else {
            roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_SCHEDULED);
            orderDao.updateOrderStatus(roomNo,Constants.STATUS_ORDER_UNCHECKED);
            return "fail";
        }
    }

    @RequestMapping(path = "/delayHandle_{roomNo}_{delayDate}_{days}")
    public @ResponseBody String delayHandle(@PathVariable String roomNo,@PathVariable Date delayDate,@PathVariable String days){
        if(orderDao.delayOrderEndDate(roomNo,delayDate,days)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/changeRoomHandle_{oldRoomNo}_{newRoomNo}_{priceDifference}")
    public @ResponseBody WebJsonResult changeRoomHandle(@PathVariable String oldRoomNo,@PathVariable String newRoomNo,@PathVariable String priceDifference){
        if(!roomDao.getRoomByRoomNo(newRoomNo).getStatus().equals(Constants.STATUS_ROOM_EMPTY)){
            return WebJsonResult.newFailure("该房间已被预订或正在使用中");
        }
        String oldRoomStatus = roomDao.getRoomByRoomNo(oldRoomNo).getStatus();
        if(roomDao.updateRoomStatus(newRoomNo,Constants.STATUS_ROOM_APPLY) && roomDao.updateRoomStatus(oldRoomNo,Constants.STATUS_ROOM_EMPTY) && orderDao.updateOrderByRoomNoForChangeRoom(oldRoomNo,newRoomNo,priceDifference)){
            return WebJsonResult.newSuccess("换房成功!");
        }else {
            roomDao.updateRoomStatus(oldRoomNo,oldRoomStatus);
            roomDao.updateRoomStatus(newRoomNo,Constants.STATUS_ROOM_EMPTY);
            return WebJsonResult.newFailure("系统异常，换房失败，请重试");
        }
    }

    @RequestMapping(path = "/getMyOrderByCustomer_{username}")
    public @ResponseBody WebJsonResult getMyOrderByCustomer(@PathVariable String username, @RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        List<MyOrderItem> myOrderItemList = orderManagerService.getMyOrderInfo(username,page,rows).getContent();
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(myOrderItemList);
        dataGridResult.setTotal(orderManagerService.getMyOrderInfo(username,page,rows).getTotal());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("您还没有订单");
    }

    @RequestMapping(path = "/cancelOrder_{roomNo}")
    public @ResponseBody String cancelOrder(@PathVariable String roomNo){
        OrderEntity orderEntity = orderDao.getOnGoingOrderByRoomNo(roomNo);
        if(orderDao.deleteOrder(orderEntity.getOrderId()) && roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_EMPTY)){
            return "success";
        }
        return "fail";
    }
}
