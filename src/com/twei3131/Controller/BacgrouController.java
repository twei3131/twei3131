package com.twei3131.Controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.twei3131.common.model.Signerror;
import com.twei3131.service.Users;

public class BacgrouController extends Controller {
	public void index(){
		render("/bacgrou/index.jsp");
	}
	
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		
		if (username.equals("") || password.equals("")) {
			renderHtml("<script>alert('用户名或密码为空')</script>");
		}else if (username.equals("14302189") || password.equals("170410")) {
			setSessionAttr("root", "max");
			render("/bacgrou/main.jsp");
		}else {
			Users users = new Users();
			if (users.judgeIndentityByUserId(username).equals("instructor")) {
				
			}else {
				
			}
		}

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
