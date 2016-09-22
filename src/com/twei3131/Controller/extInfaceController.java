package com.twei3131.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.twei3131.common.model.Signerror;
import com.twei3131.common.model.Student;

public class extInfaceController extends Controller {
	
	public void index(){
		getInfo();
	}
	
	public void getInfo(){
		String stuId = getPara("sid");
		Map<String, Object> map = new HashMap<String,Object>();
		Student student = Student.dao.findById(stuId);
		map.put("student", student);
		List<Signerror> list = Signerror.dao.find("select * from signerror where studentId = ?",stuId);
		map.put("content", list);
		String json = JsonKit.toJson(map);
		renderJson(json);
	}
}
