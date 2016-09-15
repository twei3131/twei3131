package com.twei3131.Controller;

import com.jfinal.core.Controller;

public class StudentController extends Controller {
	public void index(){
		render("/student/student.jsp");
	}
}
