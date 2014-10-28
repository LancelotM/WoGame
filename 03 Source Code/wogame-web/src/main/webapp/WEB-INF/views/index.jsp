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
    <link href="${ctx}/static/styles/slides.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/styles/paging.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.slides.min.js"></script>
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
    <script type="text/javascript">
        <c:if test="${isIndex == true}">
        logNumber("${ctx}", [isOldUser() ? '81' : '80']);
        </c:if>

        logNumber("${ctx}", ['61']);
    </script>

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

            $('#slides').slidesjs({
                width: 320,
                height: 160,
                navigation: false,
                start: 1,
                play: {
                    auto: true
                }


            });


        });
    </script>


</head>

<body class="ibody_bg">
<!--top-->
<div class="head_index relative">
    <div class="logo absolute"><a href="${ctx}/main.do;jsessionid=${sessionid}">首页</a></div>
    <div class="sousuo absolute pic"><a href="${ctx}/search/init.do;jsessionid=${sessionid}">搜索</a></div>
</div>
<!--图片广告位-->
<input type="hidden" value="${ctx}" id="ctx"/>
<c:if test="${!empty b.topAD}">
    <div id="note" class="note"
         style="display:none;background:url(${ctx}/static/images/icon_huodong.png) #ffffcf 15px center no-repeat;background-size:30px 30px;position: relative">
        <div><a href="javascript:bannerdetail('${b.topAD.url}','${b.topAD.title}')">${b.topAD.description}</a><a
                style="position: absolute; right: 0px;" href="#" onclick="closeclick()"
                class="guanbi"><img
                src="${ctx}/static/images/colose.png"
                border="0"/></a></div>
    </div>
</c:if>




<!--大图-->
<c:if test="${fn:length(adList) > 0}">
    <div id="pic_div" class="container block_home_slider">
        <div id="slides" >
            <c:forEach var="a" items="${adList}">
                <c:choose>
                    <%--外链--%>
                    <c:when test="${a.banner.resType==10}">
                        <img src="${a.banner.bannerUrl}"
                             onclick="javascript:bannerdetail('${a.banner.linkId}','${a.title}')" alt=""/>
                    </c:when>

                    <%--游戏--%>
                    <c:when test="${a.banner.resType==2}">
                        <img src="${a.banner.bannerUrl}"
                         onclick="javascript:toAdDetail('${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=${a.banner.linkId}')"
                             alt=""/>
                    </c:when>

                    <%--专题--%>
                    <c:when test="${a.banner.resType==3}">
                        <img src="${a.banner.bannerUrl}"
                             onclick="javascript:toAdDetail('${ctx}/subject/detailList.do;jsessionid=${sessionid}?id=${a.banner.linkId}')" alt=""/>
                    </c:when>

                    <%--活动--%>
                    <c:when test="${a.banner.resType==4}">
                        <img src="${a.banner.bannerUrl}"
                        onclick="javascript:toAdDetail('${ctx}/gameInfo/detail.do;jsessionid=${sessionid}?id=${a.banner.linkId}')"
                             alt=""/>


                    </c:when>

                </c:choose>


            </c:forEach>
        </div>
    </div>
</c:if>


<!--搜索-->
<!--列表-->
<div class="w_search_box" style="margin-top: 15px;">
    <div class="w_inputbox">
        <div class="w_in_01"><a onclick="clearSearch();" href="#">删除</a></div>
        <div class="w_in_02"></div>
        <input type="text" value="请输入搜索内容" onfocus="if (value =='请输入搜索内容'){value =''}"
               onblur="if (value ==''){value='请输入搜索内容'}" id="w_input" class="w_input">
    </div>
    <input type="button" onclick="search()" value="搜索" class="w_buttion" name="">
</div>
<!--活动-->


<!--活动-->
<c:if test="${fn:length(bannerList) > 0}">
    <c:set var="s" value="1"></c:set>
    <dl class="huodong_box">
        <c:forEach var="mo" items="${bannerList}">

            <c:choose>

                <c:when test="${s==1}">

                    <dt>
                    <div class="huodong">
                        <div class="index_hd_title">${mo.title}</div>
                        <a href="${ctx}/${mo.url};jsessionid=${sessionid}">
                            <div class="index_hd_count"><img src="${mo.imageName}" height="100"/></div>
                            <div class="index_hd_url">${mo.description}</div>
                        </a>
                    </div>
                    </dt>

                    <c:set var="s" value="2"></c:set>

                </c:when>
                <c:when test="${s==2}">

                    <dt>
                    <div class="huodong_r">
                        <div class="index_hd_title">${mo.title}</div>
                        <a href="${ctx}/${mo.url};jsessionid=${sessionid}">
                            <div class="index_hd_count"><img src="${mo.imageName}" height="100"/></div>
                            <div class="index_hd_url">${mo.description}</div>
                        </a>
                    </div>
                    </dt>
                </c:when>
            </c:choose>
        </c:forEach>
    </dl>
</c:if>

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

                                <a href="${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=${h.productId}"><img
                                        src="${h.iconUrl}"/></a>

                            </dt>
                            <dd class="tit etc"><a href="#">${h.title}
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
        <div class="hot_youxi_more"><a href="${ctx}/recommend/init.do;jsessionid=${sessionid}"><span>更多火热游戏</span></a>
        </div>
    </div>
