$(function(){

    $('#submit').click(function(){

    });
    $('#submit').click(function(){

    });
    $('#submit').click(function(){

    });
});

function checkBlank(id,checkInfo){
     var value = document.getElementById(id).value;
     if(value == "" || value == null){
		document.getElementById(checkInfo).innerHTML = "内容不能为空!";
     }else{
         document.getElementById(checkInfo).innerHTML = "";
     }
}
function checkForm(){
    var basePath = getBasePath();
    var username = $('#username').val();
    var password = $('#password').val();
    if(username != "" && username != null && password != "" && password != null){
        $.ajax({
            url:basePath+"/checkNamePwd",
            data:"username="+username+"&password="+password,
            type:"POST",
            success:function(data,status){
                if(data == 0){
                    var isCheck = document.getElementById("checkbox_img").style.display;
                    if(isCheck == 'inline'){
                        setCookie("login_code",username,30);
                        setCookie("pwd",password,30)
                    }else if(isCheck == 'none'){
                        delCookie("login_code");
                        delCookie("pwd");
                    }
                    $('#form').submit();
                }else if(data == 1){
                    $('#check_username').text("用户不存在！");
                }else if(data == 2){
                    $('#check_password').text("密码错误！");
                }else{
                    $('#check_username').text("登录失败！");
                }
            }
        });
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

    if(loginCode != null && loginCode != "" && pwd != null && pwd != ""){
        $('#submit').click();
    }
});



