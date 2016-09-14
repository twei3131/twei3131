package com.twei3131.Controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.twei3131.common.model.Student;
import com.twei3131.service.Scan;

public class TeacherController extends Controller {
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

}
