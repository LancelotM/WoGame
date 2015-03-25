<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
    <title>页面不存在</title>
    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<h2>页面不存在.</h2>

<p><a href="<c:url value="/main?channel=${channelId}"/>">返回首页</a></p>
<jsp:include page="../footer.jsp"/>
</body>
</html>