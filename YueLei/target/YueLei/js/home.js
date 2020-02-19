$(function () {
    $("#centerTabsId").tabs('add',{
        title:'主页',
        content:'home',
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