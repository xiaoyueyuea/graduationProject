$(function () {
    loadRoomPictureTable();
});

function loadRoomPictureTable() {
    var getRoomPictures = {
        fit: true,
        border: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        pageSize: 20,
        pageNumber: 1,
        pageList: [20, 40, 60],
        striped: true,
        fitColumns: true,
        columns: [[
            {field: "type", title: "房型", width: 50, align: "center"},
            {field: "picture1", title: "图片1", width: 50, align: "center",formatter: function (value, row, index) {
                var str1="consumerBookRoom/getImage_"+row.picture1;
                return "<img src='"+str1+"' width='150' height='100'/>";
                }},
            {field: "picture2", title: "图片2", width: 50,align: "center",formatter:function (value, row, index) {
                    var str2="consumerBookRoom/getImage_"+row.picture2;
                    return "<img src='"+str2+"' width='150' height='100'/>";
                }},
            {field: "picture3", title: "图片3", width: 50, align: "center",formatter:function (value, row, index) {
                    var str3="consumerBookRoom/getImage_"+row.picture3;
                    return "<img src='"+str3+"' width='150' height='100'/>";
                }},
            {field: "picture4", title: "图片4", width: 50, align: "center",formatter:function (value, row, index) {
                    var str4="consumerBookRoom/getImage_"+row.picture4;
                    return "<img src='"+str4+"' width='150' height='100'/>";
                }},
            {field: "action3", title: "操作", width: 20, align: "center", formatter: function (value, row, index) {
                    return "<a href='#' onclick='updatePicture(this)' data-roomType='"
                        + row.type + "'>更新</a>";
                }
            }
        ]],
        url: "roomManager/getAllRoomPicture" ,
        method: "GET",
        loadFilter: function (data) {
            return {
                "total": data.state != 0 ? 0 : data.data.total,
                "rows": data.state != 0 ? [] : data.data.rows
            }
        }
    };
    $("#uploadPicture_table").datagrid(getRoomPictures);
}

function updatePicture(parm) {
    var roomType = $(parm).attr("data-roomType");
    $("#type_pictureUpdate").html(roomType);
    $("#picture_update_dialog").dialog('open');
}

function confirmUpdatePicture() {
    var roomType = $("#type_pictureUpdate").html();
    var file1 = $("#update_picture1").textbox('getValue');
    var file2 = $("#update_picture2").textbox('getValue');
    var file3 = $("#update_picture3").textbox('getValue');
    var file4 = $("#update_picture4").textbox('getValue');
    $("#updatePictureForm").form("submit",{
        url: "roomManager/updatePicture_"+roomType,
        onSubmit:function () {
            if(file1=="" && file2=="" && file3=="" && file4==""){
                $.messager.alert("温馨提示","您未选中任何图片!");
                return false;
            }
            return true;
        },
        success:function (res) {
            if(res=="success"){
                $("#picture_update_dialog").dialog('close');
                loadRoomPictureTable();
                $.messager.alert("温馨提示","更新成功!");
            }else {
                $.messager.alert("温馨提示","更新异常，请重新尝试!!!");
            }
        }
    });
}

function cancelUpdatePicture() {
    $('#update_picture1').textbox('setValue',"");
    $('#update_picture2').textbox('setValue',"");
    $('#update_picture3').textbox('setValue',"");
    $('#update_picture4').textbox('setValue',"");
    $("#picture_update_dialog").dialog('close');
}