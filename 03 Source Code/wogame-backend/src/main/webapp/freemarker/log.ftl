<#include "/common/template.ftl">
<@template csses=["index"] jses=["log"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <div class="header">
        <img class="logo" src="${basePath}/static/images/logo.png" />
        <div class="info">
            <span>管理员，你好</span>
            <!--<a src="#" style="border:1px solid red"><img src="images/exite.png" style="border:1px solid #00A2E8;margin-top:20"></a>-->
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
            <div class="main_info">
                <div class="select_data">
                    <select>
                        <option>日数据</option>
                        <option>月数据</option>
                    </select>
                </div>

                <image src="images/icon_radio.png" class="title_image"/>今日新增用户数<span class="numberal_format">21</span>人，总计用户数<span class="numberal_format">15875</span>人
                <div id="uv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_pv.png" />主要页面流量统计
                </div>
                <div id="pv" style="width:963px;height:556px;margin-top:20px;margin-bottom:20px;"></div>
                <div class="log_title" style="margin-top:17px;">
                    <img class="title_image" src="${basePath}/static/images/icon_adv.png" />首页轮播广告点击|下载情况
                </div>
                <table class="detail_tb">
                    <caption class="table_title banner_title">
                        <p class="banner_desc left">首页轮播<br/>广告图片</p>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                        <img class="banner_img left" src="${basePath}/static/images/img_example.png"/>
                    </caption>
                    <tr class="first_tr">
                        <td>日期<td>
                        <td>广告图片1<td>
                        <td>广告图片2<td>
                        <td>广告图片3<td>
                        <td>广告图片4<td>
                        <td>广告图片5<td>
                        <td>广告图片6<td>
                    </tr>
                    <tr>
                        <td>昨日<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                    </tr>
                    <tr>
                        <td>前两天<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                    </tr>
                    <tr>
                        <td>前三天<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                    </tr>
                    <tr>
                        <td>前四天<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                    </tr>
                    <tr>
                        <td>前五天<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                        <td>11|10<td>
                    </tr>
                </table>
                <div class="log_title" style="margin-top:17px">
                    <img class="title_image" src="${basePath}/static/images/icon_ranking.png" />首页Top30游戏点击|下载情况
                </div>
                <table id="top30Game" class="detail_tb">
                    <tr class="table_title font_color">
                        <td>游戏名称<td>
                        <td>昨日<td>
                        <td>前两天<td>
                        <td>前三天<td>
                        <td>前四天<td>
                        <td>前五天<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>23|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>23|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>23|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>2|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>2|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>2|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>2|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>2|26<td>
                        <td>30|30<td>
                    </tr>
                    <tr>
                        <td><img src="${basePath}/static/images/game_img.png"/>地铁跑酷<td>
                        <td>11|15<td>
                        <td>24|16<td>
                        <td>23|14<td>
                        <td>2|26<td>
                        <td>30|30<td>
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