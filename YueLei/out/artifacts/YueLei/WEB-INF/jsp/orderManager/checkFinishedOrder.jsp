<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/3/9
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/orderManager/checkOrder.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="checkFinishedOrder_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="searchFinishedOrderByCustomerName" class="easyui-textbox" data-options="prompt:'通过用户姓名查找'"/>
    <input id="searchFinishedOrderByCustomerPhone" class="easyui-textbox" data-options="prompt:'通过用户电话查找'"/>
    <select class="easyui-combobox" id="selectType_checkFinishedOrder" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
        <c:forEach items="${types_checkFinishedOrder}" var="type">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="getFinishedOrdersByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearFinishedTableCondition()">清空</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-mini-refresh'" onclick="clearFinishedTableCondition()">刷新</a>
</div>

<table id="checkFinishedOrder_table"></table>