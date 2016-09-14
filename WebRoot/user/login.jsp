<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
	<div id="form">
		<div>登录窗体</div>
		<form action="/user/login" method="post">
			<ul>
				<li><label>用户名：</label><input type="text" name="username" placeholder="用户名"></li>
				<li><label>密	码：</label><input style="position:relative;left:30px;" type="text" name="password" placeholder="密码"></li>
				<li><label>验证码：</label><input style="width:180px" type="text"><div id="img" onclick="reloadImage()"><img alt="验证码丢了" src="/common/image.jsp"></div></li>
				<li><button id="btn" type="submit">登录</button></li>
			</ul>
		</form>
	</div>
	<script type="text/javascript">
		function reloadImage() { 
			document.getElementById('img').src = '/common/image.jsp?ts=' + new Date().getTime();
		}
</script>
</body>
</html>