			<div class="header">
				<img class="logo" src="${basePath}/static/images/logo.png" alt=""/>
				<div class="info">
                    <#if admin?exists && admin>
                         <span>管理员，你好</span>
                         <a id="exit_id" class="exite_button" href="javascript:;">安全登出</a>                        
                    <#else>
                         <span>${ChannelName!}，你好</span>
                    </#if>

				</div>
			</div>
			<div class="menu_main">
				<div class="menu">
					<div class="ul_info">
						<a href="#" class="margin_top45" onclick="show_hidden('first_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png" alt=""/>&nbsp;日志统计信息</a>
						<ul id="first_ul" class="son_ul" style="display:none">
							<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" ><a href="${basePath}/log">日志总览</a></li>
							<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" ><a href="${basePath}/getAllKeyWowrd?channelId=${developer_channel!0}">搜索下载</a></li>
						</ul>
						<#if admin?exists && admin>
							<a href="#" class="margin_top45" onclick="show_hidden('second_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png" alt=""/>&nbsp;站点管理</a>
							<ul id="second_ul" class="son_ul" style="display:none">
								<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)"><a href="${basePath}/site">建站管理</a></li>
							</ul>
                            <a href="#" class="margin_top45" onclick="show_hidden('report_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png"/>&nbsp;报表</a>
                            <ul id="report_ul" class="son_ul" style="display:none">
                                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a id="report_detail" href="javascript:;">报表详情</a></li>
                            </ul>
                            <a href="#" class="margin_top45" onclick="show_hidden('fourth_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png"/>&nbsp;Banner管理</a>
                            <ul id="fourth_ul" class="son_ul" style="display:none">
                                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="${basePath}/floatwindow">顶部漂浮窗</a></li>
                                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="${basePath}/topbanner">顶部Banner</a></li>
                                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="${basePath}/activitymodule">活动模块</a></li>
                                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="${basePath}/activitybanner">活动Banner</a></li>
                                <li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="${basePath}/bottombanner">底部广告位</a></li>
                            </ul>
                        </#if>
                        </div>
					</div>
