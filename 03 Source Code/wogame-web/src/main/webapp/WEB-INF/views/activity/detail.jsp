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
    <title>活动详情</title>

    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
    <script type="text/javascript" name="baidu-tc-cerfication"
            src="http://apps.bdimg.com/cloudaapi/lightapp.js#21e4cc6e9f6e857f9ba7ac86ababad5a"></script>

</head>

<body class="ibody_bg">
<!--top-->

<div class="head" style="position: fixed;top:0;left:0;width:100%;z-index: 1000;">

    <a   href="${ctx}/activity/list.do;jsessionid=${sessionid}">
        <div class="fanhui absolute pic"></div>

        <div class="fanhui-text absolute" style="color:#FF9C00 ">活动列表</div>
    </a>

    <div class="title">活动详情</div>

    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do;jsessionid=${sessionid}">搜索</a></div>
</div>
<div style="height: 50px;"></div>

<!--分类筛选-->
<dl class="hd_title">
    <dt>${activityContent.title}</dt>
    <dd class="hd_1">
        <jsp:useBean id="date" class="java.util.Date"/>
        <jsp:setProperty name="date" property="time" value="${activityContent.startTime}"/>
        <fmt:formatDate value="${date}" pattern="yyyy-MM-dd hh:mm"/>


    </dd>
    <dd class="hd_2">作者：${activityContent.editor}</dd>
</dl>
<div class="hd_l_title">活动游戏</div>
<div class="hd_l_count"><a href="#">
    <div class="hd_youxi_img"><img src="${activityContent.game.iconUrl}" height="70"/></div>
    <dl class="hd_youxi">
        <dt>${activityContent.game.gameName}</dt>
        <dd><fmt:formatNumber value="${activityContent.game.apkSize/1024}"  pattern="##.#" minFractionDigits="2"></fmt:formatNumber> MB</dd>

    </dl>
    <div class="hd_xiazai radius">下载</div>
</a>
</div>
<div class="hd_l_title">活动简介</div>
<div class="hd_l_count2">
    ${activityContent.content}
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>



