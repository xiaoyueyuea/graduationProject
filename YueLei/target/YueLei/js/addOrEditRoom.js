$(function () {
   loadRoomListTable();

    $("#room_listInfo_table").datagrid({
        onDblClickRow : function (rowIndex, rowData) {
            $("#room_edit_dialog").dialog("open");
            $("#roomNo_edit").textbox('setValue',rowData.roomNo);
            $("#roomType_edit").textbox('setValue',rowData.type);
            $("#roomPrice_edit").textbox('setValue',rowData.price);
            $("#roomArea_edit").textbox('setValue',rowData.area);
            $("#roomRemarks_edit").textbox('setValue',rowData.remarks);
        }
    });
});


function confirmAddOrEdit() {
    var roomNo = $("#room_no").textbox('getValue');
    var roomType = $("#room_type").textbox('getValue');
    var roomPrice = $("#room_price").textbox('getValue');
    var roomArea = $("#room_area").textbox('getValue');
    var roomRemarks = $("#room_remarks").textbox('getValue');

    $("#addOrEditRoomForm").form("submit",{
        url :"roomManager/addRoomAndUploadPicture_"+roomNo+"_"+roomType+"_"+roomPrice+"_"+roomArea+"_"+roomRemarks,
        onSubmit :function () {
            if(isNaN(roomNo)){
                $.messager.alert("温馨提示","请输入合法房间号");
                return false;
            }

            if(roomNo=="" || roomType=="" || roomPrice == "" || roomArea == "" || roomRemarks == ""){
                $.messager.alert("温馨提示","请输入完整信息");
                return false;
            }

            var isExit=true;
            $.ajaxSettings.async = false;//将post请求变为同步
            $.post("roomManager/checkRoomIsExit_"+roomNo,{},function (res) {
                if(res=="isExit"){
                    $.messager.alert("温馨提示","该房间已存在，请确定后重新输入！");
                    isExit=false;
                }
            });
            $.ajaxSettings.async = true;//将post请求变为异步

            return isExit;
        },
        success :function (res) {
            if(res=="success"){
                $.messager.alert("温馨提示","添加成功");
                $("#centerTabsId").tabs("close", "新增客房");
                window.location.reload();
            } else {
                $.messager.alert("温馨提示","添加失败");
            }
        }
    });
}

function loadRoomListTable() {
    var roomListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#room_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:50,align:"center",sortable:true},
            {field:"type",title:"房型",width:50,align:"center"},
            {field:"price",title:"价格",width:50,align:"center"},
            {field:"area",title:"面积",width:50,align:"center"},
            {field:"remarks",title:"备注",width:50,align:"center"},
            {field:"action1",title:"编辑",width:50,align:"center",formatter:function (value, row, index) {
                    return "<a href='#' onclick='editRoomInfoButton(this)' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.type
                        + "' data-price='"
                        + row.price
                        + "' data-area='"
                        + row.area
                        + "' data-remarks='"
                        + row.remarks + "'>编辑</a>";
                }}
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

    $("#room_listInfo_table").datagrid(roomListDataOptions);
}

function cancel() {
    $("#addOrEditRoomForm").form("clear");
}

function editRoomInfoButton(parm) {
    var roomNo=$(parm).attr('data-roomNo');
    var type=$(parm).attr('data-type');
    var price=$(parm).attr('data-price');
    var area=$(parm).attr('data-area');
    var remarks=$(parm).attr('data-remarks');
    $("#room_edit_dialog").dialog("open");
    $("#roomNo_edit").textbox('setValue',roomNo);
    $("#roomType_edit").textbox('setValue',type);
    $("#roomPrice_edit").textbox('setValue',price);
    $("#roomArea_edit").textbox('setValue',area);
    $("#roomRemarks_edit").textbox('setValue',remarks);
}

function confirmEditRoomInfo() {
    var roomNo = $("#roomNo_edit").textbox('getValue');
    var roomType = $("#roomType_edit").textbox('getValue');
    var roomPrice = $("#roomPrice_edit").textbox('getValue');
    var roomArea = $("#roomArea_edit").textbox('getValue');
    var roomRemarks = $("#roomRemarks_edit").textbox('getValue');

    $.post("roomManager/confirmEditRoomInfo_"+roomNo+"_"+roomType+"_"+roomPrice+"_"+roomArea+"_"+roomRemarks,{},function (res) {
        if(res=="success"){
            $.messager.alert("温馨提示","更新成功!");
            $("#room_edit_dialog").dialog("close");
            loadRoomListTable();
        }else {
            $.messager.alert("温馨提示","编辑失败!");
        }
    });

}

function findRoomByCondition() {
    var roomNo = $("#searchRoomByRoomNo").textbox('getValue');
    var roomType = $("#searchRoomByType").textbox('getValue');

    if (roomNo == "" && roomType == "") {
        $.messager.alert("温馨提示", "搜索条件不可全为空!");
        return;
    }

    var getRoomByCondition = {
        fit: true,
        border: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        pageSize: 20,
        pageNumber: 1,
        pageList: [20, 40, 60],
        toolbar: "#room_table_toolbar",
        striped: true,
        fitColumns: true,
        columns: [[
            {field: "roomNo", title: "房号", width: 50, align: "center", sortable: true},
            {field: "type", title: "房型", width: 50, align: "center"},
            {field: "price", title: "价格", width: 50, align: "center"},
            {field: "area", title: "面积", width: 50, align: "center"},
            {field: "remarks", title: "备注", width: 50, align: "center"},
            {field: "action1", title: "编辑", width: 50, align: "center", formatter: function (value, row, index) {
                    return "<a href='#' onclick='editRoomInfoButton(this)' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.type
                        + "' data-price='"
                        + row.price
                        + "' data-area='"
                        + row.area
                        + "' data-remarks='"
                        + row.remarks + "'>编辑</a>";
                }
            }
        ]],
        url: "roomManager/getRoomByCondition_" + roomNo + "_" + roomType,
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows
            }
        }
    };
    $("#room_listInfo_table").datagrid(getRoomByCondition);
}

function clearCondition() {
    $("#searchRoomByRoomNo").textbox('setValue',"");
    $("#searchRoomByType").textbox('setValue',"");
    loadRoomListTable();
}