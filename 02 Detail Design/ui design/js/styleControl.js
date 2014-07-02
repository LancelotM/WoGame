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
$(function(){
	alert("pageloadfinish!");
	$('#create_manager_tb input').focus(function(){
		if($('#channelId_input').val() == "channelID"){
			$('#channelId_input').val('');
		}
		if($('#cpid_input').val() == "CPID"){
			$('#cpid_input').val('');
		}
	});
	
	$('#create_manager_tb input').blur(function(){
		if($('#channelId_input').val() == ""){
			$('#channelId_input').val('channelID');
		}
		if($('#cpid_input').val() == ""){
			$('#cpid_input').val('CPID');
		}
	});
	
	
});