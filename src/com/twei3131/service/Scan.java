package com.twei3131.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Subject;
import com.twei3131.common.model.Subjectinfo;
import com.twei3131.common.model.Teacher;
import com.twei3131.common.model.Tempsign;

public class Scan {
	
	Users user = new Users();
	Teacheres teacheres = new Teacheres();
	/*
	 * teacher
	 */
	//�жϽ�ʦ�ĺϷ���
	@SuppressWarnings("deprecation")
	public boolean isExists(String teacherId,String password,String subjectId){
		
		boolean flag = false;
		
		//��ȡ��ǰ���ں�ʱ��
		Date date = new Date();
		int hour = date.getHours();
		int minutes = date.getMinutes();
		int seconds = date.getSeconds();
		
		//�ж��û����������һ����
		boolean isUser = user.isUserByUserId(teacherId, password);
		
		//��ʼ���α�
		List<Teacher> subjectList = teacheres.getSubjectList(teacherId);
		
		//�α�Ϊ�շ���false
		if (subjectList.size() == 0) {
			flag = false;
		}
		
		//��ȡ��ʱ��
		long count = teacheres.getSubjectTimes(teacherId, subjectId);
		
		//���ݶ�Ӧ�Ŀγ̺�,�ӿα��л�ȡ�γ�
		if(subjectList.size() != 0){
			for(int i = 0;i < subjectList.size();i++){
				if (subjectList.get(i).getStr("subjectId").equals(subjectId)) {
					String[] time = String.valueOf(subjectList.get(i).getDate("startTime")).split(":");
					long startTime = Long.valueOf(time[0])*60L*60 + Long.valueOf(time[1])*60 + Long.valueOf(time[2]);
					long curTime = Long.valueOf(hour)*60L*60 + Long.valueOf(minutes)*60 + Long.valueOf(seconds);
					long cha = (startTime - curTime)/60L;//����ʱ���

					if (cha <= 15 && cha >= -30 && count < subjectList.get(i).getInt("classNumber") && isUser) {
						flag = true;
					}else{
						flag = false;
					}
					break;
				}
			}
		}
		
		return flag;
	}
	
	/*
	 * �ж��Ƿ��ظ�����
	 */
	public Boolean isReScan(String teacherId){
		String sql = "SELECT COUNT(*) FROM tempsign WHERE teacherId = ?";
		long i = Db.queryLong(sql,teacherId);
		return i != 0;
	}
	
	/*
	 * ��ȡ�γ�����
	 */
	public String getSubjectType(String subjectId){
		
		Subject subject = Subject.dao.findById(subjectId);
		return subject.getType();
	}
	
	/*
	 * ��ȡѧ����Ϣ
	 */
	public List<Student> getStudentInfoBySubjectId(String teacherId,String subjectId,String groupId){
		String sql = "";
		
		//���޿�
		if (getSubjectType(subjectId).equals("����")) {
			sql = "SELECT a.* FROM teacher b JOIN subjecttoteacher c ON b.teacherId = c.teacherId JOIN `subject` d ON c.subjectId = d.subjectId "+
					"JOIN grouptosubject e ON d.subjectId = e.subjectId JOIN `group` f ON e.groupId = f.groupId JOIN classes g ON f.classId = g.classId "+
					"JOIN student a ON g.classId = a.classId WHERE b.teacherId = '"+teacherId+"' AND c.subjectId = '"+subjectId+"' AND f.groupId = '"+groupId+"'";
		}
		//ѡ�޿�
		if (getSubjectType(subjectId).equals("ѡ��")) {
			sql="SELECT a.* FROM teacher b JOIN subjecttoteacher c ON b.teacherId = c.teacherId JOIN `subject` d ON c.subjectId = d.subjectId "+
				"JOIN grouptosubject e ON d.subjectId = e.subjectId JOIN `group` f ON  e.groupId = f.groupId JOIN student a ON f.studentId = a.studentId "+
				"WHERE b.teacherId = '"+teacherId+"' AND d.subjectId = '"+subjectId+"' AND f.groupId = '"+groupId+"'";
		}

		//��ȡѧ����
		List<Student> students = Student.dao.find(sql);
		
		return students;
	}
	
	/*
	 * ��ѧ�����������ݿ����
	 */
	public void setStudentInfo(List<Student> students,String teacherId,String subjectId){
		
		 List<Tempsign> tempsigns = new ArrayList<Tempsign>();
		 for(int i = 0;i < students.size();i++){
			 Tempsign tempsign = new Tempsign();
			 tempsign.setStudentId(students.get(i).getStudentId());
			 tempsign.setTeacherId(teacherId);
			 tempsign.setSubjectId(subjectId);
			 tempsign.setState("δ��");
			 tempsign.setScanState("false");
			 tempsign.setHostname("");
			 tempsigns.add(tempsign);
		 }
		 Db.batchSave(tempsigns, students.size());
	}
	
	/*
	 * ����Ӧ�Ŀγ���Ϣ���뵽�����
	 */
	public void setSubjectInfo(String teacherId,String subjectId){
		Subjectinfo subjectinfo = new Subjectinfo();
		Long id = Db.queryLong("select count(*) from subjectinfo") + 1;//��ȡ����
		subjectinfo.setSubjectInfoId(id.intValue());
		subjectinfo.setSubjectId(subjectId);
		subjectinfo.setTeacherId(teacherId);
		subjectinfo.setState("������ʼ");
		subjectinfo.save();
	}
	
	/*
	 * student
	 */
	//�ж�ѧ��ɨ��ĺϷ���
	public boolean isExt(String studentId,String teacherId){
		boolean flag = false;
		//��ȡ���
		String indentity = user.judgeIndentityByUserId(studentId);
		
		//��ȡ�Ͽ�����
		Tempsign tempsign = Tempsign.dao.findById(studentId,teacherId);
		
		//�жϺϷ���
		if (indentity == "student" && tempsign != null) {
			flag = true;
		}
		
		return flag;
	}
	
	/*
	 * �ж�ѧ���Ƿ��ظ�ɨ��
	 */
	public boolean isRePlay(String studentId,String teacherId){
		boolean flag = false;
		
		//��ȡ����״̬
		Tempsign tempsign = Tempsign.dao.findById(studentId,teacherId);
		String lockState = tempsign.getScanState();
		
		//�ж�����״̬
		if (lockState.equals("false")) {
			flag = true;
		}
		
		return flag;
	}
}
