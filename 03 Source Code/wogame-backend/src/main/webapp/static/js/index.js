function checkBlank(id,checkInfo){
     var value = document.getElementById(id).value;
     if(value == "" || value == null){
		document.getElementById(checkInfo).innerHTML = "内容不能为空!";
     }else{
         document.getElementById(checkInfo).innerHTML = "";
     }
}
function checkForm(){
    var username = $('#username').val();
    var password = $('#password').val();
    if(username != "" && username != null && password != "" && password != null){
        var isCheck = document.getElementById("checkbox_img").style.display;
        if(isCheck == 'inline'){
            setCookie("login_code",username,30);
            setCookie("pwd",password,30)
        }
        $('#form').submit();
    }

}

$(window).keydown(function(event){
    if(event.keyCode == 13){
        $('#submit').click();
    }
});

$(function(){
    var loginCode = getCookie("login_code");
    var pwd =  getCookie("pwd");
    if(pwd != null){
        document.getElementById("checkbox_img").style.display='inline';
    }
    if(loginCode != null && loginCode != ""){
        $("#username").val(loginCode);
    }
    if(pwd != null && pwd != ""){
        $("#password").val(pwd);
    }
});

function getCookie(objName){
    var arrStr = document.cookie.split("; ");
        for(var i = 0;i < arrStr.length;i ++){
              var temp = arrStr[i].split("=");
              if(temp[0] == objName) return unescape(temp[1]);
           }

}
function setCookie(c_name,value,expiredays)
{
    var exdate=new Date();
    exdate.setDate(exdate.getDate()+expiredays);
    document.cookie=c_name+ "=" +escape(value)+
    ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

