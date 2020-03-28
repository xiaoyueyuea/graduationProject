<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/2/24
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkRoom.js"></script>

<div id="checkRoom_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="searchRoomByRoomNoToCheck" class="easyui-textbox" data-options="prompt:'通过房号查找'"/>
    <select class="easyui-combobox" id="selectType_checkRoom" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
        <c:forEach items="${types_checkRoom}" var="type">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select>
    <select class="easyui-combobox" id="selectStatus_checkRoom" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
        <option value="空闲">空闲</option>
        <option value="已预订">已预订</option>
        <option value="使用中">使用中</option>
    </select>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findRoomByConditionToCheck()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearConditionToCheck()">清空</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-mini-refresh'" onclick="clearConditionToCheck()">刷新</a>
</div>

<table id="checkRoom_table"></table>

<div class="easyui-dialog" title="未预定用户入住办理" id="notOrderCustomer_checkIn_dialog" style="width:380px;height:610px;padding:10px" data-options="closed:true, modal: true">
    <form id="notOrderCustomer_checkIn_form" method="post">
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomNo_notOrderCustomer_checkIn" name="roomNo_notOrderCustomer_checkIn" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomType_notOrderCustomer_checkIn" name="roomType_notOrderCustomer_checkIn" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomPrice_notOrderCustomer_checkIn" name="roomPrice_notOrderCustomer_checkIn" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomArea_notOrderCustomer_checkIn" name="roomArea_notOrderCustomer_checkIn" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="realName_notOrderCustomer_checkIn" name="realName_notOrderCustomer_checkIn" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入真实姓名'" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="phone_notOrderCustomer_checkIn" name="phone_notOrderCustomer_checkIn" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-phone',prompt:'请输入电话'" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="idCard_notOrderCustomer_checkIn" name="idCard_notOrderCustomer_checkIn" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入身份证号'" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input id="startDate_notOrderCustomer_checkIn" class="easyui-datebox" data-options="prompt:'入住时间'" required="required"/>
        </div>
        <div style="padding: 10px;">
            <input id="endDate_notOrderCustomer_checkIn" class="easyui-datebox" data-options="prompt:'离开时间'" required="required"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="remarks_notOrderCustomer_checkIn" name="remarks_notOrderCustomer_checkIn" data-options="type:'text',iconCls:'icon-man',prompt:'备注(选填)'" style="width:100%;height:30px;padding:12px"/>
        </div>
    </form>
    <div style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="employeeCommitOrderForCustomer()" style="width:80px">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#notOrderCustomer_checkIn_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>

<div class="easyui-dialog" title="退房信息确认" id="checkOutConfirm_dialog" style="width:350px;height:250px;padding:10px;text-align: center" data-options="closed:true, modal: true">
    <div style="padding: 10px;">
        <label>房号:</label>
        <input class="easyui-textbox" id="roomNo_checkOutConfirm" name="roomNo_checkOutConfirm" data-options="type:'text',editable:false"/>
    </div>
    <div style="padding: 10px;">
        <label>房型:</label>
        <input class="easyui-textbox" id="roomType_checkOutConfirm" name="roomType_checkOutConfirm" data-options="type:'text',editable:false"/>
    </div>
    <div style="padding: 10px;">
        <label>其它费用:</label>
        <input class="easyui-numberbox" type="text" id="otherCost_checkOutConfirm" name="priceDifference_changeRoom" value="0" data-options="min:0,precision:1"/>
    </div>
    <div style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="confirmCheckOut()" style="width:80px">确认</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#checkOutConfirm_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>

