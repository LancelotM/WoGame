<#include "/common/template.ftl">
<@template csses=["index"] jses=["floatwindow"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
    <div class="main">
        <div class="main_info">
            <div class="create_manager">
                <div class="log_title">
                    <img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>漂浮窗管理
                </div>
                <form id="floatWindow_form" action="${basePath}/" method="post">
                    <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                        <tr class="first_tr">
                            <td class="link_class">图标</td>
                            <td><input id="icon_input" type="file" name="iconCode" title="请上传icon" /></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">路径</td>
                            <td><input id="url_input" type="text" name="urlCode" title="请输入url" /></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">内容</td>
                            <td><input id="content_input" type="text" name="contentCode" title="请输入内容"/></td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class">是否显示</td>
                            <td>
                                <input type="radio" name="radio" id="show" checked="true" style="width: 50px;">显示</input>
                                <input type="radio" name="radio" id="hidden" style="width: 50px;">隐藏</input>
                            </td>
                        </tr>
                        <tr class="first_tr">
                            <td class="link_class"></td>
                            <td>
                                <a  href="javascript:;"><img id="dynamic_button"/></a>
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
