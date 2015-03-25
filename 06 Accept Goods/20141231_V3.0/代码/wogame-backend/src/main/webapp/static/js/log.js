
$(function(){
    var typeVlaue = $("#dateType").val();
    var channelNum = $("#channelId").val();
    var basePath = getBasePath();
    $("#select option[value='"+typeVlaue+"']").attr("selected","true");

    $('#select').change(function(){
        var type = $("#select").find("option:selected").val();
        createForm(getBasePath()+'/getlog',channelNum,type);
    });
    $.ajax({
        type: "POST",
        url: basePath+"/userLoginLog?"+Math.random(),
        data: {type:typeVlaue, channelId:channelNum},
        dataType: "json",
        success: function(data){
            var stepValue = 1;
            if(data.unit.length > 12){
                stepValue = 3;
            }
            $('#uv').highcharts({ title: { text: '联通沃游戏中心', x: -20 },subtitle:{text:'新老用户登录统计',x:-20},
                xAxis: { categories: data.unit ,labels:{step:stepValue,staggerLines: 1}},
                yAxis: { title: { text: '登陆次数' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
                tooltip: { valueSuffix: '次' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
                series: data.result });
        }
    });
    $.ajax({
        type: "POST",
        url: basePath+"/pageTrafficLog?"+Math.random(),
        data: {type:typeVlaue, channelId:channelNum},
        dataType: "json",
        success: function(data){
            var stepValue = 1;
            if(data.unit.length > 12){
                stepValue = 3;
            }
            $(function () { $('#pv').highcharts({ title: { text: '联通沃游戏中心', x: -20 },subtitle:{text:'主要页面流量统计',x:-20},
                xAxis: { categories: data.unit,labels:{step:stepValue,staggerLines: 1}},
                yAxis: { title: { text: '' }, plotLines: [{ value: 0, width: 1, color: '#808080' }] },
                tooltip: { valueSuffix: '' }, legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
                series: data.result }); });
        }
    });

});

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



