<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="sessionid" value="${pageContext.request.requestedSessionId}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<Link Rel="ICON NAME" href="${ctx}/favicon.ico">
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
<script type="text/javascript" name="baidu-tc-cerfication"
        src="http://apps.bdimg.com/cloudaapi/lightapp.js#21e4cc6e9f6e857f9ba7ac86ababad5a"></script>
<script type="text/javascript">window.bd && bd._qdc && bd._qdc.init({app_id: 'de7d38f348eeca6894ccf3aa'});</script>
<script type="text/javascript" src="${ctx}/static/js/utils.js?20140715092223"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/slider.js"></script>


<!--弹出文本提示写入cooking-->
<script type="text/javascript">
    function cookiesave(n, v, mins, dn, path) {
        if (n) {

            if (!mins) mins = 24 * 60;
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

        //当进如页面自动ajax请求 火热
        $.getJSON("${ctx}/ajaxList/hotNewest;jsessionid=${sessionid}", function (data) {

            if (data["hot"].length < 0) {
                $("#hot").hide();
            } else {
                var stringBuffer = [];
                stringBuffer.push("<ul>");
                $.each(data["hot"], function (index, entry) {
                    stringBuffer.push("<li>");
                    stringBuffer.push("<dl class='hotyouxi_list'>");
                    stringBuffer.push("<a href='${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=" + data["hot"][index].product_id + "'>");
                    stringBuffer.push("<dt>");
                    stringBuffer.push("<img src='${ctx}/static/images/gameicon.png' data-src='" + data["hot"][index].icon_url + "'>");
                    stringBuffer.push("</dt>");
                    stringBuffer.push("<dd class='tit etc'>" + data["hot"][index].title + "</dd>");
                    stringBuffer.push("<dd class='mb'>" + roundNumber(data["hot"][index].apk_size / 1024, 2) + "M</dd>");
                    stringBuffer.push("</a>");
                    stringBuffer.push("<dd class='dow'>");
                    stringBuffer.push("<a data-role='none' href=\"javascript:download('" + data["hot"][index].product_id + "','" + data["hot"][index].title + "','" + data["hot"][index].icon_url + "')\">" + "下载");
                    stringBuffer.push("</a>");
                    stringBuffer.push("</dd>");
                    stringBuffer.push("</dl>");
                    stringBuffer.push("</li>");
                });
                stringBuffer.push("</ul>");
                $("#hot_youxi_count").append(stringBuffer.join(""));
                $("img[data-src]").scrollLoading();
            }

            //最新
            if (data["newest"].length < 0) {
                $("#newest").hide();
            } else {
                var stringBuffer = [];
                $.each(data["newest"], function (index, entry) {
                    stringBuffer.push("<div class='pro_list' style='height: auto'>");
                    stringBuffer.push("<a href='#'>");
                    stringBuffer.push("<div class='pro_cp' style='border-top: #dedede 1px solid;'>");
                    stringBuffer.push("<a href='${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=" + data["newest"][index].product_id + "'>");
                    stringBuffer.push("<div class='pro_cp_l'>");
                    stringBuffer.push("<img data-src='" + data["newest"][index].icon_url + "' src='${ctx}/static/images/gameicon.png' height='86'/>");
                    stringBuffer.push("</div>");
                    stringBuffer.push("<dl class='pro_cp_c' style='height: auto'>");
                    stringBuffer.push("<dt class='etc'>" + data["newest"][index].game_name + "</dt>");
                    stringBuffer.push("<dd>" + roundNumber(data["newest"][index].apk_size / 1024, 2)+ " MB</dd>");
                    stringBuffer.push("<div class='etc' style='width: 100%'>" + data["newest"][index].intro + "</div>");
                    stringBuffer.push("</dl>");
                    stringBuffer.push("</a>");
                    stringBuffer.push("<a href=\"javascript:download('" + data["newest"][index].product_id + "','" + data["newest"][index].game_name + "','" + data["newest"][index].icon_url + "')\" data-role='none'>");
                    stringBuffer.push("<div class='pro_cp_r radius'>下载</div>");
                    stringBuffer.push("</a> </div></a></div>");
                });

                $("#newest_youxi_title").after(stringBuffer.join(""));
                $("img[data-src]").scrollLoading();
                newItemCategory();
            }
        });
    });


    function newItemCategory(){

        //新服预告  分类

        $.getJSON("${ctx}/ajaxList/newItemCategory;jsessionid=${sessionid}", function (data) {

            if (data["newItem"].length < 0) {
                $("#newItem").hide();
            } else {
                var stringBuffer = [];
                stringBuffer.push("<ul>");
                $.each(data["newItem"], function (index, entry) {
                    stringBuffer.push("<li>");
                    stringBuffer.push("<div class='kf_left'>");
                    stringBuffer.push("<a href='${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id="+data["newItem"][index].product_id+"'>");
                    stringBuffer.push("<div class='kf_img'><img data-src='"+data["newItem"][index].icon_url+"' src='${ctx}/static/images/gameicon.png'height='86'/>");
                    stringBuffer.push("</div>");
                    stringBuffer.push("</a>");
                    stringBuffer.push("<a href=\"javascript:download('"+data["newItem"][index].product_id+"','"+data["newItem"][index].game_name+"','"+data["newItem"][index].icon_url+"')\" data-role='none'>");
                    stringBuffer.push("<div class='kf_dow radius'>下载</div>");
                    stringBuffer.push("</a></div>");
                    stringBuffer.push("<div class='kf_right'>");
                    stringBuffer.push("<a href='${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id="+data["newItem"][index].product_id+"'>");
                    stringBuffer.push("<div class='kfr_1 etc'>"+data['newItem'][index].game_name);
                    stringBuffer.push("</div>");
                    stringBuffer.push("<div class='kfr_2'>"+roundNumber(data["newItem"][index].apk_size / 1024, 2)+" MB</div></a>");
                    stringBuffer.push("<div class='kfr_3 etc'>新服:"+data['newItem'][index].server_name+"</div>");
                    stringBuffer.push("<div class='kfr_4 etc' style='letter-spacing:-1px;'>时间:"+getFormatDateByLong(data["newItem"][index].open_time, "yyyy.MM.dd")+"</div>");
                    stringBuffer.push("</div> </li>");
                });
                stringBuffer.push("</ul>");
                $(".kaifu_box").append(stringBuffer.join(""));
                $("img[data-src]").scrollLoading();

            }
            //分类

            if (data["category"].length < 0) {
                $("#category").hide();
            } else {
                var stringBuffer = [];
                stringBuffer.push("<ul>");
                $.each(data["category"], function (index, entry) {
                    stringBuffer.push("<li>");
                    stringBuffer.push("<a href=\"javascript:category('"+data["category"][index].id+"', '"+data["category"][index].name+"')\" class='nf1'>"+data["category"][index].name+"</a>");
                    stringBuffer.push("</li>");
                });
                stringBuffer.push("</ul>");
                $(".new_fl").append(stringBuffer.join(""));
                activity();
            }
        });
    }


    function activity(){

        //网游活动  活动

        $.getJSON("${ctx}/ajaxList/activity;jsessionid=${sessionid}", function (data) {

            if (data["activity"].length < 0) {
                $("#activity").hide();
            } else {
                var stringBuffer = [];
                stringBuffer.push("<dl class='huodong_box22'><dt>");
                stringBuffer.push("<div class='huodong'><a href="+data["activity"][0].url+">");
                stringBuffer.push("<div class='index_hd_count'>");
                stringBuffer.push("<img data-src="+data["activity"][0].imageName+" src='${ctx}/static/images/gameicon.png' height='100'/>");
                stringBuffer.push(" </div>");
                stringBuffer.push(" <div class='index_hd_url etc'>"+data["activity"][0].description+"</div>");
                stringBuffer.push("  </a> </div></dt><dd>");
                stringBuffer.push("  <div class='huodong_r'>");
                stringBuffer.push("<a href='"+data["activity"][1].url+"'>");
                stringBuffer.push("<div class='index_hd_count'>");
                stringBuffer.push(" <img data-src='"+data["activity"][1].imageName+"' src='${ctx}/static/images/gameicon.png' width='320' height='160'/>");
                stringBuffer.push("</div>");
                stringBuffer.push("<div class='index_hd_url etc'>"+data["activity"][1].description+"</div>");
                stringBuffer.push("</a></div></dd></dl>");
            }
            $(".huodong_hot").prepend(stringBuffer.join(""));
            $("img[data-src]").scrollLoading();
        });
    }

