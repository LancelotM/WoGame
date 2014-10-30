<#include "/common/template.ftl">
<@template csses=["floatwindow"] jses=["floatwindow"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>顶部漂浮窗管理
                </div>
                <form id="floatWindow_form" method="post" enctype="multipart/form-data" autocomplete="off" spellcheck="false">
                    <input type="text" style="display: none"/>
                    <input type="file" style="display: none"/>
                    <input type="text" style="display: none"/>
                    <input type="text" style="display: none"/>
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <#if floatWindowInfos?exists>
                                <#list floatWindowInfos as floatWindowInfo>
                                <script type="text/javascript">
                                    $(function(){
                                        var $status = ${floatWindowInfo.status?string("true","false")};
                                        $("#icon_input").val("${floatWindowInfo.imageName!}");
                                        $("#url_input").val("${floatWindowInfo.url!}");
                                        $("#float_id").val("${floatWindowInfo.id!}");
                                        $("#content_input").val("${floatWindowInfo.description!}");
                                        if($status){
                                            $("#show").attr("checked",true);
                                        }else{
                                            $("#show").attr("checked",false);
                                            $("#hidden").attr("checked",true);
                                        }
                                    });
                                </script>
                            </#list>
                        </#if>
                        <tr class="first_tr">
                            <td class="head_format">图标</td>
                            <td>
                                <div class="file-box">
                                    <input type="text" id="icon_input" class="txt" name="imageName" title="请上传图标"/>
                                    <a href="javascript:;" class="btn"><img src="${basePath}/static/images/upload.png"/></a>
                                    <input type="file" name="file" class="file" id="fileField" multiple="multiple" onchange="icon_input.value=this.value"/>
                                </div>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">链接</td>
                            <td><input id="url_input" type="text" name="url" title="请输入url"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">内容</td>
                            <td><input id="content_input" type="text" name="description" title="请输入内容"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format">显示</td>
                            <td>
                                <input type="radio" name="radio" id="show" checked="true" style="width: 80px;" value="show">显示</input>
                                <input type="radio" name="radio" id="hidden" style="width: 80px;" value="hidden">隐藏</input>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format"></td>
                            <td>
                                <input id="float_id" name="id" type="text" style="display: none;"/>
                                <input id="type_input" type="text" name="adType" style="display: none;" value="1"/>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="head_format"></td>
                            <td>
                                <a  href="javascript:;" class="float_button" style="margin-left: 60px;"><img id="dynamic_button"/></a>
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
