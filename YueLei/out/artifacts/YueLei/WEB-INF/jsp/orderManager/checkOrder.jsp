<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/3/5
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/orderManager/checkOrder.js"></script>

<div id="checkOrder_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="searchOrderByRoomNo" class="easyui-textbox" data-options="prompt:'通过房号查找'"/>
    <select class="easyui-combobox" id="selectType_checkOrder" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
    </select>
    <select class="easyui-combobox" id="searchOrderByStatus" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
    </select>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findOrderByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="">清空</a>
</div>

<table id="checkOrder_table"></table>