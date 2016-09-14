package com.twei3131.Controller;

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
		
	}
}
