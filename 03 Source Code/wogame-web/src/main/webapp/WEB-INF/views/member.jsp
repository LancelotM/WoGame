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
    <title>推荐</title>

    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/paging.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/slides.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>

    <script type="text/javascript" name="baidu-tc-cerfication"
            src="http://apps.bdimg.com/cloudaapi/lightapp.js#21e4cc6e9f6e857f9ba7ac86ababad5a"></script>

</head>

<body class="ibody_bg" style="text-align: center">
<!--top-->

<div class="head" style="position: fixed;top:0;left:0;width:100%;z-index: 1000;">


    <a   href="${ctx}/index.do">
        <div class="fanhui absolute pic"></div>

        <div class="fanhui-text absolute">首页</div>
    </a>

    <div class="title">0元畅玩</div>

    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do">搜索</a></div>
</div>

<div style="height: 56px"></div>

    　　
            <iframe  style="height: 600px;" width="100%" frameborder="0" src="http://sales.wostore.cn:8083/hytq/hytq.action?link=0"/>



</body>
</html>