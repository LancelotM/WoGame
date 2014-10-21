<#include "/common/template.ftl">
<@template csses=["index"] jses=["topbanner"]>
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

                            <td class="link_class">图片上传</td>
                            <td><input id="picture_input" type="file" name="imageName" title="请上传图片" /></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">路径</td>
                            <td><input id="url_input" type="text" name="url" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class"></td>
                            <td>
                                <a href="javascript:;" id="create_submit"><img src="${basePath}/static/images/add.png" alt="detail"/></a>
                            </td>
                        </tr>
                        <tbody id="upload_tbody">

                        </tbody>                        
                    </table>
                </form>
            </div>
            <div id="dialog" style="display:none;">
                <div id="dialog_head">
                    <span id="dialog_titile">修改已上传顶部Banner信息</span>
                </div>
                <form id="update_form" action="${basePath}/updatetopbanner"  method="post">
                    <table id="dialog_table">
                        <tr>
                            <td>图片上传：</td>
                            <td><input id="dialog_picture" name="imageName" type="file" value=""/></td>
                        </tr>
                        <tr>
                            <td>路径:</td>
                            <td><input id="dialog_url" name="url" type="text" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="dialog_id" name="id" type="text" value="" style="display: none;"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a class="left" id="update_submit" href="javascript:;">
                                    <img src="${basePath}/static/images/confirm_update.png" alt="update"
                                         style="margin-right:75px;"/>
                                </a>
                                <a class="right" id="cancel" href="javascript:;"><img
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
                                                      alt=""/><span>已上传顶部Banner详细</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="remind">友情提示：拖拽表格行可进行数据排序操作</label></caption>
                    <thead>
                        <tr class="first_tr" style="cursor: pointer;">
                            <td>编号</td>
                            <td>图片</td>
                            <td>路径</td>
                            <td class="operate_td">操作</td>
                        </tr>
                    </thead>
                    <tbody class="topBanner_detail">
                        <#if topBannerInfos?exists>
                            <#list topBannerInfos as topBannerInfo>
                            <tr>
                                <td class="idNum">${topBannerInfo.position!}</td>
                                <td>${topBannerInfo.imageName!}</td>
                                <td>${topBannerInfo.url!}</td>
                                <td id="operate_td" class="operate_td">
                                    <a id="update_info" href="javascript:;" onclick="updateBanner(${topBannerInfo.id});"><img
                                            src="${basePath}/static/images/update.png"/></a>
                                    <a class="delbtn" href="javascript:;" onclick="delBanner(${topBannerInfo.id});"><img
                                            src="${basePath}/static/images/delete.png" alt="detail"/></a>
                                </td>
                            </tr>
                            </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
</@template>
