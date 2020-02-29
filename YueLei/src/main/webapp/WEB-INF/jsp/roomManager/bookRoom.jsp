<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/2/24
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bookRoom.js"></script>
<div id="consumer_bookRoom_filter_toolbar" style="padding: 10px; display: inherit;">
    入住时间：<input id="startDate" class="easyui-datebox" required="required"/>-
    <input id="endDate" class="easyui-datebox" required="required"/>

    房间类型：<select id="filter_roomStyle" class="easyui-combobox" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width: 120px;">
        <option value="全部">全部</option>
        <option value="单间">单间</option>
        <option value="标间">标间</option>
        <option value="三人间">三人间</option>
    </select>
</div>

<div id="roomBook_cardView" style="width: 100%">

</div>




