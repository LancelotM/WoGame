$(function(){
    $('#report_search').click(function(){
        var reportChaid = $('#report_chaid').val().trim();
        if(reportChaid == null || reportChaid == ""){
            alert("channelID不能为空！");
            return;
        }else if($("#startDateStr").val() > $("#endDateStr").val()){
            alert("截止日期不能大于起始日期！");
            return;
        }
        $('#report_form').submit();
    });

    $("#startDateStr").datepicker({
        showOn: 'button',
        minDate: '-10y', maxDate: new Date(),
        dateFormat: "yy-mm-dd",
        buttonImage: getBasePath()+'/static/images/calendar.gif',
        buttonImageOnly: true
    });
    $("#endDateStr").datepicker({
        showOn: 'button',
        minDate: '-10y', maxDate: new Date(),
        dateFormat: "yy-mm-dd",
        buttonImage: getBasePath()+'/static/images/calendar.gif',
        buttonImageOnly: true
    });
});