</script>




</head>

<body class="ibody_bg">
<!--top-->
<div class="head_index relative" style="position: fixed;width:100%;z-index: 1001; margin: 0 auto;">
    <div class="logo absolute"><a href="${ctx}/main.do;jsessionid=${sessionid}">首页</a></div>
    <div style="float: left;">
        <a href="javascript:download_file('http://wostore.cn/wogame')">
            <div style="margin-left: 70px;line-height:29px; font-size: 15px; color: #FF9C00">沃游戏</div>
        </a>

        <div style="margin-left: 70px;line-height:10px;">好游戏任你选</div>
    </div>
    <a href="javascript:download_file('http://wostore.cn/wogame')">
        <div style="position: absolute; right: 20px;top:12px;height: 26px;width: 92px;  line-height: 26px; background-color: #FF9C00; text-align: center;"
             class="round">下载Android版
        </div>
    </a>
</div>
<div style="height: 50px;"></div>
<!--图片广告位-->
<input type="hidden" value="${ctx}" id="ctx"/>
<c:if test="${!empty b.topAD}">
    <div id="note" class="note">

        <img src="${b.topAD.imageName==''? '1' : b.topAD.imageName}"
             onerror="this.src='${ctx}/static/images/icon_huodong.png'"
             style="height: 25px;width: 25px; top: 5px; position: absolute; left: 12px; vertical-align: middle;">
        <input type="hidden" id="topDescription" value="${b.topAD.description}"/>

        <div id="gongao" style=" margin-left: 22px; height: 40px; line-height: 40px; position: absolute">
            <div style="width:90%;height:30px;margin-left:25px;white-space: nowrap;overflow:hidden;" id="scroll_div"
                 class="scroll_div">
                <div id="scroll_begin" style="width:900px;">
                    <a id="descriptionText" href="${b.topAD.url}" style="color: #ffffff">
                    </a>
                </div>
                <div id="scroll_end"></div>
            </div>
        </div>

        <a style="position: absolute; right: 0px;" href="#" onclick="closeclick()"
           class="guanbi"><img
                src="${ctx}/static/images/colose.png"
                border="0"/></a>


    </div>
