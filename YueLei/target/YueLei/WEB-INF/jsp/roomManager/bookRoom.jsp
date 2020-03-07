<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/2/24
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bookRoom.js"></script>
<div id="consumer_bookRoom_filter_toolbar" style="padding: 10px; display: inherit;">
    入住时间：<input id="startDate" class="easyui-datebox" required="required"/>-
    <input id="endDate" class="easyui-datebox" required="required"/>

    房间类型：<select id="selectType_bookRoom" class="easyui-combobox" data-options="valueField:'id', textField:'text',limitToList:true,editable:false,panelHeight:'auto'" style="width: 120px;">
    <option value="" selected="selected">全部</option>
    <c:forEach items="${types_bookRoom}" var="type">
        <option value="${type}">${type}</option>
    </c:forEach>
    </select>
</div>

<div id="roomBook_cardView" style="width: 100%">

</div>

<div class="easyui-dialog" title="客房详情" id="checkRoomDetailToOrder_dialog" style="width:400px;height:380px;padding:10px" data-options="closed:true, modal: true">
        <div style="text-align: center;margin-top: 20px">
            <table>
                <tr>
                    <td style="width: 20%;text-align: center"><a class="easyui-linkbutton" data-options="iconCls:'icon-previous'" style="background: #00000000;border: 0" onclick="previousPicture()"></a></td>
                    <td style="width: 60%;text-align: center"><img src="" alt="图片集" id="roomDetailInfo_picture" style="width: 80%"/></td>
                    <td style="width: 20%;text-align: center"> <a class="easyui-linkbutton" data-options="iconCls:'icon-next'" style="background: #00000000;border: 0" onclick="nextPicture()"></a></td>
                </tr>
            </table>
        </div>
        <div style="margin-top: 20px">
            <p id="roomDetailInfo_type" style="text-align: center">房间类型</p>
            <p id="roomDetailInfo_remarks" style="text-align: center">房间设施</p>
            <p id="roomDetailInfo_priceAndCount" style="text-align: center">房间价格以及数量</p>
        </div>
        <div style="text-align: center">
            <a class="easyui-linkbutton" style="width: 30%;text-align: center" href="javascript:void(0)" onclick="loadOrderInitialization()">预定</a>
            <a class="easyui-linkbutton" style="width: 30%;text-align: center;margin-left: 20px" href="javascript:void(0)" onclick="$('#checkRoomDetailToOrder_dialog').dialog('close')">取消</a>
        </div>
</div>


<div class="easyui-dialog" title="客房预定信息填写" id="createOrderInfo_dialog" style="width:360px;height:580px;padding:10px" data-options="closed:true, modal: true">
    <form id="order_create_form" method="post">
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomType_createOrder" name="roomType_createOrder" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomPrice_createOrder" name="roomPrice_createOrder" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="roomArea_createOrder" name="roomArea_createOrder" data-options="type:'text',editable:false" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="realName_createOrder" name="realName_createOrder" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入真实姓名'" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="phone_createOrder" name="phone_createOrder" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-phone',prompt:'请输入电话'" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="idCard_createOrder" name="idCard_createOrder" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入身份证号'" style="width:100%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
            <input id="startDate_creatOrder" class="easyui-datebox" data-options="prompt:'入住时间'" required="required"/>
        </div>
        <div style="padding: 10px;">
            <input id="endDate_creatOrder" class="easyui-datebox" data-options="prompt:'离开时间'" required="required"/>
        </div>
        <div style="padding: 10px;">
            <input class="easyui-textbox" id="remarks_createOrder" name="remarks_createOrder" data-options="type:'text',iconCls:'icon-man',prompt:'备注(选填)'" style="width:100%;height:30px;padding:12px"/>
        </div>
    </form>
    <div style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="commitOrder()" style="width:80px">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#createOrderInfo_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>





