$(function () {

    $.post("consumerBookRoom/showAllTypeRoomInfo",{},function (res) {
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
            var roomCount=document.createElement("p");
            var checkDetail=document.createElement("button");

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
            divInformation.appendChild(roomType);
            divInformation.appendChild(roomStyle);
            divInformation.appendChild(roomPriceAndCount);
            divInformation.appendChild(roomCount);
            div1.appendChild(divInformation);
            div1.appendChild(checkDetail);
            $("#roomBook_cardView").append(div1);
        }
    });
});