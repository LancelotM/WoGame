$(function(){
    var pages = 0;
    $('#downloadinfo_search').click(function(){
        var basePath = getBasePath();
        var startDateVal = $('#startDateStr').val();
        var endDateVal = $('#endDateStr').val();
        var downloadChaidVal = $('#downloadChaid').val();
        var rowsPerPage = $('#rowsPerPage').val();
        if($("#startDateStr").val() > $("#endDateStr").val()){
            alert("截止日期不能大于起始日期！");
            return;
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
                    pages = data.totalRecords/rowsPerPage + 1;
                }
//                if(pages > 1){
//                    var pageHtml = "";
//                    for(var i = 1;i<=pages;i++){
//                        if(i == 1){
//                            pageHtml += '<a class="current_page" href="javascript:;">'+i+'</a>';
//                        }else{
//                            pageHtml += '<a href="javascript:;">'+i+'</a>';
//                        }
//                    }
//                }
//                $('#pages').append(pageHtml);
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

    $('#pages a').click(function(){
        var basePath = getBasePath();
        var startDateVal = $('#startDateStr').val();
        var endDateVal = $('#endDateStr').val();
        var downloadChaidVal = $('#downloadChaid').val();
        var pageVal = $(this).text();
        var rowsPerPage = $('#rowsPerPage').val();
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,startDate:startDateVal,endDate:endDateVal,page:pageVal,rowsPerPage:rowsPerPage},
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
        $("a[class='current_page']").removeClass();
        $(this).addClass('current_page');
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
        mouse					: 'press'
    });
});