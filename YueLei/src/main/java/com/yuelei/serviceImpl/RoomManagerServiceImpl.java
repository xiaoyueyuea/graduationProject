package com.yuelei.serviceImpl;

import com.yuelei.dao.RoomDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.service.RoomManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean editRoomInfo(String roomNo) {

        return false;
    }

    @Override
    public List<RoomEntity> getAllRoom() {
        return roomDao.getAllRoom();
    }
}
