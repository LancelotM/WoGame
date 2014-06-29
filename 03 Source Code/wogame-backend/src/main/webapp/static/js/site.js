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
//        $.get("/wogamecenter/startSite?channelId="+$('#channelId').attr('value'),function(data,status){
//            $('#wapURL').text(data.wapURL);
//            $('#logURL').text(data.logURL);
//        });
        var infoForm = document.createElement("form");
        infoForm.method="POST" ;
        infoForm.action = "/wogamecenter/startSite";
        var channelIdInput = document.createElement("input") ;
        channelIdInput.setAttribute("name", "channelId") ;
        channelIdInput.setAttribute("value", $('#channelId').attr('value'));
        infoForm.appendChild(channelIdInput) ;

        document.body.appendChild(infoForm) ;
        infoForm.submit() ;
        document.body.removeChild(infoForm) ;
    });

    $('#')
});