</c:if>


<!--大图-->
<c:if test="${fn:length(adList) > 0}">
    <div id="pic_div" class="container block_home_slider">
        <div id="slides">
            <c:forEach var="a" items="${adList}">
                <c:choose>
                    <%--外链--%>
                    <c:when test="${a.banner.resType==10}">
                        <img data-src="${a.banner.bannerUrl}" src="${ctx}/static/images/gameicon.png"
                             onclick="javascript:toAdDetail('${a.banner.externalUrl}')" alt=""/>
                    </c:when>

                    <%--游戏--%>
                    <c:when test="${a.banner.resType==2}">
                        <img data-src="${a.banner.bannerUrl}" src="${ctx}/static/images/gameicon.png"
                             onclick="javascript:toAdDetail('${ctx}/gamedetail/detaillist.do;jsessionid=${sessionid}?product_id=${a.banner.linkId}')"
                             alt=""/>
                    </c:when>

                    <%--专题--%>
                    <c:when test="${a.banner.resType==3}">
                        <img data-src="${a.banner.bannerUrl}" src="${ctx}/static/images/gameicon.png"
                             onclick="javascript:toAdDetail('${ctx}/subject/detailList.do;jsessionid=${sessionid}?id=${a.banner.linkId}')"
                             alt=""/>
                    </c:when>

                    <%--活动--%>
                    <c:when test="${a.banner.resType==4}">
                        <img data-src="${a.banner.bannerUrl}" src="${ctx}/static/images/gameicon.png"
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
                        <a href="${mo.url}">
                            <div class="index_hd_count"><img data-src="${mo.imageName}"
                                                             src="${ctx}/static/images/gameicon.png" height="100"/>
                            </div>
                            <div class="index_hd_url etc">${mo.description}</div>
                        </a>
                    </div>
                    </dt>

                    <c:set var="s" value="2"></c:set>

                </c:when>
                <c:when test="${s==2}">

                    <dt>
                    <div class="huodong_r">
                        <div class="index_hd_title">${mo.title}</div>
                        <a href="${mo.url}">
                            <div class="index_hd_count"><img data-src="${mo.imageName}"
                                                             src="${ctx}/static/images/gameicon.png" height="100"/>
                            </div>
                            <div class="index_hd_url etc">${mo.description}</div>
                        </a>
                    </div>
                    </dt>
                </c:when>
            </c:choose>
        </c:forEach>
    </dl>
