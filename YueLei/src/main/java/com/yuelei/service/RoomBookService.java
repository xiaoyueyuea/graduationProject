package com.yuelei.service;

import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.item.OrderInfoItem;

import java.sql.Date;
import java.util.List;

public interface RoomBookService {
     int getSurplusRoomCountByType(String roomType);

     List<RoomEntity> getAllTypeRoomInfo();

     String getRoomCoverPicture(String type);

     String getPreviousPictureAddress(String type,String currentPictureAddress);

     String getNextPictureAddress(String type,String currentPictureAddress);

     OrderInfoItem getInfoToOrderInitialization(String type, String username);

     boolean createOrder(String roomNo, String roomType, String roomPrice, String roomArea, String currentUsername, String realName, String phone, String idCard, Date startTime, Date endTime, String days, String remarks);
}
