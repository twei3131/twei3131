<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成二维码</title>
<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/css/qrcode.css">
</head>
<body>
	<div id="head">
		<div id="title">扫描二维码</div>
	</div>
	<div id="qrcode"></div>
	<div id="chacter">请扫描上方二维码</div>
	<a href="#">上课</a>
	<div>${err}</div>
	<script type="text/javascript" src="${CONTEXT_PATH}/lib/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/lib/qrcode/jquery.qrcode.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/lib/qrcode/qrcode.js"></script>
	<script>
		var basehref = '${CONTEXT_PATH}';
		var text = window.localStorage['qrcodeUrl'];
		$('#qrcode').qrcode({width: 512,height: 512,text: text});
		var content = localStorage['qrcodeUrl'].split("?")[1];
		$("a").attr('href',basehref+'/teacher/getSub/?'+content);
	</script>
</body>
</html>