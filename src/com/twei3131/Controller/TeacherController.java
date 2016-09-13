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
	 * ���ɶ�ά��
	 */
	public void initQRCode(){
		String teacherId = getPara("teacherId");
		String password = getPara("password");
		String subjectId = getPara("subjectId");
		String groupId = getPara("groupId");
		
		//�������Ϊ��
		if (teacherId.equals("") || password.equals("") || subjectId.equals("") || groupId.equals("")) {
				renderError(500);
		}
		
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
		
		//�Ϸ�
		if (isLegal && !isReScan) {
			List<Student> list = scan.getStudentInfoBySubjectId(teacherId, subjectId, groupId);
			scan.setStudentInfo(list, teacherId, subjectId);//���ݿ����
			scan.setSubjectInfo(teacherId, subjectId);//���ݿ����
			String url = "/student/getState?subjectId="+subjectId+"&teacherId="+teacherId;
			setAttr("errcode", "000");
			setAttr("url", url);			
			renderJson();
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
		
		List<String> str = new ArrayList<String>();//��ʼ��һ���洢���ŵ�����
		boolean flag = true;//����һ����ʶ��
		
		for(int i = 0;i < list.size();i++){
			
			str.add(list.get(i).getStr("groupId"));//�洢groupId
			//������
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
	 * ���ݽ�ʦ�Ż�ȡ��ʦ����
	 */
	public void getNameByUserId(){
		String username = getPara("username");
		Teacher teachere = Teacher.dao.findById(username);
		String name = teachere.getTeacherName();
		setAttr("name", name);
		renderJson();
	}
	
	/*
	 * ��ʦ����Ͽΰ�ť
	 */
	public void getSub(){
		String subjectId = getPara(0);
		String teacherId = getPara(1);
		
		//�޸Ŀγ�״̬
		String sql = "select * from subjectInfo where teacherId = '"+teacherId+"' and subjectId='"+subjectId+"'and state='������ʼ'";
		Subjectinfo subjectinfo = Subjectinfo.dao.findFirst(sql);
		subjectinfo.setState("�Ͽ���");
		subjectinfo.update();
		
		render("/teacher/qrcode.jsp");
	}
}
