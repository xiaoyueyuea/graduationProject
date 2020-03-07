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
                if(row.status=="空闲"){
                    return "<a href='#' onclick='' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.type
                        + "' data-price='"
                        + row.price
                        + "' data-area='"
                        + row.area
                        + "' data-status='"
                        + row.status + "'>入住</a>";
                } else {
                    return "<a href='#' onclick='' data-roomNo='"
                        + row.roomNo
                        + "' data-type='"
                        + row.type
                        + "' data-price='"
                        + row.price
                        + "' data-area='"
                        + row.area
                        + "' data-status='"
                        + row.status + "'>退房</a>";
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
                    if(row.status=="空闲"){
                        return "<a href='#' onclick='' data-roomNo='"
                            + row.roomNo
                            + "' data-type='"
                            + row.type
                            + "' data-price='"
                            + row.price
                            + "' data-area='"
                            + row.area
                            + "' data-status='"
                            + row.status + "'>入住</a>";
                    } else {
                        return "<a href='#' onclick='' data-roomNo='"
                            + row.roomNo
                            + "' data-type='"
                            + row.type
                            + "' data-price='"
                            + row.price
                            + "' data-area='"
                            + row.area
                            + "' data-status='"
                            + row.status + "'>退房</a>";
                    }}
            }
        ]],
        url : "roomManager/getRoomInfoToCheckByCondition_"+roomNo+"_"+roomType,
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
    $("#selectType_checkRoom").textbox('setValue',"");
    loadRoomListTableToCheck();
}