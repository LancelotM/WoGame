$(function(){
    if($("#url_input").val().trim("").length > 0){
        $("#dynamic_button").attr("src",getBasePath()+"/static/images/update.png");
    }else{
        $("#dynamic_button").attr("src",getBasePath()+"/static/images/add.png");
    }
});