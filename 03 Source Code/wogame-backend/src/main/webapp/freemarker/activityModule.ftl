<#include "/common/template.ftl">
<@template csses=["activitymodule"] jses=["activitymodule"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>新增信息管理
                </div>
                <form id="module_form" action="${basePath}/addactivitymodule" method="post" enctype="multipart/form-data">
                    <table id="create_manager_table" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="head_format">图片</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="picture_input" class="txt"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="imageName" class="file" id="fileField" size="28"
                                           multiple="multiple" onchange="picture_input.value=this.value"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">标题</td>
                            <td><input id="title_input" type="text" name="titleCode" title="请输入title"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">介绍</td>
                            <td><textarea id="introduce_input"  rows="3" cols="40" name="introduceCode" title="请输入文字介绍内容"></textarea></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">链接</td>
                            <td><input id="url_input" type="text" name="urlCode" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format"></td>
                            <td>
                                <a href="javascript:;" id="create_submit"><img src="${basePath}/static/images/add.png" alt="detail"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="module_dialog" style="display:none;">
                <div id="module_dialog_head">
                    <span id="module_dialog_titile">修改已上传信息</span>
                </div>
                <form id="module_update_form"  action="${basePath}/addactivitymodule" method="post" enctype="multipart/form-data">
                    <table id="module_dialog_table">
                        <tr>
                            <td>图片：</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="dialog_picture" class="txt"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="imageName" class="file" id="fileField" size="28"
                                           multiple="multiple" onchange="dialog_picture.value=this.value"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>标题：</td>
                            <td><input id="dialog_title" type="text" name="titleCode" value=""/></td>
                        </tr>
                        <tr>
                            <td>介绍：</td>
                            <td><textarea id="dialog_introduce"  rows="3" cols="40" name="introduceCode" value=""></textarea></td>
                        </tr>
                        <tr>
                            <td>链接：</td>
                            <td><input id="dialog_url" name="urlCode" type="text" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="dialog_id" name="id" type="text" value="" style="display: none;"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a class="module_left" id="update_submit" href="javascript:;">
                                    <img src="${basePath}/static/images/confirm_update.png" alt="update" style="margin-right:75px;"/>
                                </a>
                                <a class="module_right" id="cancel" href="javascript:;"><img src="${basePath}/static/images/cancel.png" alt="cancel"/></a>
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
                        <td>介绍</td>
                        <td>链接</td>
                        <td class="operate_td">操作</td>
                    </tr>
                    <#if activityModuleInfos?exists>
                    <#list activityModuleInfos as activityModuleInfo>
                        <tr>
                            <td  class="hidden_title" style="position: relative;z-index: 900;">
                                <#if (activityModuleInfo.title?length) gt 20>
                                    ${activityModuleInfo.title?substring(0,20)}...
                                <#else>
                                    ${activityModuleInfo.title}
                                </#if>
                                <div style="z-index: 999; position: relative; left: 0px;top: -20px;display: none;">${activityModuleInfo.title!}</div>
                            </td>
                            <td   class="hidden_imageName" style="position: relative;z-index: 900;">
                                <#if (activityModuleInfo.imageName?length) gt 15>
                                    ${activityModuleInfo.imageName?substring(0,15)}...
                                <#else>
                                    ${activityModuleInfo.imageName}
                                </#if>
                                <div style="z-index: 999; position: relative; left: 0px;top: -20px;display: none;">${activityModuleInfo.imageName!}</div>
                            </td>
                            <td   class="hidden_description" style="position: relative;z-index: 900;">
                                <#if (activityModuleInfo.description?length) gt 10>
                                    ${activityModuleInfo.description?substring(0,10)}...
                                <#else>
                                    ${activityModuleInfo.description}
                                </#if>
                                <div style="z-index: 999; position: relative; left: 0px;top: -20px;display: none;">${activityModuleInfo.description!}</div>
                            </td>
                            <td   class="hidden_url" style="position: relative;z-index: 900;">
                                <#if (activityModuleInfo.url?length) gt 10>
                                    ${activityModuleInfo.url?substring(0,10)}...
                                <#else>
                                    ${activityModuleInfo.url}
                                </#if>
                                <div style="z-index: 999; position: relative; left: 0px;top: -20px;display: none;">${activityModuleInfo.url!}</div>
                            </td>
                            <td id="operate_td" class="operate_td">
                                <a id="update_info" href="javascript:;" onclick="updateModuleBanner(${activityModuleInfo.id});"><img src="${basePath}/static/images/update.png" /></a>
                                <a class="delbtn" href="javascript:;" onclick="delModuleBanner(${activityModuleInfo.id});"><img src="${basePath}/static/images/delete.png" alt="detail"/></a>
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
