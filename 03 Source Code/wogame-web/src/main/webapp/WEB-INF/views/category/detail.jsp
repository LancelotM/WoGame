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
    <title>${categoryName}</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        #wrapper {
            position: absolute;
            z-index: 1;
            top: 45px;
            bottom: 4px;
            left: -9999px;
            width: 100%;
            background: #aaa;
            overflow: auto;
        }

        #scroller {
            position: absolute;
            z-index: 1;
            /*	-webkit-touch-callout:none;*/
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
            width: 100%;
            padding: 0;
        }

        #scroller ul {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
            text-align: left;
        }

        #scroller li {
            padding: 0 10px;
            height: 40px;
            line-height: 40px;
            border-bottom: 1px solid #ccc;
            border-top: 1px solid #fff;
            background-color: #fafafa;
            font-size: 14px;
        }

        #myFrame {
            position: absolute;
            top: 0;
            left: 0;
        }

        /**
         *
         * Pull down styles
         *
         */
        #pullDown, #pullUp {
            background: #fff;
            height: 40px;
            line-height: 40px;
            padding: 5px 10px;
            border-bottom: 1px solid #ccc;
            font-weight: bold;
            font-size: 14px;
            color: #888;
        }

        #pullDown .pullDownIcon, #pullUp .pullUpIcon {
            display: block;
            float: left;
            width: 40px;
            height: 40px;
            background: url(pull-icon@2x.png) 0 0 no-repeat;
            -webkit-background-size: 40px 80px;
            background-size: 40px 80px;
            -webkit-transition-property: -webkit-transform;
            -webkit-transition-duration: 250ms;
        }

        #pullDown .pullDownIcon {
            -webkit-transform: rotate(0deg) translateZ(0);
        }

        #pullUp .pullUpIcon {
            -webkit-transform: rotate(-180deg) translateZ(0);
        }

        #pullDown.flip .pullDownIcon {
            -webkit-transform: rotate(-180deg) translateZ(0);
        }

        #pullUp.flip .pullUpIcon {
            -webkit-transform: rotate(0deg) translateZ(0);
        }

        #pullDown.loading .pullDownIcon, #pullUp.loading .pullUpIcon {
            background-position: 0 100%;
            -webkit-transform: rotate(0deg) translateZ(0);
            -webkit-transition-duration: 0ms;

            -webkit-animation-name: loading;
            -webkit-animation-duration: 2s;
            -webkit-animation-iteration-count: infinite;
            -webkit-animation-timing-function: linear;
        }

        @-webkit-keyframes loading {
            from {
                -webkit-transform: rotate(0deg) translateZ(0);
            }
            to {
                -webkit-transform: rotate(360deg) translateZ(0);
            }
        }
    </style>
</head>

<body class="ibody_bg">
<!--top-->
<div class="w-header">
    <div class="w-sousuo_icon"><a href="${ctx}/category/list;jsessionid=${sessionid}"></a></div>
    <div class="w-sousuo"><a href="${ctx}/category/list;jsessionid=${sessionid}">${categoryName}</a></div>
    <div class="w_search"><a href="${ctx}/search/init;jsessionid=${sessionid}">搜索</a></div>
</div>
<input type="hidden" id="categoryId" value="${categoryId}"/>

<div id="wrapper">
    <div id="scroller">
        <div id="pullDown">
            <span class="pullDownIcon"></span><span class="pullDownLabel">Pull down to refresh...</span>
        </div>
        <div id="list">
            <!--列表-->
            <c:forEach items="${list}" var="item">

                <a href="${ctx}/gameInfo;jsessionid=${sessionid}?productId=${item.productId}">
                    <div class="w_list_fenlei">
                        <div class="w_list_img"><img src="${item.iconUrl}" width="48" height="48"/></div>
                        <div class="w_list_title">${item.appName}</div>
                        <div class="w_list_numm">${item.description}</div>
                    </div>
                </a>


            </c:forEach>
        </div>
        <div id="pullUp">
            <span class="pullUpIcon"></span><span class="pullUpLabel">Pull up to refresh...</span>
        </div>
    </div>
