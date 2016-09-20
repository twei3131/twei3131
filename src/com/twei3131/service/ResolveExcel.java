package com.twei3131.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.kit.PathKit;

public class ResolveExcel {
	public final String basePath = PathKit.getWebRootPath()+File.separator+"twei3131Load"+File.separator;
	@SuppressWarnings("resource")
	public void studentRes(String filename) throws IOException{
		InputStream inStream = new FileInputStream(basePath+filename+".xlsx");
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
				for(int colIx = minCilIx;colIx < maxCilIx;colIx++){
					HSSFCell cell = hssfRow.getCell(colIx);
					if (cell == null) {
						continue;
					}
					//将数据存入数组中
				}
			}
		}
	}
}
