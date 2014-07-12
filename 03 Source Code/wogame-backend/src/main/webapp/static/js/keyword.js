$(function(){
    $('#downloadinfo_search').click(function(){
        var basePath = getBasePath();
        var dateStrVal = $('#dateStr').val();
        var gameNameVal = $('#gameName').val();
        var downloadChaidVal = $('#downloadChaid').val();
        var rowsPerPage = $('#rowsPerPage').val();
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,dateStr:dateStrVal,gameName:gameNameVal,page:1,rowsPerPage:rowsPerPage},
            dataType: "json",
            async:false,
            success: function(data){
                $("tr[class='append_tr']").remove();
                $('#pages a').remove();
                var pages = 0;
                $('#totalRecord').text(data.totalRecords);
                if(data.totalRecords%rowsPerPage == 0){
                    pages = data.totalRecords/rowsPerPage;
                }else {
                    pages = data.totalRecords/rowsPerPage + 1;
                }
                if(pages > 1){
                    var pageHtml = "";
                    for(var i = 1;i<=pages;i++){
                        if(i == 1){
                            pageHtml += '<a class="current_page" href="javascript:;">'+i+'</a>';
                        }else{
                            pageHtml += '<a href="javascript:;">'+i+'</a>';
                        }
                    }
                }
                $('#pages').append(pageHtml);
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
        var startDateVal = $('#startDate').val();
        var endDateVal = $('#endDate').val();
        var downloadChaidVal = $('#downloadChaid').val();
        var pageVal = $(this).text();
        var rowsPerPage = $('#rowsPerPage').val();
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,dateStr:startDateVal,endDate:endDateVal,page:pageVal,rowsPerPage:rowsPerPage},
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

});