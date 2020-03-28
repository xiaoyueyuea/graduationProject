package com.yuelei.controller.roomManagerController;

import com.yuelei.dao.OrderDao;
import com.yuelei.dao.RoomDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.UserEntity;
import com.yuelei.entity.bean.OrderInfoItem;
import com.yuelei.service.RoomBookService;
import com.yuelei.util.Constants;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/consumerBookRoom")
public class ConsumerBookRoomController {

    @Autowired(required = false)
    private RoomBookService roomBookService;

    @Autowired(required = false)
    private RoomDao roomDao;

    @Autowired(required = false)
    private OrderDao orderDao;

    @RequestMapping(path = "/consumerBookRoomInformation")
    public String consumerBookRoomInformation(Model model){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        List<String> types=new ArrayList<>();
        for(RoomEntity roomEntity : roomEntityList){
            types.add(roomEntity.getType());
        }
        model.asMap().put("types_bookRoom",types);
        return "roomManager/bookRoom";
    }

    @RequestMapping(path = "/showAllTypeRoomInfo")
    public @ResponseBody
    WebJsonResult showAllTypeRoomInfo(){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        if(roomEntityList!=null){
            return WebJsonResult.newSuccess(roomEntityList);
        }
        return  WebJsonResult.newFailure("List为空!");
    }

    @RequestMapping(path = "/getSurplusRoomCount_{type}")
    public @ResponseBody int getSurplusRoomCount(@PathVariable String type){
        return roomBookService.getSurplusRoomCountByType(type);
    }

    @RequestMapping(path = "/getRoomCoverPictureAddress_{type}")
    public @ResponseBody String getRoomCoverPictureAddress(@PathVariable String type){
        return roomBookService.getRoomCoverPicture(type);
    }

    /**
     * 处理图片显示请求
     * @param fileName
     */
    @RequestMapping("/getImage_{fileName}.{suffix}")
    public void showPicture(@PathVariable String fileName,
                            @PathVariable String suffix,
                            HttpServletResponse response){
        File imgFile = new File(Constants.IMG_PATH + fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream()){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //使用@PathVariable会截取“.”后面的字符串，参数后加上:.+
    @RequestMapping(path = "/previousPicture_{roomType}_{currentPictureAddress:.+}")
    public @ResponseBody  String getPreviousPicture(@PathVariable String roomType,@PathVariable String currentPictureAddress){
        if(!roomBookService.getPreviousPictureAddress(roomType,currentPictureAddress).isEmpty()){
            return roomBookService.getPreviousPictureAddress(roomType,currentPictureAddress);
        }
        return "error";
    }

    @RequestMapping(path = "/nextPicture_{roomType}_{currentPictureAddress:.+}")
    public @ResponseBody  String getNextPicture(@PathVariable String roomType,@PathVariable String currentPictureAddress){
        if(!roomBookService.getNextPictureAddress(roomType,currentPictureAddress).isEmpty()){
            return roomBookService.getNextPictureAddress(roomType,currentPictureAddress);
        }
        return "error";
    }

    @RequestMapping(path = "/getOrderInitializationInfo_{roomType}_{username}")
    public @ResponseBody WebJsonResult getFirstRoomToOrder(@PathVariable String roomType,@PathVariable String username){
        OrderInfoItem orderInfoItem = roomBookService.getInfoToOrderInitialization(roomType,username);
        if(orderInfoItem!=null){
            return WebJsonResult.newSuccess(orderInfoItem);
        }
        return WebJsonResult.newFailure("此房型没有空房间了");
    }

    @RequestMapping(path = "/getCurrentUsername")
    public @ResponseBody String getCurrentUsername(HttpSession session){
        UserEntity user =(UserEntity) session.getAttribute("user");
        return user.getUsername();
    }

    @RequestMapping(path = "/commitOrder_{roomNo}_{roomType}_{roomPrice}_{roomArea}_{currentUsername}_{realName}_{phone}_{idCard}_{startTime}_{endTime}_{days}_{remarks}")
    public @ResponseBody String commitOrder(@PathVariable String roomNo,
                                            @PathVariable String roomType,
                                            @PathVariable String roomPrice,
                                            @PathVariable String roomArea,
                                            @PathVariable String currentUsername,
                                            @PathVariable String realName,
                                            @PathVariable String phone,
                                            @PathVariable String idCard,
                                            @PathVariable Date startTime,
                                            @PathVariable Date endTime,
                                            @PathVariable String days,
                                            @PathVariable String remarks){
        if(roomBookService.createOrder(roomNo,roomType,roomPrice,roomArea,currentUsername,realName,phone,idCard,startTime,endTime,days,remarks)){
            roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_SCHEDULED);//更改房间状态
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/notOrderCheckedIn_{roomNo}_{roomType}_{roomPrice}_{roomArea}_{currentUsername}_{realName}_{phone}_{idCard}_{startTime}_{endTime}_{days}_{remarks}")
    public @ResponseBody String notOrderCheckedIn(@PathVariable String roomNo,
                                            @PathVariable String roomType,
                                            @PathVariable String roomPrice,
                                            @PathVariable String roomArea,
                                            @PathVariable String currentUsername,
                                            @PathVariable String realName,
                                            @PathVariable String phone,
                                            @PathVariable String idCard,
                                            @PathVariable Date startTime,
                                            @PathVariable Date endTime,
                                            @PathVariable String days,
                                            @PathVariable String remarks){
        if(roomBookService.createOrder(roomNo,roomType,roomPrice,roomArea,currentUsername,realName,phone,idCard,startTime,endTime,days,remarks)){
            roomDao.updateRoomStatus(roomNo,Constants.STATUS_ROOM_APPLY);//到店办理入住的顾客直接入住
            orderDao.updateOrderStatus(roomNo,Constants.STATUS_ORDER_CHECKED);//订单直接变为已入住状态
            return "success";
        }
        return "fail";
    }
}
