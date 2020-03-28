package com.yuelei.service;

import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.entity.bean.PageResult;
import com.yuelei.entity.bean.RoomItem;

import java.util.List;

public interface RoomManagerService {
    boolean checkRoomIsExit(String roomNo);

    boolean addRoomInfo(String roomNO, String type, String price, String area, String remarks,String picture1Address,String picture2Address,String picture3Address,String picture4Address);

    boolean editRoomInfo(String roomNo,String type,String price,String area,String remarks);

    PageResult<RoomEntity> getAllRoom(Integer page, Integer rows);

    List<RoomItem> getRoomEditListByCondition(String roomNo,String type);

    List<RoompictureEntity> getAllRoomPicture();
}
