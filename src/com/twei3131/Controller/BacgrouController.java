package com.twei3131.Controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.twei3131.common.model.Signerror;

public class BacgrouController extends Controller {
	public void index(){
		render("/bacgrou/index.jsp");
	}
	
	private void login() {
		

	}
	
	public void audit(){
		String instructorId = getPara("instructorId");
		String subjectId = getPara("subjectId");
		String classId = getPara("classId");
		
		String sql = "SELECT a.* FROM signerror a JOIN student b ON a.studentId = b.studentId "+
		" JOIN classes c ON b.classId = c.classId JOIN instructor d ON c.instructorId = d.instructorId "+ 
		"WHERE d.instructorId='"+instructorId+"' AND c.classId = '"+classId+"' AND a.subjectId = '"+subjectId+"'";
		
		List<Signerror> list = Signerror.dao.find(sql);
		
		setAttr("context", list);
		
		render("/bacgrou/table.jsp");
	}
}
