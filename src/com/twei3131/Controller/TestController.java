package com.twei3131.Controller;

import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Subjectinfo;
import com.twei3131.common.model.Teacher;
import com.twei3131.common.model.Tempsign;
import com.twei3131.service.Scan;
import com.twei3131.service.Students;
import com.twei3131.service.Teacheres;

public class TestController extends Controller {
	public void index(){
//		Teacheres teacheres = new Teacheres();
//		String days = String.valueOf(teacheres.getSubjectList("tea0001").get(0).getDate("startTime"));
//		System.out.println(days);
//		Date date = new Date();
//		long mesc = date.getTime();
//		long curTime = System.currentTimeMillis();
//		System.out.println("mesc="+mesc);
//		System.out.println("curTime="+curTime);
		Scan scan = new Scan();
		//System.out.println(scan.isExists("tea0001","11", "sub0001"));
		//System.out.println(scan.isReScan("tea0001"));
		//List<Record> teacheres = scan.getStudentInfoBySubjectId("tea0002", "sub0003", "gp0002");
//		List<Student> list = scan.getStudentInfoBySubjectId("tea0002", "sub0003", "gp0002");
//		scan.setStudentInfo(list, "tea0002", "sub0003");
//		System.out.println(list.get(0).getStr("studentName"));
//		String stuId = getSessionAttr("username").toString();
//		String teaId = getSessionAttr("teaId").toString();
//		String subId = getSessionAttr("subId");
//		Subjectinfo subjectinfo = Subjectinfo.dao.findFirst("select * from subjectInfo where teacherId = '" + teaId + "' and subjectId='"+subId + "'");
//		Tempsign tempsign = Tempsign.dao.findById(stuId, teaId);
//		Students students = new Students();
//		System.out.println(students.getStuStateByClassState("stuFirst", subjectinfo.getState(), stuId, teaId));
		
		render("/test/index.jsp");
	}
}
