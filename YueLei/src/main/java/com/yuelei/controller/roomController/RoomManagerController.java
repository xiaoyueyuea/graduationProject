package com.yuelei.controller.roomController;

import com.yuelei.entity.item.DataGridResult;
import com.yuelei.service.RoomManagerService;
import com.yuelei.util.Constants;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(path = "/roomManager")
public class RoomManagerController {

    @Autowired(required = false)
    private RoomManagerService roomManagerService;

    @RequestMapping(path = "/checkAllRoom")
    public String checkAllRoom(){
        return "roomManager/checkRoom";
    }

    @RequestMapping(path = "/addOrEditRoom")
    public String addOrEditRoom(){
        return "roomManager/addOrEditRoom";
    }

    @RequestMapping(path = "/editRoomInfo")
    public String editRoomInfo(){
        return "roomManager/editRoomInfo";
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
    WebJsonResult getRoomList(){
        DataGridResult dataGridResult=new DataGridResult();
        dataGridResult.setRows(roomManagerService.getAllRoom());
        dataGridResult.setTotal(roomManagerService.getAllRoom().size());
        return WebJsonResult.newSuccess(dataGridResult);
    }
}
