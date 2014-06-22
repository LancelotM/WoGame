<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=2.0">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="false" id="twcClient" name="twcClient">
    <title>首页</title>

    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
    <script src="${ctx}/static/js/index.js"></script>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w_search"><a href="#">搜索</a></div>
</div>
<!--分类筛选-->
<div class="w_paihangtitle" id="w_paihangtitle">
    <!--选中状态-->
    <div class="w_new_01"><a href="#">首页</a></div>
    <!--没有选中-->
    <div class="w_new_022"><a href="${ctx}/category/list">分类</a></div>
    <div class="w_new_033"><a href="${ctx}/weeklyHot?pageNum=1">一周热榜</a></div>
    <div class="w_new_044"><a href="${ctx}/newGame?pageNum=1">最新</a></div>

</div>
<!--大图-->
<div id="pic_div">
    <div id="pic">
        <c:forEach items="${adList}" var="item">
            <a><img src="${item.bannerUrl}" width="320" height="160"/></a>
        </c:forEach>

    </div>
    <div id="mark"></div>
    <ul id="sign">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
<script type="text/javascript">
    var li = document.getElementsByTagName("li");
    var i_all = li.length - 1;
    var i_width = 320;//图片宽度，10的倍数
    var i = 0;//当前图片编号
    var scs = setInterval(function () {
    }, 1000);
    var pic = document.getElementById("pic");
    touches(pic, "swipeleft", function () {
        i++;
        roll_pic(-10);
    });
    touches(pic, "swiperight", function () {
        i--;
        roll_pic(10);
    });
    var img = document.getElementsByTagName("img");
    function roll_pic(sp) {
        clearInterval(scs);
        i = i < 0 ? i_all : (i > i_all ? 0 : i);
        var img1 = img[i], img0, img2;
        if (i == i_all) {
            img0 = img[i - 1];
            img2 = img[0];
        } else if (i == 0) {
            img0 = img[i_all];
            img2 = img[1];
        } else {
            img0 = img[i - 1];
            img2 = img[i + 1];
        }
        for (var k = 0; k <= i_all; k++) {
            img[k].style.zIndex = 1;
            li[k].style.backgroundColor = "#fff";
        }
        img0.style.zIndex = 2;
        img1.style.zIndex = 2;
        img2.style.zIndex = 2;
        var i_left = 0;
        scs = setInterval(function () {
            i_left += sp;
            img0.style.left = (-i_width + i_left) + "px";
            img1.style.left = i_left + "px";
            img2.style.left = (i_width + i_left) + "px";
            if (Math.abs(i_left) == i_width) {
                clearInterval(scs);
            }
        }, 10);
        li[i].style.backgroundColor = "#f00";
    }
    roll_pic(0);
    function touches(obj, direction, fun) {
        //obj:ID对象
        //direction:swipeleft,swiperight,swipetop,swipedown,singleTap,touchstart,touchmove,touchend
        //          划左，    划右，     划上，   划下，    点击，    开始触摸， 触摸移动， 触摸结束
        //fun:回调函数
        var defaults = {x: 5, y: 5, ox: 0, oy: 0, nx: 0, ny: 0};
        direction = direction.toLowerCase();
        //配置：划的范围在5X5像素内当点击处理
        obj.addEventListener("touchstart", function () {
            defaults.ox = event.targetTouches[0].pageX;
            defaults.oy = event.targetTouches[0].pageY;
            defaults.nx = defaults.ox;
            defaults.ny = defaults.oy;
            if (direction.indexOf("touchstart") != -1)fun();
        }, false);
        obj.addEventListener("touchmove", function () {
            event.preventDefault();
            defaults.nx = event.targetTouches[0].pageX;
            defaults.ny = event.targetTouches[0].pageY;
            if (direction.indexOf("touchmove") != -1)fun();
        }, false);
        obj.addEventListener("touchend", function () {
            var changeY = defaults.oy - defaults.ny;
            var changeX = defaults.ox - defaults.nx;
            if (Math.abs(changeX) > Math.abs(changeY) && Math.abs(changeY) > defaults.y) {
                //左右事件
                if (changeX > 0) {
                    if (direction.indexOf("swipeleft") != -1)fun();
                } else {
                    if (direction.indexOf("swiperight") != -1)fun();
                }
            } else if (Math.abs(changeY) > Math.abs(changeX) && Math.abs(changeX) > defaults.x) {
                //上下事件
                if (changeY > 0) {
                    if (direction.indexOf("swipetop") != -1)fun();
                } else {
                    if (direction.indexOf("swipedown") != -1)fun();
                }
            } else {
                //点击事件
                if (direction.indexOf("singleTap") != -1)fun();
            }
            if (direction.indexOf("touchend") != -1)fun();
        }, false);
    }
</script>
<!--列表-->
<c:forEach items="${recommendedList}" var="item">
    <c:forEach items="${item.apps}" var="appItem" step="2" varStatus="idx">
        <c:if test="${item.adType == 4}">
            <div class="w_houlist">
                <div class="w_houlist_l">
                    <div class="w_img_bg"><a href="#"><img width="100" height="100" src="${appItem.iconUrl}"></a></div>
                    <div class="w_img_count">
                        <h3><a href="#">${appItem.appName}</a></h3>

                        <div class="isio">
                            <div class="w_start_0${appItem.rate}${appItem.rate}">一星</div>
                            <!--<div class="w_start_022">二星</div><div class="w_start_033">三星</div><div class="w_start_044">四星</div><div class="w_start_055">五星</div>-->
                        </div>
                        <h5>${appItem.apkSize / 1000}MB</h5>
                    </div>
                    <div class="w_img_xz"><a href="#">下载</a></div>
                </div>
                <c:if test="${idx.index+1 < fn:length(item.apps)}">
                    <div class="w_houlist_r">
                        <div class="w_img_bg"><a href="#"><img width="100" height="100"
                                                               src="${item.apps[idx.index+1].iconUrl}"></a></div>
                        <div class="w_img_count">
                            <h3><a href="#">${item.apps[idx.index+1].appName}</a></h3>

                            <div class="isio">
                                <div class="w_start_0${item.apps[idx.index+1].rate}${item.apps[idx.index+1].rate}">二星
                                </div>
                                <!--<div class="w_start_011">一星</div><div class="w_start_033">三星</div><div class="w_start_044">四星</div><div class="w_start_055">五星</div>-->
                            </div>
                            <h5>${item.apps[idx.index+1].apkSize / 1000}MB</h5>
                        </div>
                        <div class="w_img_xz"><a href="#">下载</a></div>

                    </div>
                </c:if>
            </div>
        </c:if>
    </c:forEach>
</c:forEach>

<script type="text/javascript">

    $(function () {
        app.initialize();
        $("#w_paihangtitle").bind("swipeleft", function () {
            location.href = "${ctx}/newGame?pageNum=1";
        });
        $("#w_paihangtitle").bind("swiperight", function () {
            location.href = "${ctx}/category/list";
        });


    });
</script>

</body>
</html>
