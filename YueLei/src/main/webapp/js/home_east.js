function customerMessageHandle() {
    var message=$("#message_box").textbox("getValue");
    $.post("messageManager/customerMessageHandle_"+message,{},function (res) {
        if(res=="success"){
            $.messager.alert("温馨提示","留言成功!");
            $("#message_box").textbox("setValue","");
        }else {
            $.messager.alert("温馨提示","登录超时，请重新登录");
        }
    });
}