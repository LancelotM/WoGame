<#include "/common/template.ftl">
<@template csses=["index"] jses=["index"]>
<#assign basePath=attrs.contextPath>
		<div class="main_block">
			<div class="header">
				<img class="logo" src="${basePath}/static/images/logo.png" alt=""/>
				<div class="info">
					<span>管理员，你好</span>
                    <a class="exite_button" href="${basePath}/exit">安全登出</a>
				</div>
			</div>
			<div class="menu_main">
				<div class="menu">
					<div class="ul_info">
						<a href="#" class="margin_top45" onclick="show_hidden('first_ul');"><img src="${basePath}/static/images/leftmenu_arrow.png" alt=""/>&nbsp;日志统计信息</a>
						<ul id="first_ul" class="son_ul" style="display:none">
							<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="#">日志总览</a></li>
							<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="#">搜索日志</a></li>
						</ul>
					<a href="#" class="margin_top45" onclick="show_hidden('second_ul');"><img src="images/leftmenu_arrow.png" alt=""/>&nbsp;站点管理</a>
						<ul id="second_ul" class="son_ul" style="display:none">
							<li onmouseover="addStyle(this)" onmouseout="deleteStyle(this)" class=""><a href="#">建站管理</a></li>
						</ul>
					</div>
				</div>
				<div class="main">
					<div class="main_info">
						<div class="create_manager">
							<div class="log_title">
								<img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>建站管理
							</div>
							<table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
									<tr class="first_tr">
									<td style="color:#ed6d02">
										<span id="channel">北京联通</span>
										<a href="javascript:void(0)" class="cityTab" onmouseover="show_hidden('province_div');">
											[ <b></b>切换地区<label>▼</label>]
										</a>
										<div id="province_div" class="myCity" style="display:none">
											  <ul>
												<li>
													<span>A-G</span>
													<a name="18130" href="javascript:;">北京联通</a>
													<a name="18129" href="javascript:;">安徽联通</a>
													<a name="18131" href="javascript:;">重庆联通</a>
													<a name="18132" href="javascript:;">福建联通</a>
													<a name="18182" href="javascript:;">广东联通</a>
													<a name="18133" href="javascript:;">甘肃联通</a>
													<a name="18134" href="javascript:;">广西联通</a>
													<a name="18135" href="javascript:;">贵州联通</a>
												</li>
												<li>
													<span>H-J</span>
													<a name="18130" href="javascript:;">湖北联通</a>
													<a name="18130" href="javascript:;">湖南联通</a>
													<a name="18130" href="javascript:;">河北联通</a>
													<a name="18130" href="javascript:;">河南联通</a>
													<a name="18130" href="javascript:;">海南联通</a>
													<a name="18130" href="javascript:;">黑龙江联通</a>
													<a name="18130" href="javascript:;">江苏联通</a>
													<a name="18130" href="javascript:;">吉林联通</a>
													<a name="18130" href="javascript:;">江西联通</a>
												</li>
												<li>
													<span>L-S</span>
													<a name="18130" href="javascript:;">辽宁联通</a>
													<a name="18130" href="javascript:;">内蒙古联通</a>
													<a name="18130" href="javascript:;">宁夏联通</a>
													<a name="18130" href="javascript:;">青海联通</a>
													<a name="18130" href="javascript:;">山东联通</a>
													<a name="18130" href="javascript:;">上海联通</a>
													<a name="18130" href="javascript:;">山西联通</a>
													<a name="18130" href="javascript:;">陕西联通</a>
													<a name="18130" href="javascript:;">四川联通</a>
												</li>
												<li>
													<span>T-Z</span>
													<a name="18130" href="javascript:;">天津联通</a>
													<a name="18130" href="javascript:;">新疆联通</a>
													<a name="18130" href="javascript:;">西藏联通</a>
													<a name="18130" href="javascript:;">云南联通</a>
													<a name="18130" href="javascript:;">浙江联通</a>
												</li>
											  </ul>
										</div>
									</td>
									<td><a href="#" style="text-align:right"><img src="${basePath}/static/images/launch.png" alt=""/></a></td>
								</tr>
								<tr class="first_tr">
									<td class="link_class">站点链接</td>
									<td class="link_address" colspan="2"></td>
								</tr>
								<tr class="first_tr">
									<td class="link_class">后台管理链接</td>
									<td class="link_address" colspan="2" ></td>
								</tr>
							</table>
						</div>
						<div class="create_detail">
							<div class="log_title">
								<img class="title_image" src="${basePath}/static/images/icon_sitelist.png" alt="" />已建站<span class="numberal_format">3</span>个
							</div>
							<table class="detail_tb">
								<caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>已建站站点详细</span></caption>
								<tr class="first_tr">
									<td>渠道名</td>
									<td>站点链接</td>
									<td>后台管理链接</td>
									<td>建站时间</td>
									<td class="operate_td">站点操作</td>
								</tr>
								<#if channelInfos?exists>
	                                <#list channelInfos as channelInfo>
										<tr>
											<td>${channelInfo.channelName!}</td>
											<td>${channelInfo.wapURL!}</td>
											<td>${channelInfo.logURL!}</td>
											<td>${channelInfo.dateCreated!}</td>
											<td class="operate_td">
												<a href="#"><img src="${basePath}/static/images/detail.png" alt=""/></a>
											</td>
										</tr>
	                                </#list>
								</#if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>	
</@template>
