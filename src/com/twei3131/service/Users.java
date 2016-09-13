package com.twei3131.service;

import com.twei3131.common.model.Department;
import com.twei3131.common.model.Instructor;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Teacher;

public class Users {
	/*
	 * �ж��û����
	 */
	public String judgeIndentityByUserId(String userId){
		String indentity = "";
		//�ж��Ƿ���ѧ��
		Student student = Student.dao.findById(userId);
		if (student != null) {
			indentity = "student";
		}
		
		//�ж��Ƿ�����ʦ
		Teacher teacher = Teacher.dao.findById(userId);
		if (teacher != null) {
			indentity = "teacher";
		}
		
		//�ж��Ƿ��Ǹ���Ա
		Instructor instructor = Instructor.dao.findById(userId);
		if (instructor != null) {
			indentity = "instructor";
		}
		
		//�ж��Ƿ���ϵ�쵼
		Department department = Department.dao.findById(userId);
		if (department != null) {
			indentity = "leader";
		}
		
		//�û�������
		if (userId == "") {
			indentity = "Error";
		}
		return indentity;
	}
	
	/*
	 * ��֤�û��ĺϷ���(ɨ��)
	 */
	public Boolean isUserByUserId(String userId,String password){
		
		String indentity = judgeIndentityByUserId(userId); //��ȡ�û����
		
		boolean flag = false;
		
		//�û��������벻����
		if (userId == "" || password == "") {
			flag = false;
		}
		
		//�û���ѧ��
		if (indentity == "student") {
			Student student = Student.dao.findById(userId);
			if (student.getPassword().equals(password)) {
				flag = true;
			}
		}
		
		//�û�����ʦ
		if (indentity == "teacher") {
			Teacher teacher = Teacher.dao.findById(userId);
			if (teacher.getPassword().equals(password)) {
				flag = true;
			}
		}
		
		//�û��Ǹ���Ա
		if (indentity == "instructor") {
			Instructor instructor = Instructor.dao.findById(userId);
			if (instructor.getPassword().equals(password)) {
				flag = true;
			}
		}
		
		//�û���ϵ�쵼
		if (indentity == "leader") {
			flag = true;
		}
		
		return flag;
	}
	
	/*
	 * �ж��û��Ƿ����״ε�¼
	 */	
	public Boolean isFirstLoginByUserId(String userId){
		
		String indentity = judgeIndentityByUserId(userId); //��ȡ�û����
		boolean flag = false;
		
		//�û���ѧ��
		if (indentity == "student") {
			Student student = Student.dao.findById(userId);
			if (student.getStr("password").equals("none")) {
				flag = true;
			}
			System.out.println(student.getPassword());
		}
		
		//�û�����ʦ
		if (indentity == "teacher") {
			Teacher teacher = Teacher.dao.findById(userId);
			if (teacher.getPassword().equals("none")) {
				flag = true;
			}
		}
		
		//�û��Ǹ���Ա
		if (indentity == "instructor") {
			Instructor instructor = Instructor.dao.findById(userId);
			if (instructor.getPassword().equals("none")) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	/*
	 * ������״ε�¼����������
	 */
	public Boolean setPassword(String userId,String password){
		
		String indentity = judgeIndentityByUserId(userId); //��ȡ�û����
		boolean flag = false;//��ʼ�����ݱ���״̬
		
		//�û���ѧ��
		if (indentity == "student") {
			Student student = Student.dao.findById(userId);
			student.setPassword(password);
			flag = student.update();
		}
		
		//�û�����ʦ
		if (indentity == "teacher") {
			Teacher teacher = Teacher.dao.findById(userId);
			teacher.setPassword(password);
			flag = teacher.update();
		}
		
		//�û� �Ǹ���Ա
		if (indentity == "instructor") {
			Instructor instructor = Instructor.dao.findById(userId);
			instructor.setPassword(password);
			instructor.update();
		}
		
		return flag;
	}
	
	/*
	 * �ж��û����û����������һ����
	 */
}
