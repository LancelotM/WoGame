<#include "/common/template.ftl">
<@template csses=["index"] jses=["site"]>
<#assign basePath=attrs.contextPath>
		<div class="main_block">
				<#include "/menu.ftl">
				<div class="main">
					<div class="main_info">
						<div class="create_manager">
							<div class="log_title">
								<img class="title_image" src="${basePath}/static/images/icon_createsite.png" alt=""/>建站管理
							</div>
                            <form id="launch_form" action="${basePath}/startSite" method="post">
                                <table id="create_manager_tb" cellspacing="0" style="margin-bottom:42px;">
                                    <tr class="first_tr">
                                        <td id="switch_region" style="color:#ed6d02">
                                            <span id="channel">${(channelInfoDomain.channelName)!'点击选择'}</span>
                                            <input id="channelName" type="hidden" value="北京联通" name="channelName"/>
                                            <a href="javascript:void(0)" class="cityTab">
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
                                                        <a name="18185" href="javascript:;">湖北联通</a>
                                                        <a name="18128" href="javascript:;">湖南联通</a>
                                                        <a name="18137" href="javascript:;">河北联通</a>
                                                        <a name="18138" href="javascript:;">河南联通</a>
                                                        <a name="18136" href="javascript:;">海南联通</a>
                                                        <a name="18139" href="javascript:;">黑龙江联通</a>
                                                        <a name="18141" href="javascript:;">江苏联通</a>
                                                        <a name="18140" href="javascript:;">吉林联通</a>
                                                        <a name="18142" href="javascript:;">江西联通</a>
                                                    </li>
                                                    <li>
                                                        <span>L-S</span>
                                                        <a name="18143" href="javascript:;">辽宁联通</a>
                                                        <a name="18144" href="javascript:;">内蒙古联通</a>
                                                        <a name="18145" href="javascript:;">宁夏联通</a>
                                                        <a name="18146" href="javascript:;">青海联通</a>
                                                        <a name="18147" href="javascript:;">山东联通</a>
                                                        <a name="18150" href="javascript:;">上海联通</a>
                                                        <a name="18148" href="javascript:;">山西联通</a>
                                                        <a name="18149" href="javascript:;">陕西联通</a>
                                                        <a name="18180" href="javascript:;">四川联通</a>
                                                    </li>
                                                    <li>
                                                        <span>T-Z</span>
                                                        <a name="18181" href="javascript:;">天津联通</a>
                                                        <a name="18183" href="javascript:;">新疆联通</a>
                                                        <a name="18182" href="javascript:;">西藏联通</a>
                                                        <a name="18184" href="javascript:;">云南联通</a>
                                                        <a name="18185" href="javascript:;">浙江联通</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                        <td><input id="channelId_input" type="text" name="channelCode" value="${(channelInfoDomain.channelCode)!'channelID'}" title="请输入channelID"/></td>
                                        <td><input id="cpid_input" type="text" name="cpid" value="${(channelInfoDomain.cpId)!'CPID'}"  title="请输入CPID"/></td>
                                        <td><a id="launch" href="javascript:;" style="text-align:right"><img id="launch_img" src="" alt=""/></a></td>
                                        <input type="hidden" id="channelId" value="${(channelInfoDomain.channelId)!}"/>
                                    </tr>
                                    <tr class="first_tr">
                                        <td class="link_class">站点链接</td>
                                        <td id="wapURL" class="link_address" colspan="3">${(channelInfoDomain.wapToken)!}</td>
                                    </tr>
                                    <tr class="first_tr">
                                        <td class="link_class">后台管理链接</td>
                                        <td id="logURL" class="link_address" colspan="3" >${(channelInfoDomain.logToken)!}</td>
                                    </tr>
                                </table>
                            </form>
						</div>
                        <div id="dialog" style="display:none;">
                            <div id="dialog_head">
                                <span id="dialog_titile">修改已建站点信息</span>
                            </div>
                            <form id="update_form" action="${basePath}/updateSit" method="post">
                                <table id="dialog_table">
                                    <tr>
                                        <td>渠道名：</td>
                                        <td><span id="dialog_chaName"></span></td>
                                        <input id="chaId" name="channelId" type="hidden" value=""/>
                                    </tr>
                                    <tr>
                                        <td>ChannelID:</td>
                                        <td><input id="dialog_chaId" name="channelCode" type="text" value=""/></td>
                                    </tr>
                                    <tr>
                                        <td>CPID</td>
                                        <td><input id="dialog_cpid" name="cpid" type="text" value=""/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <a class="left" id="update_submit" href="javascript:;">
                                                <img src="${basePath}/static/images/confirm_update.png" alt="update" style="margin-right:75px;"/>
                                            </a>
                                            <a class="right" id="cancel" href="javascript:;"><img src="${basePath}/static/images/cancel.png" alt="cancel"/></a>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
						<div class="create_detail">
							<div class="log_title">
								<img class="title_image" src="${basePath}/static/images/icon_sitelist.png" alt="" />已建站
                                <span class="numberal_format">
                                    <#if channelInfos?exists>
                                         ${channelInfos?size}
                                        <#else>
                                            0
                                    </#if>
                                </span>个
							</div>
							<table class="detail_tb">
								<caption class="table_title"><img class="title_image" src="${basePath}/static/images/icon_table.png" alt=""/><span>已建站站点详细</span></caption>
								<tr class="first_tr">
									<td>渠道名</td>
									<td>channelID</td>
									<td>CPID</td>
									<td>建站时间</td>
									<td class="operate_td">站点操作</td>
								</tr>
								<#if channelInfos?exists>
	                                <#list channelInfos as channelInfo>
										<tr>
											<td>${channelInfo.channelName!}</td>
											<td>${channelInfo.channelCode!}</td>
											<td>${channelInfo.cpId!}</td>
											<td>${channelInfo.date!}</td>
											<td id="operate_td" class="operate_td">
                                                <a id="update_info" href="javascript:;" onclick="getUpdateInfo(${channelInfo.channelId?c});"><img src="${basePath}/static/images/update.png" /></a>
												<a href="javascript:;" onclick="getDetailInfo(${channelInfo.channelId?c})"><img src="${basePath}/static/images/detail.png" alt="detail"/></a>
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
