
$(function () {

    var $picture_input = $("#picture_input").val().trim("");
    var $content_input = $("#content_input").val().trim("");
    if ($picture_input.length > 0 && $content_input.length > 0) {
        $("#dynamic_button").attr("src", getBasePath() + "/static/images/update.png");
        $(".float_button").attr("id", "updateBtn");
        $("#bottombanner_form").attr("action", getBasePath() + "/addbottombanner");
    } else {
        $("#dynamic_button").attr("src", getBasePath() + "/static/images/add.png");
        $(".float_button").attr("id", "addBtn");
        $("#bottombanner_form").attr("action", getBasePath() + "/addbottombanner");
        $("#bottom_id").removeAttr("name");
    }

    $("#addBtn").click(function () {
        var $picture_input = $('#picture_input').val().trim("");
        var $content_input = $('#content_input').val().trim("");
        var $url_input = $('#url_input').val().trim("");
        if (checkInputValue($picture_input, $content_input, $url_input)) {
            $("#bottombanner_form").submit();
        }
    });

    $("#updateBtn").click(function () {
        var $picture_input = $('#picture_input').val().trim("");
        var $content_input = $('#content_input').val().trim("");
        var $url_input = $('#url_input').val().trim("");
        if (checkInputValue($picture_input, $content_input, $url_input)) {
            $("#bottombanner_form").submit();
        }
    });

});

function checkInputValue(imageName, description, url) {
    if (imageName.length == 0) {
        alert("请上传图标！");
        return false;
    }else if (description.length == 0) {
        alert("请输入描述内容！");
        return false;
    }else if (url.length == 0) {
        alert("url不能为空！");
        return false;
    }else {
        return true;
    }
}