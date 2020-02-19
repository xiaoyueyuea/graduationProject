$(function () {
    loadEmployeeListTable();
    loadConsumeListTable();

    $("#employee_listInfo_table_id").datagrid({
        onDblClickRow : function (rowIndex, rowData) {
            $("#employee_edit_dialog").dialog("open");
            $("#employee_name_edit").textbox('setValue',rowData.username);
            $("#employee_sex_edit").textbox('setValue',rowData.sex);
            $("#employee_phone_edit").textbox('setValue',rowData.phone);
            $("#select_admin_edit").combobox('setValue',rowData.admin);
        }
    });
});


function loadEmployeeListTable() {
    var employeeListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        striped :true,
        fitColumns : true,
        toolbar: "#employee_table_toolbar",
        idField : "id",
        columns:[[
            {field:"userId",title:"编号",width:50,align:"center"},
            {field:"username",title:"用户名",width:50,align:"center"},
            {field:"sex",title:"性别",width:50,align:"center"},
            {field:"phone",title:"联系电话",width:50,align:"center"},
            {field:"admin",title:"权限",width:50,align:"center"}
        ]],
        url : "userManager/getEmployeeList",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    }
    $("#employee_listInfo_table_id").datagrid(employeeListDataOptions);
}

function loadConsumeListTable() {
    var consumeListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#consume_table_toolbar",
        striped :true,
        fitColumns : true,
        idField : "id",
        columns:[[
            {field:"userId",title:"编号",width:50,align:"center"},
            {field:"username",title:"用户名",width:50,align:"center"},
            {field:"sex",title:"性别",width:50,align:"center"},
            {field:"phone",title:"联系电话",width:50,align:"center"},
            {field:"admin",title:"权限",width:50,align:"center"}
        ]],
        url : "userManager/getConsumeList",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };
    $("#consume_listInfo_table_id").datagrid(consumeListDataOptions);
}

function employeeInfoEdit() {
    $.messager.confirm("温馨提示","确认修改此员工信息吗?",function (r) {
        if(r){
            var name=$("#employee_name_edit").textbox('getValue');
            var sex=$("#employee_sex_edit").textbox('getValue');
            var phone=$("#employee_phone_edit").textbox('getValue');
            var admin=$("#select_admin_edit").combobox('getValue');

            $.post("userManager/updateEmployeeInfo_"+name+"_"+sex+"_"+phone+"_"+admin,{},function (res) {
                if(res=="success"){
                    $.messager.alert("温馨提示","修改成功!");
                    $("#employee_edit_dialog").dialog('close');
                }else {
                    $.messager.alert("温馨提示","修改失败!!");
                }
            });
        }

    });
}

function findConsumeByCondition() {
    var username=$("#searchConsumeByName").textbox('getValue');
    var phone=$("#searchConsumeByTel").textbox('getValue');

    if(username =="" && phone == ""){
        $.messager.alert("温馨提示","搜索条件不可全为空!");
        return;
    }

    var getConsumeList = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#consume_table_toolbar",
        striped :true,
        fitColumns : true,
        idField : "id",
        columns:[[
            {field:"userId",title:"编号",width:50,align:"center"},
            {field:"username",title:"用户名",width:50,align:"center"},
            {field:"sex",title:"性别",width:50,align:"center"},
            {field:"phone",title:"联系电话",width:50,align:"center"},
            {field:"admin",title:"权限",width:50,align:"center"}
        ]],
        url : "userManager/getConsumeByCondition_"+username+"_"+phone,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };
    $("#consume_listInfo_table_id").datagrid(getConsumeList);
}

function findEmployeeByCondition() {
    var number=$("#searchEmployeeByNo").textbox('getValue');
    var username=$("#searchEmployeeByName").textbox('getValue');
    var phone=$("#searchEmployeeByTel").textbox('getValue');

    if(username =="" && phone == "" && number == ""){
        $.messager.alert("温馨提示","搜索条件不可全为空!");
        return;
    }

    var getEmployeeList = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#employee_table_toolbar",
        striped :true,
        fitColumns : true,
        idField : "id",
        columns:[[
            {field:"userId",title:"编号",width:50,align:"center"},
            {field:"username",title:"用户名",width:50,align:"center"},
            {field:"sex",title:"性别",width:50,align:"center"},
            {field:"phone",title:"联系电话",width:50,align:"center"},
            {field:"admin",title:"权限",width:50,align:"center"}
        ]],
        url : "userManager/getEmployeeByCondition_"+number+"_"+username+"_"+phone,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };
    $("#employee_listInfo_table_id").datagrid(getEmployeeList);
}

function clearConsumeSearchBox() {
    $("#searchConsumeByName").textbox('setValue',"");
    $("#searchConsumeByTel").textbox('setValue',"");
    loadConsumeListTable();
}

function clearEmployeeSearchBox() {
    $("#searchEmployeeByNo").textbox('setValue',"");
    $("#searchEmployeeByName").textbox('setValue',"");
    $("#searchEmployeeByTel").textbox('setValue',"");
    loadEmployeeListTable();
}

function addEmployee() {
    var newEmployName=$("#employee_name_add").textbox('getValue');
    var newEmployPassword=$("#employee_password_add").textbox('getValue');
    var sex=$("input[name='sex_add']:checked").val();
    var newEmployTel=$("#employee_phone_add").textbox('getValue');
    var newEmployAdmin=$("#select_admin_add").combobox('getValue');

    $("#add_employee_form").form("submit",{
        url : "userManager/addEmployee_"+newEmployName+"_"+newEmployPassword+"_"+sex+"_"+newEmployTel+"_"+newEmployAdmin,
        onSubmit : function () {
            if(newEmployName == "" || newEmployPassword =="" || newEmployTel == ""){
                $.messager.alert("温馨提示","请完善信息!");
                return false;
            }
            return true;
        },
        success : function (res) {
            if(res=="success"){
                $.messager.alert("温馨提示","添加成功!",'info');
                $("#employee_add_dialog").dialog('close');
                loadEmployeeListTable();
            }else {
                $.messager.alert("温馨提示","添加失败!!");
            }
        }
    });
}

