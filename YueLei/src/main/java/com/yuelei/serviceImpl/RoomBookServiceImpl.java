package com.yuelei.serviceImpl;

import com.yuelei.dao.RoomDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.service.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomBookServiceImpl implements RoomBookService {

    @Autowired
    private RoomDao roomDao;

    @Override
    public int getSurplusRoomCountByType(String roomType) {
        List<RoomEntity> roomEntityList = roomDao.getAllEmptyRoom();
        int roomCount=0;
        for(RoomEntity roomEntity : roomEntityList){
            if(roomEntity.getType().equals(roomType)){
                roomCount++;
            }
        }
        return roomCount;
    }

    @Override
    public List<RoomEntity> getAllTypeRoomInfo() {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        List<String> typeList = roomDao.getAllRoomType();
        for(String type:typeList){
            roomEntityList.add(roomDao.getFirstRoomByType(type));
        }
        return roomEntityList;
    }

    @Override
    public String getRoomCoverPicture(String type) {
        RoompictureEntity roompictureEntity = roomDao.getRoomAlbum(type);
        if(roompictureEntity !=null){
            return roompictureEntity.getPicture1();
        }
        return null;
    }
}
