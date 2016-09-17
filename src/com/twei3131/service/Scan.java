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
	//判断教师的合法性
	@SuppressWarnings("deprecation")
	public boolean isExists(String teacherId,String password,String subjectId){
		
		boolean flag = false;
		
		//获取当前日期和时间
		Date date = new Date();
		int hour = date.getHours();
		int minutes = date.getMinutes();
		int seconds = date.getSeconds();
		
		//判断用户名与密码的一致性
		boolean isUser = user.isUserByUserId(teacherId, password);
		
		//初始化课表
		List<Teacher> subjectList = teacheres.getSubjectList(teacherId);
		
		//课表为空返回false
		if (subjectList.size() == 0) {
			flag = false;
		}
		
		//获取课时数
		long count = teacheres.getSubjectTimes(teacherId, subjectId);
		
		//根据对应的课程号,从课表中获取课程
		if(subjectList.size() != 0){
			for(int i = 0;i < subjectList.size();i++){
				if (subjectList.get(i).getStr("subjectId").equals(subjectId)) {
					String[] time = String.valueOf(subjectList.get(i).getDate("startTime")).split(":");
					long startTime = Long.valueOf(time[0])*60L*60 + Long.valueOf(time[1])*60 + Long.valueOf(time[2]);
					long curTime = Long.valueOf(hour)*60L*60 + Long.valueOf(minutes)*60 + Long.valueOf(seconds);
					long cha = (startTime - curTime)/60L;//计算时间差

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
	 * 判断是否重复开课
	 */
	public Boolean isReScan(String teacherId){
		String sql = "SELECT COUNT(*) FROM tempsign WHERE teacherId = ?";
		long i = Db.queryLong(sql,teacherId);
		return i != 0;
	}
	
	/*
	 * 获取课程类型
	 */
	public String getSubjectType(String subjectId){
		
		Subject subject = Subject.dao.findById(subjectId);
		return subject.getType();
	}
	
	/*
	 * 获取学生信息
	 */
	public List<Student> getStudentInfoBySubjectId(String teacherId,String subjectId,String groupId){
		String sql = "";
		
		//必修课
		if (getSubjectType(subjectId).equals("必修")) {
			sql = "SELECT a.* FROM teacher b JOIN subjecttoteacher c ON b.teacherId = c.teacherId JOIN `subject` d ON c.subjectId = d.subjectId "+
					"JOIN grouptosubject e ON d.subjectId = e.subjectId JOIN `group` f ON e.groupId = f.groupId JOIN classes g ON f.classId = g.classId "+
					"JOIN student a ON g.classId = a.classId WHERE b.teacherId = '"+teacherId+"' AND c.subjectId = '"+subjectId+"' AND f.groupId = '"+groupId+"'";
		}
		//选修课
		if (getSubjectType(subjectId).equals("选修")) {
			sql="SELECT a.* FROM teacher b JOIN subjecttoteacher c ON b.teacherId = c.teacherId JOIN `subject` d ON c.subjectId = d.subjectId "+
				"JOIN grouptosubject e ON d.subjectId = e.subjectId JOIN `group` f ON  e.groupId = f.groupId JOIN student a ON f.studentId = a.studentId "+
				"WHERE b.teacherId = '"+teacherId+"' AND d.subjectId = '"+subjectId+"' AND f.groupId = '"+groupId+"'";
		}

		//获取学生集
		List<Student> students = Student.dao.find(sql);
		
		return students;
	}
	
	/*
	 * 将学生集插入数据库表中
	 */
	public void setStudentInfo(List<Student> students,String teacherId,String subjectId){
		
		 List<Tempsign> tempsigns = new ArrayList<Tempsign>();
		 for(int i = 0;i < students.size();i++){
			 Tempsign tempsign = new Tempsign();
			 tempsign.setStudentId(students.get(i).getStudentId());
			 tempsign.setTeacherId(teacherId);
			 tempsign.setSubjectId(subjectId);
			 tempsign.setState("未到");
			 tempsign.setScanState("false");
			 tempsign.setHostname("");
			 tempsigns.add(tempsign);
		 }
		 Db.batchSave(tempsigns, students.size());
	}
	
	/*
	 * 将相应的课程信息插入到库表中
	 */
	public void setSubjectInfo(String teacherId,String subjectId){
		Subjectinfo subjectinfo = new Subjectinfo();
		Long id = Db.queryLong("select count(*) from subjectinfo") + 1;//获取主键
		subjectinfo.setSubjectInfoId(id.intValue());
		subjectinfo.setSubjectId(subjectId);
		subjectinfo.setTeacherId(teacherId);
		subjectinfo.setState("即将开始");
		subjectinfo.save();
	}
	
	/*
	 * student
	 */
	//判断学生扫描的合法性
	public boolean isExt(String studentId,String teacherId){
		boolean flag = false;
		//获取身份
		String indentity = user.judgeIndentityByUserId(studentId);
		
		//获取上课内容
		Tempsign tempsign = Tempsign.dao.findById(studentId,teacherId);
		
		//判断合法性
		if (indentity == "student" && tempsign != null) {
			flag = true;
		}
		
		return flag;
	}
	
	/*
	 * 判断学生是否重复扫描
	 */
	public boolean isRePlay(String studentId,String teacherId){
		boolean flag = false;
		
		//获取锁定状态
		Tempsign tempsign = Tempsign.dao.findById(studentId,teacherId);
		String lockState = tempsign.getScanState();
		
		//判断锁定状态
		if (lockState.equals("false")) {
			flag = true;
		}
		
		return flag;
	}
}