</c:if>

<!--活动版块-->
<c:if test="${fn:length(bList) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i2"></em><span>${bList[0].title}</span></div>
        <div class="huodong_hot">

            <div class="huodongzt">
                <a href="${bList[0].url}">
                    <div class="index_hd_count"><img src="${bList[0].imageName}" height="130"/></div>
                    <div class="index_hd_url">${bList[0].description}</div>
                </a>
            </div>

        </div>


        <dl class="huodong_box22">
            <dt>
            <div class="huodong">
                <a href="${bList[1].url}">
                    <div class="index_hd_count"><img src="${bList[1].imageName}" height="100"/></div>
                    <div class="index_hd_url">${bList[1].description}</div>
                </a>
            </div>
            </dt>
            <dd>
                <div class="huodong_r">
                    <a href="${bList[2].url}">
                        <div class="index_hd_count"><img src="${bList[2].imageName}" width="320" height="160"/></div>
                        <div class="index_hd_url">${bList[2].description}</div>
                    </a>
                </div>
            </dd>
        </dl>
        <div class="hot_youxi_more"><a href="${ctx}/activity/list.do;jsessionid=${sessionid}"><span>全部活动</span></a>
        </div>
    </div>
</c:if>


<!--最新榜-->
<c:if test="${fn:length(newest) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i3"></em><span>最新榜</span></div>
        <!--列表-->

        <c:forEach var="n" items="${newest}">
            <div class="pro_list"><a href="#">
                <div class="pro_cp">
                    <a href="${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=${n.productId}">
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


        <div class="hot_youxi_more"><a
                href="${ctx}/newGame/topNewest.do;jsessionid=${sessionid}"><span>查看更多最新榜</span></a></div>
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
                    <li><a href="${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=${n.productId}">
                        <div class="kf_left">
                            <div class="kf_img"><img src="${n.iconUrl}" height="86"/></div>
                            <div class="kf_dow radius">下载</div>
                        </div>
                        <div class="kf_right">
                            <div class="kfr_1 etc">
                                    ${n.gameName}
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
    </div>
</c:if>

<!--分类-->
<c:if test="${fn:length(category) > 0}">
    <div class="hot_youxi">
        <div class="hot_youxi_title"><em class="i6"></em><span>分类</span></div>
        <!--列表-->
        <div class="new_fl">
            <ul>

                <c:forEach var="ca" items="${category}">
                    <li>
                        <a href="${ctx}/category/detail.do;jsessionid=${sessionid}?categoryId=${ca.id}&categoryName=${ca.name}"
                           class="nf1">${ca.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="hot_youxi_more"><a href="${ctx}/category/list.do;jsessionid=${sessionid}"><span>查看更多</span></a>
        </div>
    </div>
</c:if>


<!--主menu区-->
<div class="user_menu">
    <ul>
        <li><a href="${ctx}/subject/list.do;jsessionid=${sessionid}">
            <dl class="user_icon">
                <dt><img src="${ctx}/static/images/user_icon8.png"/></dt>
                <dd>专题</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/member.do;jsessionid=${sessionid}">
            <dl class="user_icon2">
                <dt><img src="${ctx}/static/images/user_icon3.png"/></dt>
                <dd>0元畅玩</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/newGame/topNewest.do;jsessionid=${sessionid}">
            <dl class="user_icon3">
                <dt><img src="${ctx}/static/images/user_icon4.png"/></dt>
                <dd>新游排行</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/category/list.do;jsessionid=${sessionid}">
            <dl class="user_icon4">
                <dt><img src="${ctx}/static/images/user_icon5.png"/></dt>
                <dd>分类</dd>
            </dl>
        </a></li>
    </ul>
</div>


<!-- 底部公告-->
<c:if test="${!empty b.bottomAD }">
    <div id="note" class="note"
         style="background:url(${ctx}/static/images/icon_huodong.png) #ffffcf 15px center no-repeat;background-size:30px 30px;">
        <div>
            <a href="javascript:bannerdetail('${b.bottomAD.url}','${b.bottomAD.title}')">${b.bottomAD.description}</a><a
                href="#" onclick="closeclick()" class="guanbi"><img src="${ctx}/static/images/colose.png"
                                                                    border="0"/></a></div>
    </div>



</c:if>



<script type="application/javascript">

    function clearSearch() {

        $("#w_input").val('');

    }

    function search() {
        var keyword;
        var inputtext = $("#w_input").val();

        if (inputtext == "" || inputtext == "请输入搜索内容") {

            alert("必须项")
            return false;
        } else {

            keyword = encodeURI(encodeURI(inputtext));

            location.href = "${ctx}/search/result.do;jsessionid=${sessionid}?keyword=" + keyword;
        }
    }

    function bannerdetail(linkid, title) {

        var title = encodeURI(encodeURI(title));

        location.href = "${ctx}/banner.do;jsessionid=${sessionid}?linkId=" + linkid + '&title=' + title;

    }


    function toAdDetail(url){

        location.href = url;
    }


</script>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>