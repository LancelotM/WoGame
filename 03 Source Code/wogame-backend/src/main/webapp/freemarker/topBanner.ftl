<#include "/common/template.ftl">
<@template csses=["topbanner"] jses=["topbanner"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>顶部Banner管理
                </div>
                <form id="create_form" action="${basePath}/createtopbanner" method="post" enctype="multipart/form-data">
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="head_format">图标</td>
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
                            <td class="head_format">链接</td>
                            <td><input id="url_input" type="text" name="url" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format"></td>
                            <td>
                                <a href="javascript:;" id="create_submit"><img src="${basePath}/static/images/add.png"
                                                                               alt="detail"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="top_dialog" style="display:none;">
                <div id="top_dialog_head">
                    <span id="top_dialog_titile">修改已上传顶部Banner信息</span>
                </div>
                <form id="update_form" action="${basePath}/createtopbanner" method="post" enctype="multipart/form-data">
                    <table id="top_dialog_table">
                        <tr>
                            <td>图标：</td>
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
                            <td>链接:</td>
                            <td><input id="dialog_url" name="url" type="text" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="dialog_id" name="id" type="text" value="" style="display: none;"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a class="top_left" id="update_submit" href="javascript:;">
                                    <img src="${basePath}/static/images/confirm_update.png" alt="update"
                                         style="margin-right:45px;"/>
                                </a>
                                <a class="top_right" id="cancel" href="javascript:;"><img
                                        src="${basePath}/static/images/cancel.png" alt="cancel"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="create_detail">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_sitelist.png" alt=""/>已上传顶部Banner
                                <span class="numberal_format">
                                    <#if topBannerInfos?exists>
                                    ${topBannerInfos?size}
                                    <#else>
                                        0
                                    </#if>
                                </span>个
                </div>
                <table class="detail_tb">
                    <caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png"
                                                      alt=""/><span>已上传顶部Banner详细</span></caption>
                    <thead>
                    <tr class="first_tr" style="cursor: pointer;">
                        <td>编号</td>
                        <td>图标</td>
                        <td>链接</td>
                        <td class="operate_td">操作</td>
                    </tr>
                    </thead>
                    <tbody class="topBanner_detail">
                        <#if topBannerInfos?exists>
                            <#list topBannerInfos as topBannerInfo>
                            <tr>
                                <td class="idNum">${topBannerInfo.id!}</td>
                                <td class="hidden_txt" style="position: relative;z-index: 900;">
                                    <#if (topBannerInfo.imageName?length) gt 20>
                                        ${topBannerInfo.imageName?substring(0,20)}...
                                    <#else>
                                        ${topBannerInfo.imageName}
                                    </#if>
                                    <div style="z-index: 999; position: relative; left: 0px;top: -20px;display: none;">${topBannerInfo.imageName!}</div>
                                </td>
                                <td class="hidden_url"  style="position: relative;">
                                    <#if (topBannerInfo.url?length) gt 20>
                                        ${topBannerInfo.url?substring(0,20)}...
                                    <#else>
                                        ${topBannerInfo.url}
                                    </#if>
                                    <#--<span  id="span_url" style="z-index: 999; position: absolute;display: none; left: 0px;top: 20px;">${topBannerInfo.url!}</span>-->
                                </td>
                                <td id="operate_td" class="operate_td">
                                    <a id="update_info" href="javascript:;"
                                       onclick="updateBanner(${topBannerInfo.id});"><img
                                            src="${basePath}/static/images/update.png"/></a>
                                    <a class="delbtn" href="javascript:;" onclick="delBanner(${topBannerInfo.id});"><img
                                            src="${basePath}/static/images/delete.png" alt="detail"/></a>
                                </td>
                            </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
                <table class="sort" id="sort_table">
                    <caption class="table_title"><img class="title_image"
                                                      src="${basePath}/static/images/icon_table.png"
                                                      alt=""/><span>对图片进行排序</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span id="remind">友情提示：请手动输入图片顺序，如1、2、3...</span></caption>
                    <tr class="sort_tr">
                        <td class="sort_text">编号</td>
                        <#if topBannerInfos?exists>
                            <#list topBannerInfos as topBannerInfo>
                                <td class="sort_id"><span name="id">${topBannerInfo.id!}</span></td>
                            </#list>
                        </#if>
                    </tr>
                    <tr class="sort_tr">
                        <td class="sort_text">序号</td>
                        <#if topBannerInfos?exists>
                            <#list topBannerInfos as topBannerInfo>
                                <td class="sort_position"><input class="position_tb" name="position" type="text"
                                                                 value="${topBannerInfo.position!}"/></td>
                            </#list>
                        </#if>
                    </tr>
                </table>
                <div id="sort_submit">
                    <a href="javascript:;"><img src="${basePath}/static/images/submit.png"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</@template>
