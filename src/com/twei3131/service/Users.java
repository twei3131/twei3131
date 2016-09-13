package com.twei3131.service;

import com.twei3131.common.model.Department;
import com.twei3131.common.model.Instructor;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Teacher;

public class Users {
	/*
	 * 判断用户身份
	 */
	public String judgeIndentityByUserId(String userId){
		String indentity = "";
		//判断是否是学生
		Student student = Student.dao.findById(userId);
		if (student != null) {
			indentity = "student";
		}
		
		//判断是否是老师
		Teacher teacher = Teacher.dao.findById(userId);
		if (teacher != null) {
			indentity = "teacher";
		}
		
		//判断是否是辅导员
		Instructor instructor = Instructor.dao.findById(userId);
		if (instructor != null) {
			indentity = "instructor";
		}
		
		//判断是否是系领导
		Department department = Department.dao.findById(userId);
		if (department != null) {
			indentity = "leader";
		}
		
		//用户名错误
		if (userId == "") {
			indentity = "Error";
		}
		return indentity;
	}
	
	/*
	 * 验证用户的合法性(扫描)
	 */
	public Boolean isUserByUserId(String userId,String password){
		
		String indentity = judgeIndentityByUserId(userId); //获取用户身份
		
		boolean flag = false;
		
		//用户名或密码不存在
		if (userId == "" || password == "") {
			flag = false;
		}
		
		//用户是学生
		if (indentity == "student") {
			Student student = Student.dao.findById(userId);
			if (student.getPassword().equals(password)) {
				flag = true;
			}
		}
		
		//用户是老师
		if (indentity == "teacher") {
			Teacher teacher = Teacher.dao.findById(userId);
			if (teacher.getPassword().equals(password)) {
				flag = true;
			}
		}
		
		//用户是辅导员
		if (indentity == "instructor") {
			Instructor instructor = Instructor.dao.findById(userId);
			if (instructor.getPassword().equals(password)) {
				flag = true;
			}
		}
		
		//用户是系领导
		if (indentity == "leader") {
			flag = true;
		}
		
		return flag;
	}
	
	/*
	 * 判断用户是否是首次登录
	 */	
	public Boolean isFirstLoginByUserId(String userId){
		
		String indentity = judgeIndentityByUserId(userId); //获取用户身份
		boolean flag = false;
		
		//用户是学生
		if (indentity == "student") {
			Student student = Student.dao.findById(userId);
			if (student.getStr("password").equals("none")) {
				flag = true;
			}
			System.out.println(student.getPassword());
		}
		
		//用户是老师
		if (indentity == "teacher") {
			Teacher teacher = Teacher.dao.findById(userId);
			if (teacher.getPassword().equals("none")) {
				flag = true;
			}
		}
		
		//用户是辅导员
		if (indentity == "instructor") {
			Instructor instructor = Instructor.dao.findById(userId);
			if (instructor.getPassword().equals("none")) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	/*
	 * 如果是首次登录，保存密码
	 */
	public Boolean setPassword(String userId,String password){
		
		String indentity = judgeIndentityByUserId(userId); //获取用户身份
		boolean flag = false;//初始化数据保存状态
		
		//用户是学生
		if (indentity == "student") {
			Student student = Student.dao.findById(userId);
			student.setPassword(password);
			flag = student.update();
		}
		
		//用户是老师
		if (indentity == "teacher") {
			Teacher teacher = Teacher.dao.findById(userId);
			teacher.setPassword(password);
			flag = teacher.update();
		}
		
		//用户 是辅导员
		if (indentity == "instructor") {
			Instructor instructor = Instructor.dao.findById(userId);
			instructor.setPassword(password);
			instructor.update();
		}
		
		return flag;
	}
	
	/*
	 * 判断用户的用户名和密码的一致性
	 */
}
