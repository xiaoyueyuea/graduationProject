package com.yuelei.controller.roomManagerController;

import com.yuelei.dao.RoomDao;
import com.yuelei.entity.RoomEntity;
import com.yuelei.entity.RoompictureEntity;
import com.yuelei.entity.bean.DataGridResult;
import com.yuelei.service.RoomBookService;
import com.yuelei.service.RoomManagerService;
import com.yuelei.util.Constants;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/roomManager")
public class RoomManagerController {

    @Autowired(required = false)
    private RoomManagerService roomManagerService;

    @Autowired(required = false)
    private RoomDao roomDao;

    @Autowired(required = false)
    private RoomBookService roomBookService;

    @RequestMapping(path = "/checkAllRoom")
    public String checkAllRoom(Model model){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        List<String> types=new ArrayList<>();
        for(RoomEntity roomEntity : roomEntityList){
            types.add(roomEntity.getType());
        }
        model.asMap().put("types_checkRoom",types);

        return "roomManager/checkRoom";
    }

    @RequestMapping(path = "/addOrEditRoom")
    public String addOrEditRoom(){
        return "roomManager/addOrEditRoom";
    }

    @RequestMapping(path = "/editRoomInfo")
    public String editRoomInfo(Model model){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        List<String> types=new ArrayList<>();
        for(RoomEntity roomEntity : roomEntityList){
            types.add(roomEntity.getType());
        }
        model.asMap().put("types_editRoomInfo",types);
        return "roomManager/editRoomInfo";
    }

    @RequestMapping(path = "/uploadPicture")
    public String uploadPicture(){
        return "roomManager/uploadPicture";
    }

    @RequestMapping(path = "/checkRoomIsExit_{roomNo}")
    public @ResponseBody String checkRoomIsExit(@PathVariable String roomNo){
        if(roomManagerService.checkRoomIsExit(roomNo)){
            return "isExit";
        }
        return "NotExit";
    }

    @RequestMapping(path = "/addRoomAndUploadPicture_{roomNo}_{roomType}_{roomPrice}_{roomArea}_{roomRemarks}")
    public @ResponseBody String addRoomAndUploadPicture(@PathVariable String roomNo,
                                                        @PathVariable String roomType,
                                                        @PathVariable String roomPrice,
                                                        @PathVariable String roomArea,
                                                        @PathVariable String roomRemarks,
                                                        @RequestParam("uploadPicture1") MultipartFile file1,
                                                        @RequestParam("uploadPicture2") MultipartFile file2,
                                                        @RequestParam("uploadPicture3") MultipartFile file3,
                                                        @RequestParam("uploadPicture4") MultipartFile file4) throws IOException {

        String file1Address=uploadPictures(file1);
        String file2Address=uploadPictures(file2);
        String file3Address=uploadPictures(file3);
        String file4Address=uploadPictures(file4);

        if(roomManagerService.addRoomInfo(roomNo,roomType,roomPrice,roomArea,roomRemarks,file1Address,file2Address,file3Address,file4Address)){
            return "success";
        }
        return "fail";
    }

    public String uploadPictures(MultipartFile file) throws IOException {
        // 原始文件名
        String originalFileName = file.getOriginalFilename();
        if (originalFileName!=null && originalFileName .contains(".")) {
            // 获取图片后缀
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            // 图片存储路径
            String filePath = Constants.IMG_PATH + fileName;
            File targetDirectory = new File(Constants.IMG_PATH);
            if(!targetDirectory.exists() && !targetDirectory.isDirectory()){
                targetDirectory.mkdirs();
            }

            File saveFile = new File(filePath);
            file.transferTo(saveFile);
            return fileName;
        }
        return "";
    }

