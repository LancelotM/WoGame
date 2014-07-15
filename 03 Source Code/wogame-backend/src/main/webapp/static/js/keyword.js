$(function(){
    var pages = 0;
    $('#downloadinfo_search').click(function(){
        var basePath = getBasePath();
        var startDateVal = $('#startDateStr').val().trim();
        var endDateVal = $('#endDateStr').val().trim();
        var downloadChaidVal = $('#downloadChaid').val().trim();
        var rowsPerPage = 20;
        if(startDateVal > endDateVal){
            alert("截止日期不能大于起始日期！");
            return;
        }else if(startDateVal != null && startDateVal !="" && (endDateVal == null || endDateVal == "")){
            endDateVal = startDateVal;
        }else if(endDateVal != null && endDateVal !="" && (startDateVal == null || startDateVal == "")){
            startDateVal = endDateVal;
        }
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,startDate:startDateVal,endDate:endDateVal,page:1,rowsPerPage:rowsPerPage},
            dataType: "json",
            async:false,
            success: function(data){
                $("tr[class='append_tr']").remove();
                $('#pages a').remove();

                $('#totalRecord').text(data.totalRecords);
                if(data.totalRecords%rowsPerPage == 0){
                    pages = data.totalRecords/rowsPerPage;
                }else {
                    pages = parseInt(data.totalRecords/rowsPerPage) + 1;
                }

                var downloadLefhtml = "";
                var downloadRighthtml = "";
                $.each(data.downloadInfomodels, function(commentIndex, comment){
                    if(commentIndex%2==0){
                        downloadLefhtml += '<tr class="append_tr"><td>'+comment['productName']
                            +'</td><td>'+comment['downloadCount']+'</td></tr>';
                    }

                });
                $.each(data.downloadInfomodels, function(commentIndex, comment){
                    if(commentIndex%2!=0){
                        downloadRighthtml += '<tr class="append_tr"><td>'+comment['productName']
                            +'</td><td>'+comment['downloadCount']+'</td></tr>';
                    }
                });
                $('#download_info_left').append(downloadLefhtml);
                $('#download_info_right').append(downloadRighthtml);
            }
        });
    });

    $('#downloadinfo_search').trigger('click');

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

    $("#pages").paginate({
        count 		: pages,
        start 		: 1,
        display     : 8,
        border					: true,
        border_color			: '#ed6e02',
        text_color  			: '#ed6e02',
        background_color    	: 'white',
        border_hover_color		: '#ccc',
        text_hover_color  		: '#000',
        background_hover_color	: '#ed6e02',
        images					: false,
        mouse					: 'press',
        onChange                : function(pageVal){
                                    var basePath = getBasePath();
                                    var startDateVal = $('#startDateStr').val();
                                    var endDateVal = $('#endDateStr').val();
                                    var downloadChaidVal = $('#downloadChaid').val();
                                    $.ajax({
                                        type: "POST",
                                        url: basePath+"/getDownloadInfo?"+Math.random(),
                                        data: {channelId:downloadChaidVal,startDate:startDateVal,endDate:endDateVal,page:pageVal,rowsPerPage:20},
                                        dataType: "json",
                                        async:false,
                                        success: function(data){
                                            $("tr[class='append_tr']").remove();
                                            var downloadLefhtml = "";
                                            var downloadRighthtml = "";
                                            $.each(data.downloadInfomodels, function(commentIndex, comment){
                                                if(commentIndex%2==0){
                                                    downloadLefhtml += '<tr class="append_tr"><td>'+comment['productName']
                                                        +'</td><td>'+comment['downloadCount']+'</td></tr>';
                                                }

                                            });
                                            $.each(data.downloadInfomodels, function(commentIndex, comment){
                                                if(commentIndex%2!=0){
                                                    downloadRighthtml += '<tr class="append_tr"><td>'+comment['productName']
                                                        +'</td><td>'+comment['downloadCount']+'</td></tr>';
                                                }
                                            });
                                            $('#download_info_left').append(downloadLefhtml);
                                            $('#download_info_right').append(downloadRighthtml);
                                        }
                                    });
                                  }


    });
});