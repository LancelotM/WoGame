<#include "/common/template.ftl">
<@template csses=["index"] jses=["log"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
        <#include "/menu.ftl">
        <div class="main">
            <div class="main_info">
                <form action="${basePath}/getLog" method="post" id="dateForm">
                    <div class="select_data">
                        <select id="select">
                            <option value="1">日数据</option>
                            <option value="2">月数据</option>
                        </select>
                        <input id="channelId" type="hidden" value="${channelId?c}"/>
                        <input id="dateType" type="hidden" value="${type!}" />
                    </div>
                </form>
                <img src="${basePath}/static/images/icon_radio.png" class="title_image"/>总计用户数<span class="numberal_format">${totalUserCount!'0'}</span>人
                <div id="uv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_pv.png" />主要页面流量统计
                </div>
                <div id="pv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>

            </div>
        </div>
    </div>
</div>
</@template>