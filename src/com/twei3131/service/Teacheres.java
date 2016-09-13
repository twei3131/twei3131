package com.twei3131.service;

import java.util.Calendar;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.twei3131.common.model.Teacher;

public class Teacheres {
	/*
	 * 生成课表
	 */
	public List<Teacher> getSubjectList(String teacherId){
		
		//获取当前日期是一周的第几天
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);

		if (day != 1) {
			day--;
		}else{
			day = 7;
		}

		//初始化sql语句
		String sql = "SELECT a.subjectId,a.subjectName,a.type,a.classNumber,b.days,b.startTime,b.endTime,c.teacherId FROM teacher c JOIN subjecttoteacher d "+ 
				"ON c.teacherId = d.teacherId JOIN `subject` a ON a.subjectId = d.subjectId JOIN subjecttotimes e ON a.subjectId = e.subjectId "+
				"JOIN times b ON b.timesId = e.timesId WHERE c.teacherId = ? AND b.days = " + day;  
		
		//执行sql语句
		List<Teacher> teachers = Teacher.dao.find(sql,teacherId);
		
		return teachers;
	}
	
	/*
	 * 获取课时数
	 */
	public long getSubjectTimes(String teacherId,String subjectId){
		String sql = "SELECT COUNT(*) FROM subjectinfo WHERE teacherId = '"+teacherId+"' AND subjectId = '"+subjectId+"'";
		long i = Db.queryLong(sql);
		return i;
	}
	
	/*
	 * 根据教师编号获取课程编号,课程名
	 */
	public List<Record> getSubjectIdByUserId(String userId){
		String sql = "SELECT a.subjectId,b.subjectName FROM subjecttoteacher "+
				" a JOIN `subject` b ON a.subjectId = b.subjectId WHERE a.teacherId = ?";
		
		List<Record> list = Db.find(sql, userId);
		
		return list;
	}
	
	/*
	 * 获取组编号
	 */
	public List<Record> getGpId(String teacherId,String subjectId){
		String sql = "SELECT a.groupId,a.groupName FROM  subjecttoteacher b JOIN `subject` c " +
					"ON b.subjectId = c.subjectId JOIN grouptosubject d ON c.subjectId = d.subjectId "+ 
				"JOIN grouptosubject e ON d.groupId = e.groupId JOIN `group` a ON e.groupId = a.groupId WHERE "+
					" b.subjectId = '"+subjectId+"' AND b.teacherId = '"+teacherId+"'";
		List<Record> list = Db.find(sql);
		return list;
	}
}
