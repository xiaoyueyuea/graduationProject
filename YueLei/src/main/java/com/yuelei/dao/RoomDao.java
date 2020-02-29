package com.yuelei.dao;

import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;

import java.util.List;

public interface RoomDao {
    List<RoomEntity> getAllEmptyRoom();

    List<String> getAllRoomType();

    RoomEntity getFirstRoomByType(String type);

    RoompictureEntity getRoomAlbum(String type);

    boolean checkRoomIsExit(String roomNo);

    boolean addRoom(String roomNO,String type,String price,String area,String remarks);

    boolean addOrEditRoomPictureByType(String type);

    boolean addOrEditRoomTypePicture(String type,String picture1Address,String picture2Address,String picture3Address,String picture4Address);

    void createNewRoomType(String type);

    List<RoomEntity> getAllRoom();

}
