<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>分类</title>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
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
    <div class="w_new_02"><a href="#">分类</a></div>
    <div class="w_new_033"><a href="${ctx}/changWan;jsessionid=${sessionid}">0元畅玩</a></div>
    <div class="w_new_044"><a href="${ctx}/weeklyHot/list;jsessionid=${sessionid}?pageNum=1">一周热榜</a></div>
    <div class="w_new_055"><a href="${ctx}/newGame/list;jsessionid=${sessionid}?pageNum=1">最新</a></div>

    <!--<div class="w_new_077"><a href="#">分类</a></div>-->
</div>
<div id="pageContent" style="margin-bottom: 10px;">
<!--列表-->
    <c:forEach items="${list}" var="item">
        <c:if test="${item.source == 'UUC'}">
            <a href="javascript:listDetail('${item.categoryId}','${item.categoryTitle}');">
                <div class="w_list_fenlei">
                    <c:if test="${item.recommendType>0}">
                        <div class="index_xiejiao index_xiejiao_${item.recommendType}"></div>
                    </c:if>
                    <div class="w_list_img"><img src="${ctx}/static/images/gameicon.png"
                                                 data-src="${item.iconUrl}" width="60" height="60"/></div>
                    <div class="w_list_title">${item.categoryTitle}</div>
                    <div class="w_list_category" style="top:35px;">${item.description}</div>
                </div>
            </a>
        </c:if>
    </c:forEach>
</div>

<div class="bottom-record">
	<p>&copy;2009-2014 中国联通. 版权所有   京ICP备11023907号</p>
	<p>中国联合网络通信有限公司上海市分公司</p>
	<p><a href="http://store.wo.com.cn/images/zzdxxkz.png" target="_blank">增值电信许可证</a> | <a href="http://store.wo.com.cn/images/wlwhxkz.jpg"  target="_blank">网络文化许可证</a></p>
</div>

<div class="adPlaceHolder"></div>
<div id="adContainer" class="adPlaceHolder" onclick="toChangWanPage();">
    <div class="adTextContainer">
        <div class="adRow">中奖率>彩票10000倍！</div>
        <div class="adRow">只需6元1000元游戏无限次畅玩，更</div>
        <div class="adRow">可参与抽60000元+大奖</div>
    </div>
    <div class="adImg"></div>
</div>

<script type="text/javascript">
    function toChangWanPage() {
        location.href="${ctx}/changWan;jsessionid=${sessionid}";
    }
</script>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.touchwipe.js"></script>
<script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
<script type="text/javascript">
    $(function () {
        $("#pageContent .w_list_fenlei").touchwipe({

            wipeLeft: function (e) {
                e.preventDefault();
                location.href = "${ctx}/changWan;jsessionid=${sessionid}";
            },
            wipeRight: function (e) {
                e.preventDefault();
                location.href = "${ctx}/main;jsessionid=${sessionid}";
            },
            preventDefaultEvents: false
        });
    })
    ;
    function listDetail(cid, cname) {
        var url = "${ctx}/category/detail;jsessionid=${sessionid}?categoryId=" + cid + "&categoryName=" + encodeURI(encodeURI(cname));
        location.href = url;
    }
</script>
<script type="text/javascript">
    logNumber("${ctx}", ['62']);
</script>
</body>
</html>
