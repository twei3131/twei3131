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
				"where teacherId = '"+teacherId+"' and subjectId = '"+subjectId+"' and state = '������ʼ'";
		long count = Db.queryLong(sql);

		if (count == 1) {
			setAttr("state", "ȷ���Ͽ�");
			index();
		}else{
			setAttr("state", "ȷ���¿�");
			index();
		}
	}
}
