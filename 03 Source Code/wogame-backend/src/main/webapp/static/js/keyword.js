$(function(){
    var pages = 0;
    var keyword;
    $('#downloadinfo_search').click(function(page){
        var basePath = getBasePath();
        var startDateVal = $('#startDateStr').val().trim();
        var endDateVal = $('#endDateStr').val().trim();
        var downloadChaidVal = $('#downloadChaid').val().trim();
        var rowsPerPage = 20;
        if(!isEmpty(startDateVal)&&!isEmpty(endDateVal)&&startDateVal > endDateVal){
            alert("起始日期不能大于截止日期！");
            return;
        }else if(!isEmpty(startDateVal) && (isEmpty(endDateVal))){
            endDateVal = startDateVal;
        }else if(!isEmpty(endDateVal) && (isEmpty(startDateVal))){
            startDateVal = endDateVal;
        }
        $.ajax({
            type: "POST",
            url: basePath+"/getDownloadInfo?"+Math.random(),
            data: {channelId:downloadChaidVal,startDate:startDateVal,endDate:endDateVal,page:1,rowsPerPage:rowsPerPage},
            dataType: "json",
            async:false,
            success: function(data){
                $('#totalRecord').text(data.totalRecords);

                appendData(data);
                if(data.totalRecords%rowsPerPage == 0){
                    pages = data.totalRecords/rowsPerPage;
                }else {
                    pages = parseInt(data.totalRecords/rowsPerPage) + 1;
                }

                if(pages>1){
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
                            AJAXRequest(downloadChaidVal,startDateVal,endDateVal,pageVal,rowsPerPage);
                        }
                    });
                }else{
                    $("#pages").empty();
                }
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

    $(".keyword div").each(function(){
        if($(this).text().length > 14){
            $(this).removeClass("keyword_show_content");
            $(this).addClass("keyword_div");
        }
    });

    $(".keyword div").mouseenter(function(){
        if($(this).attr("class") == 'keyword_div'){
            $(this).removeClass("keyword_div");
            $(this).addClass("keyword_show_content");
            keyword = $(this).next().text();
            if(($(this).index()+1)%5 != 0){
                $(this).next().text("");
            }
        }

    });
    $(".keyword div").mouseleave(function(){
        if($(this).attr("class") == 'keyword_show_content' && $(this).text().length > 14){
            $(this).removeClass("keyword_show_content");
            $(this).addClass("keyword_div");
            if(($(this).index()+1)%5 != 0){
                $(this).next().text(keyword);
            }
        }
    });

});

function AJAXRequest(downloadChaidVal,startDateVal,endDateVal,pageVal,rowsPerPage){
    $.ajax({
        type: "POST",
        url: getBasePath()+"/getDownloadInfo?"+Math.random(),
        data: {channelId:downloadChaidVal,startDate:startDateVal,endDate:endDateVal,page:pageVal,rowsPerPage:rowsPerPage},
        dataType: "json",
        async:false,
        success: function(data){
            appendData(data);
        }
    });
}
function appendData(data){
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
