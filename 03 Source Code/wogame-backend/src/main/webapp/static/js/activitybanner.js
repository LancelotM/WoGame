var Dialog={};
$(function () {

    $("#create_submit").click(function(){
        var $Category = $('input[name="categoryCode"]:checked').val().trim("");
        var bigNum = 0, smallNum = 0;
        var arr = $(".category");
        for(var i=0; i< arr.length; i++){
            if($(arr[i]).text().trim() == "大Banner"){
                bigNum++;
            }else if($(arr[i]).text().trim() == "小Banner"){
                smallNum++;
            }
        }
        if($Category == "bigCategory" && bigNum >= 1){
            alert("已经添加了一条大Banner数据，不可以进行添加操作！如若必须添加，可先删除一条大Banner数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#picture_input").val("");
            $("#title_input").val("");
            $("#url_input").val("");
        }else if($Category == "smallCategory" && smallNum >= 2){
            alert("已经添加了两条小Banner数据，不可以进行添加操作！如若必须添加，可先删除一条小Banner数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#picture_input").val("");
            $("#title_input").val("");
            $("#url_input").val("");
        }else if($(".detail_tb tr").length >= 4){
            alert("已经添加了三条数据，不可以进行添加操作！如若必须添加，可先删除一条数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#picture_input").val("");
            $("#title_input").val("");
            $("#url_input").val("");
        }else{
            $("#create_submit").removeAttr("disabled");
            var $picture_input = $("#picture_input").val().trim("");
            var $title_input = $("#title_input").val().trim("");
            var $url_input = $("#url_input").val().trim("");
            if(checkValue($picture_input, $title_input,$url_input)){
                $("#activityBanner_form").submit();
                $("#picture_input").val("");
                $("#title_input").val("");
                $("#url_input").val("");
            }
        }
    });

    $('#cancel').click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $('#activity_dialog').hide();
    });

    $("#update_submit").click(function(){
        var $dialog_category = $('input[name="categoryCode"]:checked').val().trim("");
        var $dialog_picture = $("#dialog_picture").val().trim("");
        var $dialog_title = $("#dialog_title").val().trim("");
        var $dialog_url = $("#dialog_url").val().trim("");
        if(checkValue1($dialog_category,$dialog_picture, $dialog_title, $dialog_url)){
            $("#activity_update_form").submit();
        }
    });
});


function updateActivityBanner(id){
    maskActivityDialog('#activity_dialog');
    $.get(getBasePath()+"/topbannerinfo?id="+id,function(data,status){
        var $categoryCode = data.position;
        if($categoryCode == 1){
            $("#dialog_bigCategory").attr("checked",true);
        }else if($categoryCode == 2){
            $("#dialog_smallCategory").attr("checked",true);
        }
        $('#dialog_picture').val(data.imageName);
        $('#dialog_title').val(data.title);
        $('#dialog_url').val(data.url);
        $("#dialog_id").val(data.id);
    });
}

maskActivityDialog = function(dialogId,absoluteHeight){
    buildActivityDiv();
    var dialog = $(dialogId);
    $(dialog).show();
}

buildActivityDiv = function(zIndex){
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
function checkValue(imageName, title, url){
    if(imageName.length == 0){
        alert("请上传图片！");
        return false;
    }else if(title.length == 0){
        alert("请输入标题！");
        return false;
    }else if(url.length == 0){
        alert("url不能为空！");
        return false;
    }else{
        return true;
    }
}

function checkValue1(category,imageName, title, url){
    if(imageName.length == 0){
        alert("请上传图片！");
        return false;
    }else if(title.length == 0){
        alert("请输入标题！");
        return false;
    }else if(category.length == 0){
        alert("请选择Banner类别！");
        return false;
    }else if(url.length == 0){
        alert("url不能为空！");
        return false;
    }else{
        return true;
    }
}

function delActivityBanner(id){
    if(confirm("确定要删除这条信息吗？")){
        $.get(getBasePath()+"/delbanner?id="+id+"&type=4");
        alert("信息已删除");
        location.reload();
    }
}