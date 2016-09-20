package com.twei3131.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.twei3131.common.model.Student;

public class ResolveExcel {
	public final String basePath = PathKit.getWebRootPath()+File.separator+"twei3131Load"+File.separator;
	@SuppressWarnings("resource")
	public List<List<String>> resExcel(String filename) throws IOException{
		InputStream inStream = new FileInputStream(basePath+filename+".xlsx");
		List<List<String>> restult = new ArrayList<List<String>>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inStream);
		for(int numSheet = 0;numSheet < hssfWorkbook.getNumberOfSheets();numSheet++){
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			for(int rowNum = 1;rowNum<hssfSheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				
				int minCilIx = hssfRow.getFirstCellNum();
				int maxCilIx = hssfRow.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				
				for(int colIx = minCilIx;colIx < maxCilIx;colIx++){
					HSSFCell cell = hssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}
					//将数据存入数组中
					rowList.add(cell.getStringCellValue());
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
			student.setStudentId(tempList.get(0));
			student.setStudentName(tempList.get(1));
			student.setDepartmentId(Integer.valueOf(tempList.get(3)));
			student.setClassId(Integer.valueOf(tempList.get(4)));
			student.setPassword(tempList.get(5));
			students.add(student);
		}
		Db.batchSave(students, list.size());
	}
}
