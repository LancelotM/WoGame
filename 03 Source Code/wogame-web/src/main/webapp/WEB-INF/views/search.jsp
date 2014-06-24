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
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
    <script src="${ctx}/static/js/index.js"></script>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w-sousuo_icon"><a href="javascript:back(-1);"></a></div>
    <div class="w-sousuo"><a href="javascript:back(-1);">${categoryName}</a></div>
    <div class="w-sousuo_icon"><a href="＃">搜索</a></div>
<%--<div class="w_download2"><a href="#">下载</a></div>--%>
</div>
<!--分类筛选-->
<div class="w_search_box">
    <div class="w_inputbox">
        <div class="w_in_01"><a href="#">关闭</a></div>
        <div class="w_in_02"></div>
        <input name="txtSearch" id="txtSearch" type="text" class="w_input"/></div>
    <input name="" type="button" class="w_buttion" value="搜索" onclick="search()"/>
</div>

<c:forEach items="${list}" var="item">
    <a href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${item.id}">
        <div class="w_list_youxi">${item.name}</div>
    </a>
</c:forEach>
<!--列表-->

<script type="text/javascript">
    function search() {
        var keyword = $("#txtSearch").val();
        location.href = "${ctx}/search/keyword;jsessionid=${sessionid}?keyword=" + keyword;
    }
</script>

</body>
</html>
