<#include "/common/template.ftl">
<@template csses=["index"] jses=["bottombanner"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>底部Banner管理
                </div>
                <form id="bottombanner_form"  method="post">
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="link_class">图片上传</td>
                            <td><input id="picture_input" type="file" name="pictureCode" title="请上传图片"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">路径</td>
                            <td><input id="url_input" type="text" name="urlCode" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class"></td>
                            <td>
                                <a href="javascript:;"><img src="${basePath}/static/images/add.png" alt="detail"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="dialog" style="display:none;">
                <div id="dialog_head">
                    <span id="dialog_titile">修改已上传底部Banner信息</span>
                </div>
                <form id="update_form"  method="post">
                    <table id="dialog_table">
                        <tr>
                            <td>图片上传：</td>
                            <td><input id="dialog_picture" name="pictureCode" type="file" value=""/></td>
                        </tr>
                        <tr>
                            <td>路径:</td>
                            <td><input id="dialog_url" name="urlCode" type="text" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a class="left" id="update_submit" href="javascript:;">
                                    <img src="${basePath}/static/images/confirm_update.png" alt="update" style="margin-right:75px;"/>
                                </a>
                                <a class="right" id="cancel" href="javascript:;"><img src="${basePath}/static/images/cancel.png" alt="cancel"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="create_detail">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_sitelist.png" alt="" />已上传底部Banner
                                <span class="numberal_format">
                                    <#if bottomBannerInfos?exists>
                                    ${bottomBannerInfos?size}
                                    <#else>
                                        0
                                    </#if>
                                </span>个
                </div>
                <table class="detail_tb">
                    <caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>已上传底部Banner详细</span></caption>
                    <tr class="first_tr">
                        <td>编号</td>
                        <td>图片</td>
                        <td>路径</td>
                        <td class="operate_td">操作</td>
                    </tr>
                    <#if bottomBannerInfos?exists>
                        <#list bottomBannerInfos as bottomBannerInfo>
                            <tr>
                                <td>${bottomBannerInfo.id!}</td>
                                <td>${bottomBannerInfo.picture!}</td>
                                <td>${bottomBannerInfo.url!}</td>
                                <td id="operate_td" class="operate_td">
                                    <a id="update_info" href="javascript:;"><img src="${basePath}/static/images/update.png" /></a>
                                    <a class="delbtn" href="javascript:;"><img src="${basePath}/static/images/delete.png" alt="detail"/></a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
</@template>
