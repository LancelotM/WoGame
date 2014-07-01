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
                <image src="${basePath}/static/images/icon_radio.png" class="title_image"/>今日新增用户数<span class="numberal_format">21</span>人，总计用户数<span class="numberal_format">15875</span>人
                <div id="uv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_pv.png" />主要页面流量统计
                </div>
                <div id="pv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_adv.png" />首页轮播广告点击|下载情况
                </div>
                <table id="dateTale" style="width:90px;" class="detail_tb banner_desc_tb left">
                    <tr id="page_banner"><td><p>首页轮播<br/>广告图片</p></td></tr>
                    <tr class="first_tr"><td>日期</td></tr>
                    <#--<tr><td>昨日</td></tr>-->
                    <#--<tr><td>前两天</td></tr>-->
                    <#--<tr><td>前三天</td></tr>-->
                    <#--<tr><td>前四天</td></tr>-->
                    <#--<tr><td>前五天</td></tr>-->
                </table>
                <table id="banner" style="width:877px;"class="detail_tb banner_tb left">
                    <caption class="table_title banner_title">
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                    </caption>
                    <tr class="first_tr">
                        <td>广告图片1</td>
                        <td>广告图片2</td>
                        <td>广告图片3</td>
                        <td>广告图片4</td>
                        <td>广告图片5</td>
                        <td>广告图片6</td>
                    </tr>
                </table>
                <div class="log_title" style="margin-top:17;px;margin-top:17px;clear:both;">
                    <img class="title_image" src="${basePath}/static/images/icon_ranking.png" />首页Top30游戏点击|下载情况
                </div>
                <table id="top30Game" class="detail_tb">
                    <tr id="gameFirstTd" class="table_title font_color">
                        <td>游戏名称</td>
                        <#--<td>昨日</td>-->
                        <#--<td>前两天</td>-->
                        <#--<td>前三天</td>-->
                        <#--<td>前四天</td>-->
                        <#--<td>前五天</td>-->
                    </tr>
                </table>
                <div id="pages">
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                </div>
            </div>
        </div>
    </div>
</div>
</@template>