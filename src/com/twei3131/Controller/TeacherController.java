package com.twei3131.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Record;
import com.twei3131.common.model.Student;
import com.twei3131.service.Scan;
import com.twei3131.service.Teacheres;

public class TeacherController extends Controller {
	Teacheres teacheres = new Teacheres();
	
	public void index(){
		render("/teacher/teacher.jsp");
	}
	
	/*
	 * 生成二维码
	 */
	public void initQRCode(){
		String teacherId = getPara("teacherId");
		String password = getPara("password");
		String subjectId = getPara("subjectId");
		String groupId = getPara("groupId");
		
		Scan scan = new Scan();
		
		//教师的合法性
		boolean isLegal = scan.isExists(teacherId, password, subjectId);
		//是否重复扫描
		boolean isReScan = scan.isReScan(teacherId);
		
		//如果用户非法
		if (!isLegal) {
			setAttr("errcode", "100");//非法
			renderJson();
		}
		
		//如果重复扫码
		if (isReScan) {
			setAttr("errcode", "200");
			renderJson();
		}
		
		if (isLegal && !isReScan) {
			List<Student> list = scan.getStudentInfoBySubjectId(teacherId, subjectId, groupId);
			scan.setStudentInfo(list, teacherId, subjectId);
			setAttr("url", "www.baidu.com?subjectId="+subjectId+"&teacherId="+teacherId);
			render("/teacher/qrcode.jsp");
		}
	}
	
	/*
	 * 获取教师的课程编号
	 */
	public void getInfo(){
		String username = getPara("username");
		String password = getPara("password");
		
		Map<String, String> map = new HashMap<String,String>();
		ArrayList<Map<String, String>> arr = new ArrayList<Map<String, String>>();
		List<Record> list = teacheres.getSubjectIdByUserId(username);
		for(int i = 0;i < list.size();i++){
			map.put("subjectId", list.get(i).getStr("subjectId"));
			map.put("subjectName", list.get(i).getStr("subjectName"));
			arr.add(map);
			map.clear();
		}
		String json = JsonKit.toJson(arr);
		renderJson(json);
	}

}
