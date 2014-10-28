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
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>

</head>

<body class="ibody_bg">
<!--top-->

<div class="head" style="position: fixed;top:0;left:0;width:100%;z-index: 1000;">

    <a href="#" onclick="javascript:history.go(-1); return false">
        <div class="fanhui absolute pic"></div>

        <div class="fanhui-text absolute" style="color:#FF9C00 ">${v.gameName}</div>
    </a>


    <div class="title"></div>
    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do;jsessionid=${sessionid}">搜索</a></div>
</div>
<div class="head-after"></div>
<!--图片-->
<div class="youxi_lr_05">
    <div class="youxi_lr_06" style="width:${fn:length(v.pics) * 120}px">
        <c:forEach var="p" items="${v.pics}" varStatus="index">
            <a href="javascript:popup('popdiv${index.index}')">
                <img src="${p}" width="108" height="156"/>
            </a>
        </c:forEach>
    </div>
</div>

<!--弹出层,默认不显示-->
<c:forEach var="p" items="${v.pics}" varStatus="index">
    <div id="popdiv${index.index}" style="display:none;position: absolute;z-index: 9999;top:5px;left:5px;">
        <img src="${p}" onclick="jvascript:$('#popdiv${index.index}').hide();"/>
    </div>
</c:forEach>


<!--介绍-->
<div class="jieshao relative">
    <div class="jieshao_img absolute"><img src="${v.iconUrl}" height="86"/></div>
    <dl class="jieshao_dl absolute">
        <dt>
            <c:choose>
                <c:when test="${fn:length(v.gameName) > 14}">
                    <c:out value="${fn:substring(v.gameName, 0, 12)}..."/>
                </c:when>
                <c:otherwise>
                    <c:out value="${v.gameName}"/>
                </c:otherwise>
            </c:choose>
        </dt>
        <dd>大小： <fmt:formatNumber type="number" value="${v.apkSize/1024}" maxFractionDigits="2"/> MB</dd>
        <dd>版本：${v.versionName}</dd>
        <c:if test="${fn:length(v.categories) > 0}">
            <dd class="etc">类型：

                <c:forEach var="c" items="${v.categories}">


                    <c:if test="${flag}">,</c:if>
                    ${c.name}

                    <c:set var="flag" value="true" scope="request"/>


                </c:forEach>


            </dd>
        </c:if>
    </dl>
</div>


<!--切换-->

<!--内容一-->
<c:if test="${fn:length(v.attributes) > 0}">
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
</c:if>



<div class="youxi_lr_02">
    <div class="youxi_lr_02_title">游戏介绍</div>
</div>
<div class="youxi_lr_03 youxi_desc_height_min" onclick="toggleShowDesc();">
    ${v.description}

    <div class="youxi_lr_04 youxi_lr_04_bottom"
         style="width: 12px;position: absolute; right: 0px; bottom: 10px; ><a href=" javascript:void(0);" ></a></div>
</div>


<!--TAG-->
<c:if test="${fn:length(v.tags) > 0}">
    <div class="nr_count4">
        <div class="nr_lizz">
            <ul>
                <c:forEach var="a" items="${v.tags}">
                    <li>${a}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>

<div  class="nr_count4">
    <jsp:include page="footer.jsp"/>


</div>

<!--列表-->
<div class="w_footer_dow">


    <dl class="w_dowload">
        <a href="javascript:download('${v.productId}','${v.gameName}','${v.iconUrl}')" data-role="none">
            <dt>下载</dt>
        </a>
    </dl>
</div>

<script type="application/javascript">

    function popup(id) {
        $('#' + id).show();
    }


    var descEle = $(".youxi_lr_03");
    var descImg = $(".youxi_lr_04");
    function toggleShowDesc() {

        if (descEle.hasClass("youxi_desc_height_min")) {
            descEle.removeClass("youxi_desc_height_min");
            descImg.removeClass("youxi_lr_04_bottom").addClass("youxi_lr_04_up");
        } else {
            descEle.addClass("youxi_desc_height_min");
            descImg.removeClass("youxi_lr_04_up").addClass("youxi_lr_04_bottom");
        }

    }


    function download(id, name, icon) {

        doDownload("${ctx}/download.do;jsessionid=${sessionid}", id, name, icon);
    }


</script>

</body>
</html>