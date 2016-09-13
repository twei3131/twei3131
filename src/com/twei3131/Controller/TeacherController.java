package com.twei3131.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Record;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Subjectinfo;
import com.twei3131.common.model.Teacher;
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
		
		//如果数据为空
		if (teacherId.equals("") || password.equals("") || subjectId.equals("") || groupId.equals("")) {
				renderError(500);
		}
		
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
		
		//合法
		if (isLegal && !isReScan) {
			List<Student> list = scan.getStudentInfoBySubjectId(teacherId, subjectId, groupId);
			scan.setStudentInfo(list, teacherId, subjectId);//数据库操作
			scan.setSubjectInfo(teacherId, subjectId);//数据库操作
			String url = "/student/getState?subjectId="+subjectId+"&teacherId="+teacherId;
			setAttr("errcode", "000");
			setAttr("url", url);			
			renderJson();
		}
	}
	
	/*
	 * 获取教师的课程编号
	 */
	public void getInfo(){
		String username = getPara("username");
		String password = getPara("password");
		
		ArrayList<Map<String, String>> arr = new ArrayList<Map<String, String>>();
		List<Record> list = teacheres.getSubjectIdByUserId(username);
		for(int i = 0;i < list.size();i++){
			Map<String, String> map = new HashMap<String,String>();
			map.put("subjectId", list.get(i).getStr("subjectId"));
			map.put("subjectName", list.get(i).getStr("subjectName"));
			arr.add(map);//将map封装到数组里
		}
		String json = JsonKit.toJson(arr);//生成Json数据
		renderJson(json);
	}
	
	/*
	 * 获取组编号
	 */
	public void getGroupId(){
		String username = getPara("username");
		String subjectId = getPara("subjectId");
		
		ArrayList<Map<String, String>> arr = new ArrayList<Map<String, String>>();
		
		List<Record> list = teacheres.getGpId(username, subjectId);
		
		List<String> str = new ArrayList<String>();//初始化一个存储组编号的容器
		boolean flag = true;//返回一个标识符
		
		for(int i = 0;i < list.size();i++){
			
			str.add(list.get(i).getStr("groupId"));//存储groupId
			//过滤器
			for(int j = 0;j < str.size();j++){
				if (list.get(i).getStr("groupId") == str.get(j) && i != 0) {
					flag = false;
				}
			}

			if (flag == true) {
				Map<String, String> map = new HashMap<String,String>();
				map.put("groupId", list.get(i).getStr("groupId"));
				map.put("groupName", list.get(i).getStr("groupName"));			
				arr.add(map);
			}
		}
		String json = JsonKit.toJson(arr);
		renderJson(json);
	}
	
	/*
	 * 根据教师号获取教师姓名
	 */
	public void getNameByUserId(){
		String username = getPara("username");
		Teacher teachere = Teacher.dao.findById(username);
		String name = teachere.getTeacherName();
		setAttr("name", name);
		renderJson();
	}
	
	/*
	 * 教师点击上课按钮
	 */
	public void getSub(){
		String subjectId = getPara(0);
		String teacherId = getPara(1);
		
		//修改课程状态
		String sql = "select * from subjectInfo where teacherId = '"+teacherId+"' and subjectId='"+subjectId+"'and state='即将开始'";
		Subjectinfo subjectinfo = Subjectinfo.dao.findFirst(sql);
		subjectinfo.setState("上课中");
		subjectinfo.update();
		
		render("/teacher/qrcode.jsp");
	}
}
