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
    <link href="${ctx}/static/styles/new_main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

    <script type="text/javascript" src="${ctx}/static/js/slider.js"></script>

    <!--弹出文本提示写入cooking-->
    <script type="text/javascript">
        function cookiesave(n, v, mins, dn, path) {
            if (n) {

                if (!mins) mins = 365 * 24 * 60;
                if (!path) path = "/";
                var date = new Date();

                date.setTime(date.getTime() + (mins * 60 * 1000));

                var expires = "; expires=" + date.toGMTString();

                if (dn) dn = "domain=" + dn + "; ";
                document.cookie = n + "=" + v + expires + "; " + dn + "path=" + path;
            }
        }
        function cookieget(n) {
            var name = n + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) == ' ') c = c.substring(1, c.length);
                if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
            }
            return "";
        }
        function closeclick() {
            document.getElementById('note').style.display = 'none';
            cookiesave('closeclick', 'closeclick', '', '', '');
        }
        function clickclose() {
            if (cookieget('closeclick') == 'closeclick') {
                document.getElementById('note').style.display = 'none';
            } else {
                document.getElementById('note').style.display = 'block';
            }
        }
        window.onload = clickclose;

        function searchkey() {
            var key = $("#w_input").val();
            if (key == "" || key == "请输入搜索内容") {

                alert("请输入关键字");
                return false;

            } else {

                return true;
            }


        }

    </script>
</head>

<body class="ibody_bg">
<!--top-->
<div class="head_index relative">
    <div class="logo absolute"><a href="${ctx}/index.do">首页</a></div>
    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do">搜索</a></div>
</div>
<!--图片广告位-->

<c:if test="${!empty b.topAD}">
    <div id="note" class="note"
         style="display:none;background:url(${ctx}/static/images/icon_huodong.png) #ffffcf 15px center no-repeat;background-size:30px 30px;position: relative">
        <div>${b.topAD.title}<a style="position: absolute; right: 0px;" href="#" onclick="closeclick()"
                                class="guanbi"><img
                src="${ctx}/static/images/colose.png"
                border="0"/></a></div>
    </div>
</c:if>
<c:if test="${fn:length(adList) > 0}">
    <div class="hot_banner">
        <div class="block_home_slider">
            <div id="home_slider" class="flexslider">
                <ul class="slides">


                    <c:forEach var="a" items="${adList}">
                        <li>
                            <div class="slide">
                                <c:choose>
                                    <%--游戏--%>
                                <c:when test="${a.banner.resType==2}">
                                <a href="${ctx}/gamedetail/detaillist.do?product_id=${a.banner.linkId}">
                                    </c:when>
                                        <%--专题--%>
                                    <c:when test="${a.banner.resType==3}">

                                    <a href="${ctx}/subject/detailList.do?id=${a.banner.linkId}">

                                        </c:when>
                                            <%--活动--%>
                                        <c:when test="${a.banner.resType==4}">
                                        <a href="${ctx}/gameInfo/detail.do?id=${a.banner.linkId}">


                                            </c:when>


                                            </c:choose>


                                            <img src="${a.banner.bannerUrl}" alt=""/>
                                        </a>

                                        <div class="caption">
                                        </div>
                            </div>
                        </li>
                    </c:forEach>


                </ul>
            </div>

            <script type="text/javascript" language="JavaScript">
                $(function () {
                    $('#home_slider').flexslider({
                        animation: 'slide',
                        controlNav: true,
                        directionNav: true,
                        animationLoop: true,
                        slideshow: false,
                        useCSS: false
                    });

                });
            </script>
        </div>


    </div>
</c:if>
<!--搜索-->
<!--列表-->
<div class="w_search_box" style="margin-top: 15px;">
    <div class="w_inputbox"><div class="w_in_01"><a onclick="clearSearch();" href="#">删除</a></div>
        <div class="w_in_02"></div>
        <input type="text" value="请输入搜索内容" onfocus="if (value =='请输入搜索内容'){value =''}" onblur="if (value ==''){value='请输入搜索内容'}" id="w_input" class="w_input">
    </div><input type="button"  onclick="search()" value="搜索" class="w_buttion" name="">
</div>
<!--活动-->


