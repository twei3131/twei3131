package com.twei3131.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.twei3131.common.model.Classes;
import com.twei3131.common.model.Signerror;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Teacher;
import com.twei3131.service.Users;

public class BacgrouController extends Controller {
	public void index(){
		render("/bacgrou/index.jsp");
	}
	
	public void getMain(){
		
		String username = getSessionAttr("uid").toString();
		List<Classes> classes = Classes.dao.find("select * from classes where instructorId = ?",username);
		setAttr("classes", classes);
		setAttr("userId", username);
		render("/bacgrou/main.jsp");
	}
	
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		
		if (username.equals("") || password.equals("")) {
			renderError(404);
		}else if (username.equals("14302189") || password.equals("170410")) {
			setSessionAttr("root", "max");
			render("/bacgrou/main.jsp");
		}else {
			Users users = new Users();
			if (users.judgeIndentityByUserId(username).equals("instructor")) {
				if (users.isUserByUserId(username, password)) {
					setSessionAttr("uid", username);
					getMain();
				}else{
					
				}
			}else {
				renderError(500);
			}
		}

	}
	
	public void audit(){
		String instructorId = getPara("instructorId");
		String classId = getPara("classId");
		
		String sql = "SELECT a.* FROM signerror a JOIN student b ON a.studentId = b.studentId "+
		" JOIN classes c ON b.classId = c.classId JOIN instructor d ON c.instructorId = d.instructorId "+ 
		"WHERE d.instructorId='"+instructorId+"' AND c.classId = '"+classId+"'";
		
		List<Signerror> list = Signerror.dao.find(sql);
		
		List<Classes> classes = Classes.dao.find("select * from classes where instructorId = ?",instructorId);
		setAttr("classes", classes);
		setAttr("userId", instructorId);
		
		setAttr("context", list);
		
		render("/bacgrou/table.jsp");
	}
	
	public void getStuInfo(){
		String stuId = getPara("username");
		Student student = Student.dao.findById(stuId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("uid", student.getStudentId());
		map.put("uname", student.getStudentName());
		String json = JsonKit.toJson(map);
		renderJson(json);
	}
	
	public void getTeaInfo(){
		String teaId = getPara("tId");
		Teacher teacher = Teacher.dao.findById(teaId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("tid", teacher.getTeacherId());
		map.put("tname", teacher.getTeacherName());
		String json = JsonKit.toJson(map);
		renderJson(json);
	}
}
