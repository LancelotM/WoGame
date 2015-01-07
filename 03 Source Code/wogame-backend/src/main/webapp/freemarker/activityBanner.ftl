<#include "/common/template.ftl">
<@template csses=["activitybanner"] jses=["activitybanner"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>活动Banner管理
                </div>
                <form id="activityBanner_form" action="${basePath}/saveorupdatebanner" method="post" enctype="multipart/form-data" autocomplete="off" spellcheck="false">
					<input type="text" style="display:none">
					<input type="file" style="display:none">
					<input type="text" style="display:none">               
                    <input id="random_input" type="hidden" name="random" value=""/>
                    <input id="type_input" type="text" name="adType" style="display: none;" value="4"/>
                    <table id="create_manager_table" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="head_format">图片</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="picture_input" class="txt" title="请上传图片"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="file" class="file" id="fileField"  multiple="multiple" onchange="picture_input.value=this.value"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">介绍</td>
                            <td><textarea id="introduce_input"  rows="3" cols="40" name="description" title="请输入文字介绍内容"></textarea></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">链接</td>
                            <td><input id="url_input" type="text" name="url" title="请输入URL"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">类别</td>
                            <td>
                                <#--<input id="bigCategory" type="radio" name="category" value="bigCategory" style="width: 50px;"/>大Banner-->
                                <input id="smallCategory" type="radio" name="category" value="smallCategory" style="width: 50px;"/>小Banner
                            </td>
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
            <div id="activity_dialog" style="display:none;">
                <div id="activity_dialog_head">
                    <span id="activity_dialog_titile">修改已上传活动Banner信息</span>
                </div>
                <form id="activity_update_form" action="${basePath}/saveorupdatebanner"  method="post" enctype="multipart/form-data" autocomplete="off" spellcheck="false">
                    <input type="text" style="display:none">
                    <input type="file" style="display:none">
                    <input type="text" style="display:none">
                    <table id="activity_dialog_table">
                        <tr>
                            <td>图片：</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="dialog_picture" class="txt" name="imageName"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="file" class="file" id="fileField" multiple="multiple" onchange="dialog_picture.value=this.value"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>介绍：</td>
                            <td><textarea id="dialog_introduce"  rows="3" cols="40" name="description" value=""></textarea></td>
                        </tr>
                        <tr>
                            <td>链接:</td>
                            <td><input id="dialog_url" name="url" type="text" value=""/></td>
                        </tr>
                        <tr>
                            <td>类别：</td>
                            <td>
                                <#--<input id="dialog_bigCategory" type="radio" name="category" value="bigCategory" style="width: 50px;"/>大Banner-->
                                <input id="dialog_smallCategory" type="radio" name="category" value="smallCategory" style="width: 50px;"/>小Banner
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="dialog_id" name="id" type="text" value="" style="display: none;"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input id="dialog_type" type="text" name="adType" style="display: none;" value="4"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a class="activity_left" id="update_submit" href="javascript:;">
                                    <img src="${basePath}/static/images/confirm_update.png" alt="update" style="margin-right:75px;"/>
                                </a>
                                <a class="activity_right" id="cancel" href="javascript:;"><img src="${basePath}/static/images/cancel.png" alt="cancel"/></a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="create_detail">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_sitelist.png" alt="" />已上传活动Banner
                        <span class="numberal_format">
                            <#if activityBannerInfos?exists>
                                ${activityBannerInfos?size}
                            <#else>
                                 0
                            </#if>
                        </span>个
                </div>
                <table class="detail_tb">
                    <caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>已上传活动Banner详细</span></caption>
                    <thead class="activity_head">
                        <tr class="first_tr">
                            <td>类别</td>
                            <td>链接</td>
                            <td>图片</td>
                            <td>介绍</td>
                            <td class="operate_td">操作</td>
                        </tr>
                    </thead>
                    <tbody class="activity_detail">
                        <#if activityBannerInfos?exists>
                            <#list activityBannerInfos as activityBannerInfo>
                                <tr>
                                    <td class="category">
                                        <if activityBannerInfo.position == 2>
                                         小Banner
                                        </if>
                                        <#--<#if activityBannerInfo.position == 1>
                                            大Banner
                                        <#elseif  activityBannerInfo.position == 2>
                                            小Banner
                                        </#if>-->
                                    </td>
                                    <td  class="hidden_url">
                                        <span>
                                            <#if (activityBannerInfo.url?length) gt 10>
                                            ${activityBannerInfo.url?substring(0,10)}...
                                            <#else>
                                            ${activityBannerInfo.url}
                                            </#if>
                                        </span>
                                        <div class="url_div">${activityBannerInfo.url!}</div>
                                    </td>
                                    <td  class="hidden_txt">
                                        <span>
                                            <#if (activityBannerInfo.imageName?length) gt 10>
                                            ${activityBannerInfo.imageName?substring(0,10)}...
                                            <#else>
                                            ${activityBannerInfo.imageName}
                                            </#if>
                                        </span>
                                        <div class="imageName_div">${activityBannerInfo.imageName!}</div>
                                    </td>
                                    <td  class="hidden_description">
                                        <span>
                                            <#if (activityBannerInfo.description?length) gt 10>
                                            ${activityBannerInfo.description?substring(0,10)}...
                                            <#else>
                                            ${activityBannerInfo.description}
                                            </#if>
                                        </span>
                                        <div  class="description_div">${activityBannerInfo.description!}</div>
                                    </td>
                                    <td id="operate_td" class="operate_td">
                                        <a id="update_info" href="javascript:;" onclick="updateActivityBanner(${activityBannerInfo.id});" ><img src="${basePath}/static/images/update.png" /></a>
                                        <a class="delbtn" href="javascript:;" onclick="delActivityBanner(${activityBannerInfo.id});"><img src="${basePath}/static/images/delete.png"/></a>
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
