
var submitFlag = true;
$(function(){
//    $.ajax({
//        type: "GET",
//        url: getBasePath()+"/getChannels?"+Math.random(),
//        dataType: "json",
//        success: function(data){
//            $('.channelLi').remove();
//            var html = "";
//            $.each(data, function(commentIndex, comment){
//
//                html += '<li class="channelLi"><span>'+comment['key']+'</span>';
//                $.each(comment['channels'], function(commentIndex, innerData){
//                    html += '<a href="javascript:;">'+innerData+'</a>'
//                });
//                html += '</li>';
//            });
//            $('#chnanels').append(html);
//        }
//    });

    $('#province_div a').click(function(){
            var basePath = getBasePath();
            $('.launch_img').remove();
            $.ajax({
                url:basePath+"/getChaByName?"+Math.random(),
                data:{channelName:$(this).text()},
                async:false,
                type:"POST",
                success:function(data,status){
                    if(data.flag){
                        submitFlag = false;
//                        $('#launch_img').attr('src',basePath+'/static/images/launched.png');
                        $('#launch').append('<img class="launch_img" src="'+basePath+'/static/images/launched.png" alt="launched"/>');
                        $('#channelId_input').val(data.channelCode);
                        $('#cpid_input').val(data.cpId);
                        $('#wapURL').text(data.wapToken) ;
                        $('#logURL').text(data.logToken);
                    }else{
                        submitFlag = true;
                        $('#launch').append('<img class="launch_img" src="'+basePath+'/static/images/launch.png" alt="launch"/>');
//                        $('#launch_img').attr('src',basePath+'/static/images/launch.png');
                        $('#channelId_input').val("channelID");
                        $('#cpid_input').val("CPID");
                        $('#wapURL').text("") ;
                        $('#logURL').text("");
                        $("#launch_form input").each(function(){
                            $(this).setDefauleValue();
                        });
                    }
                    $('#launch_form input').trigger('focus');
                    $('#launch_form input').trigger('blur');
                }
            });
            var dispayVal = $('#province_div').css('display');
            if(dispayVal=="block"){
                $('#province_div').css('display','none');
            }
            $('#channel').text($(this).text());
            $('#channelName').val($(this).text());
        }
    );
    $('#launch').click(function(){
        if(submitFlag){
//            var chaId = $('#channelId_input').val().trim();
//            var cpid = $('#cpid_input').val().trim();
//            if(chaId == 'channelID' || chaId == "" || chaId == null){
//                alert("channelID不能为空！");
//                return;
//            }else if(chaId.length != 5 && chaId.length != 8){
//                alert("channelID必须是5字符或8字符！");
//                return;
//            }else if(cpid == 'CPID' || cpid == "" || cpid == null){
//                alert("CPID不能为空！");
//                return;
//            }
//            $('#launch_form').submit();
            var chaId = $('#channelId_input').val().trim();
            var cpid = $('#cpid_input').val().trim();
            if(checkChaIdAndCpid(chaId,cpid)){
                $('#launch_form').submit();
            }
        }
    });
    $('#cancel').click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $('#dialog').hide();
    });
    $('#update_submit').click(function(){
        var chaId = $('#dialog_chaId').val().trim();
        var cpid = $('#dialog_cpid').val().trim();
        if(checkChaIdAndCpid(chaId,cpid)){
            $('#update_form').submit();
        }
    });

    $('#switch_region').mouseenter(function(){
        if($('#province_div').css('display')=="none"){
            $('#province_div').css('display','block');
        }
    });

    $('#switch_region').mouseleave(function(){
        if($('#province_div').css('display')=="block"){
            $('#province_div').css('display','none');
        }
    });
});

function getDetailInfo(id){
    createForm(getBasePath()+'/getlog',id,'1');
}

function createForm(url,channel,date){
    var infoForm = document.createElement("form");
    infoForm.method="POST" ;
    infoForm.action = url;
    var channelIdInput = document.createElement("input") ;
    channelIdInput.setAttribute("name", "channelId") ;
    channelIdInput.setAttribute("value", channel);
    infoForm.appendChild(channelIdInput) ;

    var dateInput = document.createElement("input") ;
    dateInput.setAttribute("name", "type") ;
    dateInput.setAttribute("value", date);
    infoForm.appendChild(dateInput) ;

    document.body.appendChild(infoForm) ;
    infoForm.submit() ;
    document.body.removeChild(infoForm) ;
}

function getUpdateInfo(channelId){
    mask('#dialog');
    $.get(getBasePath()+"/getChannel?channelId="+channelId,function(data,status){
        $('#chaId').attr('value',data.channelId);
        $('#dialog_chaName').text(data.channelName);
        $('#dialog_chaId').attr('value',data.channelCode);
        $('#dialog_cpid').attr('value',data.cpId);
    });
}

//设置input,textarea默认值
$.fn.setDefauleValue = function() {
    var defauleValue = $(this).val();
    $(this).val(defauleValue).css("color","#999");

    return this.each(function() {
        $(this).focus(function() {
            if ($(this).val() == defauleValue) {
                $(this).val("").css("color","#000000");//输入值的颜色
            }
        }).blur(function() {
                if ($(this).val() == "") {
                    $(this).val(defauleValue).css("color","#999");//默认值的颜色
                }
            });
    });
}

function checkChaIdAndCpid(chaId,cpid){
    if(chaId == 'channelID' || chaId == "" || chaId == null){
        alert("channelID不能为空！");
        return false;
    }else if(chaId.length != 5 && chaId.length != 8){
        alert("channelID必须是5字符或8字符！");
        return false;
    }else if(cpid == 'CPID' || cpid == "" || cpid == null){
        alert("CPID不能为空！");
        return false;
    }else{
        return true;
    }
}




