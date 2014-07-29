function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}

var contextPath = getContextPath();

/**
 * 画面点击出现等待效果
 */
$.fn.extend({
    waiting: function (msg) {
        var msgText = msg ? msg : "处理中，请等待...";
        //小圈子下面加上文字
        $(this).bind('tap', function () {
            $.mobile.loadingMessageTextVisible = true;
            $.mobile.showPageLoadingMsg('a', msgText);
        });
    }

});

String.prototype.PadLeft = function (totalWidth, paddingChar) {
    if (paddingChar != null) {
        return this.PadHelper(totalWidth, paddingChar, false);
    } else {
        return this.PadHelper(totalWidth, ' ', false);
    }
}
String.prototype.PadRight = function (totalWidth, paddingChar) {
    if (paddingChar != null) {
        return this.PadHelper(totalWidth, paddingChar, true);
    } else {
        return this.PadHelper(totalWidth, ' ', true);
    }

}
String.prototype.PadHelper = function (totalWidth, paddingChar, isRightPadded) {

    if (this.length < totalWidth) {
        var paddingString = new String();
        for (i = 1; i <= (totalWidth - this.length); i++) {
            paddingString += paddingChar;
        }

        if (isRightPadded) {
            return (this + paddingString);
        } else {
            return (paddingString + this);
        }
    } else {
        return this;
    }
}


/*Javascript设置要保留的小数位数，四舍五入。
 *ForDight(Dight,How):数值格式化函数，Dight要格式化的 数字，How要保留的小数位数。
 *这里的方法是先乘以10的倍数，然后去掉小数，最后再除以10的倍数。
 */
function roundNumber(Dight, How) {
    Dight = Math.round(Dight * Math.pow(10, How)) / Math.pow(10, How);
    return Dight;
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

var pageNum = 1;

function pullDownAction() {
    if (ajaxGetData) {
        ajaxGetData(1, function () {
            pageNum = 1;
            myScroll.refresh();
        });
    }
}

function pullUpAction() {
    if (ajaxGetData) {
        ajaxGetData(pageNum + 1, function () {
            pageNum++;
            myScroll.refresh();
        });
    }
}

function loaded() {
    pullDownEl = document.getElementById('pullDown');
    pullDownOffset = pullDownEl.offsetHeight;
    pullUpEl = document.getElementById('pullUp');
    pullUpOffset = pullUpEl.offsetHeight;

    myScroll = new iScroll('wrapper', {
        useTransition: true,
        topOffset: pullDownOffset,
        buttomOffset: pullUpOffset,
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
            $("img[data-src]").scrollLoading();
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
            $("img[data-src]").scrollLoading();
        }
    });

    setTimeout(function () {
        document.getElementById('wrapper').style.left = '0';
    }, 800);
}

var storage = window.localStorage;
var oldUserFlag = storage.getItem("unicom.game.user.anonymous");
var URL_BUSINESS_LOG = "/businessLog";
var URL_NUMBER_LOG = "/numberLog";

if (oldUserFlag && oldUserFlag == "TRUE") {
    window.sessionStorage.setItem("oldUserFlag", "TRUE");
} else {
    setUsageFlag();
}
function setUsageFlag() {
    storage.setItem("unicom.game.user.anonymous", "TRUE");
}
function isOldUser() {
    return window.sessionStorage.getItem("oldUserFlag") == "TRUE";
}
function logBusiness(rootUrl, data, callback) {
    $.getJSON(rootUrl + URL_BUSINESS_LOG, {"data": encodeURI(JSON.stringify(data))}, function (result) {
        if (callback) {
            callback();
        }
    });
}
function logNumber(rootUrl, data, callback) {
    $.getJSON(rootUrl + URL_NUMBER_LOG, {"data": data.join('')}, function (result) {
        if (callback) {
            callback();
        }
    });
}
$(function () {
    var iframe = $('<iframe frameborder="0" height="0" scrolling="no"></iframe>');
    iframe.attr("src", contextPath + "/common/keepSessionAlive.jsp");
    $(document.body).append(iframe);
});

/*!
 * jquery.scrollLoading.js
 * by zhangxinxu  http://www.zhangxinxu.com
 * 2010-11-19 v1.0
 * 2012-01-13 v1.1 偏移值计算修改 position → offset
 * 2012-09-25 v1.2 增加滚动容器参数, 回调参数
 */
(function ($) {
    $.fn.scrollLoading = function (options) {
        var defaults = {
            attr: "data-src",
            container: $(window),
            callback: $.noop
        };
        var params = $.extend({}, defaults, options || {});
        params.cache = [];
        $(this).each(function () {
            var node = this.nodeName.toLowerCase(), url = $(this).attr(params["attr"]);
            //重组
            var data = {
                obj: $(this),
                tag: node,
                url: url
            };
            params.cache.push(data);
        });

        var callback = function (call) {
            if ($.isFunction(params.callback)) {
                params.callback.call(call.get(0));
            }
        };
        //动态显示数据
        var loading = function () {

            var contHeight = params.container.height();
            if ($(window).get(0) === window) {
                contop = $(window).scrollTop();
            } else {
                contop = params.container.offset().top;
            }

            $.each(params.cache, function (i, data) {
                var o = data.obj, tag = data.tag, url = data.url, post, posb;

                if (o) {
                    post = o.offset().top - contop, post + o.height();

                    if ((post >= 0 && post < contHeight) || (posb > 0 && posb <= contHeight)) {
                        if (url) {
                            //在浏览器窗口内
                            if (tag === "img") {
                                //图片，改变src
                                callback(o.attr("src", url));
                            } else {
                                o.load(url, {}, function () {
                                    callback(o);
                                });
                            }
                        } else {
                            // 无地址，直接触发回调
                            callback(o);
                        }
                        data.obj = null;
                    }
                }
            });
        };

        //事件触发
        //加载完毕即执行
        loading();
        //滚动执行
        params.container.bind("scroll", loading);
    };
})(jQuery);

$(function () {
    $("img[data-src]").scrollLoading();
})