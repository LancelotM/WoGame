function checkBlank(id,checkInfo){
     var value = document.getElementById(id).value;
     if(value == "" || value == null){
		document.getElementById(checkInfo).innerHTML = "内容不能为空!";
     }
}
function checkForm(){
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    if(username != "" && username != null && password != "" && password != null){
         document.getElementById('form').submit();
    }

}