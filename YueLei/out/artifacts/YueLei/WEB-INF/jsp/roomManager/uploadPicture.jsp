<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/3/2
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPicture.js"></script>

<table id="uploadPicture_table"></table>

<div class="easyui-dialog" title="图片更新" id="picture_update_dialog" style="width:380px;height:430px;padding:10px" data-options="closed:true, modal: true">
    <p id="type_pictureUpdate" style="text-align: center">房型</p>
    <form id="updatePictureForm" method="post" enctype="multipart/form-data">
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="update_picture1"  name="updatePicture1" data-options="prompt:'更新图片1',accept: 'image/*'" style="width:80%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="update_picture2" name="updatePicture2" data-options="prompt:'更新图片2',accept: 'image/*'" style="width:80%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="update_picture3" name="updatePicture3" data-options="prompt:'更新图片3',accept: 'image/*'" style="width:80%;height:30px;padding:12px"/>
        </div>
        <div style="text-align: center; padding: 15px">
            <input class="easyui-filebox" id="update_picture4" name="updatePicture4" data-options="prompt:'更新图片4',accept: 'image/*'" style="width:80%;height:30px;padding:12px"/>
        </div>
    </form>

    <div style="text-align: center; padding: 15px;">
        <a class="easyui-linkbutton" style="width:40%;height:36px;padding:12px" onclick="confirmUpdatePicture()">确定</a>
        <a class="easyui-linkbutton" style="width:40%;height:36px;padding:12px" onclick="cancelUpdatePicture()">取消</a>
    </div>
</div>