</c:if>

<!--热门游戏-->

<div class="hot_youxi" id="hot">
    <div class="hot_youxi_title"><em class="i1"></em><span>火热</span></div>
    <div class="hot_youxi_count" id="hot_youxi_count">


    </div>
    <div class="hot_youxi_more"><a href="${ctx}/recommend/init.do;jsessionid=${sessionid}"><span>更多火热游戏</span></a>
    </div>
</div>

<!--活动版块-->
<div class="hot_youxi" id="activity">
<div class="hot_youxi_title"><em class="i2"></em><span>活动</span></div>
<div class="huodong_hot">


    <div class="hot_youxi_more"><a href="${ctx}/activity/list.do;jsessionid=${sessionid}"><span>全部活动</span></a>
    </div>
</div>



<!--最新榜-->
<div class="hot_youxi" id="newest">
    <div class="hot_youxi_title" id="newest_youxi_title"><em class="i3"></em><span>最新榜</span></div>
    <!--列表-->


    <div class="hot_youxi_more"><a
            href="${ctx}/newGame/topNewest.do;jsessionid=${sessionid}"><span>查看更多最新榜</span></a></div>
</div>
<%--</c:if>--%>
<!--开服信息-->

<div class="hot_youxi" id="newItem">
    <div class="hot_youxi_title"><em class="i4"></em><span>${netGame}</span></div>
    <!--列表-->
    <div class="kaifu_box">



    </div>
</div>


<!--分类-->

<div class="hot_youxi" id="category">
    <div class="hot_youxi_title"><em class="i6"></em><span>分类</span></div>
    <!--列表-->
    <div class="new_fl">




    </div>
    <div class="hot_youxi_more"><a href="${ctx}/category/list.do;jsessionid=${sessionid}"><span>查看更多</span></a>
    </div>
</div>



<!--主menu区-->
<div class="user_menu">
    <ul>
        <li><a href="${ctx}/subject/list.do;jsessionid=${sessionid}">
            <dl class="user_icon">
                <dt><img src="${ctx}/static/images/user_icon8.png"/></dt>
                <dd>专题</dd>
            </dl>
        </a></li>
        <li><a href="http://sales.wostore.cn:8083/hytq/hytq.action?link=0" target="_blank">
            <dl class="user_icon2">
                <dt><img src="${ctx}/static/images/user_icon3.png"/></dt>
                <dd>0元畅玩</dd>
            </dl>
        </a></li>
        <li><a href="${ctx}/newGame/topHotest.do;jsessionid=${sessionid}">
            <dl class="user_icon3">
                <dt><img src="${ctx}/static/images/user_icon4.png"/></dt>
                <dd>飙升</dd>
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

