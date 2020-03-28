$(function () {
    $("#centerTabsId").tabs('add',{
        title:'主页',
        href:'consumerBookRoom/consumerBookRoomInformation',
        closable:false,
        iconCls:'icon-home',
        padding:'5px'
    });
});

$(function () {
    $.post("checkPermission",{},function (res) {
        if(res=="consumer"){
            $("#layout_home").layout("remove","west");
        }
    });
});

$(function () {
    loadMyOrderTable();
});

function loadMyOrderTable() {
    var username = $("#currentName").text().trim();

    var MyOrderListDataOptions = {
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : 4,
        pageNumber : 1,
        pageList : [ 4, 8, 12 ],
        striped :true,
        fitColumns : true,
        columns:[[
            {field:"roomNo",title:"房号",width:40,align:"center",hidden:true},
            {field:"roomType",title:"房型",width:40,align:"center"},
            {field:"picture",title:"图片",width:50,align:"center",formatter: function (value, row, index) {
                    var str1="consumerBookRoom/getImage_"+row.picture;
                    return "<img src='"+str1+"' width='150' height='100'/>";
                }},
            {field:"roomPrice",title:"价格",width:40,align:"center"},
            {field:"startDate",title:"入住时间",width:50,align:"center",sortable:true,formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"endDate",title:"离店时间",width:40,align:"center",sortable:true,formatter :function (value,row,index) {
                    return (new Date(value)).toLocaleDateString();
                }},
            {field:"days",title:"居住天数",width:40,align:"center"},
            {field:"status",title:"订单状态",width:40,align:"center",sortable:true},
            {field: "action6", title: "操作", width: 40, align: "center", formatter: function (value, row, index) {
                    if(row.status=="未入住"){
                        return "<a href='#' onclick='cancelOrder(this)' data-roomNo='"
                            + row.roomNo + "' data-startDate='"
                            + row.startDate + "'>退订</a>";
                    }else {
                        return "";
                    }
                }
            }
        ]],
        url : "orderManager/getMyOrderByCustomer_"+username,
        method : "GET",
        loadFilter : function(data) {
            return {
                "total" : data.state != 0 ? 0 : data.data.total,
                "rows" : data.state != 0 ? [] : data.data.rows
            }
        }
    };

    $("#myOrder_table").datagrid(MyOrderListDataOptions);
}

function cancelOrder(pram) {
    var roomNo =$(pram).attr("data-roomNo");
    var startDate=$(pram).attr("data-startDate");
    var date = new Date();
    var currentTime = new Date((date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()).replace(/-/g, '/')).getTime();
    if(startDate <= currentTime){
        $.messager.alert("温馨提示","对不起，退订时间已过，无法取消该订单");
        return;
    }
    $.messager.confirm("温馨提示","您确定要取消该订单吗?",function () {
        $.post("orderManager/cancelOrder_"+roomNo,{},function (res) {
            if(res=="success"){
                $.messager.alert("温馨提示","您的订单已成功取消");
            }else {
                $.messager.alert("温馨提示","系统异常，请联系酒店工作人员");
            }
        });
    });
}

function logout() {
    $.messager.confirm("温馨提示","您确定要退出吗?",function (r) {
        if(r){
            $.post("logout",{},function () {
            });
            window.location.href="http://localhost:8080";
        }
    })
}

function openPage(tabsId, title, iconCls, url) {
    if ($("#" + tabsId).tabs("exists", title)) {
        $("#" + tabsId).tabs("select", title);
    } else{
        $("#" + tabsId).tabs('add',{
            title : title,
            iconCls : iconCls,
            href:url,
            closable : true,
            cache : true,
            collapsible : false,
            selected : true,
        });
    }
}

function modifyPassword() {
    var oldPassword = $("#oldPassword").textbox('getValue');
    var newPassword1 = $("#newPassword1").textbox('getValue');
    var newPassword2= $("#newPassword2").textbox('getValue');

    $("#modify_password_form").form("submit",{
        url:"checkOldPassword_"+oldPassword+"_"+newPassword1,
        onSubmit:function () {
            if(oldPassword=="" || newPassword1=="" || newPassword2==""){
                $.messager.alert("温馨提示","未正确输入!",'info');
                return false;
            }
            if(newPassword1 != newPassword2){
                $.messager.alert("温馨提示","新密码输入不一致!",'info');
                return false;
            }
            if(oldPassword == newPassword1){
                $.messager.alert("温馨提示","新旧密码不能一致!",'info');
                return false;
            }
            return true;
        },
        success:function (res) {
            if(res=="fail"){
                $.messager.alert("温馨提示","旧密码输入错误!",'info');
            }
            if(res=="success"){
                $.messager.alert("温馨提示","修改成功，请重新登录!",'info',function () {
                    window.location.href="http://localhost:8080";
                });
            }
        }
    });
}