</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/iscroll.js"></script>

<script type="text/javascript">

    var myScroll,
            pullDownEl, pullDownOffset,
            pullUpEl, pullUpOffset,
            generatedCount = 0;
    var categoryId = $("#categoryId").val();
    var pageNum = 1;
    var urlBase = '${ctx}/gameInfo;jsessionid=${sessionid}?productId=';
    var el = $('#list');

    function pullDownAction() {


        $.getJSON("${ctx}/category/ajaxDetail", {"categoryId": categoryId, "pageNum": 1}, function (data) {

            if (data.length == 0) return;

            el.html("");
            $.each(data, function (index, entry) {
                var stringBuffer = [];
                stringBuffer.push('<a href="' + urlBase + entry.product_id + '">');
                stringBuffer.push('<div class="w_list_fenlei">');
                stringBuffer.push('<div class="w_list_img"><img src="' + entry.icon_url + '" width="48" height="48"/></div>');
                stringBuffer.push('<div class="w_list_title">' + entry.app_name + '</div>');
                stringBuffer.push('<div class="w_list_numm">' + entry.description + '</div>');
                stringBuffer.push('</div>');
                stringBuffer.push('</a>');

                el.append(stringBuffer.join(""));
            });
        });

        myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
    }

    function pullUpAction() {
        $.getJSON("${ctx}/category/ajaxDetail", {"categoryId": categoryId, "pageNum": pageNum + 1}, function (data) {

            if (data.length == 0) return;

            pageNum++;

            $.each(data, function (idx, entry) {
                var stringBuffer = [];
                stringBuffer.push('<a href="' + urlBase + entry.product_id + '">');
                stringBuffer.push('<div class="w_list_fenlei">');
                stringBuffer.push('<div class="w_list_img"><img src="' + entry.icon_url + '" width="48" height="48"/></div>');
                stringBuffer.push('<div class="w_list_title">' + entry.app_name + '</div>');
                stringBuffer.push('<div class="w_list_numm">' + entry.description + '</div>');
                stringBuffer.push('</div>');
                stringBuffer.push('</a>');

                el.append(stringBuffer.join(""));
            });
        });

        myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
    }

    function loaded() {
        pullDownEl = document.getElementById('pullDown');
        pullDownOffset = pullDownEl.offsetHeight;
        pullUpEl = document.getElementById('pullUp');
        pullUpOffset = pullUpEl.offsetHeight;

        myScroll = new iScroll('wrapper', {
            useTransition: true,
            topOffset: pullDownOffset,
            onRefresh: function () {
                if (pullDownEl.className.match('loading')) {
                    pullDownEl.className = '';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';
                } else if (pullUpEl.className.match('loading')) {
                    pullUpEl.className = '';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';
                }
            },
            onScrollMove: function () {
                if (this.y > 5 && !pullDownEl.className.match('flip')) {
                    pullDownEl.className = 'flip';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Release to refresh...';
                    this.minScrollY = 0;
                } else if (this.y < 5 && pullDownEl.className.match('flip')) {
                    pullDownEl.className = '';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';
                    this.minScrollY = -pullDownOffset;
                } else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
                    pullUpEl.className = 'flip';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Release to refresh...';
                    this.maxScrollY = this.maxScrollY;
                } else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
                    pullUpEl.className = '';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';
                    this.maxScrollY = pullUpOffset;
                }
            },
            onScrollEnd: function () {
                if (pullDownEl.className.match('flip')) {
                    pullDownEl.className = 'loading';
                    pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Loading...';
                    pullDownAction();	// Execute custom function (ajax call?)
                } else if (pullUpEl.className.match('flip')) {
                    pullUpEl.className = 'loading';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Loading...';
                    pullUpAction();	// Execute custom function (ajax call?)
                }
            }
        });

        setTimeout(function () {
            document.getElementById('wrapper').style.left = '0';
        }, 800);
    }

    document.addEventListener('touchmove', function (e) {
        e.preventDefault();
    }, false);

    document.addEventListener('DOMContentLoaded', function () {
        setTimeout(loaded, 200);
    }, false);
</script>


</body>
</html>
