$(function () {
    $(".delbtn").click(function(){
        $(this).closest("tr").remove();
    });
})
