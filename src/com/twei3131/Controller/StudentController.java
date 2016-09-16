package com.twei3131.Controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.twei3131.service.Scan;
import com.twei3131.service.Students;

public class StudentController extends Controller {
	Students students = new Students();
	Scan scan = new Scan();
	
	public void index(){
		render("/student/student.jsp");
	}
	
	public void getState(){
		
		String teacherId = getPara("teacherId");
		String subjectId = getPara("subjectId");
		
		String sql = "select count(*) from subjectInfo "+ 
				"where teacherId = '"+teacherId+"' and subjectId = '"+subjectId+"' and state = '������ʼ'";
		long count = Db.queryLong(sql);
		
		//����session
		setSessionAttr("subId", subjectId);
		setSessionAttr("teaId", teacherId);
		
		//�ж��Ƿ��¼
		if (getSessionAttr("status") == null) {
			redirect("/user/login.jsp");
		}else{
			if (count == 1) {
				setAttr("state", "ȷ���Ͽ�");
				index();
			}else{
				setAttr("state", "ȷ���¿�");
				index();
			}
		}
	}
	
	/*
	 * ѧ������Ͽΰ�ť
	 */
	public void sk(){
		//ѧ��ɨ��ĺϷ���
		Boolean isLegal = scan.isExt(getSessionAttr("username").toString(), getSessionAttr("teaId").toString());
		
		//�Ƿ��ظ�ɨ��
		Boolean isReScan = scan.isRePlay(getSessionAttr("username").toString(), getSessionAttr("teaId").toString());
		
		if (isLegal && !isReScan) {
			
		}
		
		
	}
}
