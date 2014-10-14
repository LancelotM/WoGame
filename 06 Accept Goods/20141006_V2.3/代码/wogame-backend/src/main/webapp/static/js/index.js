function checkForm(){
    var basePath = getBasePath();
    var username = $('#username').val().trim();
    var password = $('#password').val().trim();
    if(!isEmpty(username) && !isEmpty(password)){
        $.ajax({
            url:basePath+"/checkNamePwd",
            data:"username="+username+"&password="+password,
            type:"POST",
            success:function(data,status){
                if(data == 0){
                    var isCheck = document.getElementById("checkbox_img").style.display;
                    if(isCheck == 'inline'){
                        $('#remember').val("1");
                    }else{
                        $('#remember').val("0");
                    }
                    $('#form').submit();
                }else if(data == 1){
                    $('#check_username').text("用户不存在!");
                }else if(data == 2){
                    $('#check_password').text("密码错误!");
                }else if(data == 3){
                    $('#check_username').text("登录失败!");
                }
            }
        });
    }else if(isEmpty(username) || isEmpty(password)){
        if(isEmpty($('#username').val())){
            $('#check_username').text("用户名为空!");
        }else if(isEmpty($('#password').val())){
            $('#check_password').text("密码为空!");
        }
    }
}

$(window).keydown(function(event){
    if(event.keyCode == 13){
        $('#submit').trigger('click');
    }
});

$(function(){
    var loginCode = getCookie("login_code");
    var pwd =  getCookie("pwd");
    if(pwd != null){
        document.getElementById("checkbox_img").style.display='inline';
    }
    if(!isEmpty(loginCode)){
        $("#username").val(loginCode);
    }
    if(!isEmpty(pwd)){
        $("#password").val(pwd);
    }

    if(!isEmpty(loginCode)&& !isEmpty(pwd)){
        $('#submit').trigger('click');
    }

    checkInput();
});

function checkInput(){
    $('.enter_td input').each(function(){
        $(this).blur(function(){
            if(isEmpty($(this).val().trim())){
                if($(this).attr('name') == "username"){
                    $('#check_username').text("用户名为空!");
                }else if($(this).attr('name') == "password"){
                    $('#check_password').text("密码为空!");
                }
            }else{
                if($(this).attr('name') == "username"){
                    $('#check_username').text("");
                }else if($(this).attr('name') == "password"){
                    $('#check_password').text("");
                }
            }
        });

    });
}





