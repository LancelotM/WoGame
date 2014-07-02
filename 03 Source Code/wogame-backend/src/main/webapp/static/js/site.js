var Dialog = {};
$(function(){
    $('#province_div a').click(function(){
        var basePath = getBasePath();
        $.post(basePath+"/getChaByName",{channelName:$(this).text()},function(data,status){
            if(data.flag){
                $('#launch_img').attr('src',basePath+'/static/images/launched.png');
                $('#channelId_input').val(data.channelCode);
                $('#cpid_input').val(data.cpId);
                $('#wapURL').text(data.wapToken) ;
                $('#logURL').text(data.logToken);
            }else{
                $('#launch_img').attr('src',basePath+'/static/images/launch.png');
                $('#channelId_input').val("");
                $('#cpid_input').val("");
                $('#wapURL').text("") ;
                $('#logURL').text("");
            }
        });
        var dispayVal = $('#province_div').css('display');
        if(dispayVal=="block"){
            $('#province_div').css('display','none');
        }
        $('#channel').text($(this).text());
        $('#channelName').val($(this).text());
    });

    $('#launch').click(function(){
        $('#launch_form').submit();
    });
    $('#cancel').click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $('#dialog').hide();
    });
    $('#update_submit').click(function(){
        $('#update_form').submit();
    });
    update_submit
});

function getBasePath(){
    return $('#basePath').attr('value');
}

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


mask = function(dialogId,absoluteHeight){
    buildMask();
    var dialog = $(dialogId);
    $(dialog).show();
}

buildMask = function(zIndex){
    Dialog.maskLayer = document.createElement("div");
    Dialog.maskLayer.style.position = "absolute";
    if(zIndex){
        Dialog.maskLayer.style.zIndex = zIndex;
    }else{
        Dialog.maskLayer.style.zIndex = "999999";
    }
    var _scrollWidth = Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);
    var _scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    Dialog.maskLayer.style.width = _scrollWidth + "px";
    Dialog.maskLayer.style.height = _scrollHeight + "px";
    Dialog.maskLayer.style.top = "0px";
    Dialog.maskLayer.style.left = "0px";
    Dialog.maskLayer.style.background = "#33393C";
    Dialog.maskLayer.style.filter = "alpha(opacity=40)";
    Dialog.maskLayer.style.opacity = "0.40";
    document.body.appendChild(Dialog.maskLayer);
}

function iptBulur(){

}

function iptFocus(){

}

