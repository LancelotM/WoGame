<#include "/common/template.ftl">
<@template csses=["index"] jses=["log"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <div class="header">
        <img class="logo" src="${basePath}/static/images/logo.png" alt=""/>
        <div class="info">
            <#if ChannelName?exists>
                <span>${(ChannelName)!}，你好</span>
            <#else>
                <span>管理员，你好</span>
                <a id="exit_id" class="exite_button" href="javascript:;">安全登出</a>
            </#if>

        </div>
    </div>
    <div class="menu_main">
        <div class="menu">
        <div class="ul_info">
            <a href="#" class="margin_top45" onclick="show_hidden('first_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png" alt=""/>&nbsp;日志统计信息</a>
            <ul id="first_ul" class="son_ul" style="display:none">
                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" ><a href="${basePath}/log">日志总览</a></li>
                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" ><a href="${basePath}/getAllKeyWowrd">搜索日志</a></li>
            </ul>
            <#if admin?exists && admin>
                <a href="#" class="margin_top45" onclick="show_hidden('second_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png" alt=""/>&nbsp;站点管理</a>
                <ul id="second_ul" class="son_ul" style="display:none">
                    <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)"><a href="${basePath}/site">建站管理</a></li>
                </ul>
                <a href="#" class="margin_top45" onclick="show_hidden('report_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png"/>&nbsp;报表</a>
                <ul id="report_ul" class="son_ul" style="display:none">
                    <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="${basePath}/getReport">报表详情</a></li>
                </ul>
            </div>
            </#if>
        </div>
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
                <#--<div id="pages">-->
                    <#--<a href="javascript:;">1</a>-->
                    <#--<a href="javascript:;">2</a>-->
                    <#--<a href="javascript:;">3</a>-->
                <#--</div>-->
            </div>
        </div>
    </div>
</div>
</@template>