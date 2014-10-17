<!DOCTYPE HTML>
<#macro template csses=[] jses=[]>
    <#include "/common/header.ftl">
    <#include "/common/footer.ftl">
    <#assign basePath=attrs.contextPath>

<html>
<head>
	<title>WoGameCenter</title>
	<link rel="Shortcut Icon" href="${basePath}/static/images/favicon.ico" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    
    <!-- js -->
    <script type="text/javascript" src="${basePath}/static/js/libs/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}/static/js/libs/highcharts.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/libs/jquery.paginate.js"></script>
	<script type="text/javascript" src="${basePath}/static/js/menuControl.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/libs/jquery-ui.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/libs/jquery.dragsort-0.4.min.js"></script>

    <!-- css -->
    <link href="${basePath}/static/css/style.css" rel="stylesheet">
    <link href="${basePath}/static/css/jquery-ui.css" rel="stylesheet">
    <#--csses css-->
    <#if csses??>
        <#list csses as css>
    		<link href="${basePath}/static/css/${css}.css" rel="stylesheet">
        </#list>
    </#if>
</head>

<@header></@header>
<body>
	<div type="hidden" id="basePath" value=${basePath}></div>
	<#nested>
</body>
<@footer></@footer>
<#--jses js-->
    <#if jses??>
        <#list jses as js>
    		<script type="text/javascript" src="${basePath}/static/js/${js}.js"></script>
        </#list>
    </#if>
</html>
</#macro>
