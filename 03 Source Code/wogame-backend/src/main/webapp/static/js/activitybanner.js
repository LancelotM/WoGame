
$(function () {
    $(".delbtn").click(function(){
        $(this).closest("tr").remove();
    });

    $("#add").click(function(){
        if($(".detail_tb tr").length == 4){
            alert("已经添加了三条数据，不可以进行添加操作！如若必须添加，可先删除一条数据后再添加");
        }
    });
});