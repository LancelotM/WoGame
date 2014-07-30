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
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/paging.css" rel="stylesheet" type="text/css"/>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w-sousuo_icon"><a href="javascript:location.href='${referUrl}'"></a></div>
    <div class="w-sousuo"><a href="javascript:location.href='${referUrl}'">搜索</a></div>
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
<div id="error-container" style="display:none;text-align:center;background-color: white;width:200px;height:150px;">
    <div style="height:40px;line-height: 40px;">温馨提示</div>
    <div style="height:4px;background-color: orange;"></div>
    <div style="height:60px;line-height: 60px;">网络连接失败</div>
    <dl class="w_retry" data-role="none">
        <a href="javascript:search();" data-role="none">
            <dt>重试</dt>
        </a>
    </dl>
</div>
<div id="info-container"
     style="display:none;position:fixed;top:40%;left:10%;z-index:9999;text-align:center;background-color: white;width:200px;height:100px;border: 1px solid gray">
    <div style="height:40px;line-height: 40px;">温馨提示</div>
    <div style="height:4px;background-color: orange;"></div>
    <div style="height:60px;line-height: 60px;">文件下载中...</div>
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
<script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
<script type="text/javascript">
    var myScroll,
            pullDownEl, pullDownOffset,
            pullUpEl, pullUpOffset,
            generatedCount = 0;
    var categoryId = $("#categoryId").val();
    var isSearching = false;
    pageNum = 1;
    var urlBase = '${ctx}/gameInfo;jsessionid=${sessionid}?productId=';
    var el = $('#list');
    //    el.empty();

    function ajaxGetData(pPageNum, callback) {
        if (isSearching) {
            return;
        }

        var keyword = $("#txtSearch").val();

        if (keyword == "") {
            alert("请输入关键字。");
            return;
        }

        isSearching = true;

//        if (keyword == "") {
//            $('#pullDown, #pullUp').hide();
//            myScroll.refresh();
//            return;
//        }
//
//        $('#pullDown, #pullUp').show();

        $.getJSON("${ctx}/search/ajaxSearch;jsessionid=${sessionid}", {"pageNum": pPageNum, "keyword": encodeURI(encodeURI(keyword))}, function (data) {
            isSearching = false;

            $("#error-container").hide();

            if (data.status && data.status == -99) {
                $("#error-container").show();
                $("#wrapper").hide();
                return;
            } else {
                $("#error-container").hide();
                $("#wrapper").show();
            }

            if (data.length != 0) {

                if (pPageNum <= 1) {
                    el.empty();
                }

                $.each(data, function (index, entry) {

                    var stringBuffer = [];

                    stringBuffer.push('<div class="w_list">');
                    stringBuffer.push('<div class="w_list_img">');
                    stringBuffer.push('<a href="javascript:toDetail(\'' + entry.id + '\')">');
                    stringBuffer.push('<img src="${ctx}/static/images/gameicon.png" data-src="' + entry.icon + '" width="48" height="48"/></a></div>');
                    stringBuffer.push('<div class="w_list_title">');
                    stringBuffer.push('<a href="javascript:toDetail(\'' + entry.id + '\')">' + entry.name + '</a>');
                    stringBuffer.push('</div>');
                    stringBuffer.push('<div class="w_list_category">' + entry.category + '</div>');
                    stringBuffer.push('<div class="w_list_numm">' + roundNumber(entry.size / 1024, 2) + 'MB</div>');
                    stringBuffer.push('<div class="w_list_download">');
                    stringBuffer.push('<a href="javascript:download(\'' + entry.id
                            + '\',\'' + entry.name
                            + '\',\'' + entry.icon
                            + '\')">下载</a>');
                    stringBuffer.push('</div>');
                    stringBuffer.push('<div class="w_list_download_txt">');
                    stringBuffer.push('<a href="javascript:download(\'' + entry.id
                            + '\',\'' + entry.name
                            + '\',\'' + entry.icon
                            + '\')">下载</a>');
                    stringBuffer.push('</div>');

                    el.append(stringBuffer.join(""));
                });
                $("img[data-src]").scrollLoading();

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
        if (isSearching) {
            return;
        }

        if (keyword == "") {
            return;
        }

        isSearching = true;

        $('#pullDown, #pullUp').hide();
        $.getJSON("${ctx}/search/keyword;jsessionid=${sessionid}", {"keyword": encodeURI(encodeURI(keyword))}, function (data) {
            el.empty();

            isSearching = false;

            $("#error-container").hide();

            if (data.status && data.status == -99) {
                $("#error-container").show();
                $("#wrapper").hide();
                return;
            } else {
                $("#error-container").hide();
                $("#wrapper").show();
            }

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

    $('#txtSearch').bind("keyup", function () {
        ajaxSearchKeywords($(this).val());
    });

    function clearText() {
        $('#txtSearch').val('');
    }

    function toDetail(id) {
        location.href = urlBase + id;
    }

    function download(id, name, icon) {
        doDownload("${ctx}/download;jsessionid=${sessionid}", id, name, icon);
    }
</script>
<script type="text/javascript">
    $(window).resize(function () {
        $('#error-container').css({
            position: 'fixed',
            left: ($(window).width() - $('#error-container').outerWidth()) / 2,
            top: ($(window).height() - $('#error-container').outerHeight()) / 2 + $(document).scrollTop()
        });
    });
    $(window).resize();
</script>
</body>
</html>
