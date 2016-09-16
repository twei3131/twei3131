package com.twei3131.service;

import com.twei3131.common.model.Tempsign;

public class Students {
	//根据课程状态改变学生上课状态
	public String getStuStateByClassState(String scanState,String classState,String studentId,String teacherId){
		
		String studentState = "";//初始化学生上课状态
		if (scanState.equals("stuFirst")) {
			if (classState.equals("即将开始")) {
				studentState = "正常";
			}else if (classState.equals("上课中")) {
				studentState = "迟到";
			}else if (classState.equals("下课")) {
				Tempsign tempsign = Tempsign.dao.findById(studentId,teacherId);
				if (tempsign.getState().equals("迟到")) {
					studentState = "迟到下课";
				}else{
					studentState = "正常下课";
				}
			}
		}

		return studentState;
	}
}
