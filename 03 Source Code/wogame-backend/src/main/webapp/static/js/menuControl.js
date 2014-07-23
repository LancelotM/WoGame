function addStyle(obj){
	obj.className = "add_backround";
}
function deleteStyle(obj){
	obj.className = "";
}
function show_hidden(id){
	var obj = document.getElementById(id);
	var style = obj.style.display;
	if(obj.style.display=="block"){
        obj.style.display='none';
    }else{
        obj.style.display='block';
    }
}
function switch_className(id){
    var obj = document.getElementById(id);
    if(obj.style.display=="inline"){
        obj.style.display='none';
    }else{
        obj.style.display='inline';
    }
}
$(function($){
    $('#exit_id').click(function(){
        window.location.href = getBasePath()+"/exit";
    });

    //$('.menu').height($('.main').scrollHeight);
    $("#report_detail").click(function(){
        var infoForm = document.createElement("form");
        infoForm.method="POST" ;
        infoForm.action = getBasePath()+"/reportInfo";
        var channelIdInput = document.createElement("input") ;
        channelIdInput.setAttribute("name", "channelId") ;
        infoForm.appendChild(channelIdInput) ;

        document.body.appendChild(infoForm) ;
        infoForm.submit() ;
        document.body.removeChild(infoForm) ;
    });

    $.datepicker.regional['zh-CN'] = {
        closeText: '关闭',
        prevText: '&#x3c;上月',
        nextText: '下月&#x3e;',
        currentText: '今天',
        monthNames: ['01月','02月','03月','04月','05月','06月', '07月','08月','09月','10月','11月','12月'],
        monthNamesShort: ['01月','02月','03月','04月','05月','06月', '07月','08月','09月','10月','11月','12月'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
        dayNamesMin: ['日','一','二','三','四','五','六'],
        dateFormat: 'yy/mm/dd', firstDay: 1,
        isRTL: false};
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);

});

function getCookie(objName){
    var arrStr = document.cookie.split("; ");
    for(var i = 0;i < arrStr.length;i ++){
        var temp = arrStr[i].split("=");
        if(temp[0] == objName) return unescape(temp[1]);
    }

}

function getBasePath(){
    return $('#basePath').attr('value');
}

function createForms(url,values){
    var infoForm = document.createElement("form");
    infoForm.method="POST" ;
    infoForm.action = url;
    for(var i = 0;i<values.length;i++){
        var inputObj = document.createElement("input") ;
        inputObj.setAttribute("name", values[i].toString()) ;
        inputObj.setAttribute("value", values[i]);
        infoForm.appendChild(inputObj) ;
    }

    document.body.appendChild(infoForm) ;
    infoForm.submit() ;
    document.body.removeChild(infoForm) ;
}

function isEmpty(str){
    if(str != null && str.trim().length > 0){
        return false;
    }else{
        return true;
    }
}




