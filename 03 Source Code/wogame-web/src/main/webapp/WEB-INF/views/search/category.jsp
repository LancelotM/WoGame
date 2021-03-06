<%@ page contentType="text/html;charset=UTF-8" %>
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
    <title>搜索</title>

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


    <div class="title">搜索</div>

    <div class="sousuo absolute pic"><a href="#">搜索</a></div>
</div>
<div class="head-after"></div>


<!--列表-->
<div class="w_search_box" style="margin-top: 15px;">
    <div class="w_inputbox"><div class="w_in_01"><a onclick="clearSearch();" href="#">删除</a></div><div class="w_in_02"></div>
        <input type="text" value="请输入搜索内容" onfocus="if (value =='请输入搜索内容'){value =''}" onblur="if (value ==''){value='请输入搜索内容'}" id="w_input" class="w_input"></div><input type="button"  onclick="s1()" value="搜索" class="w_buttion" name="">
</div>


<div class="fenlei_search">
    <ul>

        <c:forEach var="l" items="${list}">

            <li><a href="javascript:s1('${l.word}');">${l.word}</a></li>

        </c:forEach>


    </ul>


</div>


<script type="application/javascript">

function s1(d){
    var keyword;

    if(d!=undefined){
        keyword=encodeURI(encodeURI(d));
    }else{

    var inputtext= $("#w_input").val();

        if(inputtext=="" || inputtext=="请输入搜索内容" ){

            alert("请输入搜索关键字")
            return false;
        }else{

            keyword=encodeURI(encodeURI(inputtext));
        }

    }

     location.href = "${ctx}/search/result.do;jsessionid=${sessionid}?keyword="+keyword;

}



</script>



<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
