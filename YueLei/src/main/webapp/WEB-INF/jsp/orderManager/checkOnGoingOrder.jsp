<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/3/5
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/orderManager/checkOrder.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="checkOnGoingOrder_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="searchOnGoingOrderByCustomerName" class="easyui-textbox" data-options="prompt:'通过用户姓名查找'"/>
    <input id="searchOnGoingOrderByCustomerPhone" class="easyui-textbox" data-options="prompt:'通过用户电话查找'"/>
    <select class="easyui-combobox" id="selectType_checkOnGoingOrder" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 120px">
        <option value="" selected="selected">全部</option>
        <c:forEach items="${types_checkOnGoingOrder}" var="type">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select>
    <select class="easyui-combobox" id="searchOnGoingOrderByStatus" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 120px">
        <option value="" selected="selected">全部</option>
        <c:forEach items="${status_checkOnGoingOrder}" var="status">
            <option value="${status}">${status}</option>
        </c:forEach>
    </select>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findOnGoingOrderByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearOnGoingOrderCondition()">清空</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-mini-refresh'" onclick="clearOnGoingOrderCondition()">刷新</a>
</div>

<table id="checkOnGoingOrder_table"></table>

<div class="easyui-dialog" title="订单延期" id="orderDelay_dialog" style="width:350px;height:330px;padding:10px" data-options="closed:true, modal: true">
    <form id="orderDelay_form" method="post">
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomNo_orderDelay" name="roomNo_orderDelay" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomType__orderDelay" name="roomType__orderDelay" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomPrice_orderDelay" name="roomPrice_orderDelay" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;display: none">
            <input class="easyui-textbox" id="endDate_orderDelay" name="endDate_orderDelay" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input id="delayDate_orderDelay" class="easyui-datebox" data-options="prompt:'延期到'" required="required"/>
        </div>
    </form>
    <div style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="commitDelayHandle()" style="width:80px">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#orderDelay_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>

<div class="easyui-dialog" title="更换房间" id="changeRoom_dialog" style="width:350px;height:350px;padding:10px" data-options="closed:true, modal: true">
    <div style="padding: 10px;">
        <label>当前房间:</label>
        <input class="easyui-textbox" id="roomNo_changeRoom" name="roomNo_changeRoom" data-options="type:'text',editable:false"/>
    </div>
    <div style="padding: 10px;">
        <label>每晚房价:</label>
        <input class="easyui-textbox" id="roomPrice_changeRoom" name="roomPrice_changeRoom" data-options="type:'text',editable:false"/>
    </div>
    <div style="padding: 10px;">
        <label>居住天数:</label>
        <input class="easyui-textbox" id="days_changeRoom" name="days_changeRoom" data-options="type:'text',editable:false"/>
    </div>
    <div style="padding: 10px;">
        <label>转到房间:</label>
        <input class="easyui-textbox" id="newRoomNo_changeRoom" name="newRoomNo_changeRoom" data-options="type:'text'"/>
    </div>
    <div style="padding: 10px;">
        <label>需补差价:</label>
        <input class="easyui-numberbox" type="text" id="priceDifference_changeRoom" name="priceDifference_changeRoom" value="0" data-options="min:0,precision:1"/>
    </div>
    <div style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="commitChangeRoomHandle()" style="width:80px">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#changeRoom_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>