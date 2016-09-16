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
	 * 注册接口
	 */
	public void register(){
		String username = getPara("username");
		String password = getPara("password");
		
		//如果密码或用户名为空或密码为初始密码none
		if (username == "" || password == "" || password.equals("none")) {
			setAttr("errcode", "err");
			renderJson();
		}
		
		//判断身份
		String indentity = user.judgeIndentityByUserId(username);
		
		//判断是否是首次登录
		boolean isFirstLoginFlag = user.isFirstLoginByUserId(username);
		
		//用户不存在
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
	 * 登录接口
	 */
	public void login(){
		String username = getPara("username");
		String password = getPara("password");
		String vcode = getPara("vcode");

		//判断用户的合法性
		Boolean isUser = user.isUserByUserId(username, password);

		if (isUser) {
			if (user.judgeIndentityByUserId(username) == "teacher") {
				//返回用户名和密码
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
