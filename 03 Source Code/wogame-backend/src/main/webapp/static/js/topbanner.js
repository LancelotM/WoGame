var Dialog = {};
$(function () {
    $("#span_url").hide();
    $("#sort_table").hide();
    $("#sort_submit").hide();
    $("#remind").hide();
    $(".sort").hover(function(){
        $("#remind").show();
    },function(){
        $("#remind").hide();
    });

    if($(".topBanner_detail tr").length > 0){
        $("#sort_table").show();
        $("#sort_submit").show();
    }else{
        $("#sort_table").hide();
        $("#sort_submit").hide();
    }

    $("#create_submit").click(function(){
        if($(".topBanner_detail tr").length == 6){
            alert("已经添加了六条数据，不可以进行添加操作！如若必须添加，可先删除一条数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#picture_input").val("");
            $("#url_input").val("")
        }else{
            $("#create_submit").removeAttr("disabled");
            var $imageName = $("#picture_input").val().trim("");
            var $url = $("#url_input").val().trim("");
            if(checkInputValue($imageName,$url)){
                $("#create_form").submit();
                $("#picture_input").val("");
                $("#url_input").val("")
            }
        }
    });

    $("#cancel").click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $("#top_dialog").hide();
    });

    $("#update_submit").click(function(){
        var $imageName = $("#dialog_picture").val().trim("");
        var $url = $("#dialog_url").val().trim("");
        if(checkInputValue($imageName,$url)){
            $("#update_form").submit();
        }
    });

    $("#sort_submit").click(function(){
        var flag = false;
        var value = 0;
        var num = null ,sort= null;
        var numString = null, sortString = null;
        var tdLen = $(".topBanner_detail tr").length;
        for(var i=0; i< 2; i++){
            for(var j=1; j<=tdLen; j++){
                if(i == 0){
                    value = $("#sort_table tr:eq("+ i +") td:eq("+ j +")").text();
                    num = value + "," + num;
                }else if(i == 1){
                    value = $("#sort_table tr:eq("+ i +") td:eq("+ j +")").find("input").val();
                    sort = value + "," + sort;
                }
            }
        }
        numString = num.substring(0,num.lastIndexOf(","));
        sortString = sort.substring(0,sort.lastIndexOf(","));

        var sortArr = sortString.split(",");
        var sortArrCopy = sortArr.sort();
        var temp = 0;
        for(var i=0; i< sortArr.length; temp=++i){
            if(sortArr[i] == sortArrCopy[i+1]){
                alert("不能输入相同的序号，请重新输入！");
                flag = false;
                return;
            }
        }
        if(temp == sortArr.length){
            flag = true;
        }
        if(flag){
            $.get(getBasePath()+"/sortbanner?numString="+numString+"&sortString="+sortString);
        }
    });

});

$(".hidden_txt, .hidden_url").mouseenter(function(){
    $(this).find("div").show();
}).mouseleave(function(){
    $(this).find("div").hide();
});

function updateBanner(id){
    maskDialog("#top_dialog");
    $.get(getBasePath()+"/topbannerinfo?id="+id,function(data,status){
        $("#dialog_picture").val(data.imageName);
        $("#dialog_url").val(data.url);
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


function delBanner(id){
    if(confirm("确定要删除这条信息吗？")){
        $.get(getBasePath()+"/delbanner?id="+id+"&type=2");
        alert("信息已删除");
        location.reload();
    }
}

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
