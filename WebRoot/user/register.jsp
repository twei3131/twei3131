<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href = "/css/register.css">
<script type="text/javascript" src="/lib/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/js/register.js"></script>
</head>
<body>
	<div id="form">
		<div id="titfont">用户登录</div>
		<form action="/user/register" method="post">
			<div id="inpt">
				<input type="text" name="username" placeholder="用户名">
				<input type="text" name="password" placeholder="密码">
				<button id="btn" type="submit">注册</button> 
			</div>
		</form>
	</div>
</body>
</html>