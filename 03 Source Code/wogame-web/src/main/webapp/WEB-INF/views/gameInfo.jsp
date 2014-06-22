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
    <title>${title}</title>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w_search2"><a href="#">搜索</a></div>

</div>
<!--分类筛选-->
<div class="w_paihangtitle">
    <!--选中状态-->
    <div class="w_new_01"><a href="#">详情</a></div>
    <!--<div class="w_new_02"><a href="#">玩家评论</a></div>-->
    <!--没有选中--><!--<div class="w_new_011"><a href="#">详情</a></div>-->
    <%--<div class="w_new_022"><a href="#">玩家评论</a></div>--%>
</div>
<!--列表-->
<div class="youxi_lr_01">
    <div class="w_img_bg"><a href="#"><img src="${info.iconUrl}" width="100" height="100"/></a></div>
    <div class="w_start_0${info.rate}"></div>
    <div class="w_img_txt">
        <ul>
            <li>类型：${info.catagory}</li>
            <li>大小：${info.size / 1000}MB</li>
            <li>时间：${info.appDate}</li>
            <li>版本：${info.versionName}</li>
        </ul>
    </div>
</div>
<div class="youxi_lr_02">
    <div class="youxi_lr_02_title">介绍</div>
</div>
<div class="youxi_lr_03">
    ${inf.description}
</div>
<div class="youxi_lr_04"><a href="#">更多</a></div>
<div class="youxi_lr_05">
    <div class="youxi_lr_06">
        <c:forEach var="screenshot" items="${item.screenshots}">
            <img src="${screenshot}" width="108" height="156"/>
        </c:forEach>
    </div>
</div>

<!--列表-->
<div class="w_footer_dow">
    <dl class="w_dowload">
        <dt>下载</dt>
    </dl>
</div>
</body>
</html>
