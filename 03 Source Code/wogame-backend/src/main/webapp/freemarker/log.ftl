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
                <img src="${basePath}/static/images/icon_radio.png" class="title_image"/>今日新增用户数
                    <span class="numberal_format">${newUserCount!'0'}</span>人，总计用户数<span class="numberal_format">${totalUserCount!'0'}</span>人
                <div id="uv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_pv.png" />主要页面流量统计
                </div>
                <div id="pv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_adv.png" />首页轮播广告位点击量
                </div>
                <table id="dateTale" style="width:90px;" class="detail_tb banner_desc_tb left">
                    <tr class="table_title font_color"><td>日期</td></tr>
                </table>
                <table id="banner" style="width:877px;" class="detail_tb banner_tb left">
                    <tr id="bannerImg_dec" class="table_title font_color">
                    </tr>
                </table>
                <div class="log_title" style="margin-top:17;px;margin-top:17px;clear:both;">
                    <img class="title_image" src="${basePath}/static/images/icon_ranking.png" />广告位点击量
                </div>
                <table id="top30Game" class="detail_tb">
                    <tr id="gameFirstTd" class="table_title font_color">
                        <td>广告位</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</@template>