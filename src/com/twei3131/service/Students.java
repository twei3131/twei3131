package com.twei3131.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
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
		System.out.println(studentState);
		return studentState;
	}
	
	/*
	 * 更具学生状态判断学生状态
	 */
	public String setStuStateByStuState(String scanState,String studentState){
		
		String stuState = "";//初始化学生上课状态
		if (scanState.equals("xk")) {
			if (studentState.equals("迟到")) {
				stuState = "迟到";
			}else if (studentState.equals("未到")) {
				stuState = "旷课";
			}else if (studentState.equals("正常")) {
				stuState = "正常";
			}
		}else if (scanState.equals("fs")) {
			if (studentState.equals("正常")) {
				stuState = "早退";
			}else if (studentState.equals("迟到")) {
				stuState = "迟到早退";
			}else if (studentState.equals("旷课")) {
				stuState = "旷课";
			}else if (studentState.equals("正常下课")) {
				stuState = "正常下课";
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