$(function(){
    $('#province_div a').click(function(){
        $.get("${basePath}/getActiveInfo",function(data,status){
            alert("数据：" + data );
            if(data == 'true'){

            }
        });
        $('#channel').text($(this).text());
    });
});
