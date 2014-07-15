$(function(){
    $('#report_search').click(function(){
        var reportChaid = $('#report_chaid').val().trim();
        var startDateVal = $("#startDateStr").val().trim();
        var endDateVal = $("#endDateStr").val().trim();
        if(isEmpty(reportChaid)){
            alert("channelID不能为空！");
            return;
        }else if(!isEmpty(startDateVal)&&!isEmpty(endDateVal)&&startDateVal > endDateVal){
            alert("起始日期不能大于截止日期！");
            return;
        }else if(!isEmpty(startDateVal) && (isEmpty(endDateVal))){
            $("#endDateStr").val(startDateVal)
        }else if(!isEmpty(endDateVal) && (isEmpty(startDateVal))){
            $("#startDateStr").val(endDateVal)
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