<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成二维码</title>
<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/css/teacher.css">
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
		<%
			String uid = "";
			String pass = "";
			if(session.getAttribute("uid") != null && session.getAttribute("pas") != null){
				uid = session.getAttribute("uid").toString();
				pass = session.getAttribute("pas").toString();
			}
		%>
		<div style="display:none" id="username"><%=uid%></div>
		<div style="display:none" id="password"><%=pass%></div>
	<script type="text/javascript" src="${CONTEXT_PATH}/lib/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/teacher.js"></script>
</body>
</html>