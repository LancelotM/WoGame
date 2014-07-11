$(function(){
    $('#report_search').click(function(){
        var reportChaid = $('#report_chaid').val().trim();
        if(reportChaid == null || reportChaid == ""){
            alert("channelID不能为空！");
            return;
        }

        $('#report_form').submit();
    });
});