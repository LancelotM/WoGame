<#include "/common/template.ftl">
<@template csses=["index"] jses=["index"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <div class="header">
        <img class="logo" src="${basePath}/static/images/logo.png" />
        <div class="info">
            <span>管理员，你好</span>
            <a class="exite_button" href="#">安全登出</a>
        </div>
    </div>
    <div class="menu_main">
        <div class="menu">
            <div class="ul_info">
                <a href="#" class="margin_top45" onclick="show_hidden('first_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png"/>&nbsp;日志统计信息</a>
                <ul id="first_ul" class="son_ul" style="display:none">
                    <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="#">日志总览</a></li>
                    <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="#">搜索日志</a></li>
                </ul>
                <a href="#" class="margin_top45" onclick="show_hidden('second_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png"/>&nbsp;站点管理</a>
                <ul id="second_ul" class="son_ul" style="display:none">
                    <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="#">建站管理</a></li>
                </ul>
            </div>
        </div>
        <div class="main">
            <div class="main_info detail">
                <div class="log_title" style="margin-top:20px;">
                    <img class="title_image" src="${basePath}/static/images/search_img.png"/>用户搜索频率最高的Top50关键字
                </div>

                <div class="keyword">
                    <#if keywords?exists>
                        <#list keywords as hotword>
                            <span>${hotword.keyword!}(${hotword.count!})</span>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@template>