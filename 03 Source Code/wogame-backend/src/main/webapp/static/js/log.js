$(function () { $('#pv').highcharts({ title: { text: '联通沃游戏中心', x: -20 },subtitle:{text:'主要页面流量统计',x:-20},
    xAxis: { categories: ['06-01', '06-02', '06-03', '06-04', '06-05', '06-06','06-07', '06-08', '06-09',
        '06-10', '06-11', '06-12', '06-13', '06-14', '06-15', '06-16', '06-17', '06-18', '06-19',
        '06-20', '06-21', '06-22', '06-23', '06-24', '06-25', '06-26', '06-27', '06-28', '06-29', '06-30'] },
    yAxis: { title: { text: '点击次数' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
    tooltip: { valueSuffix: '次' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
    series: [{ name: '首页', data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,10,18,12,8,20,2,6,9,15,2,17,15,23,17,26,12,29,14] },
        { name: '分类', data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5,15,10,3,18,21,20,26,15,13,4,12,15,24,15,17,19,25,10] },
        { name: '一周热榜', data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0,14,15,17,29,21,2,15,16,13,17,19,24,26,10,27,18,19,6] },
        { name: '最新', data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8,14,25,9,4,17,8,18,16,24,25,29,16,27,5,6,9,17,21] }] }); });


$(function () { $('#uv').highcharts({ title: { text: '联通沃游戏中心', x: -20 },subtitle:{text:'新老用户登录统计',x:-20},
    xAxis: { categories: ['06-01', '06-02', '06-03', '06-04', '06-05', '06-06','06-07', '06-08', '06-09',
        '06-10', '06-11', '06-12', '06-13', '06-14', '06-15', '06-16', '06-17', '06-18', '06-19',
        '06-20', '06-21', '06-22', '06-23', '06-24', '06-25', '06-26', '06-27', '06-28', '06-29', '06-30'] },
    yAxis: { title: { text: '登陆次数' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
    tooltip: { valueSuffix: '次' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
    series: [{ name: '老用户', data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,9,8.6,8.4,8.2,7.5,7.3,6.9,6.3,5.3,4.8,4.3,4,3.9,3.4,3.2,3.0,2.1,1.9,0.5] },
        { name: '新用户', data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0,3.1,3.4,5.0,6.1,6.8,7.9,9.3,7.2,7.0,6.3,5.8,5.6,5.9,5.0,4.8,4.6,4.0] }] }); });

$(function(){
    var typeVlaue = $("#select").find("option:selected").val();
    $.ajax({
        type: "GET",
        url: "wogamecenter/userLoginLog",
        data: {type:typeVlaue, channelId:$("#channelId").val()},
        dataType: "json",
        success: function(data){
           /* $.each(data, function(commentIndex, comment){
                html += '<div class="comment"><h6>' + comment['username']
                    + ':</h6><p class="para"' + comment['content']
                    + '</p></div>';
            });
            $('#resText').html(html); */
        }
    });
    $.ajax({
        type: "GET",
        url: "wogamecenter/userLoginLog",
        data: {type:typeVlaue, channelId:$("#channelId").val()},
        dataType: "json",
        success: function(data){
           /* $.each(data, function(commentIndex, comment){
                html += '<div class="comment"><h6>' + comment['username']
                    + ':</h6><p class="para"' + comment['content']
                    + '</p></div>';
            });
           $('#resText').html(html); */
        }
    });
    $.ajax({
        type: "GET",
        url: "wogamecenter/firstPageBannerLog",
        data: {type:typeVlaue, channelId:$("#channelId").val()},
        dataType: "json",
        success: function(data){
            /*$.each(data, function(commentIndex, comment){
                html += '';
            });
            $('#banner').append(html);*/
        }
    });

    /*private String name;

     private String icon;

     private String clickThrough;

     private String downloadCount;

     private String date;*/
    $.ajax({
        type: "GET",
        url: "wogamecenter/topGameLog",
        data: {type:typeVlaue, channelId:$("#channelId").val()},
        dataType: "json",
        success: function(data){
            $.each(data, function(commentIndex, comment){
                html += '<tr><td><img src="${basePath}/static/images/game_img.png"/>'+comment['name']
                    +'</td><td>'+comment['thisTimeData']+'</td><td>'+comment['lastTimeData']+
                    '</td><td>'+comment['last2TimeData']+'</td><td>'+comment['last3TimeData']+
                    '</td><td>'+comment['last4TimeData']+'</td></tr>'
            });
            $('#top30Game').append(html);
        }
    });
});