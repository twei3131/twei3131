package com.twei3131.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.jfinal.core.Controller;
import com.twei3131.service.Users;

public class UserController extends Controller {
	Users user = new Users();
	
	public void index(){
		render("/user/register.jsp");
	}
	
	/*
	 * ע��ӿ�
	 */
	public void register(){
		String username = getPara("username");
		String password = getPara("password");
		
		//���������û���Ϊ�ջ�����Ϊ��ʼ����none
		if (username == "" || password == "" || password.equals("none")) {
			setAttr("errcode", "err");
			renderJson();
		}
		
		//�ж����
		String indentity = user.judgeIndentityByUserId(username);
		
		//�ж��Ƿ����״ε�¼
		boolean isFirstLoginFlag = user.isFirstLoginByUserId(username);
		
		//�û�������
		if (indentity == "") {
			setAttr("errcode", "404");
			renderJson();
		}

		if (indentity == "student" || indentity == "teacher" || indentity == "instructor") {
			if (isFirstLoginFlag) {
				if (user.setPassword(username, password)) {
					index();
				}else{
					renderError(404);
				}
			}else{
				index();
			}
		}
	}
	
	/*
	 * ��¼�ӿ�
	 */
	public void login(){
		String username = getPara("username");
		String password = getPara("password");
		String vcode = getPara("vcode");

		//�ж��û��ĺϷ���
		Boolean isUser = user.isUserByUserId(username, password);

		if (isUser) {
			if (user.judgeIndentityByUserId(username) == "teacher") {
				//�����û���������
				setAttr("username", username);
				setAttr("password", password);
				
				setSessionAttr("status", "200");
				
				render("/teacher/teacher.jsp");
			}
			
			if(user.judgeIndentityByUserId(username) == "student"){
				setAttr("stuname", username);
				setAttr("pass", password);
				
				setSessionAttr("status", "200");
				
				render("/student/getState?subjectId="+getSessionAttr("subId")+"&teacherId="+getSessionAttr("teaId"));
			}
		}else{
			Map<String, String> map = new HashMap<String,String>();
			map.put("errcode", "404");
			renderJson(map);
		}
	}
}
