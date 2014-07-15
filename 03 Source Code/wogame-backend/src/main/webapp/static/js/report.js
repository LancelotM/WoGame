$(function(){
    $('#report_search').click(function(){
        var reportChaid = $('#report_chaid').val().trim();
        var startDateVal = $("#startDateStr").val().trim();
        var endDateVal = $("#endDateStr").val().trim();
        if(reportChaid == null || reportChaid == ""){
            alert("channelID不能为空！");
            return;
        }else if(startDateVal > endDateVal){
            alert("截止日期不能大于起始日期！");
            return;
        }else if(startDateVal != null && startDateVal !="" && (endDateVal == null || endDateVal == "")){
            endDateVal = startDateVal;
        }else if(endDateVal != null && endDateVal !="" && (startDateVal == null || startDateVal == "")){
            startDateVal = endDateVal;
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