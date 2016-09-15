package com.twei3131.Controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

public class StudentController extends Controller {
	public void index(){
		render("/student/student.jsp");
	}
	
	public void getState(){
		
		String teacherId = getPara("teacherId");
		String subjectId = getPara("subjectId");
		
		String sql = "select count(*) from subjectInfo "+ 
				"where teacherId = '"+teacherId+"' and subjectId = '"+subjectId+"' and state = '即将开始'";
		long count = Db.queryLong(sql);

		if (count == 1) {
			setAttr("state", "确认上课");
			index();
		}else{
			setAttr("state", "确认下课");
			index();
		}
	}
}
