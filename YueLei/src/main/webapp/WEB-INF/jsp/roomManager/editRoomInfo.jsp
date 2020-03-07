<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/2/28
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/addOrEditRoom.js"></script>

<div id="room_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="searchRoomByRoomNo" class="easyui-textbox" data-options="prompt:'通过房号查找'"/>
    <input id="searchRoomByType" class="easyui-textbox" data-options="prompt:'通过房型查找'"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findRoomByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearCondition()">清空</a>
</div>

<table id="room_listInfo_table">
</table>

<div class="easyui-dialog" title="房间信息编辑" id="room_edit_dialog" style="width:350px;height:380px;padding:10px" data-options="closed:true, modal: true">
    <div style="padding: 10px;">
        房号：<input class="easyui-textbox" id="roomNo_edit" name="roomNo_edit" data-options="type:'text',editable:false" style="width:80%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        类型：<input class="easyui-textbox" id="roomType_edit" name="roomType_edit" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        价格：<input class="easyui-textbox" id="roomPrice_edit" name="roomPrice_edit" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        面积：<input class="easyui-textbox" id="roomArea_edit" name="roomArea_edit" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        备注：<input class="easyui-textbox" id="roomRemarks_edit" name="roomRemarks_edit" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;text-align: center">
        <a class="easyui-linkbutton" onclick="confirmEditRoomInfo()" style="width:40%;height:36px;padding:12px;text-align: center">确认</a>
        <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#room_edit_dialog').dialog('close')" style="width:40%;height:36px;padding:12px;text-align: center">取消</a>
    </div>
</div>
