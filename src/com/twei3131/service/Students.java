package com.twei3131.service;

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

		return studentState;
	}
}
