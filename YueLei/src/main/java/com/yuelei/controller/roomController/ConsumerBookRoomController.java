package com.yuelei.controller.roomController;

import com.yuelei.entity.RoomEntity;
import com.yuelei.service.RoomBookService;
import com.yuelei.util.Constants;
import com.yuelei.util.WebJsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping(path = "/consumerBookRoom")
public class ConsumerBookRoomController {

    @Autowired(required = false)
    private RoomBookService roomBookService;

    @RequestMapping(path = "/consumerBookRoomInformation")
    public String consumerBookRoomInformation(){
        return "roomManager/bookRoom";
    }

    @RequestMapping(path = "/showAllTypeRoomInfo")
    public @ResponseBody
    WebJsonResult showAllTypeRoomInfo(){
        List<RoomEntity> roomEntityList = roomBookService.getAllTypeRoomInfo();
        if(roomEntityList!=null){
            return WebJsonResult.newSuccess(roomEntityList);
        }
        return  WebJsonResult.newFailure("List为空!");
    }

    @RequestMapping(path = "/getSurplusRoomCount_{type}")
    public @ResponseBody int getSurplusRoomCount(@PathVariable String type){
        return roomBookService.getSurplusRoomCountByType(type);
    }

    @RequestMapping(path = "/getRoomCoverPictureAddress_{type}")
    public @ResponseBody String getRoomCoverPictureAddress(@PathVariable String type){
        return roomBookService.getRoomCoverPicture(type);
    }

    /**
     * 处理图片显示请求
     * @param fileName
     */
    @RequestMapping("/getImage_{fileName}.{suffix}")
    public void showPicture(@PathVariable String fileName,
                            @PathVariable String suffix,
                            HttpServletResponse response){
        File imgFile = new File(Constants.IMG_PATH + fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream()){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
