$(function () {
    var $icon_input = $("#icon_input").val().trim("");
    var $url_input = $("#url_input").val().trim("");
    if ($icon_input.length > 0 && $url_input.length > 0) {
        $("#dynamic_button").attr("src", getBasePath() + "/static/images/update.png");
        $(".float_button").attr("id", "updateBtn");
        $("#floatWindow_form").attr("action", getBasePath() + "/addfloatwindow");
    } else {
        $("#dynamic_button").attr("src", getBasePath() + "/static/images/add.png");
        $(".float_button").attr("id", "addBtn");
        $("#floatWindow_form").attr("action", getBasePath() + "/addfloatwindow");
        $("#float_id").removeAttr("name");
    }

    $("#addBtn").click(function () {
        var $imageName = $('#icon_input').val().trim("");
        var $url = $('#url_input').val().trim("");
        var $description = $('#content_input').val().trim("");
        if (checkInputValue($imageName, $url, $description)) {
            $("#floatWindow_form").submit();
        }
    });

    $("#updateBtn").click(function () {
        var $imageName = $('#icon_input').val().trim("");
        var $url = $('#url_input').val().trim("");
        var $description = $('#content_input').val().trim("");
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