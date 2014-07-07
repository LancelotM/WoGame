<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sessionid" value="${pageContext.request.requestedSessionId}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=2.0">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="false" id="twcClient" name="twcClient">
    <title>一周热榜</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/paging.css" rel="stylesheet" type="text/css"/>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w_search"><a href="${ctx}/search/init;jsessionid=${sessionid}">搜索</a></div>
</div>
<!--分类筛选-->
<div class="w_paihangtitle" id="w_paihangtitle">
    <!--选中状态-->
    <div class="w_new_011"><a href="${ctx}/main;jsessionid=${sessionid}">首页</a></div>
    <!--没有选中-->
    <div class="w_new_022"><a href="${ctx}/category/list;jsessionid=${sessionid}">分类</a></div>
    <div class="w_new_03"><a href="#">一周热榜</a></div>
    <div class="w_new_044"><a href="${ctx}/newGame/list;jsessionid=${sessionid}?pageNum=1">最新</a></div>
</div>

<div id="wrapper">
    <div id="scroller">
        <div id="pullDown">
            <span class="pullDownIcon"></span><span class="pullDownLabel">刷新...</span>
        </div>
        <div id="list">

        </div>
        <div id="pullUp">
            <span class="pullUpIcon"></span><span class="pullUpLabel">更多...</span>
        </div>
    </div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.touchwipe.js"></script>
<script type="text/javascript">
    $('#pullDown, #pullUp').hide();
    var myScroll,
            pullDownEl, pullDownOffset,
            pullUpEl, pullUpOffset,
            generatedCount = 0;
    var categoryId = $("#categoryId").val();
    pageNum = 1;
    var urlBase = '${ctx}/gameInfo;jsessionid=${sessionid}?productId=';
    var el = $('#list');

    function ajaxGetData(pPageNum, callback) {


        $.getJSON("${ctx}/weeklyHot/ajaxList", {"pageNum": pPageNum}, function (data) {

            if (data.length != 0) {

                if (pPageNum <= 1) {
                    el.empty();
                }
                $.each(data, function (index, entry) {
                    var stringBuffer = [];

                    stringBuffer.push('<div class="w_list">');
                    stringBuffer.push('<div class="w_list_img">');
                    stringBuffer.push('<a href="' + urlBase + entry.product_id + '"');
//                    stringBuffer.push(' onclick="logdata(\'' + entry.product_id + '\',\''
//                            + entry.title + '\',\'' + entry.icon_url + '\',\'' + index + '\')"');
                    stringBuffer.push('>');
                    stringBuffer.push('<img src="' + entry.icon_url + '" width="48" height="48"/></a></div>');
                    stringBuffer.push('<div class="w_list_title">');
                    stringBuffer.push('<a href="' + urlBase + entry.product_id + '"');
//                    stringBuffer.push(' onclick="logdata(\'' + entry.product_id + '\',\''
//                            + entry.title + '\',\'' + entry.icon_url + '\',\'' + index + '\')"');
                    stringBuffer.push('>' + entry.title + '</a>');
                    stringBuffer.push('</div>');
                    stringBuffer.push('<div class="w_list_numm">' + roundNumber((entry.apk_size) / 1024, 2) + 'MB</div>');
                    stringBuffer.push('<div class="w_list_category">' + entry.category + '</div>');
                    stringBuffer.push('<div class="w_list_download">');
                    stringBuffer.push('<a href="javascript:download(\'' + entry.product_id
                            + '\',\'' + entry.title
                            + '\',\'' + entry.icon_url
                            + '\')">下载</a>');
                    stringBuffer.push('</div>');
                    stringBuffer.push('<div class="w_list_download_txt">');
                    stringBuffer.push('<a href="javascript:download(\'' + entry.product_id
                            + '\',\'' + entry.title
                            + '\',\'' + entry.icon_url
                            + '\')">下载</a>');
                    stringBuffer.push('</div>');

                    el.append(stringBuffer.join(""));
                });
            }

            if (callback) {
                callback();
            }
        });

    }

    document.addEventListener('touchmove', function (e) {
        e.preventDefault();
    }, false);

    document.addEventListener('DOMContentLoaded', function () {
        setTimeout(loaded, 200);
    }, false);

    ajaxGetData(1);
</script>

<script type="text/javascript">

    $(function () {
        $("#wrapper").touchwipe({
            wipeLeft: function (e) {
                e.preventDefault();
                location.href = "${ctx}/newGame/list;jsessionid=${sessionid}?pageNum=1";
            },
            wipeRight: function (e) {
                e.preventDefault();
                location.href = "${ctx}/category/list;jsessionid=${sessionid}";
            },
            preventDefaultEvents: false

        });
    });

    function download(id, name, url) {
        $.getJSON("${ctx}/download;jsessionid=${sessionid}",
                {"productId": id, "productName": name, "productIcon": url},
                function (data) {
            if (data.downloadUrl == "") {
                alert(data.description);
            } else {
                download_file(data.downloadUrl);
            }
        })
    }
</script>
<script type="text/javascript">
    logUsage("${ctx}", {
        "pageTraffic": {
            "pgeId": "3"		//页面编号1：首页 2：分类 3：一周热榜 4：最新
        }
    });

</script>
</body>
</html>
