$(function () {
    loadAllTypeRoom();
});

function loadAllTypeRoom() {
    $.post("consumerBookRoom/showAllTypeRoomInfo",{},function (res) {
        var array_type = new Array();
        var array_style=new Array();
        var array_priceAndCount=new Array();
        var array_picture=new Array();

        for(var i=0;i<res.data.length;i++){
            var surplusCount=0;
            $.ajaxSettings.async = false;//将post请求变为同步
            $.post("consumerBookRoom/getSurplusRoomCount_"+res.data[i].type,{},function (count) {
                surplusCount=count;
            });
            $.ajaxSettings.async = true;//将post请求变回异步

            var div1=document.createElement("div");
            var img=document.createElement("img");
            var divInformation=document.createElement("div");
            var roomType=document.createElement("p");
            var roomStyle=document.createElement("p");
            var roomPriceAndCount=document.createElement("p");
            var checkDetail=document.createElement("button");
            checkDetail.id=i;

            var roomCoverPicture ="";
            $.ajaxSettings.async = false;//将post请求变为同步
            $.post("consumerBookRoom/getRoomCoverPictureAddress_"+res.data[i].type,{},function (res) {
                roomCoverPicture=res;
            });
            $.ajaxSettings.async = true;//将post请求变回异步

            img.src="consumerBookRoom/getImage_"+roomCoverPicture;
            img.style.width="180px";
            img.style.height="180px";
            img.style.marginLeft="10px";
            img.style.marginTop="10px";
            div1.style.width="200px";
            div1.style.height="350px";
            div1.style.border="1px solid #000";
            div1.style.marginTop="20px";
            div1.style.marginLeft="65px";
            div1.style.boxShadow="5px 5px 5px #CCFFFF";
            div1.style.borderRadius="5px";
            div1.style.display="inline-block";
            div1.appendChild(img);
            divInformation.style.width="200px";
            divInformation.style.height="100px";
            divInformation.style.marginTop="10px";
            roomType.innerHTML=res.data[i].type;
            roomType.style.textAlign="center";
            roomStyle.innerHTML=res.data[i].remarks;
            roomStyle.style.textAlign="center";
            roomPriceAndCount.innerHTML=res.data[i].price+"元/晚起"+"  仅剩"+surplusCount+"间";
            roomPriceAndCount.style.textAlign="center";
            checkDetail.innerText="查看";
            checkDetail.style.marginLeft="50px";
            checkDetail.style.width="100px";
            checkDetail.style.height="30px";
            checkDetail.onclick=function(){
                var index = this.id;
                $("#roomDetailInfo_type").html(array_type[index]);
                $("#roomDetailInfo_remarks").html(array_style[index]);
                $("#roomDetailInfo_priceAndCount").html(array_priceAndCount[index]);
                $("#roomDetailInfo_picture").attr("src","consumerBookRoom/getImage_"+array_picture[index]);
                $("#checkRoomDetailToOrder_dialog").dialog('open');
            };
            divInformation.appendChild(roomType);
            divInformation.appendChild(roomStyle);
            divInformation.appendChild(roomPriceAndCount);
            div1.appendChild(divInformation);
            div1.appendChild(checkDetail);
            $("#roomBook_cardView").append(div1);
            array_type[i]=roomType.innerHTML;
            array_style[i]=roomStyle.innerHTML;
            array_priceAndCount[i]=roomPriceAndCount.innerHTML;
            array_picture[i]=roomCoverPicture;
        }
    });
}

function previousPicture() {
    var roomType=$("#roomDetailInfo_type").text();
    var index =$("#roomDetailInfo_picture").attr("src").lastIndexOf("_");
    var currentPictureAddress=$("#roomDetailInfo_picture").attr("src").substr(index+1);

    $.post("consumerBookRoom/previousPicture_"+roomType+"_"+currentPictureAddress,{},function (res) {
        if(res!="error"){
            $("#roomDetailInfo_picture").attr("src","consumerBookRoom/getImage_"+res);
        }else{
            $("#roomDetailInfo_picture").attr("src","consumerBookRoom/getImage_"+currentPictureAddress);
        }
    });
}

