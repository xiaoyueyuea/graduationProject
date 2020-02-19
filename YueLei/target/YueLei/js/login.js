
function attemptLogin() {
    var username = $("#username").textbox('getValue');
    var password = $("#password").textbox('getValue');

    $("#loginForm").form("submit",{
        url:"loginAction_"+username+"_"+password,
        onSubmit:function () {
            if(username=="" || password==""){
                $.messager.alert("温馨提示","账号或密码为空!");
                return false;
            }
            return true;
        },
        success:function (res) {
            if(res=="true"){
                window.location.href="home";
            }else{
                $.messager.alert("温馨提示","账号或密码错误!");
            }
        }
    });
}

function insertUser() {
    var username = $("#consume_username").textbox('getValue');
    var password1 = $("#consume_password1").textbox('getValue');
    var password2 = $("#consume_password2").textbox('getValue');
    var sex=$("input[name='sex']:checked").val();
    var phone = $("#consume_phone").textbox('getValue');

    $("#consume_register_form").form("submit",{
        url : "userManager/consumeRegister_"+username+"_"+password1+"_"+sex+"_"+phone,
        onSubmit : function () {
            if(username == "" || password1 =="" || password2 == "" || phone == ""){
                $.messager.alert("温馨提示","请完善信息!");
                return false;
            }
            if(password1 != password2){
                $.messager.alert("警告","两次密码不一致!");
                return false;
            }
            return true;
        },
        success : function (res) {
            if(res=="success"){
                $.messager.alert("温馨提示","注册成功");
                $("#consume_insert_dialog").dialog("close");
            }
            if(res=="NameIsExit"){
                $.messager.alert("温馨提示","此用户已存在");
            }
            if(res=="fail"){
                $.messager.alert("温馨提示","注册失败，请重新注册!");
            }
        }

    });
}

function clearForm() {
    $("#loginForm").form("clear");
}