package com.yuelei.dao;

import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.entity.bean.PageResult;

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

    PageResult<RoomEntity> getAllRoom(Integer page, Integer rows);

    boolean editRoomInfo(String roomNo,String type,String price,String area,String remarks);

    List<RoomEntity> getRoomListByCondition(String roomNo,String roomType);

    RoomEntity getRoomByRoomNo(String roomNo);

    List<RoompictureEntity> getAllRoomPicture();

    RoompictureEntity getRoompictureEntityByType(String type);

    RoomEntity getFirstRoomToOrder(String type);

    boolean updateRoomStatus(String roomNo,String status);

    List<RoomEntity> getRoomListByConditionToCheck(String roomNo, String roomType,String status);
}
