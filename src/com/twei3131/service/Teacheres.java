package com.twei3131.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.twei3131.common.model.Signerror;
import com.twei3131.common.model.Subjectinfo;
import com.twei3131.common.model.Teacher;
import com.twei3131.common.model.Tempsign;

public class Teacheres {
	/*
	 * ���ɿα�
	 */
	public List<Teacher> getSubjectList(String teacherId){
		
		//��ȡ��ǰ������һ�ܵĵڼ���
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);

		if (day != 1) {
			day--;
		}else{
			day = 7;
		}

		//��ʼ��sql���
		String sql = "SELECT a.subjectId,a.subjectName,a.type,a.classNumber,b.days,b.startTime,b.endTime,c.teacherId FROM teacher c JOIN subjecttoteacher d "+ 
				"ON c.teacherId = d.teacherId JOIN `subject` a ON a.subjectId = d.subjectId JOIN subjecttotimes e ON a.subjectId = e.subjectId "+
				"JOIN times b ON b.timesId = e.timesId WHERE c.teacherId = ? AND b.days = " + day;  
		
		//ִ��sql���
		List<Teacher> teachers = Teacher.dao.find(sql,teacherId);
		
		return teachers;
	}
	
	/*
	 * ��ȡ��ʱ��
	 */
	public long getSubjectTimes(String teacherId,String subjectId){
		String sql = "SELECT COUNT(*) FROM subjectinfo WHERE teacherId = '"+teacherId+"' AND subjectId = '"+subjectId+"'";
		long i = Db.queryLong(sql);
		return i;
	}
	
	/*
	 * ���ݽ�ʦ��Ż�ȡ�γ̱��,�γ���
	 */
	public List<Record> getSubjectIdByUserId(String userId){
		String sql = "SELECT a.subjectId,b.subjectName FROM subjecttoteacher "+
				" a JOIN `subject` b ON a.subjectId = b.subjectId WHERE a.teacherId = ?";
		
		List<Record> list = Db.find(sql, userId);
		
		return list;
	}
	
	/*
	 * ��ȡ����
	 */
	public List<Record> getGpId(String teacherId,String subjectId){
		String sql = "SELECT a.groupId,a.groupName FROM  subjecttoteacher b JOIN `subject` c " +
					"ON b.subjectId = c.subjectId JOIN grouptosubject d ON c.subjectId = d.subjectId "+ 
				"JOIN grouptosubject e ON d.groupId = e.groupId JOIN `group` a ON e.groupId = a.groupId WHERE "+
					" b.subjectId = '"+subjectId+"' AND b.teacherId = '"+teacherId+"'";
		List<Record> list = Db.find(sql);
		return list;
	}
	
	/*
	 * �޸Ŀγ�״̬
	 */
	public Boolean lockState(String teacherId,String subjectId){
		String sql = "select * from subjectInfo where teacherId = '"+teacherId+"' and subjectId='"+subjectId+"'and state='������ʼ'";
		Subjectinfo subjectinfo = Subjectinfo.dao.findFirst(sql);
		subjectinfo.setState("�Ͽ���");
		Boolean flag = subjectinfo.update();
		return flag;
	}
	
	/*
	 * ���Ŀγ�״̬
	 */
	public boolean unlockState(String teacherId,String subjectId){
		String sql = "select * from subjectInfo where teacherId = '"+teacherId+"' and subjectId='"+subjectId+"'and state='�Ͽ���'";
		Subjectinfo subjectinfo = Subjectinfo.dao.findFirst(sql);
		subjectinfo.setState("�¿�");
		Boolean flag = subjectinfo.update();
		return flag;
	}
	
	/*
	 * ���ѧ������״̬
	 */
	public boolean unlockStuScanState(String teacherId){
		Integer i = Db.update("update tempsign set scanState = 'true' where teacherId = ?",teacherId);
		return i >= 0;
	}
	
	/*
	 * ���Ŀγ�״̬Ϊ��ѧ
	 */
	public boolean setFs(String teacherId,String subjectId){
		String sql = "select * from subjectInfo where teacherId = '"+teacherId+"' and subjectId='"+subjectId+"'and state='�¿�'";
		Subjectinfo subjectinfo = Subjectinfo.dao.findFirst(sql);
		subjectinfo.setState("��ѧ");
		Boolean flag = subjectinfo.update();
		return flag;
	}
	
	/*
	 * ɾ��״̬Ϊ�����¿ε�ѧ��
	 */
	public boolean deletStu(String teacherId){
		String sql = "delete from tempsign where state = '�����¿�' and teacherId = ?";
		Integer i = Db.update(sql,teacherId);
		return i >= 0;
	}
	
	public boolean delStu(String teacherId){
		String sql = "delete from tempsign where teacherId = ?";
		Integer i = Db.update(sql,teacherId);
		return i>= 0;
	}
	
	public void setSignError(String teacherId,String subjectId){
		List<Tempsign> tempsign = Tempsign.dao.find("select * from tempsign where teacherId = ?",teacherId);
		for(int i = 0;i < tempsign.size();i++){
			Signerror signerror = new Signerror();
			signerror.setStudentId(tempsign.get(i).getStudentId());
			signerror.setSubjectId(tempsign.get(i).getSubjectId());
			signerror.setTeacherId(tempsign.get(i).getTeacherId());
			signerror.setState(tempsign.get(i).getState());
			signerror.setAuditState("δ���");
			signerror.setTimes((int)getSubjectTimes(teacherId, subjectId));
			signerror.setTime(new Date("yyyy-mm-dd hh:mm:ss"));
			signerror.save();
		}
	}
}
