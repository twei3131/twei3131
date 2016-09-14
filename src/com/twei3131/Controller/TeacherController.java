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
	 * ���ɶ�ά��
	 */
	public void initQRCode(){
		String teacherId = getPara("teacherId");
		String password = getPara("password");
		String subjectId = getPara("subjectId");
		String groupId = getPara("groupId");
		
		Scan scan = new Scan();
		
		//��ʦ�ĺϷ���
		boolean isLegal = scan.isExists(teacherId, password, subjectId);
		//�Ƿ��ظ�ɨ��
		boolean isReScan = scan.isReScan(teacherId);
		
		//����û��Ƿ�
		if (!isLegal) {
			setAttr("errcode", "100");//�Ƿ�
			renderJson();
		}
		
		//����ظ�ɨ��
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
	 * ��ȡ��ʦ�Ŀγ̱��
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
