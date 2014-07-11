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
function download_file(url) {

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

