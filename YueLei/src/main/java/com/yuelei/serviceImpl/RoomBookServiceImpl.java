package com.yuelei.serviceImpl;

import com.yuelei.dao.OrderDao;
import com.yuelei.dao.RoomDao;
import com.yuelei.dao.UserDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.entity.UserEntity;
import com.yuelei.entity.item.OrderInfoItem;
import com.yuelei.service.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomBookServiceImpl implements RoomBookService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

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

    @Override
    public String getPreviousPictureAddress(String type, String currentPictureAddress) {
        RoompictureEntity roompictureEntity = roomDao.getRoompictureEntityByType(type);
        if(currentPictureAddress.equals(roompictureEntity.getPicture1())){
            return roompictureEntity.getPicture4();
        }else if(currentPictureAddress.equals(roompictureEntity.getPicture2())){
            return roompictureEntity.getPicture1();
        }else if(currentPictureAddress.equals(roompictureEntity.getPicture3())){
            return roompictureEntity.getPicture2();
        }else if(currentPictureAddress.equals(roompictureEntity.getPicture4())){
            return roompictureEntity.getPicture3();
        }else {
            return "";
        }
    }

    @Override
    public String getNextPictureAddress(String type, String currentPictureAddress) {
        RoompictureEntity roompictureEntity = roomDao.getRoompictureEntityByType(type);
        if(currentPictureAddress.equals(roompictureEntity.getPicture1())){
            return roompictureEntity.getPicture2();
        }else if(currentPictureAddress.equals(roompictureEntity.getPicture2())){
            return roompictureEntity.getPicture3();
        }else if(currentPictureAddress.equals(roompictureEntity.getPicture3())){
            return roompictureEntity.getPicture4();
        }else if(currentPictureAddress.equals(roompictureEntity.getPicture4())){
            return roompictureEntity.getPicture1();
        }else {
            return "";
        }
    }

    @Override
    public OrderInfoItem getInfoToOrderInitialization(String type, String username) {
        RoomEntity roomEntity = roomDao.getFirstRoomToOrder(type);
        UserEntity userEntity = userDao.getUserByName(username);
        OrderInfoItem orderInfoInitialization = new OrderInfoItem();
        if(roomEntity!=null){
            orderInfoInitialization.setRoomNo(roomEntity.getRoomNo());
            orderInfoInitialization.setRoomType(roomEntity.getType());
            orderInfoInitialization.setRoomPrice(roomEntity.getPrice());
            orderInfoInitialization.setRoomArea(roomEntity.getArea());
            orderInfoInitialization.setPhone(userEntity.getPhone());
        }
        return orderInfoInitialization;
    }

    @Override
    public boolean createOrder(String roomNo, String roomType, String roomPrice, String roomArea, String currentUsername, String realName, String phone, String idCard, Date startTime, Date endTime, String days, String remarks) {
        return orderDao.createOrder(roomNo,roomType,roomPrice,roomArea,currentUsername,realName,phone,idCard,startTime,endTime,days,remarks);
    }
}
