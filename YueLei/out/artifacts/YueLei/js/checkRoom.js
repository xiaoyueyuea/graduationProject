$(function () {
    loadRoomListTableToCheck();
});


function loadRoomListTableToCheck() {
    var roomListDataOptionsForCheck = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkRoom_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center",sortable:true},
            {field:"type",title:"房型",width:50,align:"center"},
            {field:"price",title:"价格",width:50,align:"center"},
            {field:"area",title:"面积",width:50,align:"center"},
            {field:"status",title:"状态",width:50,align:"center"},
            {field:"action2",title:"操作",width:50,align:"center",formatter:function (value, row, index) {
                if(row.status=="使用中"){
                    return "<a href='#' onclick='checkOut(this)' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.type
                        + "' data-price='"
                        + row.price
                        + "' data-area='"
                        + row.area
                        + "' data-status='"
                        + row.status + "'>退房</a>";
                } else {
                    return "<a href='#' onclick='checkIn(this)' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.type
                        + "' data-price='"
                        + row.price
                        + "' data-area='"
                        + row.area
                        + "' data-status='"
                        + row.status + "'>入住</a>";
                }}
                }
        ]],
        url : "roomManager/getRoomList",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkRoom_table").datagrid(roomListDataOptionsForCheck);
}

function findRoomByConditionToCheck() {
    var roomNo = $("#searchRoomByRoomNoToCheck").textbox('getValue');
    var roomType = $("#selectType_checkRoom").combobox('getValue');
    var roomStatus=$("#selectStatus_checkRoom").combobox('getValue')

    if(roomNo=="" && roomType=="" && roomStatus==""){
        $.messager.alert("温馨提示","搜索条件不可全为空");
        return;
    }

    var roomListDataOptionsForCheckByCondition = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkRoom_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center",sortable:true},
            {field:"type",title:"房型",width:50,align:"center"},
            {field:"price",title:"价格",width:50,align:"center"},
            {field:"area",title:"面积",width:50,align:"center"},
            {field:"status",title:"状态",width:50,align:"center"},
            {field:"action2",title:"操作",width:50,align:"center",formatter:function (value, row, index) {
                    if(row.status=="使用中"){
                        return "<a href='#' onclick='checkOut(this)' data-roomNo='"
                            + row.roomNo
                            + "' data-type='"
                            + row.type
                            + "' data-price='"
                            + row.price
                            + "' data-area='"
                            + row.area
                            + "' data-status='"
                            + row.status + "'>退房</a>";
                    } else {
                        return "<a href='#' onclick='checkIn(this)' data-roomNo='"
                            + row.roomNo
                            + "' data-type='"
                            + row.type
                            + "' data-price='"
                            + row.price
                            + "' data-area='"
                            + row.area
                            + "' data-status='"
                            + row.status + "'>入住</a>";
                    }}
            }
        ]],
        url : "roomManager/getRoomInfoToCheckByCondition_"+roomNo+"_"+roomType+"_"+roomStatus,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkRoom_table").datagrid(roomListDataOptionsForCheckByCondition);
}

function clearConditionToCheck() {
    $("#searchRoomByRoomNoToCheck").textbox('setValue',"");
    $("#selectType_checkRoom").combobox('setValue',"");
    $("#selectStatus_checkRoom").combobox('setValue',"");
    loadRoomListTableToCheck();
}

//办理入住
function checkIn(pram) {
    var roomNo=$(pram).attr("data-roomNo");
    var roomType=$(pram).attr("data-type");
    var roomPrice=$(pram).attr("data-price");
    var roomArea=$(pram).attr("data-area");
    var roomStatus=$(pram).attr("data-status");

    $.messager.confirm("温馨提示","您确定为"+roomNo+"房间办理入住吗?",function () {
        if(roomStatus=="已预订"){
            $.post("orderManager/checkIn_"+roomNo,{},function (res) {
                if(res=="success"){
                    $.messager.alert("温馨提示","入住成功！！");
                }else {
                    $.messager.alert("温馨提示","办理失败！！！！");
                }
            });
        }else {
            $("#notOrderCustomer_checkIn_dialog").dialog('open');
            $("#roomNo_notOrderCustomer_checkIn").textbox("setValue",roomNo);
            $("#roomType_notOrderCustomer_checkIn").textbox("setValue",roomType);
            $("#roomPrice_notOrderCustomer_checkIn").textbox("setValue",roomPrice+" 元/晚");
            $("#roomArea_notOrderCustomer_checkIn").textbox("setValue",roomArea+" 平方米");
        }
    });
}

