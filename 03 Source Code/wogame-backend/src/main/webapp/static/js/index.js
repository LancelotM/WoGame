function checkBlank(id,checkInfo){
     var value = document.getElementById(id).value;
     if(value == "" || value == null){
		document.getElementById(checkInfo).innerHTML = "内容不能为空!";
     }
}
function checkForm(form){
    if(form.username.value != "" && form.username.value != null && form.password.value != "" && form.password.value != null){
         form.submit();
    }

}