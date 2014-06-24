<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>建站管理</title>
        <link type="text/css" rel="stylesheet" media="all" href="wogame.css"/>
		<script type="text/javascript" src="js/styleControl.js"></script>
    </head>
    <body>
		<div class="main_block">
			<div class="header">
				<img class="logo" src="images/logo.png" alt=""/>
				<div class="info">
					<span>管理员，你好</span>
					<input type="button" class="exite_button" value="安全登出"/>
				</div>
			</div>
			<div class="menu_main">
				<div class="menu">
					<div class="ul_info">
						<a href="#" class="margin_top45" onclick="show_hidden('first_ul');"><img src="images/leftmenu_arrow.png" alt=""/>&nbsp;日志统计信息</a>
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
								<img class="title_image" src="images/icon_createsite.png" alt=""/>建站管理
							</div>
							<table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
									<tr class="first_tr">
									<td style="color:#ed6d02">
										${city}
										<a href="javascript:void(0)" class="cityTab" onmouseover="show_hidden('province_div');">
											[ <b></b>切换地区<label>▼</label>]
										</a>
										<div id="province_div" class="myCity" style="display:none">
											  <ul>
												<li>
													<span>A-G</span>
													<a href="javascript:void(0)">北京联通</a>
													<a href="javascript:void(0)">安徽联通</a>
													<a href="javascript:void(0)">重庆联通</a>
													<a href="javascript:void(0)">福建联通</a>
													<a href="javascript:void(0)">广东联通</a>
													<a href="javascript:void(0)">甘肃联通</a>
													<a href="javascript:void(0)">广西联通</a>
													<a href="javascript:void(0)">贵州联通</a>
												</li>
												<li>
													<span>H-J</span>
													<a href="javascript:void(0)">湖北联通</a>
													<a href="javascript:void(0)">湖南联通</a>
													<a href="javascript:void(0)">河北联通</a>
													<a href="javascript:void(0)">河南联通</a>
													<a href="javascript:void(0)">海南联通</a>
													<a href="javascript:void(0)">黑龙江联通</a>
													<a href="javascript:void(0)">江苏联通</a>
													<a href="javascript:void(0)">吉林联通</a>
													<a href="javascript:void(0)">江西联通</a>
												</li>
												<li>
													<span>L-S</span>
													<a href="javascript:void(0)">辽宁联通</a>
													<a href="javascript:void(0)">内蒙古联通</a>
													<a href="javascript:void(0)">宁夏联通</a>
													<a href="javascript:void(0)">青海联通</a>
													<a href="javascript:void(0)">山东联通</a>
													<a href="javascript:void(0)">上海联通</a>
													<a href="javascript:void(0)">山西联通</a>
													<a href="javascript:void(0)">陕西联通</a>
													<a href="javascript:void(0)">四川联通</a>
												</li>
												<li>
													<span>T-Z</span>
													<a href="javascript:void(0)">天津联通</a>
													<a href="javascript:void(0)">新疆联通</a>
													<a href="javascript:void(0)">西藏联通</a>
													<a href="javascript:void(0)">云南联通</a>
													<a href="javascript:void(0)">浙江联通</a>
												</li>
											  </ul>
										</div>
									</td>
									<td><a href="#" style="text-align:right"><img src="images/launch.png" alt=""/></a></td>
								</tr>
								<tr class="first_tr">
									<td class="link_class">站点链接</td>
									<td class="link_address" colspan="2">${url}</td>
								</tr>
								<tr class="first_tr">
									<td class="link_class">后台管理链接</td>
									<td class="link_address" colspan="2" >${url}</td>
								</tr>
							</table>
						</div>
						<div class="create_detail">
							<div class="log_title">
								<img class="title_image" src="images/icon_sitelist.png" alt="" />已建站<span class="numberal_format">3</span>个
							</div>
							<table class="detail_tb">
								<caption class="table_title"><img class="title_image" src="images/icon_table.png" alt=""/><span>已建站站点详细</span></caption>
								<tr class="first_tr">
									<td>渠道名</td>
									<td>站点链接</td>
									<td>后台管理链接</td>
									<td>建站时间</td>
									<td class="operate_td">站点操作</td>
								</tr>
                                <#list channelInfos as channelInfo>
									<tr>
										<td>${channelInfo.channelName}</td>
										<td>${}</td>
										<td></td>
										<td>2014/06/22</td>
										<td class="operate_td">
											<a href="#"><img src="images/detail.png" alt=""/></a>
										</td>
									</tr>
                                </#list>
								<tr>
									<td>上海联通</td>
									<td>http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</td>
									<td>http://xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</td>
									<td>2014/06/22</td>
									<td class="operate_td">
										<a href="#"><img src="images/detail.png" alt=""/></a>
									</td>
								</tr>
								<tr>
									<td>浙江联通</td>
									<td>http://xxx</td>
									<td>http://xxx</td>
									<td>2014/06/22</td>
									<td class="operate_td">
										<a href="#"><img src="images/detail.png" alt=""/></a>
									</td>
								</tr>
								<tr>
									<td>江苏联通</td>
									<td>http://xxx</td>
									<td>http://xxx</td>
									<td>2014/06/22</td>
									<td class="operate_td">
										<a href="#"><img src="images/detail.png" alt=""/></a>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>	
    </body>
</html>
