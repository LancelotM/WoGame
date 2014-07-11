<#include "/common/template.ftl">
<@template csses=["index"] jses=["index"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
<#include "/menu.ftl">
        <div class="main">
            <div class="main_info detail">
                <div class="log_title" style="margin-top:20px;">
                    <img class="title_image" src="${basePath}/static/images/search_img.png"/>用户搜索频率最高的Top50关键字
                </div>

                <div class="keyword">
                    <#if keywords?exists>
                        <#list keywords as hotword>
                            <div id="keyword_span">${hotword.keyword!}(${hotword.count!})</div>
                        </#list>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@template>