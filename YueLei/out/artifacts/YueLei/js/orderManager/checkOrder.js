$(function () {
    loadOrderListTable();
});

function loadOrderListTable() {
    var orderListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkOrder_table_toolbar",
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
                        return "<a href='#' onclick='' data-roomNo='"
                            + row.roomNo
                            + "'>逾期</a>";
                    }
            }
        ]],
        url : "orderManager/getOrderList",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkOrder_table").datagrid(orderListDataOptions);
}

function findOrderByCondition() {

    var roomNo=$("#searchOrderByRoomNo").textbox("getValue");
    var roomType=$("#selectType_checkOrder").combobox("getValue");
    var orderStatus=$("#searchOrderByStatus").combobox("getValue");

    var findOrderByCondition = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#checkOrder_table_toolbar",
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
                    return "<a href='#' onclick='' data-roomNo='"
                        + row.roomNo
                        + "'>逾期</a>";
                }
            }
        ]],
        url : "orderManager/getOrderListByCondition_"+roomNo+"_"+roomType+"_"+orderStatus,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#checkOrder_table").datagrid(findOrderByCondition);
}