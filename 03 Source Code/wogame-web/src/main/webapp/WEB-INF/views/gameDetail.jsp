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
    <title>游戏详情</title>

    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>

    <script type="text/javascript" name="baidu-tc-cerfication"
            src="http://apps.bdimg.com/cloudaapi/lightapp.js#21e4cc6e9f6e857f9ba7ac86ababad5a"></script>

</head>

<body class="ibody_bg">
<!--top-->

<div class="head" style="position: fixed;top:0;left:0;width:100%;z-index: 1000;">
    <div class="fanhui absolute pic"><a href="#">返回</a></div>
    <div class="title">游戏详情</div>
    <div class="fanhui-text absolute"><a href="#">${v.gameName}</a></div>
    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do">搜索</a></div>
</div>
<div class="head-after"></div>

<!--图片-->
<div class="youxi_lr_05">
    <div class="youxi_lr_06">
        <c:forEach var="p" items="${v.pics}" varStatus="i">
            <img src="${p}" width="108" height="156"/>
        </c:forEach>
    </div>
</div>
<!--介绍-->
<div class="jieshao relative">
    <div class="jieshao_img absolute"><img src="${v.iconUrl}" height="86"/></div>
    <dl class="jieshao_dl absolute">
        <dt>${v.gameName}</dt>
        <dd>大小： <fmt:formatNumber type="number" value="${v.apkSize/1024}" maxFractionDigits="2"/> MB</dd>
        <dd>版本：${v.versionName}</dd>
        <dd>类型：
            <c:forEach var="c" items="${v.categories}">
                ${c.name},

            </c:forEach>


        </dd>
    </dl>
</div>
<!--切换-->

<!--内容一-->
<div class="nr_count">
    <div class="nr_li">
        <ul>
            <c:forEach var="a" items="${v.attributes}">
                <c:choose>
                    <c:when test="${a.name=='官方正版'}">
                        <li class="zb">${a.name}</li>
                    </c:when>

                    <c:otherwise>
                        <li>${a.name}</li>
                    </c:otherwise>

                </c:choose>


            </c:forEach>
        </ul>
    </div>
</div>

<div class="nr_count2">
    <div class="nr_count1_title">游戏介绍</div>
    <div class="nr_count1_count">
        ${v.description}

    </div>
</div>
<!--TAG-->
<div class="nr_count4">
    <div class="nr_lizz">
        <ul>
            <c:forEach var="a" items="${v.tags}">
                <li>${a}</li>
            </c:forEach>
        </ul>
    </div>
</div>

<!--列表-->
<div class="w_footer_dow">
    <dl class="w_dowload">
        <dt>下载</dt>
    </dl>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>