<dl class="huodong_box">


    <c:set var="s" value="1"></c:set>
    <c:forEach var="mo" items="${b.activityModule}">
        <c:if test="${mo.adType==3 && s==1}">
            <dt>
            <div class="huodong">
                <div class="index_hd_title">网游活动</div>
                <a href="${ctx}/netgame/info.do">
                    <div class="index_hd_count"><img src="img/4.png" height="100"/></div>
                    <div class="index_hd_url">${mo.description}</div>
                </a>
                <c:set var="s" value="2"></c:set>
            </div>
            </dt>
        </c:if>
    </c:forEach>


    <c:set var="i" value="2"></c:set>
    <c:forEach var="mo" items="${b.activityModule}">

    <c:choose>

    <c:when test="${mo.adType==3 && i==2}">

        <c:set var="i" value="1"></c:set>

    </c:when>

    <c:when test="${mo.adType==3 && i==1}">

    <dd>
        <div class="huodong_r">
            <div class="index_hd_title">活动</div>

            <a href="${ctx}/activity/list.do">
                <div class="index_hd_count"><img src="img/5.png" width="320" height="160"/></div>
                <div class="index_hd_url">${mo.description}</div>
            </a>

        </div>
    </dd>
</dl>
</c:when>


</c:choose>


</c:forEach>


<!--热门游戏-->
<c:if test="${fn:length(hot) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i1"></em><span>火热</span></div>
        <div class="hot_youxi_count">
            <ul>
                <c:forEach var="h" items="${hot}">
                    <li>
                        <dl class="hotyouxi_list">
                            <dt>
                                <a href="${ctx}/gamedetail/detaillist.do?product_id=${h.productId}"><img
                                        src="${h.iconUrl}"/></a>
                            </dt>
                            <dd class="tit"><a href="#">


                                <c:choose>
                                    <c:when test="${fn:length(h.title) > 4}">
                                        <c:out value="${fn:substring(h.title, 0, 4)}..."/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${h.title}"/>
                                    </c:otherwise>
                                </c:choose>


                            </a></dd>
                            <dd class="mb">
                                <fmt:formatNumber value="${h.apkSize/1024}" pattern="##.#" minFractionDigits="1"/>M
                            </dd>
                            <dd class="dow">
                                <a href="#">下载</a></dd>
                        </dl>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="hot_youxi_more"><a href="${ctx}/recommend/init.do"><span>更多火热游戏</span></a></div>
    </div>
</c:if>
<!--活动版块-->
<div class="hot_youxi">
    <div class="hot_youxi_title"><em class="i2"></em><span>活动</span></div>

    <c:set var="s1" value="1"></c:set>
    <c:forEach var="mo" items="${b.activityBanner}">


        <c:if test="${mo.adType==4 && s1==1}">
            <div class="huodong_hot">
                <div class="huodongzt">
                    <a href="${mo.url}">
                        <div class="index_hd_count"><img src="${mo.imageName}" height="130"/></div>
                        <div class="index_hd_url">${mo.description}</div>
                    </a>
                </div>
            </div>
            <c:set var="s1" value="2"></c:set>
        </c:if>
    </c:forEach>


    <c:set var="i" value="2"></c:set>
    <dl class="huodong_box22">
        <c:forEach var="mo" items="${b.activityBanner}">
            <dd>
                <c:choose>
                    <c:when test="${mo.adType==4 && i==2}">

                        <c:set var="i" value="1"></c:set>

                    </c:when>

                    <c:when test="${mo.adType==4 && i==1}">


                        <div class="huodong">
                            <a href="#">
                                <div class="index_hd_count"><img src="img/5.png" width="320" height="160"/></div>
                                <div class="index_hd_url">${mo.description}</div>
                            </a>
                        </div>
                        <c:set var="i" value="0"></c:set>

                    </c:when>
                    <c:when test="${mo.adType==4 && i==0}">
                        <div class="huodong_r">
                            <a href="#">
                                <div class="index_hd_count"><img src="img/5.png" width="320" height="160"/></div>
                                <div class="index_hd_url">${mo.description}</div>
                            </a>
                        </div>
                    </c:when>

                </c:choose>

            </dd>

        </c:forEach>
    </dl>


    <div class="hot_youxi_more"><a href="${ctx}/activity/list.do"><span>全部活动</span></a></div>
