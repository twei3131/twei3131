<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成二维码</title>
<link rel="stylesheet" type="text/css" href="/css/teacher.css">
</head>
<body>
	<div id="bott">
			<ul>
				<li><div id="teaname">夏孝云</div></li>
				<li><label>课程编号:</label><select id="subId" name="subjectId"></select></li>
				<li><label>课组编号:</label><select id="gpId" name="groupId"></select></li>
				<a id="qrcod" href="javascript:sub()">生成二维码</a>
			</ul>
		</div>
		<div style="display:none" id="username">${username}</div>
		<div style="display:none" id="password">${password}</div>
	<script type="text/javascript" src="/lib/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="/js/teacher.js"></script>
</body>
</html>