<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/1/17
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/home_east.js"></script>

<div style="width: 100%">
    <div class="easyui-panel" title="公告中心"  style="width: 100%;height: 200px;">
        <p>暂无公告!</p>
    </div>
    <div style="margin-top: 100px;width: 100%;margin-left: 10px">
        <input class="easyui-textbox"  id="message_box" data-options="type:'text',prompt:'请输入留言',multiline:true" style="width:100%;height:150px;"/>
    </div>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-message',iconAlign:'left'" onclick="customerMessageHandle()" style="width:30%;height:36px;margin-top: 10px;margin-left: 75px">留言</a>

    <div class="easyui-calendar" id="calendar" style="width:100%;height:250px;margin-top: 100px;margin-left: 10px">
    </div>
</div>
