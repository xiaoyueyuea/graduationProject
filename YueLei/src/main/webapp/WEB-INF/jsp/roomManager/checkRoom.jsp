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
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findRoomByConditionToCheck()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearConditionToCheck()">清空</a>
</div>

<table id="checkRoom_table"></table>

