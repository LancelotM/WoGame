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
    <title>首页</title>

    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" name="baidu-tc-cerfication" src="http://apps.bdimg.com/cloudaapi/lightapp.js#21e4cc6e9f6e857f9ba7ac86ababad5a"></script>

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
    <div class="w_new_022" data-role="none"><a href="${ctx}/category/list;jsessionid=${sessionid}">分类</a></div>
    <div class="w_new_03"><a href="${ctx}/changWan;jsessionid=${sessionid}">0元畅玩</a></div>
    <div class="w_new_044"><a href="${ctx}/weeklyHot/list;jsessionid=${sessionid}?pageNum=1">一周热榜</a></div>
    <div class="w_new_055"><a href="${ctx}/newGame/list;jsessionid=${sessionid}?pageNum=1">最新</a></div>

</div>
 
 <c:if test="${weixin == true}">
 
	<iframe src="http://sales.wostore.cn:8083/hytq/hytq.action?link=0&original=wogame&weixin=1" frameBorder=0
	        height="550px" scrolling=yes id="ifm" width="100%" name="ifm" style="margin-top:-90px;">
	</iframe> 

 </c:if>
 
<c:if test="${weixin == false}">
 
	<iframe src="http://sales.wostore.cn:8083/hytq/hytq.action?link=0&original=wogame" frameBorder=0
	        height="550px" scrolling=yes id="ifm" width="100%" name="ifm" style="margin-top:-90px;">
	</iframe> 

 </c:if>
       
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.touchwipe.js"></script>
<script type="text/javascript">
    $("#ifm").load(function () {
        $(this).height($(window).height());

//        var doc = document.getElementById('ifm' ).contentWindow.document;
//        alert(doc.body.innerHTML);
    });


</script>

</body>
</html>
