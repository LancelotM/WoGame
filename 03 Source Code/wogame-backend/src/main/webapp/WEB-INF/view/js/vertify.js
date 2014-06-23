function checkBlank(id){
     var value = document.getElementById(id).value;
     if(value == "" || value == null){
         alert("内容不能为空！");
         return false;
     }
}
function checkForm(form){
    if(form.username.value != "" && form.username.value != null && form.password.value != "" && form.password.value != null){
         form.submit();
    }

}