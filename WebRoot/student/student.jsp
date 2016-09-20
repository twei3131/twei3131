<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生签到</title>
<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/css/student.css">
<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/lib/percircle/css/percircle.css">
</head>
</head>
<body>
	<div id="clock" class="purple big"></div>
	<a id="btn" href="${CONTEXT_PATH}${href}">${state}</a>
	<script type="text/javascript" src="${CONTEXT_PATH}/lib/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/lib/percircle/js/percircle.min.js"></script>
	<script type="text/javascript" src="${CONTEXT_PATH}/js/student.js"></script>
	<script type="text/javascript">
		$(function(){
			 $("#clock").percircle({
                perclock: true
            });
		});
	</script>
</body>
</html>