$(function () {
    var $icon_input = $.trim($("#icon_input").val());
    var $url_input = $.trim($("#url_input").val());
    if ($icon_input.length > 0 && $url_input.length > 0) {
        $("#dynamic_button").attr("src", getBasePath() + "/static/images/update.png");
        $(".float_button").attr("id", "updateBtn");
        $("#floatWindow_form").attr("action", getBasePath() + "/saveorupdatebanner");
    } else {
        $("#dynamic_button").attr("src", getBasePath() + "/static/images/add.png");
        $(".float_button").attr("id", "addBtn");
        $("#floatWindow_form").attr("action", getBasePath() + "/saveorupdatebanner");
        $("#float_id").removeAttr("name");
    }

    $("#addBtn").click(function () {
        var $imageName = $.trim($('#icon_input').val());
        var $url = $.trim($('#url_input').val());
        var $description = $.trim($('#content_input').val());
        if (checkInputValue($imageName, $url, $description)) {
            $("#floatWindow_form").submit();
        }
    });

    $("#updateBtn").click(function () {
        var $imageName = $.trim($('#icon_input').val());
        var $url = $.trim($('#url_input').val());
        var $description = $.trim($('#content_input').val());
        if (checkInputValue($imageName, $url, $description)) {
            $("#floatWindow_form").submit();
        }
    });
});

function checkInputValue(imageName, url, description) {
    if (imageName.length == 0) {
        alert("请上传icon！");
        return false;
    } else if (url.length == 0) {
        alert("url不能为空！");
        return false;
    } else if (description.length == 0) {
        alert("请输入描述内容！");
        return false;
    } else {
        return true;
    }
}