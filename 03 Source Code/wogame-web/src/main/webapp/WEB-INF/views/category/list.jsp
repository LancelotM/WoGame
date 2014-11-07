<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sessionid" value="${pageContext.request.requestedSessionId}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <Link Rel="ICON NAME" href="${ctx}/favicon.ico">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=2.0">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="false" id="twcClient" name="twcClient">
    <title>分类</title>

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

<div class="head" style="position: fixed;top:0;left:0;width:100%;z-index: 1000;">

    <a   href="${ctx}/main.do;jsessionid=${sessionid}">
        <div class="fanhui absolute pic"></div>

        <div class="fanhui-text absolute" style="color:#FF9C00 ">首页</div>
    </a>

    <div class="title">分类</div>

    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do;jsessionid=${sessionid}">搜索</a></div>
</div>
<div class="head-after"></div>
<div style="height: 15px;"></div>


<!--<div class="w_new_077"><a href="#">分类</a></div>-->
</div>
<div id="pageContent" style="margin-bottom: 10px;">
    <!--列表-->
    <c:forEach items="${list}" var="i">

        <div class="hd_l_title">
            <div class="hdjianjie">${i.name}</div>
            <div class="hdmore">

                <a href="javascript:category('${i.id}','${i.name}');">查看全部</a>
            </div>
        </div>
        <div class="fenlei">
            <ul>
                <c:forEach var="ie" items="${i.items}">

                    <li>

                        <a href="javascript:category('${ie.id}','${ie.name}');">${ie.name}</a>
                    </li>

                </c:forEach>
            </ul>
        </div>


    </c:forEach>
</div>


<script type="text/javascript">
    function toChangWanPage() {
        location.href = "${ctx}/changWan;jsessionid=${sessionid}";
    }
    function category(id,name){
        var name=encodeURI(encodeURI(name));

        location.href = "${ctx}/category/detail.do;jsessionid=${sessionid}?categoryId="+id+"&categoryName="+name;

    }


</script>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
