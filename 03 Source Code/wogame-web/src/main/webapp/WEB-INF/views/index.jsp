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
    <script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
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
    </script>
</head>

<body class="ibody_bg">
<!--top-->
<div class="head_index relative">
    <div class="logo absolute"><a href="/">首页</a></div>
    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do" >搜索</a></div>
</div>
<!--图片广告位-->
<div id="note" class="note"
     style="display:none;background:url(images/icon_huodong.png) #ffffcf 15px center no-repeat;background-size:30px 30px;">
    <div>为了庆祝十一，关注官方微信送价值千元大礼包一份<a href="#" onclick="closeclick()" class="guanbi"><img src="images/colose.png"
                                                                                       border="0"/></a></div>
</div>

<div class="hot_banner">
    <div class="block_home_slider">
        <div id="home_slider" class="flexslider">
            <ul class="slides">


                <c:forEach var="a" items="${adList}">
                    <li>
                        <div class="slide">
                            <img src="${a.banner.bannerUrl}" alt=""/>

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
<script type="text/javascript" src="${ctx}/static/js/slider.js"></script>
<!--搜索-->
<div class="index_search_box">
    <div class="w_inputbox">
        <div class="w_in_01"><a href="#">删除</a></div>
        <div class="w_in_02">
            <form action="${ctx}/search/result.do" onsubmit=" return searchkey()" >

        </div>
        <input type="text" value="请输入搜索内容" onfocus="if (value =='请输入搜索内容'){value =''}"
               onblur="if (value ==''){value='请输入搜索内容'}" id="w_input" class="w_input" name="keyword"></div>

    <input type="submit" value="搜索" class="w_buttion">
    </form>



        </div>

</div>
<!--活动-->
<dl class="huodong_box">
    <dt>
    <div class="huodong">
        <div class="index_hd_title">网游活动</div>
        <a href="#">
            <div class="index_hd_count"><img src="img/4.png" height="100"/></div>
            <div class="index_hd_url">每天最开心的三件事就是：吃饭、睡觉、打僵尸……</div>
        </a>
    </div>
    </dt>
    <dd>
        <div class="huodong_r">
            <div class="index_hd_title">活动</div>
            <a href="#">
                <div class="index_hd_count"><img src="img/5.png" width="320" height="160"/></div>
                <div class="index_hd_url">吐血推荐！不好玩你打我，别打死就行，哈哈，来打我啊</div>
            </a>
        </div>
    </dd>
</dl>

<!--热门游戏-->
<div class="hot_youxi">
    <div class="hot_youxi_title"><em class="i1"></em><span>火热</span></div>
    <div class="hot_youxi_count">
        <ul>
            <c:forEach var="h" items="${hot}">
                <li>
                    <dl class="hotyouxi_list">
                        <dt>
                            <a href="${ctx}/gamedetail/detaillist.do?product_id=${h.productId}"><img src="${h.iconUrl}"/></a>
                        </dt>
                        <dd class="tit"><a href="#">${h.gameName}</a></dd>
                        <dd class="mb">
                            <fmt:formatNumber value="${h.apkSize/1024}" minFractionDigits="2"></fmt:formatNumber>M
                        </dd>
                        <dd class="dow">
                            <a href="#">下载</a></dd>
                    </dl>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="hot_youxi_more"><a href="${ctx}/newGame/topHotest.do" ><span>更多火热游戏</span></a></div>
</div>
<!--活动版块-->
<div class="hot_youxi">
    <div class="hot_youxi_title"><em class="i2"></em><span>活动</span></div>
    <div class="huodong_hot">


        <div class="huodongzt">
            <a href="#">
                <div class="index_hd_count"><img src="img/n1.png" height="130"/></div>
                <div class="index_hd_url">每天最开心的三件事就是：吃饭、睡觉、打僵</div>
            </a>
        </div>


    </div>


    <dl class="huodong_box22">
        <dt>
        <div class="huodong">
            <a href="#">
                <div class="index_hd_count"><img src="img/4.png" height="100"/></div>
                <div class="index_hd_url">每天最开心的三件事就是：吃饭、睡觉、打僵尸……</div>
            </a>
        </div>
        </dt>
        <dd>
            <div class="huodong_r">
                <a href="#">
                    <div class="index_hd_count"><img src="img/5.png" width="320" height="160"/></div>
                    <div class="index_hd_url">吐血推荐！不好玩你打我，别打死就行，哈哈，来打我啊</div>
                </a>
            </div>
        </dd>
    </dl>
    <div class="hot_youxi_more"><a href="${ctx}/gameInfo/list.do"   ><span>全部活动</span></a></div>