    @RequestMapping(path = "/getRoomList")
    public @ResponseBody
    WebJsonResult getRoomList(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(roomManagerService.getAllRoom(page,rows).getContent());
        dataGridResult.setTotal(roomManagerService.getAllRoom(page,rows).getTotal());
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/confirmEditRoomInfo_{roomNo}_{type}_{price}_{area}_{remarks}")
    public @ResponseBody String confirmEditRoomInfo(@PathVariable String roomNo,
                                                    @PathVariable String type,
                                                    @PathVariable String price,
                                                    @PathVariable String area,
                                                    @PathVariable String remarks){
        if(roomManagerService.editRoomInfo(roomNo,type,price,area,remarks)){
            return "success";
        }
        return "fail";
    }

    @RequestMapping(path = "/getRoomByCondition_{roomNo}_{roomType}")
    public @ResponseBody WebJsonResult getRoomByCondition(@PathVariable String roomNo,@PathVariable String roomType){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(roomManagerService.getRoomEditListByCondition(roomNo,roomType));
        dataGridResult.setTotal(roomManagerService.getRoomEditListByCondition(roomNo,roomType).size());
        if(dataGridResult.getTotal()==0){
            return WebJsonResult.newFailure("没有此房间");
        }
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/getAllRoomType")
    public @ResponseBody WebJsonResult getAllRoomType(){
        List<String> roomTypeList = roomDao.getAllRoomType();
        if(roomTypeList!=null){
            return WebJsonResult.newSuccess(roomTypeList);
        }
        return WebJsonResult.newFailure("没有查到任何房型");
    }

    @RequestMapping(path = "/getAllRoomPicture")
    public @ResponseBody WebJsonResult getAllRoomPicture(){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(roomManagerService.getAllRoomPicture());
        dataGridResult.setTotal(roomManagerService.getAllRoomPicture().size());
        if(dataGridResult.getTotal()==0){
            return WebJsonResult.newFailure("没有数据");
        }
        return WebJsonResult.newSuccess(dataGridResult);
    }

    @RequestMapping(path = "/getRoomInfoToCheckByCondition_{roomNo}_{type}_{status}")
    public @ResponseBody WebJsonResult getRoomInfoToCheckByCondition(@PathVariable String roomNo,@PathVariable String type,@PathVariable String status){
        List<RoomEntity> roomEntityList = roomDao.getRoomListByConditionToCheck(roomNo,type,status);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(roomEntityList);
        dataGridResult.setTotal(roomEntityList.size());
        if(dataGridResult.getTotal()!=0){
            return WebJsonResult.newSuccess(dataGridResult);
        }
        return WebJsonResult.newFailure("没有查到任何房型");
    }

    @RequestMapping(path = "/updatePicture_{roomType}")
    public @ResponseBody String updatePicture(@PathVariable String roomType,
                                              @RequestParam("updatePicture1") MultipartFile file1,
                                              @RequestParam("updatePicture2") MultipartFile file2,
                                              @RequestParam("updatePicture3") MultipartFile file3,
                                              @RequestParam("updatePicture4") MultipartFile file4){
        try{
            RoompictureEntity roompictureEntity = roomDao.getRoompictureEntityByType(roomType);
            if(file1.getOriginalFilename()!=null && file1.getOriginalFilename().contains(".")){
                String picture1Address = Constants.IMG_PATH+roompictureEntity.getPicture1();
                File file =new File(picture1Address);
                file1.transferTo(file);
            }
            if(file2.getOriginalFilename()!=null && file2.getOriginalFilename().contains(".")){
                String picture2Address = Constants.IMG_PATH+roompictureEntity.getPicture2();
                File file =new File(picture2Address);
                file2.transferTo(file);
            }
            if(file3.getOriginalFilename()!=null && file3.getOriginalFilename().contains(".")){
                String picture3Address = Constants.IMG_PATH+roompictureEntity.getPicture3();
                File file =new File(picture3Address);
                file3.transferTo(file);
            }
            if(file4.getOriginalFilename()!=null && file4.getOriginalFilename().contains(".")){
                String picture4Address = Constants.IMG_PATH+roompictureEntity.getPicture4();
                File file =new File(picture4Address);
                file4.transferTo(file);
            }
            return "success";
        }catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
