$(function(){
    $('#downloadinfo_search').click(function(){
        var dateStrVal = $('#dateStr').val();
        var gameNameVal = $('#gameName').val();
        var downloadChaidVal = $('#downloadChaid').val();
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,dateStr:dateStrVal,gameName:gameNameVal,page:1},
            dataType: "json",
            success: function(data){
                var pageHtml = "";
                for(var i = 1;i<=data.totalPages;i++){
                    if(i == 1){
                        pageHtml += '<a class="current_page" href="javascript:;">'+i+'</a>';
                    }
                    pageHtml += '<a href="javascript:;">'+i+'</a>';
                }
                var downloadInfohtml = "";
                $.each(data.downloadInfomodels, function(commentIndex, comment){
                    downloadInfohtml += '<tr class="append_tr"><td>'+comment['productName']
                        +'</td><td>'+comment['downloadCount']+'</td></tr>'
                });
                $('#dowloadInfo_tb').append(downloadInfohtml);
            }
        });
    });

    $('#pages a').click(function(){
        var dateStrVal = $('#dateStr').val();
        var gameNameVal = $('#gameName').val();
        var downloadChaidVal = $('#downloadChaid').val();
        var pageVal = $(this).text();
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,dateStr:dateStrVal,gameName:gameNameVal,page:pageVal},
            dataType: "json",
            success: function(data){
                $("tr[class='append_tr']").remove();
                var downloadInfohtml = "";
                $.each(data.downloadInfomodels, function(commentIndex, comment){
                    downloadInfohtml += '<tr class="append_tr"><td>'+comment['productName']
                        +'</td><td>'+comment['downloadCount']+'</td></tr>'
                });
                $('#dowloadInfo_tb').append(html);
                $("a[class='current_page']").removeClass();
                $(this).addClass('current_page');
            }
        });
    });
});