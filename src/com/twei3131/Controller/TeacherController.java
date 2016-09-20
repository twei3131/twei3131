package com.twei3131.Controller;

import java.io.File;
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
import com.twei3131.service.Students;
import com.twei3131.service.Teacheres;

public class TeacherController extends Controller {
	Teacheres teacheres = new Teacheres();
	Students students = new Students();
	
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
			String url = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath()+"/student/getState?subjectId="+subjectId+"&teacherId="+teacherId;
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
		String subjectId = getPara("subjectId");
		String teacherId = getPara("teacherId");
		
		//修改课程状态
		boolean fla = teacheres.lockState(teacherId, subjectId);
		
		//判断是否修改成功
		if (fla) {
			setAttr("name","下课");
			setAttr("href","/teacher/doneSub?subjectId="+subjectId+"&teacherId="+teacherId);
			render("/teacher/qrcodeNext.jsp");
		}else{
			setAttr("err", "500");
			render("/teacher/qrcode.jsp");
		}
	}
	
	/*
	 * 老师点击下课按钮
	 */
	public void doneSub(){
		String subjectId = getPara("subjectId");
		String teacherId = getPara("teacherId");
		
		teacheres.unlockState(teacherId, subjectId);//更改课程状态
		
		teacheres.unlockStuScanState(teacherId);//解除学生扫描锁定状态
		
		students.updateStuState(teacherId, "xk");//更改部分学生上课状态
		
		setAttr("name", "放学");
		setAttr("href", "/teacher/fd?subjectId="+subjectId+"&teacherId="+teacherId);
		
		render("/teacher/qrcodeNext.jsp");
	}
	
	/*
	 * 老师点放学按钮
	 */
	public void fd(){
		String subjectId = getPara("subjectId");
		String teacherId = getPara("teacherId");
		
		teacheres.setFs(teacherId, subjectId);//更改课程状态为放学
		
		students.updateStuState(teacherId, "fs");//更改部分学生上课状态
		
		teacheres.deletStu(teacherId);//更改课程状态为放学
		
		teacheres.setSignError(teacherId,subjectId);//删除状态为正常下课的学生
		
		teacheres.delStu(teacherId);//删除剩下的所有学生
		
		setAttr("name", "结束");
		setAttr("href", "/javascript:void(0)");
		
		render("/teacher/qrcodeNext.jsp");
	}
}
