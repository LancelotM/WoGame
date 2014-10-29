<#include "/common/template.ftl">
<@template csses=["bottombanner"] jses=["bottombanner"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>底部广告位管理
                </div>
                <form id="bottombanner_form" method="post" enctype="multipart/form-data">
                    <#if bottomBannerInfos?exists>
                        <#list bottomBannerInfos as bottomBannerInfo>
                            <script type="text/javascript">
                                $(function(){
                                    $("#picture_input").val("${bottomBannerInfo.imageName!}");
                                    $("#content_input").val("${bottomBannerInfo.description!}");
                                    $("#url_input").val("${bottomBannerInfo.url!}");
                                    $("#bottom_id").val("${bottomBannerInfo.id!}");
                                });
                            </script>
                        </#list>
                    </#if>
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="head_format">图标</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="picture_input" class="txt" name="imageName"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="file" class="file" id="fileField" multiple="multiple" onchange="picture_input.value=this.value"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">内容</td>
                            <td><input id="content_input" type="text" name="description" title="请输入内容"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">链接</td>
                            <td><input id="url_input" type="text" name="url" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format"></td>
                            <td>
                                <input id="bottom_id" name="id" type="text" style="display: none;"/>
                                <input id="type_input" type="text" name="adType" style="display: none;" value="5"/>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format"></td>
                            <td>
                                <a  href="javascript:;" class="float_button"><img id="dynamic_button"/></a>
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