function nextPicture() {
    var roomType=$("#roomDetailInfo_type").text();
    var index =$("#roomDetailInfo_picture").attr("src").lastIndexOf("_");
    var currentPictureAddress=$("#roomDetailInfo_picture").attr("src").substr(index+1);

    $.post("consumerBookRoom/nextPicture_"+roomType+"_"+currentPictureAddress,{},function (res) {
        if(res!="error"){
            $("#roomDetailInfo_picture").attr("src","consumerBookRoom/getImage_"+res);
        }else{
            $("#roomDetailInfo_picture").attr("src","consumerBookRoom/getImage_"+currentPictureAddress);
        }
    });
}

function loadOrderInitialization() {
    var roomType=$("#roomDetailInfo_type").html();
    var currentUsername="";
    $.ajaxSettings.async=false;
    $.post("consumerBookRoom/getCurrentUsername",{},function (res) {
        currentUsername=res;
    });
    $.ajaxSettings.async=true;

    $.post("consumerBookRoom/getSurplusRoomCount_"+roomType,{},function (count) {
        if(count==0){
            $.messager.alert("温馨提示","抱歉，此房型暂时没有剩余房间了");
        }else {
            $('#createOrderInfo_dialog').dialog('open');
            $("#roomType_createOrder").textbox('setValue',roomType);
            $.post("consumerBookRoom/getOrderInitializationInfo_"+roomType+"_"+currentUsername,{},function (res) {
                if(res.state==0){
                    $("#roomPrice_createOrder").textbox("setValue",res.data.roomPrice+"/晚");
                    $("#roomArea_createOrder").textbox("setValue",res.data.roomArea+"平方米");
                    $("#phone_createOrder").textbox("setValue",res.data.phone);
                }else {
                    $.messager.alert("温馨提示","此房型暂时没有空闲房间了");
                    $('#createOrderInfo_dialog').dialog('close');
                }
            });
        }
    });
}

function commitOrder() {
    var roomNo = "";
    var roomType=$("#roomType_createOrder").textbox("getValue");
    var roomPrice="";
    var roomArea="";
    var currentUsername="";
    var realName=$("#realName_createOrder").textbox("getValue");
    var phone=$("#phone_createOrder").textbox("getValue");
    var idCard=$("#idCard_createOrder").textbox("getValue");
    var startTime=$("#startDate_creatOrder").datebox("getValue");
    var endTime=$("#endDate_creatOrder").datebox("getValue");
    var days=0;
    var remarks=$("#remarks_createOrder").textbox("getValue");


    $.ajaxSettings.async=false;
    $.post("consumerBookRoom/getCurrentUsername",{},function (res) {
        currentUsername=res;
    });

    $.post("consumerBookRoom/getOrderInitializationInfo_"+roomType+"_"+currentUsername,{},function (res) {
        roomNo=res.data.roomNo;
        roomPrice=res.data.roomPrice;
        roomArea=res.data.roomArea;
    });
    $.ajaxSettings.async=true;

    var st=startTime.replace(/-/g, '/');
    var et=endTime.replace(/-/g, '/');
    var date=new Date();
    var currentTime = new Date((date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDay()).replace(/-/g, '/')).getTime();

    var ms = new Date(et).getTime()-new Date(st).getTime();
    days=Math.ceil(ms / 1000 / 24 / 60 / 60);

    $("#order_create_form").form("submit",{
        url :"consumerBookRoom/commitOrder_"+roomNo+"_"+roomType+"_"+roomPrice+"_"+roomArea+"_"+currentUsername+"_"+realName+"_"+phone+"_"+idCard+"_"+startTime+"_"+endTime+"_"+days+"_"+remarks,
        onSubmit :function () {
            if(realName=="" || phone=="" || idCard=="" || startTime == "" || endTime == ""){
                $.messager.alert("温馨提示","请完善订单信息");
                return false;
            }
            if(st >= et){
                $.messager.alert("温馨提示","入住时间不能大于或等于离开时间");
                return false;
            }
            if(new Date(st).getTime() < currentTime){
                $.messager.alert("温馨提示","入住时间不能小于当前时间");
                return false;
            }
            return true;
        },
        success :function (res) {
            if(res=="success"){
                $('#createOrderInfo_dialog').dialog('close');
                $('#checkRoomDetailToOrder_dialog').dialog('close');
                $.messager.alert("温馨提示","预定成功");
            }else {
                $.messager.alert("温馨提示","预定失败!!!!");
            }
        }
    });
}