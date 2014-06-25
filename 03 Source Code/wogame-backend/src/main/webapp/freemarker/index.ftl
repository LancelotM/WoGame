<#include "/common/template.ftl">
<@template csses=["index"] jses=["index"]>
<#assign basePath=attrs.contextPath>

		<div class="login_img" style="background-image: url(${basePath}/static/images/login_bg.png);">
			<div class="login_box" style="background-image: url(${basePath}/static/images/login_blackframe.png);">
				<form action="${basePath}/login" id="form" method="post">
					<table class="login_table">
						<tr>
							<td class="title_td">用户名</td>
							<td class="enter_td"><input type="text" id="username" name="username" onblur="checkBlank(this.id,'check_username')"/></td>
							<td id="check_username" class="error_info"></td>
						</tr>
						<tr>
							<td class="title_td">密码</td>
							<td class="enter_td"><input type="password" id="password" name="password" onblur="checkBlank(this.id,'check_password')"/></td>
							<td id="check_password" class="error_info"></td>
						</tr>
						<tr>
							<td></td>
							<td class="check_box">
								<div class="login_checkbox" style="background-image:url(${basePath}/static/images/check_back.png);"><img src="${basePath}/static/images/check_in.png" id="checkbox_img"/></div>&nbsp;
								<label class="label_info">记住我的登录账号</label>
							</td>
							<td><td>
						</tr>
					</table>
					<a id="submit" class="submit_button" style="background-image:url(${basePath}/static/images/login_btn.png);" href="javascript:;" onclick="checkForm();">
						登录
					</a>
				</form>
			</div>
		</div>
</@template>