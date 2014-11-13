var Dialog={};
var before_category;
$(function () {
    initEvent();
    $("#random_input").val(Math.random()*99+1);

    $("#create_submit").click(function(){
        var $Category = $.trim($('input[name="category"]:checked').val());
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
            $("#bigCategory").removeAttr("checked");
            $("#smallCategory").removeAttr("checked");
            $("#picture_input").val("");
            $("#introduce_input").val("");
            $("#url_input").val("");
        }else if($Category == "smallCategory" && smallNum >= 2){
            alert("已经添加了两条小Banner数据，不可以进行添加操作！如若必须添加，可先删除一条小Banner数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#bigCategory").removeAttr("checked");
            $("#smallCategory").removeAttr("checked");
            $("#picture_input").val("");
            $("#introduce_input").val("");
            $("#url_input").val("");
        }else if($(".detail_tb tr").length >= 4){
            alert("已经添加了三条数据，不可以进行添加操作！如若必须添加，可先删除一条数据后再添加");
            $("#create_submit").attr("disabled",true);
            $("#bigCategory").removeAttr("checked");
            $("#smallCategory").removeAttr("checked");
            $("#picture_input").val("");
            $("#introduce_input").val("");
            $("#url_input").val("");
        }else{
            $("#create_submit").removeAttr("disabled");
            var $picture_input = $.trim($("#picture_input").val());
            var $introduce_input = $.trim($("#introduce_input").val());
            var $url_input = $.trim($("#url_input").val());
            if(checkValue1($Category, $picture_input, $introduce_input,$url_input)){
                $("#activityBanner_form").submit();
                $("#bigCategory").removeAttr("checked");
                $("#smallCategory").removeAttr("checked");
                $("#picture_input").val("");
                $("#introduce_input").val("");
                $("#url_input").val("");
            }
        }
    });

    $('#cancel').click(function(){
        document.body.removeChild(Dialog.maskLayer);
        $('#activity_dialog').hide();
    });

    $("#update_submit").click(function(){
        var categoryBefore  = before_category;
        var $dialog_category = $.trim($('input[name="category"]:checked').val());
        var bigNum = 0, smallNum = 0;
        var arr = $(".category");
        for(var i=0; i< arr.length; i++){
            if($(arr[i]).text().trim() == "大Banner"){
                bigNum++;
            }else if($(arr[i]).text().trim() == "小Banner"){
                smallNum++;
            }
        }
        if(categoryBefore == 2 &&$dialog_category == "bigCategory" && bigNum == 1){
            alert("已经添加了一条大Banner数据，不可以进行修改操作！如若必须添加，可先删除一条大Banner数据后再修改");
            var $dialog_picture = $.trim($("#dialog_picture").val());
            var $dialog_introduce = $.trim($("#dialog_introduce").val());
            var $dialog_url = $.trim($("#dialog_url").val());
            $("#dialog_smallCategory").attr("checked","true");
            $("#dialog_bigCategory").removeAttr("checked");
            if(checkValue($dialog_picture, $dialog_introduce, $dialog_url)){
                $("#activity_update_form").submit();
                $("#dialog_bigCategory").removeAttr("checked");
                $("#dialog_smallCategory").removeAttr("checked");
            }
        }else if(categoryBefore == 1 && $dialog_category == "smallCategory" && smallNum == 2){
            alert("已经添加了两条小Banner数据，不可以进行修改操作！如若必须添加，可先删除一条小Banner数据后再修改");
            var $dialog_picture = $.trim($("#dialog_picture").val());
            var $dialog_introduce = $.trim($("#dialog_introduce").val());
            var $dialog_url = $.trim($("#dialog_url").val());
            $("#dialog_bigCategory").attr("checked","true");
            $("#dialog_smallCategory").removeAttr("checked");
            if(checkValue($dialog_picture, $dialog_introduce, $dialog_url)){
                $("#activity_update_form").submit();
                $("#dialog_bigCategory").removeAttr("checked");
                $("#dialog_smallCategory").removeAttr("checked");
            }
        }else{
            var $dialog_picture = $.trim($("#dialog_picture").val());
            var $dialog_introduce = $.trim($("#dialog_introduce").val());
            var $dialog_url = $.trim($("#dialog_url").val());
            if(checkValue1($dialog_category,$dialog_picture, $dialog_introduce, $dialog_url)){
                $("#activity_update_form").submit();
                $("#dialog_bigCategory").removeAttr("checked");
                $("#dialog_smallCategory").removeAttr("checked");
            }
        }

    });
});