//顾客未预订，到店办理
function employeeCommitOrderForCustomer() {
    var roomNo=$("#roomNo_notOrderCustomer_checkIn").textbox("getValue");
    var roomType=$("#roomType_notOrderCustomer_checkIn").textbox("getValue");
    var index1=$("#roomPrice_notOrderCustomer_checkIn").textbox("getValue").lastIndexOf(" ");
    var roomPrice=$("#roomPrice_notOrderCustomer_checkIn").textbox("getValue").substring(0,index1);
    var index2=$("#roomArea_notOrderCustomer_checkIn").textbox("getValue").lastIndexOf(" ");
    var roomArea=$("#roomArea_notOrderCustomer_checkIn").textbox("getValue").substring(0,index2);
    var currentName="到店办理客户";
    var customerName=$("#realName_notOrderCustomer_checkIn").textbox("getValue");
    var phone=$("#phone_notOrderCustomer_checkIn").textbox("getValue");
    var id_card=$("#idCard_notOrderCustomer_checkIn").textbox("getValue");
    var startDate=$("#startDate_notOrderCustomer_checkIn").datebox("getValue");
    var endDate=$("#endDate_notOrderCustomer_checkIn").datebox("getValue");
    var days=0;
    var remarks=$("#remarks_notOrderCustomer_checkIn").textbox("getValue");

    var sd=startDate.replace(/-/g, '/');
    var ed=endDate.replace(/-/g, '/');
    var currentTime=new Date().getTime();

    var ms = new Date(ed).getTime()-new Date(sd).getTime();
    days=Math.ceil(ms / 1000 / 24 / 60 / 60);

    $("#notOrderCustomer_checkIn_form").form("submit",{
        url :"consumerBookRoom/notOrderCheckedIn_"+roomNo+"_"+roomType+"_"+roomPrice+"_"+roomArea+"_"+currentName+"_"+customerName+"_"+phone+"_"+id_card+"_"+startDate+"_"+endDate+"_"+days+"_"+remarks,
        onSubmit :function () {
            if(customerName=="" || phone=="" || id_card=="" || startDate == "" || endDate == ""){
                $.messager.alert("温馨提示","请完善订单信息");
                return false;
            }
            if(sd >= ed){
                $.messager.alert("温馨提示","入住时间不能大于或等于离开时间");
                return false;
            }
            if(new Date(sd).getTime() < currentTime){
                $.messager.alert("温馨提示","入住时间不能小于当前时间");
                return false;
            }
            return true;
        },
        success :function (res) {
            if(res=="success"){
                $('#notOrderCustomer_checkIn_dialog').dialog('close');
                $.messager.alert("温馨提示","预定成功");
            }else {
                $.messager.alert("温馨提示","预定失败!!!!");
            }
        }
    });
}

//办理退房
function checkOut(pram) {
    $('#checkOutConfirm_dialog').dialog('open');
    var roomNo=$(pram).attr("data-roomNo");
    var roomType=$(pram).attr("data-type");
    $("#roomNo_checkOutConfirm").textbox("setValue",roomNo);
    $("#roomType_checkOutConfirm").textbox("setValue",roomType);
}

function confirmCheckOut() {
    var roomNo = $("#roomNo_checkOutConfirm").textbox("getValue");
    var otherCost = $("#otherCost_checkOutConfirm").textbox("getValue");

    $.messager.confirm("温馨提示","您确定为"+roomNo+"房间办理退房手续吗?",function () {
        $.post("orderManager/checkOut_"+roomNo+"_"+otherCost,{},function (res) {
            if(res=="success"){
                $('#checkOutConfirm_dialog').dialog('close');
                $.messager.alert("温馨提示","退房成功！！");
            }else {
                $.messager.alert("温馨提示","退房失败！！！！");
            }
        });
    });
}