package com.yuelei.serviceImpl;

import com.yuelei.dao.RoomDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.entity.bean.PageResult;
import com.yuelei.entity.bean.RoomItem;
import com.yuelei.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomManagerServiceImpl implements RoomManagerService {

    @Autowired
    private RoomDao roomDao;

    @Override
    public boolean checkRoomIsExit(String roomNo) {
        return roomDao.checkRoomIsExit(roomNo);
    }

    @Override
    public boolean addRoomInfo(String roomNO, String type, String price, String area, String remarks,String picture1Address,String picture2Address,String picture3Address,String picture4Address) {
        List<String> typeList = roomDao.getAllRoomType();

        boolean isExitType=false;
        for(String str : typeList){
            if(str.equals(type)){
                isExitType=true;
                break;
            }
        }
        if(!isExitType){
            roomDao.createNewRoomType(type);
            return (roomDao.addRoom(roomNO,type,price,area,remarks) && roomDao.addOrEditRoomTypePicture(type,picture1Address,picture2Address,picture3Address,picture4Address));
        }else {
            roomDao.addOrEditRoomTypePicture(type,picture1Address,picture2Address,picture3Address,picture4Address);
            return (roomDao.addRoom(roomNO,type,price,area,remarks) && roomDao.addOrEditRoomTypePicture(type,picture1Address,picture2Address,picture3Address,picture4Address));
        }
    }

    @Override
    public boolean editRoomInfo(String roomNo,String type,String price,String area,String remarks) {
        return roomDao.editRoomInfo(roomNo,type,price,area,remarks);
    }

    @Override
    public PageResult<RoomEntity> getAllRoom(Integer page, Integer rows) {
        return roomDao.getAllRoom(page,rows);
    }

    @Override
    public List<RoomItem> getRoomEditListByCondition(String roomNo, String type) {
        List<RoomEntity> roomEntityList = roomDao.getRoomListByCondition(roomNo,type);
        List<RoomItem> roomItemList = new ArrayList<>();
        if(roomEntityList!=null){
            for(RoomEntity roomEntity : roomEntityList){
                RoomItem roomItem = new RoomItem();
                roomItem.setRoomNo(roomEntity.getRoomNo());
                roomItem.setType(roomEntity.getType());
                roomItem.setPrice(roomEntity.getPrice());
                roomItem.setArea(roomEntity.getArea());
                roomItem.setRemarks(roomEntity.getRemarks());
                roomItemList.add(roomItem);
            }
        }
        return roomItemList;
    }

    @Override
    public List<RoompictureEntity> getAllRoomPicture() {
        return roomDao.getAllRoomPicture();
    }
}
