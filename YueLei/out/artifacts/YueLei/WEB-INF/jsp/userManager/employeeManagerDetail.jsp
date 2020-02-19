<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2020/1/17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/userManager.js"></script>
<div id="employee_table_toolbar" style="padding: 10px; display: inherit;">
        <input id="searchEmployeeByNo" class="easyui-textbox" data-options="prompt:'通过工号查找'"/>
        <input id="searchEmployeeByName" class="easyui-textbox" data-options="prompt:'通过用户名查找'"/>
        <input id="searchEmployeeByTel" class="easyui-textbox" data-options="prompt:'通过电话查找'"/>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-condition-search'" onclick="findEmployeeByCondition()">搜索</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-clear-condition'" onclick="clearEmployeeSearchBox()">清空</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-mini-add'" href="javascript:void(0)" onclick="$('#employee_add_dialog').dialog('open')" style="background: #00000000;padding-right: 10px">新增员工</a>
</div>

<table id="employee_listInfo_table_id">
</table>

<div class="easyui-dialog" title="员工信息编辑" id="employee_edit_dialog" style="width:300px;height:350px;padding:10px" data-options="closed:true, modal: true">
        <div style="padding: 10px;">
                姓名：<input class="easyui-textbox" id="employee_name_edit" name="employee_name_edit" data-options="type:'text',editable:false" style="width:80%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
                性别：<input class="easyui-textbox" id="employee_sex_edit" name="employee_sex_edit" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
                电话：<input class="easyui-textbox" id="employee_phone_edit" name="employee_phone_edit" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
        </div>
        <div style="padding: 10px;">
                权限：<select id="select_admin_edit" name="select_admin" class="easyui-combobox" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width:80%;height:30px;padding:12px">
                        <option value="1" id="admin_1">1(普通员工)</option>
                        <option value="2" id="admin_2">2(顾客)</option>
                        <option value="0" id="admin_0">0(管理员)</option>
                     </select>
        </div>
        <div style="padding: 10px;text-align: center">
                <a class="easyui-linkbutton" onclick="employeeInfoEdit()" style="width:40%;height:36px;padding:12px;text-align: center">确认</a>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#employee_edit_dialog').dialog('close')" style="width:40%;height:36px;padding:12px;text-align: center">取消</a>
        </div>
</div>

<div class="easyui-dialog" title="新增员工" id="employee_add_dialog" style="width:300px;height:370px;padding:10px" data-options="closed:true, modal: true">
       <form id="add_employee_form" method="post">
               <div style="padding: 10px;">
                       姓名：<input class="easyui-textbox" id="employee_name_add" name="employee_name_add" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
               </div>
               <div style="padding: 10px;">
                       密码：<input class="easyui-textbox" id="employee_password_add" name="employee_name_add" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
               </div>
               <div style="padding: 10px;">
                       性别：男<input type="radio" name="sex_add" value="男" checked="checked"/>
                       女<input type="radio" name="sex_add" value="女"/>
               </div>
               <div style="padding: 10px;">
                       电话：<input class="easyui-textbox" id="employee_phone_add" name="employee_phone_add" data-options="type:'text'" style="width:80%;height:30px;padding:12px"/>
               </div>
               <div style="padding: 10px;">
                       权限：<select id="select_admin_add" name="select_admin_add" class="easyui-combobox" data-options="limitToList:true,editable:false,panelHeight:'auto'" style="width:80%;height:30px;padding:12px">
                       <option value="1" id="admin_1_add">1(普通员工)</option>
                       <option value="2" id="admin_2_add">2(顾客)</option>
                       <option value="0" id="admin_0_add">0(管理员)</option>
               </select>
               </div>
               <div style="padding: 10px;text-align: center">
                       <a class="easyui-linkbutton" onclick="addEmployee()" style="width:40%;height:36px;padding:12px;text-align: center">确认</a>
                       <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#employee_add_dialog').dialog('close')" style="width:40%;height:36px;padding:12px;text-align: center">取消</a>
               </div>
       </form>
</div>

