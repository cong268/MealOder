package com.meals.frontend.report;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.meals.frontend.bean.DataExportBean;
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.LocationBean;
import com.meals.frontend.bean.MealTimeBean;
import com.meals.frontend.bean.MealTypeBean;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.until.ConstanKey;
import com.meals.frontend.until.FunctionUtils;

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
		
		// FileInputStream obtains input bytes from the image file
		InputStream inputStream = new FileInputStream("C:/Users/nvcong/Desktop/nsrp.png");
		// Get the contents of an InputStream as a byte[].
		byte[] bytes = IOUtils.toByteArray(inputStream);
		// Adds a picture to the workbook
		int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		// close the input stream
		inputStream.close();
		// Returns an object that handles instantiating concrete classes
		CreationHelper helper = workbook.getCreationHelper();
		// Creates the top-level drawing patriarch.
		Drawing drawing = sheet.createDrawingPatriarch();
		// Create an anchor that is attached to the worksheet
		ClientAnchor anchor = helper.createClientAnchor();
		// set top-left corner for the image
		anchor.setCol1(0);
		anchor.setRow1(0);
		// Creates a picture
		Picture pict = drawing.createPicture(anchor, pictureIdx);
		// Reset the image to the original size
		pict.resize();
		
		Row rowImg = sheet.createRow(0);
		rowImg.setHeight((short) (rowImg.getHeight() * 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		
		Row rowTitle = sheet.createRow(2);
		rowTitle.setHeight((short) (rowTitle.getHeight() * 2));
		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue("CANTEEN REPORT");
		cellTitle.setCellStyle(styleTitle);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
		
		Row rowInfo = sheet.createRow(4);
		rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
		Cell cellInfo = rowInfo.createCell(0);
		cellInfo.setCellValue("Received by:");
		cellInfo.setCellStyle(styleHeader);
		cellInfo = rowInfo.createCell(3);
		cellInfo.setCellValue("Canteen");
		cellInfo.setCellStyle(styleContentHead);
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 3, 7));
		
		rowInfo = sheet.createRow(5);
		rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
		cellInfo = rowInfo.createCell(0);
		cellInfo.setCellValue("From date:");
		cellInfo.setCellStyle(styleHeader);
		cellInfo = rowInfo.createCell(3);
		cellInfo.setCellValue(dataBean.getFromDate());
		cellInfo.setCellStyle(styleContentHead);
		cellInfo = rowInfo.createCell(5);
		cellInfo.setCellValue("To date:");
		cellInfo.setCellStyle(styleHeader);
		cellInfo = rowInfo.createCell(6);
		cellInfo.setCellValue(dataBean.getToDate());
		cellInfo.setCellStyle(styleContentHead);
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, 4));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 6, 7));
		
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
		sheet.addMergedRegion(new CellRangeAddress(6, 7, 0, 7));
		
		rowInfo = sheet.createRow(8);
		rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
		cellInfo = rowInfo.createCell(0);
		cellInfo.setCellValue("STT");
		cellInfo.setCellStyle(styleHeadTable);
		cellInfo = rowInfo.createCell(1);
		cellInfo.setCellValue("Date");
		cellInfo.setCellStyle(styleHeadTable);
		cellInfo = rowInfo.createCell(2);
		cellInfo.setCellValue("Team/Section/Division");
		cellInfo.setCellStyle(styleHeadTable);
		sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 3));
		cellInfo = rowInfo.createCell(4);
		cellInfo.setCellValue("Food type");
		cellInfo.setCellStyle(styleHeadTable);
		cellInfo = rowInfo.createCell(5);
		cellInfo.setCellValue("Time");
		cellInfo.setCellStyle(styleHeadTable);
		cellInfo = rowInfo.createCell(6);
		cellInfo.setCellValue("Serving location");
		cellInfo.setCellStyle(styleHeadTable);
		cellInfo = rowInfo.createCell(7);
		cellInfo.setCellValue("Quantity");
		cellInfo.setCellStyle(styleHeadTable);
		Map<Date, List<MealsOrderBean>> mapData = dataBean.getMapDataByDate();
		List<MealTimeBean> lstMealTime = dataBean.getLstMealTime();
		List<MealTypeBean> lstMealType = dataBean.getLstMealType();
		List<LocationBean> lstLocation = dataBean.getLstLocation();
		List<DepartmentBean> lstDepartMent = dataBean.getLstDepartMent();
		int total = 0;
		int num =0;
		if(mapData != null && !mapData.isEmpty()){
			for (Date date : mapData.keySet()){
				String dateStr = FunctionUtils.convertDateStringByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
				List<MealsOrderBean> lstData = mapData.get(date);
				if (lstData != null && !lstData.isEmpty()) {
					for (DepartmentBean department : lstDepartMent) {
						for (MealTypeBean typeBean : lstMealType){
							for (MealTimeBean timeBean : lstMealTime){
								for (LocationBean locationBean : lstLocation){
									int quantity = 0;
									for (MealsOrderBean bean : lstData){
										if (bean.getDepartmentId().equals(department.getDeptId())
												&& bean.getMealId().equals(typeBean.getMealId())
												&& bean.getMealTimeId().equals(timeBean.getMealTimeId())
												&& bean.getLocationId().equals(locationBean.getLocationId())){
											quantity ++;
										}
									}
									if (quantity != 0){
										rowInfo = sheet.createRow(9 + num);
										rowInfo.setHeight((short) (rowInfo.getHeight() * 2));
										cellInfo = rowInfo.createCell(0);
										cellInfo.setCellValue(num);
										cellInfo.setCellStyle(styleBodyTable);
										cellInfo = rowInfo.createCell(1);
										cellInfo.setCellValue(dateStr);
										cellInfo.setCellStyle(styleBodyTable);
										cellInfo = rowInfo.createCell(2);
										cellInfo.setCellValue(department.getDeptName());
										cellInfo.setCellStyle(styleBodyTable);
										sheet.addMergedRegion(new CellRangeAddress(9 + num,9 + num, 2, 3));
										cellInfo = rowInfo.createCell(4);
										cellInfo.setCellValue(typeBean.getMealName());
										cellInfo.setCellStyle(styleBodyTable);
										cellInfo = rowInfo.createCell(5);
										cellInfo.setCellValue(timeBean.getMealTimeName());
										cellInfo.setCellStyle(styleBodyTable);
										cellInfo = rowInfo.createCell(6);
										cellInfo.setCellValue(locationBean.getLocationName());
										cellInfo.setCellStyle(styleBodyTable);
										cellInfo = rowInfo.createCell(7);
										cellInfo.setCellValue(quantity);
										cellInfo.setCellStyle(styleBodyTable);
										num++;
									}
									total += quantity;
								}
							}
						}
					}

				}
			}
			
		}
		Row rowTotal = sheet.createRow(9 + num);
		rowTotal.setHeight((short) (rowInfo.getHeight() * 2));
		cellInfo = rowTotal.createCell(0);
		cellInfo = rowTotal.createCell(1);
		cellInfo = rowTotal.createCell(2);
		sheet.addMergedRegion(new CellRangeAddress(9 + num,9 + num, 2, 3));
		cellInfo = rowTotal.createCell(4);
		cellInfo = rowTotal.createCell(5);
		cellInfo = rowTotal.createCell(6);
		cellInfo.setCellValue("TOTAL:");
		cellInfo = rowTotal.createCell(7);
	}
	
}
