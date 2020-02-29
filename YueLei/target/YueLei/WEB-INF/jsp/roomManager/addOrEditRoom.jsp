<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/2/27
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addOrEditRoom.js"></script>
    <form id="addOrEditRoomForm" method="post" enctype="multipart/form-data" style="margin-top: 100px">
        <div style="text-align: center; padding: 15px">
            <input class="easyui-textbox" id="room_no" name="room_no" data-options="type:'text',required:true,validType:'nameTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入房号(4位数字)'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-textbox" id="room_type" name="room_type" data-options="type:'text',required:true,validType:'nameTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入房型'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-textbox" id="room_price" name="room_price" data-options="type:'text',required:true,validType:'nameTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入价格'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-textbox" id="room_area" name="room_area" data-options="type:'text',required:true,validType:'nameTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入面积'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-textbox" id="room_remarks" name="room_remarks" data-options="type:'text',required:true,validType:'nameTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入备注'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="files_picture1"  name="uploadPicture1" data-options="prompt:'请选择图片1',accept: 'image/*'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="files_picture2" name="uploadPicture2" data-options="prompt:'请选择图片2',accept: 'image/*'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="files_picture3" name="uploadPicture3" data-options="prompt:'请选择图片3',accept: 'image/*'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="files_picture4" name="uploadPicture4" data-options="prompt:'请选择图片4',accept: 'image/*'" style="width:40%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px;">
            <a class="easyui-linkbutton" style="width:20%;height:36px;padding:12px" onclick="confirmAddOrEdit()">确定</a>
            <a class="easyui-linkbutton" style="width:20%;height:36px;padding:12px" onclick="cancel()">取消</a>
        </div>
    </form>
