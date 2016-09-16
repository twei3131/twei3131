package com.twei3131.Controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.twei3131.common.model.Subjectinfo;
import com.twei3131.common.model.Tempsign;
import com.twei3131.service.Scan;
import com.twei3131.service.Students;

public class StudentController extends Controller {
	Students students = new Students();
	Scan scan = new Scan();
	
	public void index(){
		render("/student/student.jsp");
	}
	
	public void getState(){
		
		String teacherId = getPara("teacherId");
		String subjectId = getPara("subjectId");
		
		String sql = "select count(*) from subjectInfo "+ 
				"where teacherId = '"+teacherId+"' and subjectId = '"+subjectId+"' and state = '即将开始'";
		long count = Db.queryLong(sql);
		
		//设置session
		setSessionAttr("subId", subjectId);
		setSessionAttr("teaId", teacherId);
		
		//判断是否登录
		if (getSessionAttr("status") == null) {
			redirect("/user/login.jsp");
		}else{
			if (count == 1) {
				setAttr("state", "确认上课");
				setAttr("href", "/student/sk");
				index();
			}else{
				setAttr("state", "确认下课");
				index();
			}
		}
	}
	
	/*
	 * 学生点击上课按钮
	 */
	public void sk(){
		String stuId = getSessionAttr("username").toString();
		String teaId = getSessionAttr("teaId").toString();
		String subId = getSessionAttr("subId");
		
		Boolean flag = false;
		
		//学生扫描的合法性
		Boolean isLegal = scan.isExt(stuId, teaId);
		
		//是否重复扫描
		Boolean isReScan = scan.isRePlay(stuId, teaId);
		
		if (!isLegal) {
			setAttr("errcode", "100");
			renderJson();
		}
		
		if (isReScan) {
			setAttr("errcode", "200");
			renderJson();
		}
		
		if (isLegal && !isReScan) {
			Subjectinfo subjectinfo = Subjectinfo.dao.findFirst("select * from subjectInfo where teacherId = '" + teaId + "' and subjectId='"+subId + "'");
			Tempsign tempsign = Tempsign.dao.findById(stuId, teaId);
			tempsign.setState(students.getStuStateByClassState("stuFirst", subjectinfo.getState(), stuId, teaId));
			tempsign.setScanState("false");
			tempsign.setHostname(getRequest().getLocalName());
			flag = tempsign.update();
			if (flag == true) {
				
			}else{
				renderError(500);
			}
		}
	}
}
