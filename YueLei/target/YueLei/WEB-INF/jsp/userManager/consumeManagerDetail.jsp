<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/1/17
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/userManager.js"></script>
<div id="consume_table_toolbar" style="padding: 10px; display: inherit;">
    <input id="searchConsumeByName" class="easyui-textbox" data-options="prompt:'通过用户名查找'"/>
    <input id="searchConsumeByTel" class="easyui-textbox" data-options="prompt:'通过电话查找'"/>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findConsumeByCondition()">搜索</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearConsumeSearchBox()">清空</a>
</div>
<table id="consume_listInfo_table_id">
</table>
