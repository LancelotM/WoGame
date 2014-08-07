$(function(){
    var files = new Array();
    var alertVal = new Array();
    var basepath = getBasePath();
    $('#fileField').change(function(){
        $('.add_info').remove();
        var html = '';
        files = document.getElementById("fileField").files;
        for(var i = 0;i<files.length;i++){
            var name,appid,channelId,updateType;
            //files[i] = f[i];
            var fileName = files[i].name;
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
            html += '<tr class="add_info"><td style="text-align: center;" class="check_box"><input name="checkbox" type="checkbox" /></td><td>'+name+'</td><td><input class="text" type="text" value="'+appid+'"/></td>' +
                '<td><input class="channelId_upload" type="text" value="'+channelId+'" /></td>' +
                '<td style="text-align:center;"><a class="upload_file" href="javascript:void(0);" style="white-space: nowrap;padding:2px 24px;' +
                'background: url('+basepath+'/static/images/state_yellow.png) no-repeat;color:#f7f7f7;font-weight: bold;">上传文件</a>' +
                '<input class="upload_hid" type="hidden" value="'+i+'" /></td></tr>';
        }
        $('#upload_tbody').append(html);
        $('#upload_tb').show();
        $('#upload_tb .upload_file').each(function(){
            $(this).click(function(event,type,alertValIndex){
                var currentObj = $(this);
                var input = $(this).parent().siblings().children().filter("input");
                var index = $(this).next().val();
                var fileVal = files[index];
                var fileName = fileVal.name;
                if(fileName.indexOf("@") != -1){
                    var array = fileName.split("@");
                    fileName = array[1];
                }

                var appidVal = input[1].value;
                if(isEmpty(appidVal.trim())){
                    alertVal[alertValIndex] = 1;
                    if(isEmpty(type)){
                        alert(fileName+" appid不能为空！");
                    }
                    return;
                }

                var channelVal = input[2].value;
                if(isEmpty(channelVal.trim())){
                    alertVal[alertValIndex] = 2;
                    if(isEmpty(type)){
                        alert(fileName+" channelId不能为空！");
                    }

                    return;
                }else if(channelVal.trim().length != 5 && channelVal.trim().length != 8){
                    alertVal[alertValIndex] = 3;
                    if(isEmpty(type)){
                        alert(fileName+" channelID必须是5字符或8字符！");
                    }

                    return;
                }
//                var flag = sendForm(fileVal,appidVal,channelVal);
                if(type == "allFile"){
                    if(!input[0].checked){
                        alertVal[index] = 4;
                        return;
                    }
                }else{
                    if(!input[0].checked){
                        input[0].checked = true;
                    }
                }
                var oData = new FormData(document.getElementById("upload_form"));
                oData.append("file", fileVal);
                oData.append("appid", appid);
                oData.append("channelId", channelVal);
                var oReq = new XMLHttpRequest();
                oReq.open("POST", getBasePath()+"/uploadFileHandel?"+Math.random(), true);
                oReq.onload = function(oEvent) {
                    if (oReq.status == 200 && oReq.readyState==4) {
                        var returnVal;
                        if(oReq.responseText == "true"){
                            returnVal = true;
                        }else if(oReq.responseText == "false"){
                            returnVal = false;
                        }
                        alertVal[alertValIndex] = returnVal;
                        if(returnVal){
                            currentObj.replaceWith('<a class="uploaded" href="javascript:void(0);" style="white-space: nowrap;padding:2px 24px;' +
                                'background: url('+basepath+'/static/images/state_gray.png) no-repeat;color:#f7f7f7;font-weight: bold;">已经上传</a>');
                            input[0].checked = false;
                            input[0].disabled = true;
                            input[1].disabled = true;
                            input[2].disabled = true;
                            if(isEmpty(type)){
                                alert("上传成功！");
                            }
                            return;
                        }else{
                            if(isEmpty(type)){
                                alert("上传失败！");
                            }
                        }
                    }
                };
                oReq.send(oData);
            });
        });

        $('#upload_tb .cancel_upload').each(function(){
            $(this).click(function(){
                $(this).parent().parent().remove();
                if($('#upload_tb tbody tr').length < 1){
                    $('#upload_tb').hide();
                }

            });
        });

        $('#selectAll').click(function(){
            var isChecked = $('#selectAll').attr("checked");
            if(isChecked){
                var allCheckBox = $("#upload_tb input[name='checkbox']");
                for(var i = 0;i<allCheckBox.length;i++){
                    if(!allCheckBox[i].disabled){
                        allCheckBox[i].checked = true;
                    }
                }
            }else{
                $("#upload_tb input[name='checkbox']").attr("checked",false);
            }
        });
    });
    $('#start_upload').click(function(){
        var allCheckBox = $("#upload_tb input[name='checkbox']");
        alertVal.length = 0;
        var noChecked = new Array();
        var disableds = new Array();
        var checkeds = new Array();
        for(var i = 0;i<allCheckBox.length;i++){
            if(!allCheckBox[i].checked){
                noChecked[i] = i;
                if(allCheckBox[i].disabled){
                    disableds[i] = i;
                    continue;
                }
            }else{
                checkeds[i] = i;
            }
        }
        if(noChecked.length == allCheckBox.length && noChecked.length == disableds.length){
            alert("没有需要上传的文件！");
            return;
        }else if(noChecked.length == allCheckBox.length && noChecked.length != disableds.length){
            alert("请选择文件！");
            return;
        }
        for(var x = 0;x<checkeds.length;x++){
            //alert("checkeds["+x+"]:"+checkeds[x]);
            $('.upload_file:eq('+checkeds[x]+')').trigger('click',['allFile',x]);
        }

       if(alertVal != null && alertVal.length>0){//alertVal:1代表appid是空，2代表channelId为空，3代表channelId字符个数不匹配,4代表没有选中
           var failStr = '';
           var appStr = '';
           var chaStr = '';
           var chaLength = '';
           for(var i = 0;i<checkeds.length;i++){
               //alert("alertVal["+i+"]:"+alertVal[i]);
               var fileIndex = checkeds[i];
               var file = files[fileIndex];
               var fileName = file.name;
               if(fileName.indexOf("@") != -1){
                   var array = fileName.split("@")
                   fileName = array[1];
               }
               if(alertVal[i] == 1){
                   appStr += fileName + " ";
               }else if(alertVal[i] == 2){
                   chaStr += fileName + " ";
               }else if(alertVal[i] == 3){
                   chaLength += fileName + " ";
               }else if(alertVal[i] != 4){
                   if(!alertVal[i]){
                       failStr += fileName + " ";
                   }
               }
                   }
           if(!isEmpty(appStr)){
               alert(appStr+"appid不能为空！");
           }
           if(!isEmpty(chaStr)){
               alert(chaStr+"channelId不能为空！");
           }
           if(!isEmpty(chaLength)){
               alert(chaLength+"channelID必须是5字符或8字符！");
           }
           if(!isEmpty(failStr)){
               alert(failStr+"上传失败！");
           }
        }else{
            return;
        }
    });
});

function sendForm(file,appid,channelid) {
    var returnVal;
    var oData = new FormData(document.getElementById("upload_form"));
    oData.append("file", file);
    oData.append("appid", appid);
    oData.append("channelId", channelid);
    var oReq = new XMLHttpRequest();
    oReq.open("POST", getBasePath()+"/uploadFileHandel?"+Math.random(), true);
    oReq.onload = function(oEvent) {
        if (oReq.status == 200 && oReq.readyState==4) {
            if(oReq.responseText == "true"){
                alert("oReq.responseText"+oReq.responseText);
                return oReq.responseText;
                returnVal = true;
            }else if(oReq.responseText == "false"){
                returnVal = false;
                return oReq.responseText;
            }

        }
    };
    oReq.send(oData);

}

function getFileName(file){
    var fileName = file.name;
    if(fileName.indexOf("@") != -1){
        var array = fileName.split("@")
        fileName = array[1];
    }
    return fileName;
}

