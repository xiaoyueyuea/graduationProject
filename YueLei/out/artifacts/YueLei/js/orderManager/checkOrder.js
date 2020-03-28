$(function () {
    loadOnGoingOrderListTable();
    loadFinishedOrdersTable();
});

function loadOnGoingOrderListTable() {
    var orderListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkOnGoingOrder_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center"},
            {field:"roomType",title:"房型",width:50,align:"center"},
            {field:"roomPrice",title:"价格",width:50,align:"center"},
            {field:"roomArea",title:"面积",width:50,align:"center"},
            {field:"customerNickname",title:"用户昵称",width:50,align:"center"},
            {field:"customerRealname",title:"真实姓名",width:50,align:"center"},
            {field:"phone",title:"电话",width:50,align:"center"},
            {field:"idCard",title:"身份证",width:50,align:"center"},
            {field:"startDate",title:"入住时间",width:50,align:"center",formatter :function (value,row,index) {
                return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"离店时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:50,align:"center"},
            {field:"status",title:"订单状态",width:50,align:"center"},
            {field:"remarks",title:"用户备注",width:50,align:"center"},
            {field:"action4",title:"操作",width:50,align:"center",formatter:function (value, row, index) {
                if(row.status=="未入住"){
                    return "<a href='#' onclick='overdueHandle(this)' data-roomNo='"
                        + row.roomNo
                        + "'>逾期</a>";
                }else {
                    return "<a href='#' onclick='delayHandle(this)' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.roomType
                        + "' data-price='"
                        + row.roomPrice
                        + "' data-endDate='"
                        + row.endDate
                        + "' data-status='"
                        + row.status + "'>延期  </a><a href='#' onclick='changeRoomHandle(this)' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.roomType
                        + "' data-price='"
                        + row.roomPrice
                        + "' data-endDate='"
                        + row.endDate
                        + "' data-days='"
                        + row.days + "'>  换房</a>";
                }
                    }
            }
        ]],
        url : "orderManager/getOnGoingOrderList",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkOnGoingOrder_table").datagrid(orderListDataOptions);
}

function findOnGoingOrderByCondition() {

    var customerName=$("#searchOnGoingOrderByCustomerName").textbox("getValue");
    var customerPhone=$("#searchOnGoingOrderByCustomerPhone").textbox("getValue");
    var roomType=$("#selectType_checkOnGoingOrder").combobox("getValue");
    var orderStatus=$("#searchOnGoingOrderByStatus").combobox("getValue");

    if(customerName=="" && customerPhone=="" && roomType=="" && orderStatus==""){
        $.messager.alert("温馨提示","搜索条件为空");
        return;
    }

    var findOrderByCondition = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkOnGoingOrder_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center"},
            {field:"roomType",title:"房型",width:50,align:"center"},
            {field:"roomPrice",title:"价格",width:50,align:"center"},
            {field:"roomArea",title:"面积",width:50,align:"center"},
            {field:"customerNickname",title:"用户昵称",width:50,align:"center"},
            {field:"customerRealname",title:"真实姓名",width:50,align:"center"},
            {field:"phone",title:"电话",width:50,align:"center"},
            {field:"idCard",title:"身份证",width:50,align:"center"},
            {field:"startDate",title:"入住时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"离店时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:50,align:"center"},
            {field:"status",title:"订单状态",width:50,align:"center"},
            {field:"remarks",title:"用户备注",width:50,align:"center"},
            {field:"action4",title:"操作",width:50,align:"center",formatter:function (value, row, index) {
                    if(row.status=="未入住"){
                        return "<a href='#' onclick='overdueHandle(this)' data-roomNo='"
                            + row.roomNo
                            + "'>逾期</a>";
                    }else {
                        return "<a href='#' onclick='delayHandle(this)' data-roomNo='"
                            + row.roomNo
                            + "' data-type='"
                            + row.roomType
                            + "' data-price='"
                            + row.roomPrice
                            + "' data-endDate='"
                            + row.endDate
                            + "' data-status='"
                            + row.status + "'>延期  </a><a href='#' onclick='changeRoomHandle(this)' data-roomNo='"
                            + row.roomNo
                            + "' data-type='"
                            + row.roomType
                            + "' data-price='"
                            + row.roomPrice
                            + "' data-endDate='"
                            + row.endDate
                            + "' data-days='"
                            + row.days + "'>  换房</a>";
                    }
                }
            }
        ]],
        url : "orderManager/getOnGoingOrderListByCondition_"+customerName+"_"+customerPhone+"_"+roomType+"_"+orderStatus,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkOnGoingOrder_table").datagrid(findOrderByCondition);
}

function clearOnGoingOrderCondition() {
    $("#searchOnGoingOrderByCustomerName").textbox("setValue","");
    $("#searchOnGoingOrderByCustomerPhone").textbox("setValue","");
    $("#selectType_checkOnGoingOrder").combobox("setValue","");
    $("#searchOnGoingOrderByStatus").combobox("setValue","");

    loadOnGoingOrderListTable();
}

function loadFinishedOrdersTable() {
    var FinishedOrderListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkFinishedOrder_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center"},
            {field:"roomType",title:"房型",width:50,align:"center"},
            {field:"roomPrice",title:"价格",width:50,align:"center"},
            {field:"roomArea",title:"面积",width:50,align:"center"},
            {field:"customerNickname",title:"用户昵称",width:50,align:"center"},
            {field:"customerRealname",title:"真实姓名",width:50,align:"center"},
            {field:"phone",title:"电话",width:50,align:"center"},
            {field:"idCard",title:"身份证",width:50,align:"center"},
            {field:"startDate",title:"入住时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"离店时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:50,align:"center"},
            {field:"status",title:"订单状态",width:50,align:"center"},
            {field:"remarks",title:"备注",width:50,align:"center"},
        ]],
        url : "orderManager/getFinishedOrderList",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkFinishedOrder_table").datagrid(FinishedOrderListDataOptions);
}

