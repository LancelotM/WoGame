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
                <div class="log_title" style="margin-top:20px;">
                    <form	id="report_form" action="${basePath}/reportInfo" method="post">
                        日期：<input id="dateStr" type="text" value="${start!}" name="dateStr" style="height:22px;"/>&nbsp;&nbsp;
                        游戏名称：<input id="gameName" type="text" value="${end!}" name="gameName" style=" height:22px;"/>&nbsp;&nbsp;
                        ChannelID：<input id="downloadChaid" type="text" value="${chaid!}" name="channelId" style="height:22px;"/>&nbsp;&nbsp;
                        <a id="downloadinfo_search" href="javascript:;"  style="color:black;"><img class="title_image" src="${basePath}/static/images/search_img.png"/>下载日志搜索</a>
                    </form>
                    <table id="dowloadInfo_tb" class="detail_tb">
                        <caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>游戏下载详情</span></caption>
                        <tr class="first_tr">
                            <td>游戏名称</td>
                            <td>下载次数</td>
                        </tr>
                        <tr class='append_tr'>
                            <td>植物大战僵尸</td>
                            <td>1500</td>
                        </tr>
                    </table>
                    <div id="pages">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@template>