<div id="note" class="note">

    <img src="${b.bottomAD.imageName==''? '1' : b.bottomAD.imageName}"
         onerror="this.src='${ctx}/static/images/icon_huodong.png'"
         style="height: 25px;width: 25px; top: 5px; position: absolute; left: 12px; vertical-align: middle;">
    <input type="hidden" id="BottomDescription" value="${b.bottomAD.description}"/>

    <div id="gongao1" style=" height: 40px; line-height: 40px; position: absolute">
        <div style="width:80%;height:30px; margin-left:50px;white-space: nowrap;overflow:hidden;" id="scroll_div1"
             class="scroll_div">
            <div id="bottom_scroll_begin" style="width:900px;">
                <a id="bottomDescriptionText" style="color: #ffffff"
                   href="javascript:download_file('${b.bottomAD.url}');">
                </a>
            </div>
            <div id="scroll_end1"></div>
        </div>

    </div>

</div>

</c:if>


<script type="application/javascript">

    function category(id, name) {
        var name = encodeURI(encodeURI(name));

        location.href = "${ctx}/category/detail.do;jsessionid=${sessionid}?categoryId=" + id + "&categoryName=" + name;
    }

    function clearSearch() {
        $("#w_input").val('');
    }

    function search() {
        var keyword;
        var inputtext = $("#w_input").val();

        if (inputtext == "" || inputtext == "请输入搜索内容") {

            alert("请输入搜索关键字")
            return false;
        } else {

            keyword = encodeURI(encodeURI(inputtext));

            location.href = "${ctx}/search/result.do;jsessionid=${sessionid}?keyword=" + keyword;
        }
    }

    function bannerdetail(linkid, title) {

        var link = encodeURI(encodeURI(linkid));
        var title = encodeURI(encodeURI(title));

        location.href = "${ctx}/banner.do;jsessionid=${sessionid}?linkId=" + link + '&title=' + title;

    }


    function toAdDetail(url) {

        location.href = url;
    }
    function download(id, name, icon) {

        doDownload("${ctx}/download.do;jsessionid=${sessionid}", id, name, icon);
    }

</script>

<style type="text/css">
    #gongao {
        width: 80%;
        height: 30px;
        overflow: hidden;
        line-height: 30px;
        font-size: 13px;
        font-family: '宋体';
        color: #ffffff;
        font-weight: bold;
    }

    #gongao #scroll_begin, #gongao #scroll_end {
        display: inline
    }

    #gongao1 {
        width: 95%;
        height: 30px;
        overflow: hidden;
        line-height: 30px;
        font-size: 13px;
        font-family: '宋体';
        color: #ffffff;
        font-weight: bold;
    }

    #gongao1 #bottom_scroll_begin, #gongao1 #scroll_end1 {
        display: inline
    }

</style>
<script type="text/javascript">
    function ScrollImgLeft() {
        var speed = 50;
        $("#descriptionText").text($("#topDescription").val() + '　　　　　　　　　　　　');
        var scroll_begin = document.getElementById("scroll_begin");
        var scroll_end = document.getElementById("scroll_end");
        var scroll_div = document.getElementById("scroll_div");
        scroll_end.innerHTML = scroll_begin.innerHTML;
        function Marquee() {
            if (scroll_end.offsetWidth - scroll_div.scrollLeft <= 0) {
                scroll_div.scrollLeft -= scroll_begin.offsetWidth;
            }
            else {
                scroll_div.scrollLeft++;
            }
        }

        var MyMar = setInterval(Marquee, speed);
    }

    function ScrollImgLeft1() {
        var speed = 50;
        $("#bottomDescriptionText").text($("#BottomDescription").val() + '　　　　　　');
        var scroll_begin = document.getElementById("bottom_scroll_begin");
        var scroll_end = document.getElementById("scroll_end1");
        var scroll_div = document.getElementById("scroll_div1");
        scroll_end.innerHTML = scroll_begin.innerHTML;
        function MarqueeButtom() {
            if (scroll_end.offsetWidth - scroll_div.scrollLeft <= 0)
                scroll_div.scrollLeft -= scroll_begin.offsetWidth;
            else
                scroll_div.scrollLeft++;
        }

        var MyMar = setInterval(MarqueeButtom, speed);
    }

</script>
<script type="text/javascript">ScrollImgLeft();</script>
<script type="text/javascript">ScrollImgLeft1();</script>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>