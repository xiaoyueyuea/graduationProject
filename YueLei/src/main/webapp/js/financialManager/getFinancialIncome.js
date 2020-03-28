$(function () {
    loadGetFinancialIncomeTable();
});

function loadGetFinancialIncomeTable() {
    var financialIncomeDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#financialIncome_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"orderId",title:"订单号",width:50,align:"center"},
            {field:"roomType",title:"房型",width:50,align:"center"},
            {field:"roomPrice",title:"价格",width:50,align:"center"},
            {field:"startDate",title:"开始时间",width:50,align:"center",formatter :function (value,row,index) {
                    if(value==null){
                        return value;
                    }
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"结束时间",width:50,align:"center",formatter :function (value,row,index) {
                    if(value==null){
                        return value;
                    }
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:50,align:"center"},
            {field:"roomCharge",title:"房费",width:50,align:"center"},
            {field:"priceDifference",title:"换房差价",width:50,align:"center"},
            {field:"otherCost",title:"其它消费",width:50,align:"center"},
            {field:"totalCost",title:"总收益",width:50,align:"center",formatter:function (value, row, index) {
                return (parseFloat(row.roomCharge)+parseFloat(row.priceDifference)+parseFloat(row.otherCost));
                }
            }
        ]],
        onLoadSuccess : computeTotal,
        url : "financialManager/getAllFinancialIncome",
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#getFinancialIncome_table").datagrid(financialIncomeDataOptions);
}

function loadGetFinancialIncomeTableByCondition() {
    var startDate = $("#getFinancialIncomeByStartDate").datebox("getValue");
    var endDate = $("#getFinancialIncomeByEndDate").datebox("getValue");
    var roomType = $("#selectType_getFinancialIncome").combobox("getValue");
    if(startDate=="" && endDate=="" && roomType==""){
        $.messager.alert("温馨提示","搜索条件不可全为空");
        return;
    }

    var financialIncomeDataOptionsByCondition = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 20,
        pageNumber : 1,
        pageList : [ 20, 40, 60 ],
        toolbar : "#financialIncome_table_toolbar",
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"orderId",title:"订单号",width:50,align:"center"},
            {field:"roomType",title:"房型",width:50,align:"center"},
            {field:"roomPrice",title:"价格",width:50,align:"center"},
            {field:"startDate",title:"开始时间",width:50,align:"center",formatter :function (value,row,index) {
                    if(value==null){
                        return value;
                    }
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"结束时间",width:50,align:"center",formatter :function (value,row,index) {
                    if(value==null){
                        return value;
                    }
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:50,align:"center"},
            {field:"roomCharge",title:"房费",width:50,align:"center"},
            {field:"priceDifference",title:"换房差价",width:50,align:"center"},
            {field:"otherCost",title:"其它消费",width:50,align:"center"},
            {field:"totalCost",title:"总收益",width:50,align:"center",formatter:function (value, row, index) {
                    return (parseFloat(row.roomCharge)+parseFloat(row.priceDifference)+parseFloat(row.otherCost));
                }
            }
        ]],
        onLoadSuccess : computeTotal,
        url : "financialManager/getAllFinancialIncomeByCondition_"+startDate+"_"+endDate+"_"+roomType,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#getFinancialIncome_table").datagrid(financialIncomeDataOptionsByCondition);

}

function clearCondition() {
    $("#getFinancialIncomeByStartDate").datebox("setValue","");
    $("#getFinancialIncomeByEndDate").datebox("setValue","");
    $("#selectType_getFinancialIncome").combobox("setValue","");
    loadGetFinancialIncomeTable();
}

function computeTotal() {
    var rows = $('#getFinancialIncome_table').datagrid('getRows');//获取当前的数据行
    var totalRoomCharge = 0;
    var totalPriceDifference = 0;
    var totalOtherCost = 0;
    for (var i = 0; i < rows.length; i++) {
        totalRoomCharge += parseFloat(rows[i]['roomCharge']);
        totalPriceDifference += parseFloat(rows[i]['priceDifference']);
        totalOtherCost += parseFloat(rows[i]['otherCost']);
    }
//新增一行显示统计信息
    $('#getFinancialIncome_table').datagrid('appendRow', { orderId: '<b>合计：</b>',roomCharge :totalRoomCharge, priceDifference : totalPriceDifference,otherCost : totalOtherCost});
}