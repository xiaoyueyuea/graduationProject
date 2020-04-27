$(function () {
    loadMessageTable();

    $("#messageHandle_table").datagrid({
        onDblClickRow : function (rowIndex, rowData) {
            $("#messageHandle_dialog").dialog("open");
            $("#username_messageHandle").textbox('setValue',rowData.username);
            $("#sex_messageHandle").textbox('setValue',rowData.sex);
            $("#phone_messageHandle").textbox('setValue',rowData.phone);
            $("#date_messageHandle").textbox('setValue',new Date(rowData.messageDate).toLocaleString());
            $("#content_messageHandle").textbox('setValue',rowData.messageContent);
            $("#status_messageHandle").textbox('setValue',rowData.status);
            $("#messageDate_hidden").val(rowData.messageDate);
        }
    });
});

function loadMessageTable() {
    var messageListDataOptionsForCheck = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#messageHandle_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"username",title:"用户名",width:50,align:"center"},
            {field:"sex",title:"性别",width:50,align:"center"},
            {field:"phone",title:"电话",width:50,align:"center"},
            {field:"messageDate",title:"留言时间",width:50,align:"center",sortable:true,formatter:function (value,row,index) {
                return new Date(value).toLocaleString();
                }},
            {field:"status",title:"留言状态",width:50,align:"center"},
            {field:"messageContent",title:"内容",width:50,align:"center"}
        ]],
        url : "messageManager/getAllMessage",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#messageHandle_table").datagrid(messageListDataOptionsForCheck);
}

function unTreatedMessageHandle() {
    var username=$("#username_messageHandle").textbox("getValue");
    var messageDate=$("#messageDate_hidden").val();
    var status=$("#status_messageHandle").textbox('getValue');
    if(status=="已处理"){
        $.messager.alert("温馨提示","该留言已处理");
        return;
    }
    $.post("messageManager/unTreatedMessageHandle_"+username+"_"+messageDate,{},function (res) {
        if(res=="success"){
            $.messager.alert("温馨提示","处理成功");
            $("#messageHandle_dialog").dialog("close");
        }else {
            $.messager.alert("温馨提示","系统异常，处理失败!")
        }
    });
}

function loadMessageTableByCondition() {

    var status = $("#selectStatus_checkMessage").combobox("getValue");

    var getMessageListByCondition = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#messageHandle_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"username",title:"用户名",width:50,align:"center"},
            {field:"sex",title:"性别",width:50,align:"center"},
            {field:"phone",title:"电话",width:50,align:"center"},
            {field:"messageDate",title:"留言时间",width:50,align:"center",sortable:true,formatter:function (value,row,index) {
                    return new Date(value).toLocaleString();
                }},
            {field:"status",title:"留言状态",width:50,align:"center"},
            {field:"messageContent",title:"内容",width:50,align:"center"}
        ]],
        url : "messageManager/getMessageByCondition_"+status,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#messageHandle_table").datagrid(getMessageListByCondition);
}

function clearCondition() {
    $("#selectStatus_checkMessage").combobox("setValue","");
    loadMessageTable();
}