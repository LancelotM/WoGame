<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
    <title>页面不存在</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<h2>页面不存在.</h2>

<p><a href="<c:url value="/main?channel=${channelId}"/>">返回首页</a></p>

<div class="bottom-record">
	<p>&copy;2009-2014 中国联通. 版权所有   京ICP备11023907号</p>
	<p>中国联合网络通信有限公司上海市分公司</p>
	<p><a href="http://store.wo.com.cn/images/zzdxxkz.png" target="_blank">增值电信许可证</a> | <a href="http://store.wo.com.cn/images/wlwhxkz.jpg"  target="_blank">网络文化许可证</a></p>
</div>
</body>
</html>