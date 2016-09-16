package com.twei3131.Controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.twei3131.service.Scan;
import com.twei3131.service.Students;

public class StudentController extends Controller {
	Students students = new Students();
	Scan scan = new Scan();
	
	public void index(){
		render("/student/student.jsp");
	}
	
	public void getState(){
		
		String teacherId = getPara("teacherId");
		String subjectId = getPara("subjectId");
		
		String sql = "select count(*) from subjectInfo "+ 
				"where teacherId = '"+teacherId+"' and subjectId = '"+subjectId+"' and state = '即将开始'";
		long count = Db.queryLong(sql);
		
		//设置session
		setSessionAttr("subId", subjectId);
		setSessionAttr("teaId", teacherId);
		
		//判断是否登录
		if (getSessionAttr("status") == null) {
			redirect("/user/login.jsp");
		}else{
			if (count == 1) {
				setAttr("state", "确认上课");
				index();
			}else{
				setAttr("state", "确认下课");
				index();
			}
		}
	}
	
	/*
	 * 学生点击上课按钮
	 */
	public void sk(){
		//学生扫描的合法性
		Boolean isLegal = scan.isExt(getSessionAttr("username").toString(), getSessionAttr("teaId").toString());
		
		//是否重复扫描
		Boolean isReScan = scan.isRePlay(getSessionAttr("username").toString(), getSessionAttr("teaId").toString());
		
		if (isLegal && !isReScan) {
			
		}
		
		
	}
}
