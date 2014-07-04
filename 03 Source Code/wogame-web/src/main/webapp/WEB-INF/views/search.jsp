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
    <title>搜索</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/paging.css" rel="stylesheet" type="text/css"/>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w-sousuo_icon"><a data-rel="back"></a></div>
    <div class="w-sousuo"><a data-rel="back">搜索</a></div>
</div>
<!--分类筛选-->
<div class="w_search_box">
    <div class="w_inputbox">
        <div class="w_in_01"><a href="javascript:clearText();">关闭</a></div>
        <div class="w_in_02"></div>
        <input name="txtSearch" id="txtSearch" type="text" data-role="none" class="w_input"/>
    </div>
    <input name="" type="button" class="w_buttion" value="搜索" data-role="none" onclick="search()"/>
</div>

<div id="wrapper">
    <div id="scroller">
        <div id="pullDown">
            <span class="pullDownIcon"></span><span class="pullDownLabel">刷新...</span>
        </div>
        <div id="list">
            <c:forEach items="${list}" var="item">
                <a href="javascript:doSearch('${item.hotWord}');">
                    <div class="w_list_youxi">${item.hotWord}</div>
                </a>
            </c:forEach>
        </div>
        <div id="pullUp">
            <span class="pullUpIcon"></span><span class="pullUpLabel">更多...</span>
        </div>
    </div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript">

    var myScroll,
            pullDownEl, pullDownOffset,
            pullUpEl, pullUpOffset,
            generatedCount = 0;
    var categoryId = $("#categoryId").val();
    pageNum = 1;
    var urlBase = '${ctx}/gameInfo;jsessionid=${sessionid}?productId=';
    var el = $('#list');
    //    el.empty();

    function ajaxGetData(pPageNum, callback) {

        var keyword = $("#txtSearch").val();

//        if (keyword == "") {
//            $('#pullDown, #pullUp').hide();
//            myScroll.refresh();
//            return;
//        }
//
//        $('#pullDown, #pullUp').show();

        $.getJSON("${ctx}/search/ajaxSearch;jsessionid=${sessionid}", {"pageNum": pPageNum, "keyword": keyword}, function (data) {

            if (data.length != 0) {

                if (pPageNum <= 1) {
                    el.empty();
                }

                $.each(data, function (index, entry) {

                    var stringBuffer = [];

                    stringBuffer.push('<div class="w_list">');
                    stringBuffer.push('<div class="w_list_img">');
                    stringBuffer.push('<a href="javascript:toDetail(\'' + entry.id + '\')">');
                    stringBuffer.push('<img src="' + entry.icon + '" width="48" height="48"/></a></div>');
                    stringBuffer.push('<div class="w_list_title">');
                    stringBuffer.push('<a href="javascript:toDetail(\'' + entry.id + '\')">' + entry.name + '</a>');
                    stringBuffer.push('</div>');
                    stringBuffer.push('<div class="w_list_category">' + entry.category + '</div>');
                    stringBuffer.push('<div class="w_list_numm">' + roundNumber(entry.size / 1024, 2) + 'MB</div>');
                    stringBuffer.push('<div class="w_list_download">');
                    stringBuffer.push('<a href="javascript:download(\'' + JSON.stringify(entry) + '\')">下载</a>');
                    stringBuffer.push('</div>');
                    stringBuffer.push('<div class="w_list_download_txt">');
                    stringBuffer.push('<a href="javascript:download(\'' + JSON.stringify(entry) + '\')">下载</a>');
                    stringBuffer.push('</div>');

                    el.append(stringBuffer.join(""));
                });

            } else {
                $('#pullDown, #pullUp').hide();
            }
            if (callback) {
                callback();
            } else {
                myScroll.refresh();
            }
        });


    }

    function ajaxSearchKeywords(keyword) {
        $.getJSON("${ctx}/search/keyword;jsessionid=${sessionid}", {"keyword": keyword}, function (data) {
            el.empty();
            if (data.length != 0) {

                $.each(data, function (index, entry) {
                    var stringBuffer = [];

                    stringBuffer.push('<a href="javascript:doSearch(\'' + entry.hot_word + '\');">');
                    stringBuffer.push('<div class="w_list_youxi">' + entry.hot_word + '</div>');
                    stringBuffer.push('</a>');

                    el.append(stringBuffer.join(""));
                });

            } else {
                $('#pullDown, #pullUp').hide();
            }

            myScroll.refresh();

        });
    }

    document.addEventListener('touchmove', function (e) {
        e.preventDefault();
    }, false);

    document.addEventListener('DOMContentLoaded', function () {
        setTimeout(loaded, 200);
    }, false);

</script>

<script type="text/javascript">
    function search() {
        ajaxGetData(1);
    }

    function doSearch(keyword) {
        $('#txtSearch').val(keyword);
        search();
    }

    $('#txtSearch').bind("change", function () {
        ajaxSearchKeywords($(this).val());
    });

    function clearText() {
        $('#txtSearch').val('');
    }

    function toDetail(id) {
        location.href = urlBase + id;
    }
</script>

</body>
</html>
