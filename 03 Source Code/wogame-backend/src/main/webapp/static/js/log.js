
$(function(){
    var typeVlaue = $("#dateType").val();
    var channelNum = $("#channelId").val();
    var basePath = getBasePath();
    $("#select option[value='"+typeVlaue+"']").attr("selected","true");
    var dateTableDay = '<tr><td>昨日</td></tr><tr><td>前两天</td></tr><tr><td>前三天</td></tr><tr><td>前四天</td></tr><tr><td>前五天</td></tr>';
    var dateTdDay = ' <td>昨日</td><td>前两天</td><td>前三天</td><td>前四天</td><td>前五天</td>';
    var dateTableMonth = '<tr><td>本月</td></tr><tr><td>上月</td></tr><tr><td>前两月</td></tr><tr><td>前三月</td></tr><tr><td>前四月</td></tr>';
    var dateTdMonth = ' <td>本月</td><td>上月</td><td>前两月</td><td>前三月</td><td>前四月</td>';
    if(typeVlaue == '1'){
        $('#dateTale').append(dateTableDay);
        $('#gameFirstTd').append(dateTdDay);
    }else if(typeVlaue == '2'){
        $('#dateTale').append(dateTableMonth);
        $('#gameFirstTd').append(dateTdMonth);
    }
    $('#select').change(function(){
        var type = $("#select").find("option:selected").val();
        createForm(getBasePath()+'/getlog',channelNum,$("#select").find("option:selected").val());
    });
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
        data: {type:typeVlaue, channelId:channelNum,page:1},
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
        var type = $("#select").find("option:selected").val();
        createForm(getBasePath()+'/getlog',channelNum,$("#select").find("option:selected").val());
    });

    $('#pages a').click(function(){
        var curPage = $(this).text();
        $.ajax({
            type: "POST",
            url: basePath+"/topGameLog",
            data: {type:typeVlaue, channelId:channelNum,page:curPage},
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
        $(this).addClass('current_page');
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

