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
    <title>分类</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
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
    <div class="w_new_02"><a href="#">分类</a></div>
    <div class="w_new_033"><a href="${ctx}/weeklyHot;jsessionid=${sessionid}?pageNum=1">一周热榜</a></div>
    <div class="w_new_044"><a href="${ctx}/newGame;jsessionid=${sessionid}?pageNum=1">最新</a></div>

    <!--<div class="w_new_077"><a href="#">分类</a></div>-->
</div>
<!--列表-->
<c:forEach items="${list}" var="item">

    <a href="${ctx}/category/detail;jsessionid=${sessionid}?categoryId=${item.categoryId}&pageNum=1">
        <div class="w_list_fenlei">
            <div class="w_list_img"><img src="${item.iconUrl}" width="48" height="48"/></div>
            <div class="w_list_title">${item.categoryTitle}</div>
            <div class="w_list_numm">${item.description}</div>
        </div>
    </a>

</c:forEach>

</body>
</html>
