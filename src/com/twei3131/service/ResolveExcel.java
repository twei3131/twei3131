package com.twei3131.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.twei3131.common.model.Classes;
import com.twei3131.common.model.Group;
import com.twei3131.common.model.Grouptosubject;
import com.twei3131.common.model.Instructor;
import com.twei3131.common.model.Student;
import com.twei3131.common.model.Subject;
import com.twei3131.common.model.Subjecttoteacher;
import com.twei3131.common.model.Teacher;

public class ResolveExcel {
	public final String basePath = PathKit.getWebRootPath()+File.separator+"twei3131Load"+File.separator;
	@SuppressWarnings("resource")
	public List<List<String>> resExcel(String filename) throws IOException{
		InputStream inStream = new FileInputStream(basePath+filename+".xls");
		List<List<String>> restult = new ArrayList<List<String>>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inStream);
		for(int numSheet = 0;numSheet < hssfWorkbook.getNumberOfSheets();numSheet++){
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			for(int rowNum = 1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				
				int minCilIx = hssfRow.getFirstCellNum();
				int maxCilIx = hssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				
				for(int colIx = minCilIx;colIx < maxCilIx;colIx++){
					HSSFCell cell = hssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						rowList.add(String.valueOf(cell.getNumericCellValue()));
					}else{
						rowList.add(cell.getStringCellValue());
					}
					//将数据存入数组中
				}
				restult.add(rowList);
			}
		}
		return restult;
	}
	
	public void savStu() throws IOException{
		List<List<String>> list = resExcel("demo_Student");
		List<Student> students = new ArrayList<Student>();
		for(int i = 0;i < list.size();i++){
			Student student = new Student();
			List<String> tempList = list.get(i);
			Long cou = Db.queryLong("select count(*) from student where studentId = ?",tempList.get(0));
			if (cou != 1) {
				student.setStudentId(tempList.get(0));
				student.setStudentName(tempList.get(1));
				student.setDepartmentId(Integer.valueOf(tempList.get(2)));
				student.setClassId(Integer.valueOf(tempList.get(3)));
				student.setPassword(tempList.get(4));
				students.add(student);
			}
		}
		Db.batchSave(students, students.size());
	}
	
	public void savCla() throws IOException{
		List<List<String>> list = resExcel("demo_Classes");
		List<Classes> classes = new ArrayList<Classes>();
		for(int i = 0;i < list.size();i++){
			List<String> tempList = list.get(i);
			
			Classes claes = new Classes();
			Instructor instructor = new Instructor();
			
			Long claCou = Db.queryLong("select count(*) from classes where classId = ?",tempList.get(0).replace(".0", ""));
			Long insCou = Db.queryLong("select count(*) from instructor where instructorId = ?",tempList.get(4).replace(".0", ""));

			if (claCou == 0) {
				claes.setClassId(tempList.get(0).replace(".0", ""));
				claes.setName(tempList.get(1));
				claes.setDepartmentId(tempList.get(2).replace(".0", ""));
				claes.setGradeId(Integer.valueOf(tempList.get(3).replace(".0", "")));
				claes.setInstructorId(tempList.get(4));
				classes.add(claes);
			}

			if (insCou == 0) {
				instructor.setInstructorId(tempList.get(4));
				instructor.setInstructorName(tempList.get(5));
				instructor.setPhoneNumber(tempList.get(6));
				instructor.setPassword("none");
				instructor.save();
			}
		}
		
		Db.batchSave(classes, classes.size());
	}
	
	public void savTea() throws IOException{
		List<List<String>> list = resExcel("demo_Teacher");
		List<Teacher> teacheres = new ArrayList<Teacher>();
		for(int i = 0;i < list.size();i++){
			List<String> tempList = list.get(i);
			Teacher teacher = new Teacher();
			Long cou = Db.queryLong("select count(*) from teacher where teacherId = ?",tempList.get(0));
			if (cou != 1) {
				teacher.setTeacherId(tempList.get(0));
				teacher.setTeacherName(tempList.get(1));
				teacher.setDepartmentId(tempList.get(2).replace(".0", ""));
				teacher.setPassword("none");
				teacheres.add(teacher);
			}
		}
		
		Db.batchSave(teacheres, teacheres.size());
	}
	
	public void savSub() throws IOException{
		List<List<String>> list = resExcel("demo_Subject");
		for(int i = 0;i < list.size();i++){
			List<String> tempList = list.get(i);
			
			Subject subject = new Subject();
			Subjecttoteacher subjecttoteacher = new Subjecttoteacher();
			
			Long subCou = Db.queryLong("select count(*) from subject where subjectId = ?",tempList.get(0));
			Long sttCou = Db.queryLong("select count(*) from subjecttoteacher where subjectId ='"+tempList.get(0)+"' and teacherId ='"+tempList.get(4)+"'");
			
			if (subCou != 1) {
				subject.setSubjectId(tempList.get(0));
				subject.setSubjectName(tempList.get(1));
				subject.setClassNumber(Integer.valueOf(tempList.get(2).replace(".0", "")));
				subject.setType(tempList.get(3));
				subject.save();
			}
			
			if (sttCou != 1) {
				subjecttoteacher.setSubjectId(tempList.get(0));
				subjecttoteacher.setTeacherId(tempList.get(4));
				subjecttoteacher.save();
			}
		}
	}
	
	public void savGp() throws IOException{
		List<List<String>> list = resExcel("demo_Group");
		for(int i = 0;i < list.size();i++){
			List<String> tempList = list.get(i);
			Group group = new Group();
			Grouptosubject grouptosubject = new Grouptosubject();
			
			BigDecimal bigDecimal = new BigDecimal(tempList.get(0));
			
			Long gpCou = Db.queryLong("select count(*) from group where groupId ='"+tempList.get(0)+"' and studentId='"+bigDecimal.toPlainString()+"'");
			Long gptCou = Db.queryLong("select count(*) from grouptosubject where groupId = '"+tempList.get(0)+"' and subjectId ='"+tempList.get(5));
			
			if (gpCou == 0) {
				Long sumCou = Db.queryLong("select count(*) from group");
				sumCou++;
				
				group.setId(sumCou.intValue());
				group.setGroupId(bigDecimal.toPlainString());
				group.setStudentId(tempList.get(1));
				group.setTeacherId(tempList.get(2));
				group.setGroupName(tempList.get(3));
				group.setClassId("");
				
				group.save();
			}
			
			if (gptCou == 0) {
				grouptosubject.setGroupId(bigDecimal.toPlainString());
				grouptosubject.setSubjectId(tempList.get(5));
				
				grouptosubject.save();
			}
		}
	}
	
	public void deleteFile(String filename){
		String path = basePath + filename + ".xls";
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
}
