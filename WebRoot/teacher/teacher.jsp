<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成二维码</title>
<link rel="stylesheet" type="text/css" href="./../css/teacher.css">
</head>
<body>
	<div id="bott">
			<ul>
				<li><div id="teaname">夏孝云</div></li>
				<li><label>课程编号:</label><select name="subjectId"></select></li>
				<li><label>课组编号:</label><select name="groupId"></select></li>
				<a href="/teacher/initQRCode?teacherId=tea0002&password=13&subjectId=sub0003&groupId=gp0002">生成二维码</a>
			</ul>
		</div>
	<script type="text/javascript" src="./../lib/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="./../js/teacher.js"></script>
</body>
</html>