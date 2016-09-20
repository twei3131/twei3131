package com.twei3131.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.twei3131.common.model.Tempsign;

public class Students {
	//���ݿγ�״̬�ı�ѧ���Ͽ�״̬
	public String getStuStateByClassState(String scanState,String classState,String studentId,String teacherId){
		
		String studentState = "";//��ʼ��ѧ���Ͽ�״̬
		if (scanState.equals("stuFirst")) {
			if (classState.equals("������ʼ")) {
				studentState = "����";
			}else if (classState.equals("�Ͽ���")) {
				studentState = "�ٵ�";
			}else if (classState.equals("�¿�")) {
				Tempsign tempsign = Tempsign.dao.findById(studentId,teacherId);
				if (tempsign.getState().equals("�ٵ�")) {
					studentState = "�ٵ��¿�";
				}else{
					studentState = "�����¿�";
				}
			}
		}
		System.out.println(studentState);
		return studentState;
	}
	
	/*
	 * ����ѧ��״̬�ж�ѧ��״̬
	 */
	public String setStuStateByStuState(String scanState,String studentState){
		
		String stuState = "";//��ʼ��ѧ���Ͽ�״̬
		if (scanState.equals("xk")) {
			if (studentState.equals("�ٵ�")) {
				stuState = "�ٵ�";
			}else if (studentState.equals("δ��")) {
				stuState = "����";
			}else if (studentState.equals("����")) {
				stuState = "����";
			}
		}else if (scanState.equals("fs")) {
			if (studentState.equals("����")) {
				stuState = "����";
			}else if (studentState.equals("�ٵ�")) {
				stuState = "�ٵ�����";
			}else if (studentState.equals("����")) {
				stuState = "����";
			}else if (studentState.equals("�����¿�")) {
				stuState = "�����¿�";
			}
		}

		return stuState;
	}
	
	public void updateStuState(String teacherId,String scanState){
		List<Tempsign> list = Tempsign.dao.find("select * from tempsign where teacherId = ?",teacherId);
		for(int i = 0;i < list.size();i++){
			list.get(i).setState(setStuStateByStuState(scanState, list.get(i).getState()));
		}
		Db.batchUpdate(list, list.size());
	}
	
	
}