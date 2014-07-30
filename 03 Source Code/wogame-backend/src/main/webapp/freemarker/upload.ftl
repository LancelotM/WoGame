<#include "/common/template.ftl">
<@template csses=["upload"] jses=["upload"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
        <div class="main">
            <div class="main_info detail">
                <div class="log_title" style="margin-top:20px;">
                    <img class="title_image" src="${basePath}/static/images/upload.png"/>上传文件
                </div>
                <div type="text-align:right">
                    <form action="" method="">
                        <table id="upload_table" cellpadding="0" cellspacing="0" style="width:400px;background-color:#f7f7f7;margin:30px auto;">
                            <tr>
                                <td>更新类型：</td>
                                <td>
                                    <select id="updateType" >
                                        <option value="add">增加</option>
                                        <option value="update">修改</option>
                                        <option value="delete">删除</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>APPID:</td>
                                <td>
                                    <input id="upload_appid" type="text" value="" name="appid" />
                                </td>
                            </tr>
                            <tr>
                                <td>channelID:</td>
                                <td>
                                    <input id="upload_channelId" type="text" value="" name="channelID" />
                                </td>
                            </tr>
                            <tr style="height:24px;border:0px">
                                <td colspan="2" style="padding-top:5px;padding-left:3px">
                                    <input id="file_upload" type="file" value="" name="file"/>

                                    <a href="javascript:$('#file_upload').uploadifyClearQueue()"
                                       style="padding:2px 24px;background: url(${basePath}/static/images/state_gray.png) no-repeat;color:#f7f7f7;font-weight: bold;float: right">取消上传</a>
                                    <a class="easyui-linkbutton" onclick="startUpload();" href="javascript:void(0);"
                                       style="padding:2px 24px;background: url(${basePath}/static/images/state_yellow.png) no-repeat;color:#f7f7f7;font-weight: bold;float: right">开始上传</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>
</@template>