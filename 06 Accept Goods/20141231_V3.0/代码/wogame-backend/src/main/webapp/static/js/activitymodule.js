var Dialog={};
$(function(){
    initEvent();

    $("#random_input").val(Math.random()*99+1);

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
            var $title_input = $.trim($("#title_input").val());
            var $picture_input = $.trim($("#picture_input").val());
            var $introduce_input = $.trim($("#introduce_input").val());
            var $url_input = $.trim($("#url_input").val());
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
        var $dialog_title = $.trim($("#dialog_title").val());
        var $dialog_picture = $.trim($("#dialog_picture").val());
        var $dialog_introduce = $.trim($("#dialog_introduce").val());
        var $dialog_url = $.trim($("#dialog_url").val());
        if(checkValue($dialog_picture, $dialog_title, $dialog_introduce, $dialog_url)){
            $("#module_update_form").submit();
        }
    });



});

function updateModuleBanner(id){
    maskModuleDialog('#module_dialog');
    $.get(getBasePath()+"/fetchtobanner?id="+id,function(data,status){
        $('#dialog_picture').val(data.imageName);
        $('#dialog_title').val(data.title);
        $('#dialog_introduce').val(data.description);
        $('#dialog_url').val(data.url);
        $("#dialog_id").val(data.id);
    });
}

function initEvent(){
    $(".hidden_title, .hidden_imageName, .hidden_description, .hidden_url").mouseenter(function(){
        $(this).find("span").hide();
        $(this).find("div").show();
    }).mouseleave(function(){
        $(this).find("div").hide();
        $(this).find("span").show();
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
    var basePath = getBasePath();
    if(confirm("确定要删除这条信息吗？")){
        $.ajax({
            type:"GET",
            url:basePath + "/delbanner",
            data:"id=" + id +"&type=3",
            async:false,
            success:function(data,status){
                if(data.length == 0){
                    $(".module_detail").empty();
                    $(".numberal_format").html(0);
                }else{
                    $(".module_detail").empty();
                    var id = 0 ;
                    var html = "";
                    var indexNum = 0;
                    $.each(data,function(index,content){
                        indexNum++;
                        html += "<tr>";
                        id = content.id;

                        html += '<td class="hidden_title"><span>'+ changeLength("title", content.title) +'</span><div class="title_div">'+ content.title +'</div></td>';

                        html += '<td class="hidden_url"><span>'+ changeLength("url", content.url)+'</span><div class="url_div">'+ content.url +'</div></td>';

                        html += '<td class="hidden_imageName"><span>'+ changeLength("imageName", content.imageName) +'</span><div class="imageName_div">'+ content.imageName +'</div></td>';

                        html += '<td class="hidden_description"><span>'+ changeLength("description", content.description) +'</span><div class="description_div">'+ content.description +'</div></td>';



                        /*$.each(content,function(index,obj){
                            if(index == "id"){
                                id = obj;
                            }
                            if(index == "title"){
                                html += '<td class="hidden_title">'+ changeLength(index, obj) +'<div class="title_div">'+ obj +'</div></td>';
                            }
                            if(index == "imageName"){
                                html += '<td class="hidden_imageName">'+ changeLength(index, obj) +'<div class="imageName_div">'+ obj +'</div></td>';
                            }
                            if(index == "description"){
                                html += '<td class="hidden_description">'+ changeLength(index, obj) +'<div class="description_div">'+ obj +'</div></td>';
                            }
                            if(index == "url"){
                                html += '<td class="hidden_url">'+ changeLength(index, obj)+'<div class="url_div">'+ obj +'</div></td>';
                            }

                        });*/
                        html += '<td id="operate_td" class="operate_td"><a id="update_info" href="javascript:;" onclick="updateModuleBanner('+ id +');">' +
                            '<img src="'+ basePath +'/static/images/update.png"/></a><a class="delbtn" href="javascript:;" onclick="delModuleBanner('+ id +');">' +
                            '<img src="'+ basePath +'/static/images/delete.png"/></a></td>';
                        html += "</tr>";
                    });
                    $(".numberal_format").html(indexNum);
                    $(".module_detail").append(html);
                }
            }
        });
        initEvent();
    }
}
function changeLength(index, obj){
    var val = null;
    if(index == "title"){
        if(obj.length > 20){
            val = obj.substring(0,20) + "...";
        }else{
            val = obj;
        }
        return val;
    }else if(index == "imageName"){
        if(obj.length > 15){
            val = obj.substring(0,15) + "...";
        }else{
            val = obj;
        }
        return val;
    }else{
        if(obj.length > 10){
            val = obj.substring(0,10) + "...";
        }else{
            val = obj;
        }
        return val;
    }
}

