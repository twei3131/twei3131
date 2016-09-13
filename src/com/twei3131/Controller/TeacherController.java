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
		
		ArrayList<Map<String, String>> arr = new ArrayList<Map<String, String>>();
		List<Record> list = teacheres.getSubjectIdByUserId(username);
		for(int i = 0;i < list.size();i++){
			Map<String, String> map = new HashMap<String,String>();
			map.put("subjectId", list.get(i).getStr("subjectId"));
			map.put("subjectName", list.get(i).getStr("subjectName"));
			arr.add(map);//��map��װ��������
		}
		String json = JsonKit.toJson(arr);//����Json����
		renderJson(json);
	}
	
	/*
	 * ��ȡ����
	 */
	public void getGroupId(){
		String username = getPara("username");
		String subjectId = getPara("subjectId");
		
		ArrayList<Map<String, String>> arr = new ArrayList<Map<String, String>>();
		List<Record> list = teacheres.getGpId(username, subjectId);
		for(int i = 0;i < list.size();i++){
			Map<String, String> map = new HashMap<String,String>();
			map.put("groupId", list.get(i).getStr("groupId"));
			map.put("groupName", list.get(i).getStr("groupName"));
			arr.add(map);
		}
		String json = JsonKit.toJson(arr);
		renderJson(json);
	}

}
