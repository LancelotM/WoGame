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
                <div class="file-box">
                    <input type='text' name='textfield' id='textfield' class='txt' />
                    <input type='button' class='btn' value='浏览...' />
                    <input type="file" name="fileField" class="file" id="fileField" size="28" multiple="multiple" />
                </div>
                <form action="" enctype="multipart/form-data" method="post" id="upload_form">
                    <table id="upload_tb" class="detail_tb" style="display: none">
                        <thead>
                        <tr class="table_title">
                            <td>文件名</td>
                            <td>appid</td>
                            <td>channelID</td>
                            <td>操作</td>
                            <td></td>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="5" style="text-align: right">
                                <a id="start_upload" href="javascript:void(0);" style="padding:2px 24px;background: url(${basePath}/static/images/state_yellow.png) no-repeat;color:#f7f7f7;font-weight: bold;">上传全部</a>
                            <#--<a id="cancel_all" href="javascript:void(0);" style="padding:2px 24px;background: url(${basePath}/static/images/state_gray.png) no-repeat;color:#f7f7f7;font-weight: bold;">取消上传</a>-->
                            </td>
                        </tr>
                        </tfoot>
                        <tbody id="upload_tbody">

                        </tbody>
                    </table>
                </form>

                <#--<div id="dialog" style="display:none;" class="min_height325">-->
                    <#--<div id="dialog_head">-->
                        <#--<span id="dialog_titile">上传文件</span>-->
                    <#--</div>-->

                    <#--<div id="footer">-->
                        <#--<a id="start_upload" href="javascript:void(0);" style="padding:2px 24px;background: url(${basePath}/static/images/state_yellow.png) no-repeat;color:#f7f7f7;font-weight: bold;">开始上传</a>-->

                    <#--</div>-->
                <#--</div>-->

            </div>

        </div>
    </div>
</div>
</@template>