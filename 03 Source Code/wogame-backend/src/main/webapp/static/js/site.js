$(function(){
    $('#province_div a').click(function(){
        $.get("/wogamecenter/getActiveInfo?channelId="+$(this).attr('name'),function(data,status){
            if(data == 'true'){

            }
        });
        $('#channel').text($(this).text());
        $('#channelId').attr('value',$(this).attr('name'));
    });

    $('#launch').click(function(){
        $.get("/wogamecenter/startSite?channelId="+$('#channelId').attr('value'),function(data,status){
            alert("data:"+data.wapURL);
            $('#wapURL').text(data.wapURL);
            $('#logURL').text(data.logURL);
        });
    });
});

