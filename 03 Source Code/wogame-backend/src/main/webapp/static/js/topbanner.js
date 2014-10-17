var Dialog = {};
$(function () {
    $("#remind").hide();
    $(".detail_tb").hover(function(){
        $("#remind").show();
    },function(){
        $("#remind").hide();
    })

    $(".delbtn").click(function(){
        $(this).closest("tr").remove();
    });

/*    $(".topBanner_detail").dragsort({
        itemSelector: "tr",
        dragSelector: "tr",
        dragBetween: true,
        placeHolderTemplate: "<tr></tr>"
    });
;*/

});



function updateBanner(id){
    maskDialog('#dialog');
    $.get(getBasePath()+"/topbannerinfo?id="+id,function(data,status){
        alert(data.imageName);
        alert(data.url);
        $('#dialog_picture').val(data.imageName);
        $('#dialog_url').val(data.url);
        $("#dialog_id").val(data.id);
    });
}

maskDialog = function(dialogId,absoluteHeight){
    buildMaskDiv();
    var dialog = $(dialogId);
    $(dialog).show();
}

buildMaskDiv = function(zIndex){
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


$('#cancel').click(function(){
    document.body.removeChild(Dialog.maskLayer);
    $('#dialog').hide();
});

$('#update_submit').click(function(){
    var $imageName = $('#dialog_picture').val().trim("");
    var $url = $('#dialog_url').val().trim("");
    if(checkInputValue($imageName,$url)){
        $('#update_form').submit();
    }
});

function delBanner(id){
    $.get(getBasePath()+"/deltopbanner?id="+id);
}

$("#create_submit").click(function(){
    var $imageName = $('#picture_input').val().trim("");
    var $url = $('#url_input').val().trim("");
    if(checkInputValue($imageName,$url)){
        $("#create_form").submit();
    }
});

function checkInputValue(imageName,url){
    if(imageName.length == 0){
        alert("请上传图片！");
        return false;
    }else if(url.length == 0){
        alert("url不能为空！");
        return false;
    }else{
        return true;
    }
}
