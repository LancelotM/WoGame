$(function(){
    $('#province_div a').click(function(){
        var basePath = getBasePath();
        $.get(basePath+"/getActiveInfo?channelId="+$(this).attr('name'),function(data,status){
            var returnData = data.toString();
            if(returnData == 'true'){
                $('launch_img').attr('src',basePath+'/static/images/launched.png');
            }
        });
        $('#channel').text($(this).text());
        $('#channelId').attr('value',$(this).attr('name'));
    });

    $('#launch').click(function(){
        var basePath = getBasePath();
        alert($('#channelId').attr('value'));
        var infoForm = document.createElement("form");
        infoForm.method="POST" ;
        infoForm.action = basePath+"/startSite";
        var channelIdInput = document.createElement("input") ;
        channelIdInput.setAttribute("name", "channelId") ;
        channelIdInput.setAttribute("value", $('#channelId').attr('value'));
        infoForm.appendChild(channelIdInput) ;

        document.body.appendChild(infoForm) ;
        infoForm.submit() ;
        document.body.removeChild(infoForm) ;
    });
});

function getBasePath(){
    return $('#basePath').attr('value');
}

function getDetailInfo(id){
    createForm(getBasePath()+'/getlog',id,'1');
}

function createForm(url,channel,date){
    var infoForm = document.createElement("form");
    infoForm.method="POST" ;
    infoForm.action = url;
    var channelIdInput = document.createElement("input") ;
    channelIdInput.setAttribute("name", "channelId") ;
    channelIdInput.setAttribute("value", channel);
    infoForm.appendChild(channelIdInput) ;

    var dateInput = document.createElement("input") ;
    dateInput.setAttribute("name", "type") ;
    dateInput.setAttribute("value", date);
    infoForm.appendChild(dateInput) ;

    document.body.appendChild(infoForm) ;
    infoForm.submit() ;
    document.body.removeChild(infoForm) ;
}

