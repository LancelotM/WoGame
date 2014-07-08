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
$(function(){
    $('#exit_id').click(function(){
        window.location.href = getBasePath()+"/exit";
    });
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



