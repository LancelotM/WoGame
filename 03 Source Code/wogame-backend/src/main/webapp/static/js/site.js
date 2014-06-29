$(function(){
    $('#province_div a').click(function(){
        var basePath = getBasePath();
        alert('basePath:'+getBasePath());
        $.get(basePath+"/getActiveInfo?channelId="+$(this).attr('name'),function(data,status){
            alert('data:'+data);
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
    createForm(getBasePath()+'/getlog',id);
}

function createForm(url,value){
    var infoForm = document.createElement("form");
    infoForm.method="POST" ;
    infoForm.action = url;
    var channelIdInput = document.createElement("input") ;
    channelIdInput.setAttribute("name", "channelId") ;
    channelIdInput.setAttribute("value", value);
    infoForm.appendChild(channelIdInput) ;

    document.body.appendChild(infoForm) ;
    infoForm.submit() ;
    document.body.removeChild(infoForm) ;
}

