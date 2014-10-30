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
                <form id="create_form" action="${basePath}/saveorupdatebanner" method="post" enctype="multipart/form-data" autocomplete="off" spellcheck="false">
					<input type="text" style="display:none">
					<input type="file" style="display:none">
					<input type="text" style="display:none">
					<input id="type_input" type="text" name="adType" style="display: none;" value="2"/>
                    <input id="random_input" type="hidden" name="random" value=""/>
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="head_format">图标</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="picture_input" class="txt"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="file" class="file" id="fileField" multiple="multiple" onchange="picture_input.value=this.value"/>
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
                                <a href="javascript:void(0);" id="create_submit">
                                    <img src="${basePath}/static/images/add.png" alt="detail"/>
                                </a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="top_dialog" style="display:none;">
                <div id="top_dialog_head">
                    <span id="top_dialog_titile">修改已上传顶部Banner信息</span>
                </div>
                <form id="update_form" action="${basePath}/saveorupdatebanner" method="post" enctype="multipart/form-data">
                    <table id="top_dialog_table">
                        <tr>
                            <td>图标：</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="dialog_picture" class="txt" name="imageName"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="file" class="file" id="fileField" multiple="multiple" onchange="dialog_picture.value=this.value"/>
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
                            <td><input id="dialog_type" type="text" name="adType" style="display: none;" value="2"/></td>
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
                        <td>链接</td>
                        <td>图标</td>
                        <td class="operate_td">操作</td>
                    </tr>
                    </thead>
                    <tbody class="topBanner_detail">
                        <#if topBannerInfos?exists>
                            <#list topBannerInfos as topBannerInfo>
                            <tr>
                                <td class="idNum">${topBannerInfo.id!}</td>
                                <td class="hidden_url">
                                    <#if (topBannerInfo.url?length) gt 15>
                                    ${topBannerInfo.url?substring(0,15)}...
                                    <#else>
                                    ${topBannerInfo.url}
                                    </#if>
                                    <div class="url_div">${topBannerInfo.url!}</div>
                                </td>
                                <td class="hidden_txt">
                                    <#if (topBannerInfo.imageName?length) gt 20>
                                        ${topBannerInfo.imageName?substring(0,20)}...
                                    <#else>
                                        ${topBannerInfo.imageName}
                                    </#if>
                                    <div class="imageName_div">${topBannerInfo.imageName!}</div>
                                </td>
                                <td id="operate_td" class="operate_td">
                                    <a id="update_info" href="javascript:;"
                                       onclick="updateBanner(${topBannerInfo.id});"><img
                                            src="${basePath}/static/images/update.png"/></a>
                                    <a class="delbtn" href="javascript:;" onclick="delBanner(${topBannerInfo.id});"><img
                                            src="${basePath}/static/images/delete.png"/></a>
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
                    <tbody class="sort_body">
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
                    </tbody>
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
