<#include "/common/template.ftl">
<@template csses=["index"] jses=["activitymodule"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>新增信息管理
                </div>
                <form id="activity_form"  method="post">
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="link_class">标题</td>
                            <td><input id="title_input" type="text" name="titleCode" title="请输入title"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">图片上传</td>
                            <td><input id="picture_input" type="file" name="pictureCode" title="请上传图片"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">文字介绍</td>
                            <td><input id="introduce_input" type="text" name="introduceCode" title="请输入文字介绍内容"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">路径</td>
                            <td><input id="url_input" type="text" name="urlCode" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class"></td>
                            <td>
                                <a id="add" href="javascript:;"><img src="${basePath}/static/images/add.png" alt="detail"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="dialog" style="display:none;">
                <div id="dialog_head">
                    <span id="dialog_titile">修改已上传信息</span>
                </div>
                <form id="update_form"  method="post">
                    <table id="dialog_table">
                        <tr>
                            <td>title：</td>
                            <td><input id="dialog_title" type="text" name="titleCode" value=""/></td>
                        </tr>
                        <tr>
                            <td>图片上传：</td>
                            <td><input id="dialog_picture" name="pictureCode" type="file" value=""/></td>
                        </tr>
                        <tr>
                            <td>文字介绍</td>
                            <td><input id="dialog_introduce" type="text" name="introduceCode" value=""/></td>
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
                    <img class="title_image" src="${basePath}/static/images/icon_sitelist.png" alt="" />已上传信息
                <span class="numberal_format">
                    <#if activityModuleInfos?exists>
                    ${activityModuleInfos?size}
                    <#else>
                        0
                    </#if>
                </span>个
                </div>
                <table class="detail_tb">
                    <caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>已上传信息详细</span></caption>
                    <tr class="first_tr">
                        <td>标题</td>
                        <td>图片</td>
                        <td>文字介绍</td>
                        <td>路径</td>
                        <td class="operate_td">操作</td>
                    </tr>
                    <#if activityModuleInfos?exists>
                    <#list activityModuleInfos as activityModuleInfo>
                        <tr>
                            <td>${activityModuleInfo.title!}</td>
                            <td>${activityModuleInfo.picture!}</td>
                            <td>${activityModuleInfo.introduce!}</td>
                            <td>${activityModuleInfo.url!}</td>
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
