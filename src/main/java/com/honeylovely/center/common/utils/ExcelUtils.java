package com.honeylovely.center.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
	
	private static final short COLUMN_WIDTH = 15;
	private static final short HEADER_COLUMN_HEIGHT = 25 * 20;
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	private static CellStyle getCellStyles(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}
	
	public static void exportExcel(String sheetname, String[] headers, List<String[]> dataStrArr, OutputStream out) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetname);
		sheet.setDefaultColumnWidth(COLUMN_WIDTH);

		HSSFRow row = sheet.createRow(0);
		//header set
		CellStyle style = getCellStyles(workbook);
		for(int i=0; i< headers.length; i++){
			row.setHeight(HEADER_COLUMN_HEIGHT);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		//content set
		for(int i=0; i<dataStrArr.size(); i++){
			int number = i+1;
			row = sheet.createRow(number);
			String[] strArr = dataStrArr.get(i);
			
			for(int j=0; j<strArr.length; j++){
				row.createCell(j).setCellValue(strArr[j]);
			}
		}
		
		workbook.write(out);
	}


	/** 保存excel */
	public String writeExecl(HSSFWorkbook workbook, String excelName, StringBuilder sb) {
		String fileOut = "";
		try {
			fileOut = SystemUtil.GetProperty("orderrpt_outputpath") + excelName + ".xls";
			FileOutputStream fos = new FileOutputStream(fileOut);
			workbook.write(fos);
			fos.flush();
			fos.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileOut;
	}

	/** 写入表内数据 */
	private void addparamToRow(HSSFWorkbook workbook, List<Sheet> createdRow, List<List<String>> parameter) {
		int r = 1;
		CellStyle createdStyle = createdStyle(workbook,false);
		for(Sheet sheet: createdRow){
			List<String> list = parameter.get(0);
			for (String string : list) {
				String[] split = string.split(",");
				for (int i = 0 ; i < split.length ; i++) {
					Row row = sheet.createRow(r);
					Cell cell = row.createCell(i);
					cell.setCellStyle(createdStyle);
					cell.setCellValue(split[i]);
				}
				r++;
			}
		}
	}

	/** 写入数据 */
	private List<Sheet> createdRow(List<String> cellValue, List<Sheet> sheets, CellStyle cellStyle) {
		List<Sheet> list = new ArrayList<>();
		for (Sheet sheet : sheets) {
			Row row = sheet.createRow(0); // 创建行
			String[] cells = cellValue.get(0).split(",");
			for(int i = 0 ; i < cells.length ; i++){
				Cell cell = row.createCell(i);// 创建单元格
				cell.setCellStyle(cellStyle);// 宋体，11，左右居中
				cell.setCellValue(cells[i]);
				sheet.setColumnWidth(i, 20 * 256);
			}
			cellValue.remove(0);
			list.add(sheet);
		}
		return list;
	}

	/** 创建工作单 */
	public List<Sheet> createdSheet(List<String> sheetList, HSSFWorkbook workbook){
		if(sheetList == null || sheetList.size() == 0){
			return null;
		}
		List<Sheet> list = new ArrayList<>();
		for (String string : sheetList) {
			Sheet sheet = workbook.createSheet(string);// 表
			list.add(sheet);
		}
		return list;
	}

	/** 创建样式 */
	public CellStyle createdStyle(HSSFWorkbook workbook, Boolean bold){
		CellStyle cellStyle = workbook.createCellStyle();// 样式
		Font font = workbook.createFont();// 字体
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);
		if(bold){
			font.setBold(true);
		}
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return cellStyle;
	}

	/** 返回JSonArray
	 *  返回的数据必须用result命名
	 * */
	public JSONArray returnJsonArray(CloseableHttpResponse post) {
		HttpEntity entity = post.getEntity();
		String dataStr = "";
		try {
			if(entity != null){
				dataStr = EntityUtils.toString(entity);
				post.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject fromObject = JSONObject.fromObject(dataStr);
		logger.info("fromObject" + fromObject);
		JSONArray jsonArray = fromObject.getJSONArray("result");
		return jsonArray;
	}

	/** 创建标题行 */
	public Row createdCell(Sheet sheet, CellStyle cellStyle, List<String> cellName){
		Row row = sheet.createRow(0); // 创建行
		for(int i = 0 ; i < cellName.size() ; i++){
			Cell cell = row.createCell(i);// 创建单元格
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellName.get(i));
			sheet.setColumnWidth(i, 20 * 256);
		}
		return row;
	}

	/**
	 *  给单元格写入数据
	 * @param dataList 存放数据的集合
	 * @param dataName 取数据使用的名字集合
	 */
	public void writeToRow(HSSFWorkbook workbook, Row row,
						   Sheet sheet, List<Map<String, Object>> dataList, List<String> dataName){
		int r = 1;
		CellStyle cellStyle = createdStyle(workbook, false);
		for (Map<String, Object> data : dataList) {
			row = sheet.createRow(r);
			for(int i = 0 ; i < dataName.size() ; i++){
				Cell cell2 = row.createCell(i);
				cell2.setCellStyle(cellStyle);
				String param = dataName.get(i);
				cell2.setCellValue(data.get(param)+"");
			}
			r++;
		}
	}

	/** 创建样式07版 */
	public CellStyle createdStyle(XSSFWorkbook workbook, Boolean bold){
		CellStyle cellStyle = workbook.createCellStyle();// 样式
		Font font = workbook.createFont();// 字体
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);
		if(bold){
			font.setBold(true);
		}
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return cellStyle;
	}


	/**
	 *  给单元格写入数据(07版)
	 * @param dataList 存放数据的集合
	 * @param dataName 取数据使用的名字集合
	 */
	public void writeToRow(XSSFWorkbook workbook, Row row,
						   Sheet sheet, List<Map<String, Object>> dataList, List<String> dataName){
		int r = 1;
		CellStyle cellStyle = createdStyle(workbook, false);
		for (Map<String, Object> data : dataList) {
			row = sheet.createRow(r);
			for(int i = 0 ; i < dataName.size() ; i++){
				Cell cell2 = row.createCell(i);
				cell2.setCellStyle(cellStyle);
				String param = dataName.get(i);
				cell2.setCellValue(data.get(param)+"");
			}
			r++;
		}
	}

	/** 保存excel */
	public String writeExecl(XSSFWorkbook workbook, String excelName, StringBuilder sb) {
		String fileOut = "";
		try {
			fileOut = SystemUtil.GetProperty("orderrpt_outputpath") + excelName + ".xlsx";
			FileOutputStream fos = new FileOutputStream(fileOut);
			workbook.write(fos);
			fos.flush();
			fos.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileOut;
	}

	//设置中文文件名
	public String setFileName(String fileName,HttpServletRequest request) throws Exception{
		final String userAgent = request.getHeader("USER-AGENT");
		String finalFileName = null;
		if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
			finalFileName = URLEncoder.encode(fileName,"UTF8");
		}else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
			finalFileName = new String(fileName.getBytes(), "ISO8859-1");
		}else{
			finalFileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
		}
		return finalFileName;
	}

//	public static void main(String[] args) {
//		String[] headers = new String[]{"优惠码", "优惠码状态", "兑换用户号码", "兑换时间"};
//		List<String[]> strList = new ArrayList<>();
//		String[] strArr = new String[headers.length];
//		strArr[0]="0120120121";
//		strArr[1]="已兑换";
//		strArr[2]="15814888075";
//		strArr[3]=DateHelper.formatDatetime(new Date());
//		strList.add(strArr);
//
//		FileOutputStream fos;
//		try {
//			File file =new File("/temp/test");
//			if(!file.exists()){
//				file.mkdirs();
//			}
//			fos = new FileOutputStream(new File(file.getAbsolutePath()+"/a.xlsx"));
//			ExcelUtils.exportExcel("Coupon Code", headers, strList, fos);
//			System.out.println(file.getAbsolutePath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
