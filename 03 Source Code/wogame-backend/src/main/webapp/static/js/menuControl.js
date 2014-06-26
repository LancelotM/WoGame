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