function getFinishedOrdersByCondition() {
    var customerName=$("#searchFinishedOrderByCustomerName").textbox("getValue");
    var customerPhone=$("#searchFinishedOrderByCustomerPhone").textbox("getValue");
    var roomType=$("#selectType_checkFinishedOrder").combobox("getValue");

    if(customerName=="" && customerPhone=="" && roomType==""){
        $.messager.alert("温馨提示","搜索条件为空");
        return;
    }

    var getFinishedOrdersByCondition = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkFinishedOrder_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center"},
            {field:"roomType",title:"房型",width:50,align:"center"},
            {field:"roomPrice",title:"价格",width:50,align:"center"},
            {field:"roomArea",title:"面积",width:50,align:"center"},
            {field:"customerNickname",title:"用户昵称",width:50,align:"center"},
            {field:"customerRealname",title:"真实姓名",width:50,align:"center"},
            {field:"phone",title:"电话",width:50,align:"center"},
            {field:"idCard",title:"身份证",width:50,align:"center"},
            {field:"startDate",title:"入住时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"离店时间",width:50,align:"center",formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:50,align:"center"},
            {field:"status",title:"订单状态",width:50,align:"center"},
            {field:"remarks",title:"备注",width:50,align:"center"},
        ]],
        url : "orderManager/getFinishedOrderListByCondition_"+customerName+"_"+customerPhone+"_"+roomType,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkFinishedOrder_table").datagrid(getFinishedOrdersByCondition);

}

function clearFinishedTableCondition() {
    $("#searchFinishedOrderByCustomerName").textbox("setValue","");
    $("#searchFinishedOrderByCustomerPhone").textbox("setValue","");
    $("#selectType_checkFinishedOrder").combobox("setValue","");
    loadFinishedOrdersTable();
}

function overdueHandle(pram) {
    var roomNo = $(pram).attr("data-roomNo");
    $.messager.confirm("温馨提示","您确定对该订单进行逾期操作吗",function () {
        $.post("orderManager/overdueHandle_"+roomNo,{},function (res) {
            if(res=="success"){
                $.messager.alert("温馨提示","操作成功!");
            }else {
                $.messager.alert("温馨提示","因未知原因，操作失败，请重试");
            }
        });
    });
}

function delayHandle(pram) {
    var roomNo = $(pram).attr("data-roomNo");
    var roomType=$(pram).attr("data-type");
    var roomPrice=$(pram).attr("data-price");
    var endDate=$(pram).attr("data-endDate");

    $.messager.confirm("温馨提示","您确定对该订单进行延期操作吗",function () {
        $("#orderDelay_dialog").dialog('open');
        $("#roomNo_orderDelay").textbox("setValue",roomNo);
        $("#roomType__orderDelay").textbox("setValue",roomType);
        $("#roomPrice_orderDelay").textbox("setValue",roomPrice+"元/晚");
        $("#endDate_orderDelay").textbox("setValue",endDate);

    });
}

function commitDelayHandle(){
    var roomNo=$("#roomNo_orderDelay").textbox("getValue");
    var endDate=$("#endDate_orderDelay").textbox("getValue");
    var days=0;//延期天数
    var delayDate=$("#delayDate_orderDelay").datebox("getValue");
    var dd=delayDate.replace(/-/g, '/');
    var ms = new Date(dd).getTime()-endDate;
    days=Math.ceil(ms / 1000 / 24 / 60 / 60);
    $("#orderDelay_form").form("submit",{
        url :"orderManager/delayHandle_"+roomNo+"_"+delayDate+"_"+days,
        onSubmit :function () {
            if(delayDate == ""){
                $.messager.alert("温馨提示","日期不可为空");
                return false;
            }
            if(new Date(dd).getTime()<=endDate){
                $.messager.alert("温馨提示","延期时间不可小于结束时间");
                return false;
            }
            return true;
        },
        success :function (res) {
            if(res=="success"){
                $("#orderDelay_dialog").dialog('close');
                $.messager.alert("温馨提示","延期成功!");
            }else {
                $.messager.alert("温馨提示","异常情况导致延期失败");
            }
        }
    });
}

function changeRoomHandle(pram) {
    var roomNo = $(pram).attr("data-roomNo");
    var roomPrice=$(pram).attr("data-price");
    var days=$(pram).attr("data-days");

    $('#changeRoom_dialog').dialog('open');
    $("#roomNo_changeRoom").textbox("setValue",roomNo);
    $("#days_changeRoom").textbox("setValue",days);
    $("#roomPrice_changeRoom").textbox("setValue",roomPrice);
}

function commitChangeRoomHandle() {

    var oldRoomNo = $("#roomNo_changeRoom").textbox("getValue");
    var newRoomNo = $("#newRoomNo_changeRoom").textbox("getValue");
    var priceDifference= $("#priceDifference_changeRoom").textbox("getValue");

    if(newRoomNo==""){
        $.messager.alert("温馨提示","房间号不可为空!");
    }else {
        $.messager.confirm("温馨提示","您确定为该用户换房吗?",function () {
            $.post("orderManager/changeRoomHandle_"+oldRoomNo+"_"+newRoomNo+"_"+priceDifference.toString(),{},function (res) {
                if(res.state==0){
                    $('#changeRoom_dialog').dialog('close');
                    $.messager.alert("温馨提示",res.message);
                }else {
                    $.messager.alert("温馨提示",res.message);
                }
            });
        });
    }
}