</div>
<!--最新榜-->
<c:if test="${fn:length(newest) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i3"></em><span>最新榜</span></div>
        <!--列表-->

        <c:forEach var="n" items="${newest}">
            <div class="pro_list"><a href="#">
                <div class="pro_cp">
                    <a href="${ctx}/gamedetail/detaillist.do?product_id=${n.productId}">
                        <div class="pro_cp_l"><img src="${n.iconUrl}" height="86"/></div>
                    </a>
                    <dl class="pro_cp_c">
                        <dt>${n.gameName}</dt>
                        <dd><fmt:formatNumber value="${n.apkSize/1024}" minFractionDigits="2"></fmt:formatNumber> MB
                        </dd>
                    </dl>
                    <div class="pro_cp_r radius">下载</div>
                </div>
                <div class="plist_f etc">${n.intro}</div>
            </a>
            </div>
        </c:forEach>


        <div class="hot_youxi_more"><a href="${ctx}/newGame/topNewest.do"><span>查看更多最新榜</span></a></div>
    </div>
</c:if>
<!--开服信息-->
<c:if test="${fn:length(netGame.netGameServerItemVoList) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i4"></em><span>${netGame.sectionName}</span></div>
        <!--列表-->
        <div class="kaifu_box">
            <ul>

                <c:forEach var="n" items="${netGame.netGameServerItemVoList}">
                    <li><a href="${ctx}/gamedetail/detaillist.do?product_id=${n.productId}">
                        <div class="kf_left">
                            <div class="kf_img"><img src="${n.iconUrl}" height="86"/></div>
                            <div class="kf_dow radius">下载</div>
                        </div>
                        <div class="kf_right">
                            <div class="kfr_1">

                                <c:choose>
                                    <c:when test="${fn:length(n.gameName) > 4}">
                                        <c:out value="${fn:substring(n.gameName, 0, 4)}..."/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${n.gameName}"/>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                            <div class="kfr_2"><fmt:formatNumber pattern="##.#" value="${n.apkSize/1024}"
                                                                 minFractionDigits="1"></fmt:formatNumber> MB
                            </div>
                            <div class="kfr_3 etc">新服:${n.serverName}</div>
                            <div class="kfr_4 etc" style="letter-spacing:-1px;">时间:

                                <jsp:useBean id="date" class="java.util.Date"/>
                                <jsp:setProperty name="date" property="time" value="${n.openTime}"/>
                                <fmt:formatDate value="${date}" pattern="yyyy.MM.dd"/>

                            </div>
                        </div>
                    </a></li>
                </c:forEach>

            </ul>
        </div>
        <!--<div class="hot_youxi_more"><a href="#"><span>查看更多飚升榜</span></a></div>
        --></div>
</c:if>

<!--分类-->
<c:if test="${fn:length(category) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i6"></em><span>分类</span></div>
        <!--列表-->
        <div class="new_fl">
            <ul>

                <c:forEach var="ca" items="${category}">
                    <li><a href="${ctx}/category/detail.do?categoryId=${ca.id}&categoryName=${ca.name}"
                           class="nf1">${ca.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="hot_youxi_more"><a href="${ctx}/category/list.do"><span>查看更多</span></a></div>
    </div>
</c:if>
<!-- 底部公告-->
<c:if test="${empty b.bottomAD }">
    <div id="note" class="note"
         style="background:url(images/icon_huodong.png) #ffffcf 15px center no-repeat;background-size:30px 30px;">
        <div>${b.bottomAD.title}<a href="#" onclick="closeclick()" class="guanbi"><img src="images/colose.png"
                                                                                       border="0"/></a></div>
    </div>
</c:if>


<!--主menu区-->
<div class="user_menu">
    <ul>
        <li><a href="${ctx}/subject/list.do">
            <dl class="user_icon">
                <dt><img src="${ctx}/static/images/user_icon8.png"/></dt>
                <dd>专题</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/member.do">
            <dl class="user_icon2">
                <dt><img src="${ctx}/static/images/user_icon3.png"/></dt>
                <dd>0元畅玩</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/newGame/topNewest.do">
            <dl class="user_icon3">
                <dt><img src="${ctx}/static/images/user_icon4.png"/></dt>
                <dd>新游排行</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/category/list.do">
            <dl class="user_icon4">
                <dt><img src="${ctx}/static/images/user_icon5.png"/></dt>
                <dd>分类</dd>
            </dl>
        </a></li>
    </ul>
</div>
<script type="application/javascript">

    function clearSearch() {

        $("#w_input").val('');

    }

    function search(){
        var keyword;
        var inputtext= $("#w_input").val();

        if(inputtext=="" || inputtext=="请输入搜索内容" ){

            alert("必须项")
            return false;
        }else{

            keyword=encodeURI(encodeURI(inputtext));

           location.href = "${ctx}/search/result.do?keyword="+keyword;
        }
    }





</script>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>