<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成二维码</title>
</head>
<body>
<center><h1><a href="/teacher/initQRCode?teacherId=tea0001&password=11&subjectId=sub0001&groupId=gp0001">1</a></h1></center>
<div id="qrcode"></div>
<div id="hideSpace" style="display:none;">${url}</div>
<script type="text/javascript" src="/lib/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/lib/qrcode/jquery.qrcode.js"></script>
<script type="text/javascript" src="/lib/qrcode/qrcode.js"></script>
<script>
	var text = $("#hideSpace").text();
	$('#qrcode').qrcode({width: 512,height: 512,text: text});
</script>
</body>
</html>