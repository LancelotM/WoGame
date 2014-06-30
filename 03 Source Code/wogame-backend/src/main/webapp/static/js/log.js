
$(function(){
    var typeVlaue = $("#select").find("option:selected").val();
    var channelNum = $("#channelId").val();
    var basePath = getBasePath();
    $.ajax({
        type: "POST",
        url: basePath+"/userLoginLog",
        data: {type:typeVlaue, channelId:channelNum},
        dataType: "json",
        success: function(data){
            $('#uv').highcharts({ title: { text: '联通沃游戏中心', x: -20 },subtitle:{text:'新老用户登录统计',x:-20},
                xAxis: { categories: data.unit },
                yAxis: { title: { text: '登陆次数' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
                tooltip: { valueSuffix: '次' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
                series: data.result });
        }
    });
    $.ajax({
        type: "POST",
        url: basePath+"/pageTrafficLog",
        data: {type:typeVlaue, channelId:channelNum},
        dataType: "json",
        success: function(data){
            $(function () { $('#pv').highcharts({ title: { text: '联通沃游戏中心', x: -20 },subtitle:{text:'主要页面流量统计',x:-20},
                xAxis: { categories: data.unit },
                yAxis: { title: { text: '点击次数' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
                tooltip: { valueSuffix: '次' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
                series: data.result }); });
        }
    });
    $.ajax({
        type: "POST",
        url: basePath+"/firstPageBannerLog",
        data: {type:typeVlaue, channelId:channelNum},
        dataType: "json",
        success: function(data){
            var html = "";
            $.each(data, function(commentIndex, comment){
                html += "<tr>";
                $.each(comment,function(index,obj){
                    html += '<td>'+obj['clickThrough']+'|'+obj['downloadCount']+'</td>';
                });
                html += "</tr>"
            });
            $('#banner').append(html);
        }
    });

    $.ajax({
        type: "POST",
        url: basePath+"/topGameLog",
        data: {type:typeVlaue, channelId:channelNum},
        dataType: "json",
        success: function(data){
            var html = "";
            $.each(data, function(commentIndex, comment){
                html += '<tr><td><img src="'+basePath+'/static/images/game_img.png"/>'+comment['gameName']
                    +'</td><td>'+comment['thisTimeData']+'</td><td>'+comment['lastTimeData']+
                    '</td><td>'+comment['last2TimeData']+'</td><td>'+comment['last3TimeData']+
                    '</td><td>'+comment['last4TimeData']+'</td></tr>'
            });
            $('#top30Game').append(html);
        }
    });

    $('#select').change(function(){
        createForm(getBasePath()+'/getlog',channelNum,$("#select").find("option:selected").val());
    });
});

function getBasePath(){
    return $('#basePath').attr('value');
}

function createForm(url,value,type){
    var infoForm = document.createElement("form");
    infoForm.method="POST" ;
    infoForm.action = url;
    var channelIdInput = document.createElement("input") ;
    channelIdInput.setAttribute("name", "channelId") ;
    channelIdInput.setAttribute("value", value);
    infoForm.appendChild(channelIdInput) ;

    var dateInput = document.createElement("input") ;
    dateInput.setAttribute("name", "type") ;
    dateInput.setAttribute("value", type);
    infoForm.appendChild(dateInput) ;

    document.body.appendChild(infoForm) ;
    infoForm.submit() ;
    document.body.removeChild(infoForm) ;
}