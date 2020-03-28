<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/3/11
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messageManager/customerMessageManager.js"></script>

<div id="messageHandle_table_toolbar" style="padding: 10px; display: inherit;">
    <select class="easyui-combobox" id="selectStatus_checkMessage" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
        <option value="未处理">未处理</option>
        <option value="已处理">已处理</option>
    </select>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="loadMessageTableByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearCondition()">清空</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-mini-refresh'" onclick="clearCondition()">刷新</a>
</div>

<table id="messageHandle_table"></table>

<div class="easyui-dialog" title="用户留言处理" id="messageHandle_dialog" style="width:380px;height:500px;padding:10px" data-options="closed:true, modal: true">
    <div style="padding: 10px;">
        <input class="easyui-textbox" id="username_messageHandle" name="username_messageHandle" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        <input class="easyui-textbox" id="sex_messageHandle" name="sex_messageHandle" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        <input class="easyui-textbox" id="phone_messageHandle" name="phone_messageHandle" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        <input class="easyui-textbox" id="date_messageHandle" name="date_messageHandle" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
    </div>
    <div style="padding: 10px;">
        <input class="easyui-textbox" id="content_messageHandle" name="content_messageHandle" data-options="type:'text',editable:false,multiline:true" style="width:100%;height:120px;padding:12px;"/>
    </div>
    <div style="padding: 10px;">
        <input class="easyui-textbox" id="status_messageHandle" name="status_messageHandle" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px;"/>
    </div>

    <div style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" id="unTreatedMessageHandle" onclick="unTreatedMessageHandle()" style="width:80px">处理</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#messageHandle_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>