function updateActivityBanner(id){
    maskActivityDialog('#activity_dialog');
    $.get(getBasePath()+"/fetchtobanner?id="+id,function(data,status){
        var $categoryCode = data.position;
        if($categoryCode == 1){
            $("#dialog_bigCategory").attr("checked",true);
            before_category = 1;
        }else if($categoryCode == 2){
            $("#dialog_smallCategory").attr("checked",true);
            before_category = 2;
        }
        $('#dialog_picture').val(data.imageName);
        $('#dialog_introduce').val(data.description);
        $('#dialog_url').val(data.url);
        $("#dialog_id").val(data.id);
    });
}

maskActivityDialog = function(dialogId,absoluteHeight){
    buildActivityDiv();
    var dialog = $(dialogId);
    $(dialog).show();
};

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
};
function checkValue(imageName, description, url){
    if(imageName.length == 0){
        alert("请上传图片！");
        return false;
    }else if(description.length == 0){
        alert("请输入介绍！");
        return false;
    }else if(url.length == 0){
        alert("url不能为空！");
        return false;
    }else{
        return true;
    }
}

function checkValue1(category,imageName, description, url){
    if(imageName.length == 0){
        alert("请上传图片！");
        return false;
    }else if(description.length == 0){
        alert("请输入介绍！");
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

function initEvent(){
    $(".hidden_description,.hidden_txt, .hidden_url").mouseenter(function(){
        $(this).find("span").hide();
        $(this).find("div").show();
    }).mouseleave(function(){
        $(this).find("div").hide();
        $(this).find("span").show();
    });
}

function delActivityBanner(id){
    var basePath = getBasePath();
    if(confirm("确定要删除这条信息吗？")){
        $.ajax({
            type:"GET",
            url:basePath + "/delbanner",
            data:"id=" + id +"&type=4",
            async:false,
            success:function(data,status){
                if(data.length == 0){
                    $(".activity_detail").empty();
                    $(".numberal_format").html(0);
                }else{
                    $(".activity_detail").empty();
                    var id = 0 ;
                    var html = "";
                    var indexNum = 0;
                    $.each(data,function(index,content){
                        indexNum++;
                        html += "<tr>";

                        id = content.id;

                        html += '<td class="category"><span>'+ changeValue(content.position) +'</td>';

                        html += '<td class="hidden_url"><span>'+ changeLength(content.url)+'</span><div class="url_div">'+ content.url +'</div></td>';

                        html += '<td class="hidden_txt"><span>'+ changeLength(content.imageName) +'</span><div class="imageName_div">'+ content.imageName +'</div></td>';

                        html += '<td class="hidden_description"><span>'+ changeLength(content.description) +'</span><div class="description_div">'+ content.description +'</div></td>';

                        html += '<td id="operate_td" class="operate_td"><a id="update_info" href="javascript:;" onclick="updateActivityBanner('+ id +');">' +
                            '<img src="'+ basePath +'/static/images/update.png"/></a><a class="delbtn" href="javascript:;" onclick="delActivityBanner('+ id +');">' +
                            '<img src="'+ basePath +'/static/images/delete.png"/></a></td>';
                        html += "</tr>";
                    });
                    $(".activity_detail").append(html);
                    $(".numberal_format").html(indexNum);
                }
            }
        });
        initEvent();
    }
}

function changeLength(obj){
    var val = null;
    if(obj.length > 10){
        val = obj.substring(0,10) + "...";
    }else{
        val = obj;
    }
    return val;
}

function changeValue(obj){
    if(obj == 1){
        return "大Banner";
    }else{
        return "小Banner";
    }
}
