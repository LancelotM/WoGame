<#include "/common/template.ftl">
<@template csses=["index"] jses=["index"]>
<#assign basePath=attrs.contextPath>

		<div class="login_img" style="background:url(${basePath}/static/images/login_bg.png)  no-repeat center bottom;">
			<div class="login_box" style="background-image: url(${basePath}/static/images/login_blackframe.png);">
				<form action="${basePath}/login" id="form" method="post" autocomplete="off" spellcheck="false">
					<table class="login_table">
						<tr>
							<td class="title_td">用户名</td>
							<td class="enter_td"><input type="text" id="username" name="username" /></td>
							<td id="check_username" class="error_info"></td>
						</tr>
						<tr>
							<td class="title_td">密码</td>
							<td class="enter_td"><input type="password" id="password" name="password" /></td>
							<td id="check_password" class="error_info"></td>
						</tr>
						<tr>
							<td></td>
							<td class="check_box">
								<div class="login_checkbox" style="background-image:url(${basePath}/static/images/check_back.png);" onclick="switch_className('checkbox_img')";>
                                    <img id="checkbox_img" src="${basePath}/static/images/check_in.png" class="checked" style="display: none"/>
                                    <input id="remember" type="hidden" value="" name="remember" />
                                </div>&nbsp;
								<label class="label_info">记住我的登录账号</label>
							</td>
							<td></td>
						</tr>
					</table>
					<a id="submit" class="submit_button" style="background-image:url(${basePath}/static/images/login_btn.png);" href="javascript:;" onclick="checkForm();">
						登录
					</a>
				</form>
			</div>
		</div>
</@template>