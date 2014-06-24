<#include "/common/template.ftl">
<@template csses=["index"] jses=["index"]>
<#assign basePath=attrs.contextPath>

		<div class="login_img">
			<div class="login_box">
				<form action="/login" method="post">
					<table class="login_table">
						<tr>
							<td class="title_td">用户名</td>
							<td class="enter_td"><input type="text" id="username" onblur="checkBlank(this.id,'check_username')"/></td>
							<td id="check_username" class="error_info"></td>
						</tr>
						<tr>
							<td class="title_td">密码</td>
							<td class="enter_td"><input type="password" id="password" onblur="checkBlank(this.id,'check_password')"/></td>
							<td id="check_password" class="error_info"></td>
						</tr>
						<tr>
							<td></td>
							<td class="check_box">
								<div class="login_checkbox"><img src="${basePath}/static/images/check_in.png" id="checkbox_img"/></div>&nbsp;
								<label class="label_info">记住我的登录账号</label>
							</td>
							<td><td>
						</tr>
					</table>
					<a id="submit" class="submit_button" href="javascript:void(0)">
						登录
					</a>
				</form>
			</div>
		</div>
</@template>