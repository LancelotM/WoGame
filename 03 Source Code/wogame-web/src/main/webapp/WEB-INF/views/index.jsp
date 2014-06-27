<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>首页</title>

    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/slides.css" rel="stylesheet" type="text/css"/>


</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w_search"><a href="${ctx}/search/init;jsessionid=${sessionid}">搜索</a></div>
</div>
<!--分类筛选-->
<div class="w_paihangtitle" id="w_paihangtitle" data-role="none">
    <!--选中状态-->
    <div class="w_new_01"><a href="#">首页</a></div>
    <!--没有选中-->
    <div class="w_new_022" data-role="none"><a href="${ctx}/category/list;jsessionid=${sessionid}">分类</a></div>
    <div class="w_new_033"><a href="${ctx}/weeklyHot/list;jsessionid=${sessionid}?pageNum=1">一周热榜</a></div>
    <div class="w_new_044"><a href="${ctx}/newGame/list;jsessionid=${sessionid}?pageNum=1">最新</a></div>

</div>
<!--大图-->
<div id="pic_div" class="container">
    <div id="slides">
        <c:forEach items="${adList}" var="item">
            <img src="${item.bannerUrl}"/>
        </c:forEach>
    </div>
</div>

<!--列表-->
<c:forEach items="${recommendedList}" var="item">
    <c:forEach items="${item.apps}" var="appItem" step="2" varStatus="idx">
        <c:if test="${item.adType == 4}">
            <div class="w_houlist">
                <div class="w_houlist_l">
                    <div class="w_img_bg"><a
                            href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${appItem.productId}"><img
                            width="100" height="100" src="${appItem.iconUrl}"></a></div>
                    <div class="w_img_count">
                        <h3>
                            <a href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${appItem.productId}">${appItem.appName}</a>
                        </h3>

                        <div class="isio">
                            <div class="w_start_0${appItem.rate}${appItem.rate}">一星</div>
                            <!--<div class="w_start_022">二星</div><div class="w_start_033">三星</div><div class="w_start_044">四星</div><div class="w_start_055">五星</div>-->
                        </div>
                        <h5>${appItem.apkSize / 1000}MB</h5>
                    </div>
                    <div class="w_img_xz"><a href="javascript:download('${appItem.productId}');">下载</a></div>
                </div>
                <c:if test="${idx.index+1 < fn:length(item.apps)}">
                    <div class="w_houlist_r">
                        <div class="w_img_bg"><a
                                href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${item.apps[idx.index+1].productId}"><img
                                width="100" height="100"
                                src="${item.apps[idx.index+1].iconUrl}"></a></div>
                        <div class="w_img_count">
                            <h3>
                                <a href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${item.apps[idx.index+1].productId}">${item.apps[idx.index+1].appName}</a>
                            </h3>

                            <div class="isio">
                                <div class="w_start_0${item.apps[idx.index+1].rate}${item.apps[idx.index+1].rate}">二星
                                </div>
                                <!--<div class="w_start_011">一星</div><div class="w_start_033">三星</div><div class="w_start_044">四星</div><div class="w_start_055">五星</div>-->
                            </div>
                            <h5>${item.apps[idx.index+1].apkSize / 1000}MB</h5>
                        </div>
                        <div class="w_img_xz"><a
                                href="javascript:download('${item.apps[idx.index+1].productId}');">下载</a></div>

                    </div>
                </c:if>
            </div>
        </c:if>
        <c:if test="${item.adType == 1}">
            <div class="w_houlist">
                <a href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${appItem.productId}">
                    <img width="100%" height="100" src="${appItem.iconUrl}">
                </a>
            </div>

            <c:if test="${idx.index+1 < fn:length(item.apps)}">
                <div class="w_houlist">
                    <a href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${item.apps[idx.index+1].productId}">
                        <img width="100%" height="100" src="${item.apps[idx.index+1].iconUrl}">
                    </a>
                </div>
            </c:if>

        </c:if>
    </c:forEach>
</c:forEach>


<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/js/index.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.slides.min.js"></script>

<script type="text/javascript">

    $(function () {
        $('#slides').slidesjs({
            width: 320,
            height: 160,
            navigation: false,
            start: 1,
            play: {
                auto: true
            }
        });

        app.initialize();
        /*$("#w_paihangtitle").bind("swipeleft", function () {
         location.href = "${ctx}/newGame?pageNum=1";
        });
        $("#w_paihangtitle").bind("swiperight", function () {
            location.href = "${ctx}/category/list";
         });*/


    });

    function download(id) {
        $.getJSON("${ctx}/download;jsessionid=${sessionid}?productId=" + id, function (data) {
            if (data.downloadUrl == "") {
                alert(data.description);
            } else {
                download_file(data.downloadUrl);
            }
        })
    }


</script>

</body>
</html>
