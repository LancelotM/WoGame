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
							<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" ><a href="${basePath}/getAllKeyWowrd?channelId="+${(channelInfoDomain.channelId)!}>搜索日志</a></li>
						</ul>
						<#if admin?exists && admin>
							<a href="#" class="margin_top45" onclick="show_hidden('second_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png" alt=""/>&nbsp;站点管理</a>
							<ul id="second_ul" class="son_ul" style="display:none">
								<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)"><a href="${basePath}/site">建站管理</a></li>
							</ul>
						</#if>
					</div>
				</div>