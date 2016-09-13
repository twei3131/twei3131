package com.twei3131.service;

import java.util.Calendar;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.twei3131.common.model.Teacher;

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
}
