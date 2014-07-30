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
    <title>${title}</title>
    <link href="${ctx}/static/styles/main.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css"/>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
    <%--<script src="${ctx}/static/js/utils.js?20140715092223"></script>--%>

</head>

<body class="ibody_bg">
<!--top-->
<div data-role="header" data-position="fixed" data-theme="a" class="w-header" style="background:#404040;">
    <div class="w-sousuo_icon"><a href="javascript:location.href='${referUrl}'">后退</a></div>
    <div class="w-sousuo"><a href="javascript:location.href='${referUrl}'"
                             style="color:#FF9C00;text-shadow: none;">${info.name}</a></div>
    <div class="w_search2"><a href="javascript:toSearch();" onclick="toSearch();">搜索</a></div>

</div>
<div id="info-container"
     style="display:none;position:fixed;top:40%;left:10%;z-index:9999;text-align:center;background-color: white;width:200px;height:100px;border: 1px solid gray">
    <div style="height:40px;line-height: 40px;">温馨提示</div>
    <div style="height:4px;background-color: orange;"></div>
    <div style="height:60px;line-height: 60px;">文件下载中...</div>
</div>
<!--分类筛选-->
<div class="w_paihangtitle">
    <!--选中状态-->
    <div class="w_new_01"><a style="color:#333;text-shadow: none;">详情</a></div>
    <!--<div class="w_new_02"><a href="#">玩家评论</a></div>-->
    <!--没有选中--><!--<div class="w_new_011"><a href="#">详情</a></div>-->
    <%--<div class="w_new_022"><a href="#">玩家评论</a></div>--%>
</div>
<c:if test="${error == '1'}">
    <div id="error-container" style="text-align:center;background-color: white;width:200px;height:150px;">
        <div style="height:40px;line-height: 40px;">温馨提示</div>
        <div style="height:4px;background-color: orange;"></div>
        <div style="height:60px;line-height: 60px;">网络连接失败</div>
        <dl class="w_retry" data-role="none">
            <a href="javascript:window.location.reload();" data-role="none">
                <dt>重试</dt>
            </a>
        </dl>
    </div>
    <script type="text/javascript">
        $(window).resize(function () {
            $('#error-container').css({
                position: 'fixed',
                left: ($(window).width() - $('#error-container').outerWidth()) / 2,
                top: ($(window).height() - $('#error-container').outerHeight()) / 2 + $(document).scrollTop()
            });
        });
        $(window).resize();
    </script>
</c:if>
<c:if test="${error == ''}">
    <!--列表-->
    <div class="youxi_lr_01">
        <div class="w_img_bg_large"><img src="${ctx}/static/images/gameicon.png"
                                         data-src="${info.iconUrl}" width="80" height="80"/></div>
        <div class="w_start_0${info.rate}"></div>
        <div class="w_img_txt">
            <ul>
                <li>类型：${info.catagory}</li>
                <li>大小：<fmt:formatNumber value="${info.size/1024/1024}" pattern="0.00"/>MB</li>
                <li>
                    时间：${fn:substring(info.appDate,0,4)}-${fn:substring(info.appDate, 4, 6)}-${fn:substring(info.appDate, 6, 8)}</li>
                <li>版本：${info.versionName}</li>
            </ul>
        </div>
    </div>
    <div class="youxi_lr_02">
        <div class="youxi_lr_02_title">介绍</div>
    </div>
    <div class="youxi_lr_03 youxi_desc_height_min">
            ${info.desc}
    </div>
    <div class="youxi_lr_04 youxi_lr_04_bottom"><a href="javascript:void(0);" onclick="toggleShowDesc();">更多</a></div>
    <div class="youxi_lr_05">
        <div class="youxi_lr_06" style="width:${fn:length(info.screenshots) * 120}px">
            <c:forEach var="screenshot" items="${info.screenshots}" varStatus="index">
                <a href="javascript:popup('popdiv${index.index}')">
                    <img src="${screenshot}" width="108" height="156"/>
                </a>
            </c:forEach>
        </div>
    </div>
    <!--列表-->
    <div class="w_footer_dow" data-role="footer" data-position="fixed">
        <dl class="w_dowload" data-role="none">
            <a href="javascript:download('${info.productId}','${info.name}','${info.iconUrl}')" data-role="none">
                <dt>下载</dt>
            </a>
        </dl>
    </div>
    <!--弹出层,默认不显示-->
    <c:forEach var="screenshot" items="${info.screenshots}" varStatus="index">
        <div id="popdiv${index.index}" style="display:none;position: absolute;z-index: 9999;top:5px;left:5px;">
            <img src="${screenshot}" onclick="javascript:$('#popdiv${index.index}').hide();"/>
        </div>
    </c:forEach>
</c:if>
<script type="text/javascript">
    function popup(id) {
        $('#' + id).show();
    }


    var descEle = $(".youxi_lr_03");
    var descImg = $(".youxi_lr_04");
    function toggleShowDesc() {

        if (descEle.hasClass("youxi_desc_height_min")) {
            descEle.removeClass("youxi_desc_height_min");
            descImg.removeClass("youxi_lr_04_bottom").addClass("youxi_lr_04_up");
        } else {
            descEle.addClass("youxi_desc_height_min");
            descImg.removeClass("youxi_lr_04_up").addClass("youxi_lr_04_bottom");
        }

    }

    function download(id, name, icon) {
        doDownload("${ctx}/download;jsessionid=${sessionid}", id, name, icon);
    }

    function toSearch() {
        location.href = "${ctx}/search/init;jsessionid=${sessionid}";
    }

    function doDownload(url, id, name, icon) {

        $.getJSON(url,
                {"productId": id, "productName": encodeURI(encodeURI(name)), "productIcon": icon},
                function (data) {
                    if (data.downloadUrl) {
                        if (data.downloadUrl == "") {
                            alert(data.description);
                        } else {
                            download_file(data.downloadUrl);
                        }
                    } else {
                        alert("服务器错误。");
                    }
                })
    }

    $(function () {
        var imgUrl = $("img[data-src]").attr("data-src");
        $("img[data-src]").attr("src", imgUrl);

        $(window).resize(function () {
            $('#info-container').css({
                position: 'fixed',
                left: ($(window).width() - $('#info-container').outerWidth()) / 2,
                top: ($(window).height() - $('#info-container').outerHeight()) / 2 + $(document).scrollTop()
            });
        });
        $(window).resize();
    })
    function download_file(url) {

        $("#info-container").show(300).delay(2000).hide(300);
        location.href = url;
        return false;
        /*
         var form = $("#downloadForm");
         if (form.length > 0) {
         form.attr("target", "");
         form.attr("action", url);
         } else {
         form = $("<form>");//定义一个form表单
         form.attr("style", "display:none");
         form.attr("id", "downloadForm");
         form.attr("target", "");
         form.attr("method", "post");
         form.attr("action", url);
         $("body").append(form);//将表单放置在web中
         }

         form.submit();//表单提交
         */
    }
</script>

</body>
</html>
