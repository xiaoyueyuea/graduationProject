<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/12/24
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common.jsp" %>
<html>
<head>
    <title>用户登录</title>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div class="easyui-window" title="酒店管理系统" data-options="collapsible:false,minimizable:false,maximizable:false,closable:false,draggable:false">
    <div class="easyui-panel" data-options="border:false" style="width:300px; padding:35px;padding-bottom:10px;">
        <form id="loginForm" method="post">
            <div style="padding: 15px;">
                <input class="easyui-textbox" id="username" name="username" data-options="type:'text',required:true,validType:'nameTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入账号'" style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="padding: 15px;">
                <input class="easyui-textbox" id="password" name="password" data-options="type:'password',required:true,validType:'passwordTooLong',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-lock',prompt:'..........'" style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="text-align: center; padding: 15px;">
                <a class="easyui-linkbutton" style="width:40%;height:36px;padding:12px" onclick="attemptLogin()">登录</a>
                <a class="easyui-linkbutton" style="width:40%;height:36px;padding:12px" href="javascript:void(0)" onclick="$('#consume_insert_dialog').dialog('open')">注册</a>
            </div>
        </form>
    </div>
</div>

<div class="easyui-dialog" title="会员注册" id="consume_insert_dialog" style="width:350px;height:400px;padding:10px" data-options="closed:true, modal: true">
    <div data-options="region:'center'" style="padding:10px;">
        <form id="consume_register_form" method="post">
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="consume_username" name="consume_username" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-man',prompt:'请输入昵称'" style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="consume_password1" name="consume_password1" data-options="type:'password',required:true,validType:'password',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-lock',prompt:'请输入密码'" style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="consume_password2" name="consume_password2" data-options="type:'password',required:true,validType:'password',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-lock',prompt:'确认密码'" style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="padding: 10px;">
                男<input type="radio" name="sex" value="男" checked="checked"/>
                女<input type="radio" name="sex" value="女"/>
            </div>
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="consume_phone" name="consume_phone" data-options="type:'text',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-phone',prompt:'请输入电话'" style="width:100%;height:30px;padding:12px"/>
            </div>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="insertUser()" style="width:80px">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#consume_insert_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>
</body>
</html>
