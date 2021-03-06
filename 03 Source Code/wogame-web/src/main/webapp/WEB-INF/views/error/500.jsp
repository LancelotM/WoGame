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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>错误</title>
    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<h2>服务器故障.</h2>
<jsp:include page="../footer.jsp"/>
</body>
</html>