</div>
<!--最新榜-->
<div class="hot_youxi">
    <div class="hot_youxi_title"><em class="i3"></em><span>最新榜</span></div>
    <!--列表-->

    <c:forEach var="n" items="${newest}">
        <div class="pro_list"><a href="#">
            <div class="pro_cp">
                <a href="${ctx}/gamedetail/detaillist.do?product_id=${n.productId}">
                <div class="pro_cp_l"><img src="${n.iconUrl}" height="86"/></div></a>
                <dl class="pro_cp_c">
                    <dt>${n.gameName}</dt>
                    <dd><fmt:formatNumber value="${n.apkSize/1024}" minFractionDigits="2"></fmt:formatNumber> MB</dd>
                </dl>
                <div class="pro_cp_r radius">下载</div>
            </div>
            <div class="plist_f etc">${n.intro}</div>
        </a>
        </div>
    </c:forEach>


    <div class="hot_youxi_more"><a href="${ctx}/newGame/topNewest.do"><span>查看更多最新榜</span></a></div>
</div>
<!--开服信息-->
<div class="hot_youxi">
    <div class="hot_youxi_title"><em class="i4"></em><span>开服信息</span></div>
    <!--列表-->
    <div class="kaifu_box">
        <ul>

            <c:forEach var="n" items="${netGame}">
                <li><a href="${ctx}/gamedetail/detaillist.do?product_id=${n.productId}">
                    <div class="kf_left">
                        <div class="kf_img"><img src="${n.iconUrl}" height="86"/></div>
                        <div class="kf_dow radius">下载</div>
                    </div>
                    <div class="kf_right">
                        <div class="kfr_1">${n.gameName}</div>
                        <div class="kfr_2"><fmt:formatNumber value="${n.apkSize/1024}"
                                                             minFractionDigits="2"></fmt:formatNumber> MB
                        </div>
                        <div class="kfr_3">新服:${n.serverName}</div>
                        <div class="kfr_4">时间:

                            <jsp:useBean id="date" class="java.util.Date"/>
                            <jsp:setProperty name="date" property="time" value="${n.openTime}"/>
                            <fmt:formatDate value="${date}" type="date"/>

                        </div>
                    </div>
                </a></li>
            </c:forEach>

        </ul>
    </div>
    <!--<div class="hot_youxi_more"><a href="#"><span>查看更多飚升榜</span></a></div>
    --></div>

<!--分类-->
<div class="hot_youxi">
    <div class="hot_youxi_title"><em class="i6"></em><span>分类</span></div>
    <!--列表-->
    <div class="new_fl">
        <ul>

            <c:forEach var="ca" items="${category}">
                <li><a href="${ctx}/category/detail.do?categoryId=${ca.id}&categoryName=${ca.name}" class="nf1">${ca.name}</a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="hot_youxi_more"><a href="${ctx}/category/list.do"><span>查看更多</span></a></div>
</div>

<!--主menu区-->
<div class="user_menu">
    <ul>
        <li><a href="#">
            <dl class="user_icon">
                <dt><img src="images/user_icon8.png"/></dt>
                <dd>专题</dd>
            </dl>
        </a></li>
        <li><a href="#">
            <dl class="user_icon2">
                <dt><img src="images/user_icon3.png"/></dt>
                <dd>0元畅玩</dd>
            </dl>
        </a></li>
        <li><a href="#">
            <dl class="user_icon3">
                <dt><img src="images/user_icon4.png"/></dt>
                <dd>新游排行</dd>
            </dl>
        </a></li>
        <li><a href="#">
            <dl class="user_icon4">
                <dt><img src="images/user_icon5.png"/></dt>
                <dd>分类</dd>
            </dl>
        </a></li>
    </ul>
</div>

<div class="footer"><a href="#">更多精彩内容，请下载沃游戏客户端</a></div>
</body>
</html>
