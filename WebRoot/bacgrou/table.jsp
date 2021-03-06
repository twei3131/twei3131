<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap Styles-->
    <link href="${CONTEXT_PATH}/bacgrou/assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="${CONTEXT_PATH}/bacgrou/assets/css/font-awesome.css" rel="stylesheet" />
     <!-- Morris Chart Styles-->
   
        <!-- Custom Styles-->
    <link href="${CONTEXT_PATH}/bacgrou/assets/css/custom-styles.css" rel="stylesheet" />
     <!-- Google Fonts-->
<!--    <link href='http://fonts.useso.com/css?family=Open+Sans' rel='stylesheet' type='text/css' /> -->
     <!-- TABLE STYLES-->
    <link href="${CONTEXT_PATH}/bacgrou/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <style type="text/css">
    	a{
    		text-decoration:none;
    		color:black
    	}
    </style>
</head>
<body>
	<div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><strong><i class="icon fa fa-plane"></i> BRILLIANT</strong></a>
				<div id="sideNav" href="">
		<i class="fa fa-bars icon"></i> 
		</div>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Doe</strong>
                                    <span class="pull-right text-muted">
                                        <em>Today</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since an kwilnw...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem Ipsum has been the industry's standard dummy text ever since the...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">28% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="28" aria-valuemin="0" aria-valuemax="100" style="width: 28%">
                                            <span class="sr-only">28% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">85% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100" style="width: 85%">
                                            <span class="sr-only">85% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 min</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
        <!--/. NAV TOP  -->
         <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">

                    <li>
                        <a class="active-menu" href="${CONTEXT_PATH}/bacgrou/getMain"><i class="fa fa-dashboard"></i> 主页</a>
                    </li>
                     <li>
                        <a href="#"><i class="fa fa-sitemap"></i> 班级考核查询<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                        <c:forEach items="${classes}" var="list">
                            <li>
                                <a href="${CONTEXT_PATH}/bacgrou/audit?instructorId=${userId}&classId=${list.classId}">${list.name}</a>
                            </li>
                        </c:forEach> 
						</ul>
                    </li> 
					 
					 <li>
                        <a href="#"><i class="fa fa-sitemap"></i> 录入管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="${CONTEXT_PATH}/bacgrou/getDownPage/demo_Student">学生录入管理</a>
                            </li>
                            <li>
                                <a href="${CONTEXT_PATH}/bacgrou/getDownPage/demo_Subject">课程录入管理</a>
                            </li>
                            <li>
                                <a href="${CONTEXT_PATH}/bacgrou/getDownPage/demo_Classes">班级/辅导员录入管理</a>
                            </li>
                            <li>
                                 <a href="${CONTEXT_PATH}/bacgrou/getDownPage/demo_Teacher">教师录入管理</a>
                            </li>
                            <li>
                                 <a href="${CONTEXT_PATH}/bacgrou/getDownPage/demo_Group">选修课/体育课录入管理</a>
                            </li>
                            <li>
                                <a href="${CONTEXT_PATH}/bacgrou/cursor">课表管理</a>
                            </li>
                             <li>
                                <a href="${CONTEXT_PATH}/bacgrou/form.jsp">虚拟班级管理</a>
                            </li>
							</ul>
						</li>	
                    
                    <li>
                        <a href="${CONTEXT_PATH}/bacgrou/table.jsp"><i class="fa fa-table"></i> 学生签到情况审核管理</a>
                    </li>
                  
                </ul>

            </div>

        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
		  <div class="header"> 
                        <h1 class="page-header">
                            Tables Page <small>Responsive tables</small>
                        </h1>
						<ol class="breadcrumb">
					  <li><a href="#">Home</a></li>
					  <li><a href="#">Tables</a></li>
					  <li class="active">Data</li>
					</ol> 
									
		</div>
		
            <div id="page-inner"> 
               
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             Advanced Tables
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>学号</th><th>教师编号</th><th>课程编号</th><th>时间<th>状态</th><th>审核状态</th><th>修改</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${context}" var="list">
	                                        <tr class="odd gradeX">
	                                            <td><a href="javascript:getStuInfo(${list.studentId})">${list.studentId}</a></td>
	                                            <td><a href="javascript:getTeaInfos('${list.teacherId}')">${list.teacherId}</a></td>
	                                            <td><a href="javascript:getSubInfo('${list.subjectId}')">${list.subjectId}</a></td>
	                                            <td>${list.time}</td>
	                                            <td class="center">${list.state}</td>
	                                            <td class="center">${list.auditState}</td>
	                                            <td><a onclick="changeAudite(${list.studentId},${list.times})" class="btn btn-primary center">审核</a><a href="${CONTEXT_PATH}/bacgrou/setAudit/${list.studentId}-${list.times}" class="btn btn-danger center">修改</a></td>
	                                        </tr>
                                        </c:forEach>	
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            <div class="row">
                <div class="col-md-6">
                  
        </div>
               <footer><p>Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://www.mycodes.net/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p></footer>
    </div>
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
     <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="${CONTEXT_PATH}/bacgrou/assets/js/jquery-1.10.2.js"></script>
      <!-- Bootstrap Js -->
    <script src="${CONTEXT_PATH}/bacgrou/assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="${CONTEXT_PATH}/assets/js/jquery.metisMenu.js"></script>
     <!-- DATA TABLE SCRIPTS -->
    <script src="${CONTEXT_PATH}/bacgrou/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="${CONTEXT_PATH}/bacgrou/assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
        	var basehref = '${CONTEXT_PATH}';

            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
            function getStuInfo(stuId){
            	$.get(basehref+"/bacgrou/getStuInfo",{"username":stuId},function(data){
            		var content = "学号:"+data.uid+";姓名:"+data.uname;
            		alert(content);
            	});
            }
            function getTeaInfos(teaId){
            	$.get(basehref+"/bacgrou/getTeaInfo",{"tId":teaId},function(data){
            		var content = "教师编号:"+data.tid+";教师姓名:"+data.tname;
            		alert(content);
            	});
            }
            function getSubInfo(subjectId){
            	$.get(basehref+"/bacgrou/getSubInfo",{"subId":subjectId},function(data){
            		var content = "课程编号:"+data.subId+";课程名:"+data.subName;
            		alert(content);
            	});
            }
            function changeAudite(uId,times){
            	$.get(basehref+"/bacgrou/changeAuditState",{"userId":uId,"times":times},function(data){
            		var content = data.status;
            		var alte = "";
            		if(content == 200){
            			alte = "审核状态修改成功";
            		}else{
            			alte = "您可能已修改过审核状态！";
            		}
            		alert(alte);
            	});
            }
    </script>
         <!-- Custom Js -->
    <script src="${CONTEXT_PATH}/bacgrou/assets/js/custom-scripts.js"></script>
</body>
</html>