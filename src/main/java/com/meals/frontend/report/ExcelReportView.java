package com.meals.frontend.report;

import java.io.FileInputStream;
import java.io.InputStream;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.meals.frontend.bean.DataExportBean;
import com.meals.frontend.bean.MealsOrderBean;

public class ExcelReportView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"DataReport.xls\"");
		DataExportBean dataBean = (DataExportBean) model.get("dataExport");
		HSSFSheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, 7*256);
		sheet.setColumnWidth(1, 14*256);
		sheet.setColumnWidth(2, 17*256);
		sheet.setColumnWidth(3, 14*256);
		sheet.setColumnWidth(4, 28*256);
		sheet.setColumnWidth(5, 18*256);
		sheet.setColumnWidth(6, 14*256);
		CellStyle style = null;
		HSSFFont font = null;

		InputStream inputStream = new FileInputStream("C:/Users/CONG/Desktop/add.png");

		byte[] imageBytes = IOUtils.toByteArray(inputStream);

		int pictureureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

		inputStream.close();

		CreationHelper helper = workbook.getCreationHelper();

		Drawing drawing = sheet.createDrawingPatriarch();

		ClientAnchor anchor = helper.createClientAnchor();

		anchor.setCol1(1);
		anchor.setRow1(2);

		drawing.createPicture(anchor, pictureureIdx);
		
		Row rowTitle = sheet.createRow(1);
		rowTitle.setHeight((short) (rowTitle.getHeight() * 2));
		style = workbook.createCellStyle();
		font = workbook.createFont();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		Cell cellTitle = rowTitle.createCell(0);
		cellTitle.setCellValue("Báo Cáo Tình hình ăn của nhân viên");
		cellTitle.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));

		Row rowTime = sheet.createRow(3);
		Cell cellTime = rowTime.createCell(2);
		cellTime.setCellValue("Từ ngày: " + dataBean.getFromDate());
		cellTime = rowTime.createCell(3);
		cellTime.setCellValue("Đến ngày: " + dataBean.getToDate());

		Row rowHeader = sheet.createRow(6);
		Cell cellHeader = rowHeader.createCell(0);
		cellHeader.setCellValue("No.");
		cellHeader = rowHeader.createCell(1);
		cellHeader.setCellValue("Employee code");
		cellHeader = rowHeader.createCell(2);
		cellHeader.setCellValue("Full name");
		cellHeader = rowHeader.createCell(3);
		cellHeader.setCellValue("Food type (1: Vietnamese/2: Japanese/3: Halah)");
		cellHeader = rowHeader.createCell(4);
		cellHeader.setCellValue("Eating location");
		cellHeader = rowHeader.createCell(5);
		cellHeader.setCellValue("Meal type (1: Lunch/2: dinner/3: supper,breakfast)");
		cellHeader = rowHeader.createCell(6);
		cellHeader.setCellValue("Remark");

		Map<Integer, String> mapDepartment = dataBean.getMapDepartMent();
		Map<Integer, String> mapLocation = dataBean.getMapLocation();
		Map<Integer, List<MealsOrderBean>> mapLstByDepart = dataBean.getMapDataByDepart();

		if (mapLstByDepart != null && !mapLstByDepart.isEmpty()) {
			int num = 8;
			for (Integer departId : mapLstByDepart.keySet()) {
				sheet.addMergedRegion(new CellRangeAddress(num, num, 0, 6));
				Row rowDepart = sheet.createRow(num);
				Cell cellDepart = rowDepart.createCell(0);
				cellDepart.setCellValue("Phòng : " + mapDepartment.get(departId));
				List<MealsOrderBean> lst = mapLstByDepart.get(departId);
				if (lst != null) {
					for (int i = 0; i < lst.size(); i++) {
						int k = num + i + 1;
						MealsOrderBean bean = lst.get(i);
						Row aRow = sheet.createRow(k);
						Cell cell = aRow.createCell(0);
						cell.setCellValue(i + 1);
						CellUtil.setAlignment(cell, HorizontalAlignment.LEFT);
						cell = aRow.createCell(1);
						cell.setCellValue(bean.getStaffId());
						cell = aRow.createCell(2);
						cell.setCellValue(bean.getStaffName());
						cell = aRow.createCell(3);
						cell.setCellValue(bean.getMealId());
						cell = aRow.createCell(4);
						cell.setCellValue(mapLocation.get(bean.getLocationId()));
						cell = aRow.createCell(5);
						cell.setCellValue(bean.getMealTimeId());
						cell = aRow.createCell(6);
						cell.setCellValue("");
					}
					num = num + lst.size() + 1;
				}
			}
		}

	}

}
