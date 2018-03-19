package com.meals.frontend.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.meals.frontend.bean.DataExportBean;

public class ExcelReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"DataReport.xls\"");
		DataExportBean dataBean = (DataExportBean) model.get("dataExport");
		HSSFSheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, 5*256);
		sheet.setColumnWidth(1, 10*256);
		sheet.setColumnWidth(2, 5*256);
		sheet.setColumnWidth(3, 19*256);
		sheet.setColumnWidth(4, 14*256);
		sheet.setColumnWidth(5, 15*256);
		sheet.setColumnWidth(6, 15*256);
		sheet.setColumnWidth(7, 15*256);
		
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 7));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 4));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 6, 7));
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 7));
		
		CellStyle styleTitle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		styleTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setBold(true);
		font.setFontHeightInPoints((short) 20);
		styleTitle.setFont(font);
		
		CellStyle styleHeader = workbook.createCellStyle();
		font = workbook.createFont();
		styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setBold(true);
		font.setFontHeightInPoints((short) 10);
//		styleHeader.setBorderBottom(BorderStyle.NONE);;
//		styleHeader.setBorderTop(BorderStyle.NONE);
//		styleHeader.setBorderRight(BorderStyle.NONE);
//		styleHeader.setBorderLeft(BorderStyle.NONE);
		styleHeader.setFont(font);
		
		CellStyle styleContentHead = workbook.createCellStyle();
		font = workbook.createFont();
		styleContentHead.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setFontHeightInPoints((short) 10);
//		styleContentHead.setBorderBottom(BorderStyle.DASHED);;
//		styleContentHead.setBorderTop(BorderStyle.DASHED);
//		styleContentHead.setBorderRight(BorderStyle.DASHED);
//		styleContentHead.setBorderLeft(BorderStyle.DASHED);
		styleContentHead.setFont(font);
		
		CellStyle styleHeadTable = workbook.createCellStyle();
		font = workbook.createFont();
		styleHeadTable.setAlignment(HorizontalAlignment.CENTER);
		styleHeadTable.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setFontHeightInPoints((short) 10);
		font.setBold(true);
		styleHeadTable.setFont(font);
		
		CellStyle styleBodyTable = workbook.createCellStyle();
		font = workbook.createFont();
		styleBodyTable.setAlignment(HorizontalAlignment.CENTER);
		styleBodyTable.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setFontHeightInPoints((short) 10);
		styleBodyTable.setFont(font);
		
		Row rowTitle = sheet.createRow(2);
		rowTitle.setHeight((short) (rowTitle.getHeight() * 2));
		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue("CANTEEN REPORT");
		cellTitle.setCellStyle(styleTitle);
		
		Row rowInfo = sheet.createRow(4);
		rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
		Cell cellInfo = rowInfo.createCell(0);
		cellInfo.setCellValue("Received by:");
		cellInfo.setCellStyle(styleHeader);
		cellInfo = rowInfo.createCell(3);
		cellInfo.setCellValue("Canteen");
		cellInfo.setCellStyle(styleContentHead);
		
		rowInfo = sheet.createRow(5);
		rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
		cellInfo = rowInfo.createCell(0);
		cellInfo.setCellValue("From date:");
		cellInfo.setCellStyle(styleHeader);
		cellInfo = rowInfo.createCell(3);
		cellInfo.setCellValue("17/3/2017");
		cellInfo.setCellStyle(styleContentHead);
		cellInfo = rowInfo.createCell(5);
		cellInfo.setCellValue("To date:");
		cellInfo.setCellStyle(styleHeader);
		cellInfo = rowInfo.createCell(6);
		cellInfo.setCellValue("18/3/2017");
		cellInfo.setCellStyle(styleContentHead);
		
		rowInfo = sheet.createRow(7);
		rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
		cellInfo = rowInfo.createCell(0);
		cellInfo.setCellValue("Report the number of meal sets to send to Canteen");
		CellStyle style = workbook.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		font = workbook.createFont();
		font.setBold(true);
		font.setItalic(true);
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);
		cellInfo.setCellStyle(style);
	}
	
}
