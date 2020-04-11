<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/3/18
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/financialManager/getFinancialIncome.js"></script>

<div id="financialIncome_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="getFinancialIncomeByStartDate" class="easyui-datebox" data-options="prompt:'开始时间'"/>
    <input id="getFinancialIncomeByEndDate" class="easyui-datebox" data-options="prompt:'结束时间'"/>
    <select class="easyui-combobox" id="selectType_getFinancialIncome" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 150px">
        <option value="" selected="selected">全部</option>
        <c:forEach items="${types_getFinancialIncome}" var="type">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="loadGetFinancialIncomeTableByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearCondition()">清空</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-mini-refresh'" onclick="clearCondition()">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-excel'" onclick="exportExcel()" style="float: right">导出表格</a>
</div>

<table id="getFinancialIncome_table"></table>