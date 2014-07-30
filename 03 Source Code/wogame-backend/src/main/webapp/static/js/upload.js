$(function(){
    var basepath = getBasePath();
    var optionValue,appidValue,channelIdValue;
    $('#file_upload').uploadify({
        'height':22,
        'width' :109,
        'uploader'           : basepath+'/static/js/uploadify/uploadify.swf',
        'multi'          :true,
        'auto'          : false,
        'fileTypeExts'  : '*.*',
        'script'       :basepath+'/uploadFileHandel',
        'method'       :'post',
        'cancelImg':basepath+'/static/images/uploadImg/cancel.png',
        'buttonImg'  : basepath+'/static/images/state_orange.png',
        'buttonText' :'选择..'
//        'onUploadSuccess':function(file, data, response){
//            alert('文件 ' + file.name + response);
//        },
//        'onAllComplete' : function(event,data) {
//            alert(data.filesUploaded + ' files uploaded successfully!');
//        }
    });
});

function startUpload(){
    optionValue = $("#updateType").find("option:selected").val();
    appidValue = $("#upload_appid").val();
    channelIdValue = $("#upload_channelId").val();
    $('#file_upload').uploadifySettings('scriptData',{'updateType':optionValue,'appid':appidValue,'channelId':channelIdValue},true);
    $('#file_upload').uploadifyUpload();
}