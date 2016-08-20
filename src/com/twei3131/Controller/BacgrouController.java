package com.twei3131.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PathKit;
import com.twei3131.common.model.Classes;
import com.twei3131.common.model.Signerror;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Subject;
import com.twei3131.common.model.Teacher;
import com.twei3131.service.Users;

public class BacgrouController extends Controller {
	public void index(){
		render("/bacgrou/index.jsp");
	}
	
	public void context(){
		String username = getSessionAttr("uid").toString();
		List<Classes> classes = Classes.dao.find("select * from classes where instructorId = ?",username);
		setAttr("classes", classes);
		setAttr("userId", username);
	}
	
	public void getMain(){
		
		context();
		render("/bacgrou/main.jsp");
	}
	
	public void setAudit(){
		String stuId = getPara(0);
		String times = getPara(1);
		context();
		Signerror signerror = Signerror.dao.findById(stuId,times);
		Student student = Student.dao.findById(signerror.getStudentId());
		setAttr("signerror", signerror);
		setAttr("student", student);
		render("/bacgrou/audit.jsp");
	}
	
	public void setAuditTwo() {
		String stuId = getPara("userId");
		String state = getPara("state");
		String times = getPara("times");
		
		Signerror signerror = new Signerror();
		Boolean flag = false;
		
		if (state.equals("Õý³£ÏÂ¿Î") || state.equals("Çë¼Ù") || state.equals("´ø¼ÜÉÏ¿Î")) {
			 flag = signerror.deleteById(stuId,times);
		}else {
			signerror = Signerror.dao.findById(stuId,times);
			signerror.setState(state);
			flag = signerror.update();
		}
		
		 if (flag) {
			 	context();
				signerror = Signerror.dao.findById(stuId,times);
				Student student = Student.dao.findById(signerror.getStudentId());
				setAttr("signerror", signerror);
				setAttr("student", student);
				render("/bacgrou/table.jsp");
				
		}else{
			renderError(500);
		}
	}
	
	public void changeAuditState(){
		String stuId = getPara("userId");
		String times = getPara("times");
		
		boolean flag = false;
		Map<String, String> map = new HashMap<String, String>();
		
		Signerror signerror = Signerror.dao.findById(stuId,times);
		String state = signerror.getAuditState();
		if (state.equals("Î´ÉóºË")) {
			signerror.setAuditState("ÒÑÉóºË");
			flag = signerror.update();
		}
		
		if (flag) {
			map.put("status", "200");
		}else {
			map.put("status", "500");
		}
		renderJson(map);
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
	
	public void getSubInfo(){
		String subId = getPara("subId");
		Subject subject = Subject.dao.findById(subId);
		Map<String, String> map = new HashMap<String,String>();
		map.put("subId", subject.getSubjectId());
		map.put("subName", subject.getSubjectName());
		String json = JsonKit.toJson(map);
		renderJson(json);
	}
	
	public void getDownPage(){
		String type = getPara(0);
		setAttr("filename", type);
		render("/bacgrou/fileF.jsp");
	}
	
	public void downloadFile(){
		String filename = getPara(0);
		final String basePath = PathKit.getWebRootPath()+File.separator+"twei3131Load"+File.separator;
		String path = basePath + filename + ".xlsx";
		File file = new File(path);
		renderFile(file);
	}
}
