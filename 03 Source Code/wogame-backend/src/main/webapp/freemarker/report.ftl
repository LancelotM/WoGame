<#include "/common/template.ftl">
<@template csses=["report"] jses=["report"]>
    <#assign basePath=attrs.contextPath>
<div class="main_block">
    <#include "/menu.ftl">
        <div class="main">
            <div class="main_info detail">
                <div class="log_title" style="margin-top:20px;">
                    <form	id="report_form" action="${basePath}/reportInfo" method="post">
                        起始日期：<input id="" type="text" value="20140501" name="startDate" style="height:22px;"/>&nbsp;&nbsp;
                        结束日期：<input id="" type="text" value="20140701" name="endDate" style=" height:22px;"/>&nbsp;&nbsp;
                        ChannelID：<input id="report_chaid" type="text" value="" name="channelId" style="height:22px;"/>&nbsp;&nbsp;
                        <a id="report_search" href="javascript:;"  style="color:black;"><img class="title_image" src="${basePath}/static/images/search_img.png"/>搜索</a>
                    </form>
                </div>
                <table class="detail_tb">
                    <caption class="table_title">
                        打包信息
                    </caption>
                    <tr>
                        <td>打包个数</td>
                        <td>成功个数</td>
                        <td>失败个数</td>
                    </tr>
                    <tr>
                        <td>${(packageReportInfo.packageSum)!'0'}</td>
                        <td>${(packageReportInfo.sucessSum)!'0'}</td>
                        <td>${(packageReportInfo.failSum)!'0'}</td>
                    </tr>
                </table>
                <table class="detail_tb">
                    <caption class="table_title">
                        中兴反馈信息
                    </caption>
                    <tr>
                        <td>提取个数</td>
                        <td>成功个数</td>
                        <td>失败个数</td>
                    </tr>
                    <tr>
                        <td>700</td>
                        <td>500</td>
                        <td>200</td>
                    </tr>
                </table>
                <table class="detail_tb">
                    <caption class="table_title">
                        中兴提取包信息
                    </caption>
                    <tr>
                        <td>提取个数</td>
                        <td>成功个数</td>
                        <td>失败个数</td>
                    </tr>
                    <tr>
                        <td>${(zteReportInfo.packageSum)!'0'}</td>
                        <td>${(zteReportInfo.sucessSum)!'0'}</td>
                        <td>${(zteReportInfo.failSum)!'0'}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</@template>