var Dialog={};
$(function(){

    $("#create_submit").click(function(){
        if($(".detail_tb tr").length == 3){
            alert("已经添加了两条数据，不可以进行添加操作！如若必须添加，可先删除一条数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#picture_input").val("");
            $("#title_input").val("");
            $("#introduce_input").val("");
            $("#url_input").val("");
        }else{
            $("#create_submit").removeAttr("disabled");
            var $title_input = $("#title_input").val().trim("");
            var $picture_input = $("#picture_input").val().trim("");
            var $introduce_input = $("#introduce_input").val().trim("");
            var $url_input = $("#url_input").val().trim("");
            if(checkValue($picture_input, $title_input, $introduce_input, $url_input)){
                $("#module_form").submit();
                $("#picture_input").val("");
                $("#title_input").val("");
                $("#introduce_input").val("");
                $("#url_input").val("");
            }
        }
    });

    $('#cancel').click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $('#module_dialog').hide();
    });

    $("#update_submit").click(function(){
        var $dialog_title = $("#dialog_title").val().trim("");
        var $dialog_picture = $("#dialog_picture").val().trim("");
        var $dialog_introduce = $("#dialog_introduce").val().trim("");
        var $dialog_url = $("#dialog_url").val().trim("");
        if(checkValue($dialog_picture, $dialog_title, $dialog_introduce, $dialog_url)){
            $("#module_update_form").submit();
        }
    });


    $(".hidden_title, .hidden_imageName, .hidden_description, .hidden_url").mouseenter(function(){
        $(this).find("div").show();
    }).mouseleave(function(){
        $(this).find("div").hide();
    });
});

function updateModuleBanner(id){
    maskModuleDialog('#module_dialog');
    $.get(getBasePath()+"/topbannerinfo?id="+id,function(data,status){
        $('#dialog_picture').val(data.imageName);
        $('#dialog_title').val(data.title);
        $('#dialog_introduce').val(data.description);
        $('#dialog_url').val(data.url);
        $("#dialog_id").val(data.id);
    });
}

maskModuleDialog = function(dialogId,absoluteHeight){
    buildModuleDiv();
    var dialog = $(dialogId);
    $(dialog).show();
}

buildModuleDiv = function(zIndex){
    Dialog.maskLayer = document.createElement("div");
    Dialog.maskLayer.style.position = "absolute";
    if(zIndex){
        Dialog.maskLayer.style.zIndex = zIndex;
    }else{
        Dialog.maskLayer.style.zIndex = "9999996";
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
function checkValue(imageName, title, introduce, url){
    if(imageName.length == 0){
        alert("请上传图片！");
        return false;
    }else if(title.length == 0){
        alert("请输入标题！");
        return false;
    }else if(introduce.length == 0){
        alert("请输入文字介绍！");
        return false;
    }else if(url.length == 0){
        alert("url不能为空！");
        return false;
    }else{
        return true;
    }
}

function delModuleBanner(id){
    if(confirm("确定要删除这条信息吗？")){
        $.get(getBasePath()+"/delbanner?id="+id+"&type=3");
        alert("信息已删除");
        location.reload();
    }
}
