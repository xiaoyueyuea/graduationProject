<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/1/8
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="easyui-panel" data-options="fit:true,border:false,split:true" id="west_panel">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <div title="用户管理" data-options="iconCls:'icon-man'" style="padding:5px;padding-left: 20px;padding-right: 20px;">
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-man'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','顾客管理','icon-man','userManager/showConsumeList')">顾客管理</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-man'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','员工管理','icon-man','userManager/showEmployeeList')">员工管理</a>
        </div>

        <div title="客房管理" data-options="iconCls:'icon-room'" style="padding:5px;padding-left: 20px;padding-right: 20px;">
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-room'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','查看客房','icon-room','roomManager/checkAllRoom')">查看客房</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-room'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','新增客房','icon-room','roomManager/addOrEditRoom')">新增客房</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-room'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','客房信息修改','icon-room','roomManager/editRoomInfo')">客房信息修改</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-room'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','图片上传','icon-room','roomManager/uploadPicture')">图片上传</a>
        </div>

        <div title="订单管理" data-options="iconCls:'icon-my-order'" style="padding:5px;padding-left: 20px;padding-right: 20px;">
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-my-order'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','进行中订单','icon-my-order','orderManager/checkOnGoingOrder')">进行中订单</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-my-order'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','已完成订单','icon-my-order','orderManager/checkFinishedOrder')">已完成订单</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-my-order'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;">酒店管理</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-my-order'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;">酒店管理</a>
        </div>

        <div title="财务管理" data-options="iconCls:'icon-financial'" style="padding:5px;padding-left: 20px;padding-right: 20px;">
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-financial'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','收益统计','icon-financial','financialManager/financialManagerPage')">收益统计</a>
        </div>

        <div title="留言管理" data-options="iconCls:'icon-liuyan'" style="padding:5px;padding-left: 20px;padding-right: 20px;">
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-liuyan'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','用户留言','icon-liuyan','messageManager/getCustomerMessage')">用户留言</a>
            <a href="#" class="easyui-linkbutton" data-options="toggle:true,group:'group_1',plain:true,iconCls:'icon-liuyan'" style="width: 100%;text-align:left;margin-top: 5px;margin-bottom: 5px;" onclick="openPage('centerTabsId','公告发布','icon-liuyan','')">公告发布</a>
        </div>
    </div>
</div>