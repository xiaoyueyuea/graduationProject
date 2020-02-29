package com.yuelei.service;

import com.yuelei.entity.RoomEntity;

import java.util.List;

public interface RoomBookService {
     int getSurplusRoomCountByType(String roomType);

     List<RoomEntity> getAllTypeRoomInfo();

     String getRoomCoverPicture(String type);
}
