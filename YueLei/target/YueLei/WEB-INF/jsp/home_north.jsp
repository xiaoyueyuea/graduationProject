<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/1/8
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="easyui-layout" data-options="fit:true,border:false" style="padding: 0px;">
    <div data-options="region:'west',split:false,border:false" style="width: auto">
        <a href="https://www.swpu.edu.cn" >
            <img src="${pageContext.request.contextPath}/img/swpu.jpg" style="position: absolute; left: 10px; top: 0px; bottom: 0px; margin: auto;width: auto;height: 48px;" alt="swpu"/>
        </a>
        <a href="#">
            <img src="${pageContext.request.contextPath}/img/haha.jpg" style="margin-left: 60px;width: auto;height: 48px;" alt="swpu"/>
        </a>
    </div>
    <div class="easyui-panel" data-options="region:'east',split:false,border:false" style=" overflow:hidden; width: 600px;">
        <div style="float: right;padding-top:10px;display: inline-block;height: 100% ">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-man',iconAlign:'left'" style="text-align: center;width: 130px;border: 0px;background: #00000000">${username}</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-modifypassword',iconAlign:'left'" id="modifyPassword" onclick="$('#modify_password_dialog').dialog('open')" style="background: #00000000">修改密码</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-quit',iconAlign:'left'" style="margin-right: 10px;background: #00000000" onclick="logout()">退出</a>
        </div>
    </div>
</div>

<div class="easyui-dialog" title="修改密码" id="modify_password_dialog" style="width:350px;height:280px;padding:10px" data-options="closed:true, modal: true">
    <div data-options="region:'center'" style="padding:10px;">
        <form id="modify_password_form" method="post">
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="oldPassword" name="oldPassword" data-options="type:'password',required:true,validateOnCreate:false,validateOnBlur:true,iconCls:'icon-lock',prompt:'请输入旧密码'"  style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="newPassword1" name="newPassword1" data-options="type:'password',required:true,validType:'password',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-lock',prompt:'请输入新密码'" style="width:100%;height:30px;padding:12px"/>
            </div>
            <div style="padding: 10px;">
                <input class="easyui-textbox" id="newPassword2" name="newPassword2" data-options="type:'password',required:true,validType:'password',validateOnCreate:false,validateOnBlur:true,iconCls:'icon-lock',prompt:'再次输入'" style="width:100%;height:30px;padding:12px"/>
            </div>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align:center;padding:5px 0 0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="modifyPassword()" style="width:80px">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#modify_password_dialog').dialog('close')" style="width:80px">取消</a>
    </div>
</div>
