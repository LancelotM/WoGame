<#include "/common/template.ftl">
<@template csses=["keyword"] jses=["keyword"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
<#include "/menu.ftl">
        <div class="main">
            <div class="main_info detail">
                <img src="${basePath}/static/images/search_title.png" class="search_count"/>昨日共搜索<span class="numberal_format">${yesterdayCount!'0'}</span>次,
                最近30天共计搜索<span class="numberal_format">${totalCount!'0'}</span>次
                <div class="log_title" style="margin-top:20px;">
                    <img class="title_image" src="${basePath}/static/images/search_img.png"/>用户搜索频率最高的Top50关键字
                </div>

                <div class="keyword">
                    <#if keywords?exists>
                        <#list keywords as hotword>
                            <div class="keyword_show_content">${hotword.keyword!}(${hotword.count!})</div>
                        </#list>
                    </#if>
                </div>
                <div class="log_title" style="margin-top:20px;">
                    <form	id="report_form" action="${basePath}/reportInfo" method="post">
                        起始日期：<input id="startDateStr" class="dateStr" type="text" value="" name="startDate" readonly="true" style="height:22px;width: 150px;"/>&nbsp;&nbsp;
                        截止日期：<input id="endDateStr" type="text" value="" name="endDate" readonly="true" style="height:22px;width: 150px;"/>&nbsp;&nbsp;
                        <#--游戏名称：<input id="gameName" type="text" value="${end!}" name="gameName" style=" height:22px;"/>&nbsp;&nbsp;-->
                        <#if admin?exists && admin>
                                ChannelID：<input id="downloadChaid" type="text" name="channelId" style="height:22px;"/>&nbsp;&nbsp;
                            <#else>
                                ChannelID：<input id="downloadChaid" type="text" value="${developer_channelCode!}" disabled="disabled" name="channelId" style="height:22px;width: 150px;"/>&nbsp;&nbsp;
                        </#if>

                        <a id="downloadinfo_search" href="javascript:;"  style="color:black;"><img class="title_image" src="${basePath}/static/images/search_img.png"/>下载日志搜索</a>
                    </form>
                </div>
                <table id="download_info_left" class="detail_tb left">
                    <caption class="table_title" style="border-right-width: 0px"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>游戏下载详情&nbsp;&nbsp;<#--共<span id="totalRecord"></span>条记录</span>--></caption>
                    <tr class="first_tr">
                        <td>游戏名称</td>
                        <td>下载次数</td>
                    </tr>
                </table>
                <table id="download_info_right" class="detail_tb left">
                    <caption class="table_title" style="border-left-width: 0px"></caption>
                    <tr class="first_tr">
                        <td>游戏名称</td>
                        <td>下载次数</td>
                    </tr>
                </table>
                <div id="pages">
                    <input id="rowsPerPage" type="hidden" value="20" />

                </div>
            </div>
        </div>
    </div>
</div>
</@template>