<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>登陆</title>
        <link type="text/css" rel="stylesheet" media="all" href="wogame.css"/>
		<script type="text/javascript" src="js/vertify.js"></script>
	</head>
	<body>
		<div class="login_img">
			<div class="login_box">
				<form action="" method="post">
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
								<div class="login_checkbox"><img src="images/check_in.png" id="checkbox_img"/></div>&nbsp;
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
	</body>
</html>