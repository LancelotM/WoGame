$(function(){
    var f;
    var files = new Array();
    var alertVal;
    var basepath = getBasePath();
    $('#cancel').click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $('#dialog').hide();
    });
    $('#fileField').change(function(){
        $('.add_info').remove();
        var html = '';
        f = document.getElementById("fileField").files;
        if(f.length>5){
            alert("最多上传5个文件！");
            return;
        }else{
            for(var i = 0;i<f.length;i++){
                var name,appid,channelId,updateType;

                files[i] = f[i];
                var fileName = f[i].name;
                if(fileName.indexOf("@") != -1){
                    var array = fileName.split("@")
                    name = array[1];
                    var dataArray = array[0].split("&");
                    appid = dataArray[0];
                    channelId = dataArray[1];
                    updateType = dataArray[2];
                }else{
                    name = fileName;
                    appid = "";
                    channelId = "";
                    updateType = "";
                }
                html += '<tr class="add_info"><td>'+name+'</td><td><input class="upload_appid" type="text" value="'+appid+'"/></td>' +
                    '<td><input class="channelId_upload" type="text" value="'+channelId+'" /></td>' +
                    '<td><a href="javascript:void(0);" style="white-space: nowrap;padding:2px 24px;' +
                    'background: url('+basepath+'/static/images/state_yellow.png) no-repeat;color:#f7f7f7;font-weight: bold;">上传文件</a></td>' +
                    '<td><input class="upload_hid" type="hidden" value="'+i+'" /></td></tr>';
            }
            $('#upload_table').append(html);
            mask('#dialog');
            $('#upload_table a').each(function(){
                $(this).click(function(type){
                    var input = $(this).parent().siblings().children().filter("input");
                    var appidVal = input[0].value;
                    if(isEmpty(appidVal.trim())){
                        alert("appid不能为空！");
                        return;
                    }

                    var channelVal = input[1].value;
                    if(isEmpty(channelVal.trim())){
                        alert("channelId不能为空！");
                        return;
                    }else if(channelVal.trim().length != 5 && channelVal.trim().length != 8){
                        alert("channelID必须是5字符或8字符！");
                        return;
                    }

                    var index = input[2].value;
                    var fileVal = files[index];
                    alertVal = sendForm(fileVal,appidVal,channelVal);
                    if(type != 'allFile' && alertVal){
                       alert("上传成功！");
                    }
                });
            });
        }
    });
    $('#start_upload').click(function(){
        $('#upload_table a').trigger('click','allFile');
        if(alertVal){
           alert("上传成功！");
        }
    });
});

function getDialog(){
    mask('#dialog');
}

function sendForm(file,appid,channelid) {
    var returnVal;
    var oData = new FormData(document.getElementById("upload_form"));
    oData.append("file", file);
    oData.append("appid", appid);
    oData.append("channelid", channelid);
    var oReq = new XMLHttpRequest();
    oReq.open("POST", getBasePath()+"/uploadFileHandel", true);
    oReq.onload = function(oEvent) {
        if (oReq.status == 200 && oReq.readyState==4) {
            returnVal = oReq.responseText;
        }
    };
    oReq.send(oData);
//    $.ajax({
//        url: getBasePath()+"/uploadFileHandel",
//        type: "POST",
//        data: oData,
//        processData: false,  // 告诉jQuery不要去处理发送的数据
//        contentType: false   // 告诉jQuery不要去设置Content-Type请求头
//    });
    return returnVal;
}

function uploadFile(path,channel,appid){
    var infoForm = document.createElement("form");
    infoForm.method="POST" ;
    infoForm.enctype="multipart/form-data";
    infoForm.action = getBasePath()+"/uploadFileHandel";
    var channelIdInput = document.createElement("input") ;
    channelIdInput.setAttribute("name", "channelId") ;
    channelIdInput.setAttribute("value", channel);
    infoForm.appendChild(channelIdInput) ;

    var dateInput = document.createElement("input") ;
    dateInput.setAttribute("name", "appid") ;
    dateInput.setAttribute("value", appid);
    infoForm.appendChild(dateInput) ;

    var fileInput = document.createElement("input") ;
    fileInput.setAttribute("type","file");
    fileInput.setAttribute("name", "appid") ;
    fileInput.setAttribute("value", path);
    fileInput.appendChild(fileInput) ;

    document.body.appendChild(infoForm) ;
    infoForm.submit() ;
    document.body.removeChild(infoForm) ;
}