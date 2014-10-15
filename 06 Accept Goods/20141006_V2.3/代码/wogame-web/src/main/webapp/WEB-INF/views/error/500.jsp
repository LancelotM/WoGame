<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%
    //设置返回码200，避免浏览器自带的错误页面
    response.setStatus(200);
    //记录日志
    Logger logger = LoggerFactory.getLogger("500.jsp");
    logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<html>
<head>
    <title>错误</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<h2>服务器故障.</h2>

<div class="bottom-record">
	<p>&copy;2009-2014 中国联通. 版权所有   京ICP备11023907号</p>
	<p>中国联合网络通信有限公司上海市分公司</p>
	<p><a href="http://store.wo.com.cn/images/zzdxxkz.png" target="_blank">增值电信许可证</a> | <a href="http://store.wo.com.cn/images/wlwhxkz.jpg"  target="_blank">网络文化许可证</a></p>
</div>

</body>
</html>
