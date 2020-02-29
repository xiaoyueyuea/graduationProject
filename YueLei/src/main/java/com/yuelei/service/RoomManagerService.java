package com.yuelei.service;

import com.yuelei.entity.RoomEntity;

import java.util.List;

public interface RoomManagerService {
    boolean checkRoomIsExit(String roomNo);

    boolean addRoomInfo(String roomNO, String type, String price, String area, String remarks,String picture1Address,String picture2Address,String picture3Address,String picture4Address);

    boolean editRoomInfo(String roomNo);

    List<RoomEntity> getAllRoom();
}
