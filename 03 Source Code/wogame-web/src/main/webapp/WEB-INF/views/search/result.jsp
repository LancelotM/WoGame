<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/paging.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/slides.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/iscroll.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.touchwipe.js"></script>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>

    <%--    <script type="text/javascript" name="baidu-tc-cerfication" src="http://apps.bdimg.com/cloudaapi/lightapp.js#21e4cc6e9f6e857f9ba7ac86ababad5a"></script>--%>

</head>

<body class="ibody_bg">
<!--top-->

<div class="head" style="position: fixed;top:0;left:0;width:100%;z-index: 1000;">


    <a   href="${ctx}/search/init.do">
        <div class="fanhui absolute pic"></div>

        <div class="fanhui-text absolute" style="color:#FF9C00 ">搜索</div>
    </a>



    <div class="title">搜索</div>

    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do">搜索</a></div>
</div>
<div style="height: 50px;"></div>




<!--列表-->
<div class="w_search_box" style="margin-top: 15px;">
    <div class="w_inputbox"><div class="w_in_01"><a onclick="clearSearch();" href="#">删除</a></div>
        <div class="w_in_02"></div>
        <input type="text" id="w_input" value="${keyword}" class="w_input">
    </div><input type="button"  onclick="search()" value="搜索" class="w_buttion" name="">
</div>




<!--列表-->
<div id="wrapper" style="top: 130px;">
    <div id="scroller">
        <div id="pullDown">
            <span class="pullDownIcon"></span><span class="pullDownLabel">刷新...</span>
        </div>
        <div id="list">

        </div>
        <jsp:include page="../footer.jsp"/>
        <div id="pullUp">
            <span class="pullUpIcon"></span><span class="pullUpLabel">更多...</span>
        </div>
    </div>
</div>




<script type="application/javascript">

    var myScroll,
            pullDownEl, pullDownOffset,
            pullUpEl, pullUpOffset,
            generatedCount = 0;
    var keyword = $('#w_input').val();
    var isSearching = false;
    pageNum = 1;
    var urlBase = '${ctx}/gamedetail/detaillist.do?product_id=';
    var el = $('#list');
    el.empty();

    function ajaxGetData(pPageNum, callback) {
        if (isSearching) {
            return;
        }
        isSearching = true;

        $.getJSON("${ctx}/search/ajaxSearch.do", {"pageNum": pPageNum, "keyword": encodeURI(encodeURI(keyword))}, function (data) {

            isSearching = false;
            if (data.length != 0 && data.status!=-99) {

                if (pPageNum <= 1) {
                    el.empty();
                }

                $.each(data, function (index, entry) {

                    var stringBuffer = [];

                    if (entry.banner != null) {
                        stringBuffer.push('<div class="hot_banner2">');
                        stringBuffer.push('<div class="jiaobiao_' + entry.corner_mark + '">');
                        stringBuffer.push('</div>');
                        stringBuffer.push('<a href="' + urlBase + entry.id + '">');
                        stringBuffer.push('<img src="' + entry.banner.banner_url + '" data-src="' + entry.banner.banner_url + '" height="160"/></a>');
                        /*显示下载*/
                        if (entry.banner.res_type == 2) {
                            stringBuffer.push('<div class="pro_cp_r1 radius">');
                            stringBuffer.push('<a href="javascript:download(\'' + entry.id
                                    + '\',\'' + entry.name
                                    + '\',\'' + entry.icon
                                    + '\')">下载</a>');
                            stringBuffer.push('</div>');

                        } else if (entry.banner.res_type == 9) {
                            //do nothing
                        }


                        stringBuffer.push('</div>');
                    } else {

                        stringBuffer.push('<div class="pro_list">');

                        stringBuffer.push('<div class="jiaobiao_' + entry.corner_mark + '">');
                        stringBuffer.push('</div>');

                        stringBuffer.push('<a href="${ctx}/gamedetail/detaillist.do?product_id=' + entry.id + '">');


                        stringBuffer.push('<div class="pro_cp">');

                        /*图片*/
                        stringBuffer.push('<div class="pro_cp_l">');
                        stringBuffer.push('<a href="' + urlBase + entry.id + '">');
                        stringBuffer.push('<img src="' + entry.icon + '" data-src="' + entry.icon + '" height="86"/></a>');
                        stringBuffer.push('</div>');

                        /*游戏名称*/

                        stringBuffer.push('<dl class="pro_cp_c">');
                        stringBuffer.push('<dt class="etc">' + entry.name + '</dt>');

                        stringBuffer.push('<dd>' + roundNumber(entry.size / 1024, 2) + 'MB</dd>');
                        stringBuffer.push('</dl>');

                        stringBuffer.push('</div>');

                        stringBuffer.push('<div class="pro_cp_r radius">');

                        stringBuffer.push('<a href="javascript:download(\'' + entry.id
                                + '\',\'' + entry.name
                                + '\',\'' + entry.icon
                                + '\')">下载</a>');
                        stringBuffer.push('</div>');


                        stringBuffer.push('<div class="plist_f etc">');
                        stringBuffer.push('<a href="javascript:download(\'' + entry.id
                                + '\',\'' + entry.name
                                + '\',\'' + entry.icon
                                + '\')">' + entry.supplier + '</a></div>');


                        stringBuffer.push('</a>');


                        stringBuffer.push('</div>');


                    }


                    /*当程序加载页面自动运行ajaxGetData（1）自动加载好页面就ok*/


                    el.append(stringBuffer.join(""));
                });
                $("img[data-src]").scrollLoading();
            } else {

                $(' #pullUp').hide();

            }
            if (callback) {
                callback();
            } else {
                myScroll.refresh();
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
        $("#pageContent").touchwipe({
            wipeLeft: function (e) {
                e.preventDefault();
                location.href = "${ctx}/category/list;jsessionid=${sessionid}";
            },
            wipeRight: function (e) {
                e.preventDefault();
                location.href = "${ctx}/newGame/list;jsessionid=${sessionid}?pageNum=1";
            },
            preventDefaultEvents: false
        });




    });

    /*   function download(id, name, icon) {
     doDownload("${ctx}/download;jsessionid=${sessionid}", id, name, icon);
     }*/

    function search(){
        var keyword;
        var inputtext= $("#w_input").val();

        if(inputtext=="" || inputtext=="请输入搜索内容" ){

            alert("必须项")
            return false;
        }else{

            keyword=encodeURI(encodeURI(inputtext));

           location.href = "${ctx}/search/result.do?keyword="+keyword;
        }
    }



</script>
<script type="text/javascript">
    logNumber("${ctx}", ['64']);
</script>
</body>
</html>