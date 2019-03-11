package com.wholesmart.common.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {
	
	public static Map<String, String> dataTitlle=new HashMap<String, String>();
	
/**
 * execl模板导出
 * @param fileName 文件名
 * @param Title 文件第一行列标题集合
 * @param response
 * @return
 */
	public final static String exportTempletExcel(String fileName, String[] Title,HttpServletResponse response) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename="  + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行

			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			// sheet.mergeCells(0, 0, colWidth, 0);
			// sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.setColumnView(i, Title[i].toString().length()+25);//根据内容自动设置列宽  
				sheet.addCell(new Label(i, 0, Title[i], wcf_center));
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
	
	
	public final static String exportExcel(String fileName,List<?> listContent,HttpServletResponse response,Map<String, String> dataTitlle, String[] title) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行

			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			// sheet.mergeCells(0, 0, colWidth, 0);
			// sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
				setSheetData(sheet, listContent, wcf_center, wcf_left,dataTitlle,title);
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 默认导出所有
	 * @param sheet
	 * @param list
	 * @param title
	 * @param wcf_center
	 * @param wcf_left
	 * @throws Exception
	 */
	public static void setSheetDataAll(WritableSheet sheet,List<?> list,String[] title,WritableCellFormat wcf_center,WritableCellFormat wcf_left) throws Exception{
		for (int i = 0; i < title.length; i++) {
			sheet.setColumnView(i, title[i].toString().length()+7);//根据内容自动设置列宽  
			sheet.addCell(new Label(i, 0, title[i], wcf_center));
		}
		Field[] fields = null;
		int i = 1;
		for (Object obj : list) {
			fields = obj.getClass().getDeclaredFields();
			int j = 0;
			for (Field v : fields) {
				v.setAccessible(true);
				Object va = v.get(obj);
				if (va == null) {
					va = "";
				}
				sheet.addCell(new Label(j, i, va.toString(), wcf_left));
				j++;
			}
			i++;
		}
	}
	
	public static void setSheetData(WritableSheet sheet,List<?> list,WritableCellFormat wcf_center,WritableCellFormat wcf_left,Map<String, String> dataTitlle,String[] title) throws Exception{
		Map<String, Integer> showData=new HashMap<String, Integer>();	
		for (int i = 0; i < title.length; i++) {
		    sheet.setColumnView(i, title[i].toString().length()+7);//根据内容自动设置列宽  
			sheet.addCell(new Label(i, 0, title[i], wcf_center));
			showData.put(dataTitlle.get(title[i]), i);
		}
		Field[] fields = null;
		int i = 1;
		for (Object obj : list) {
			fields = obj.getClass().getDeclaredFields();
			int j = 0;
			for (Field v : fields) {
				v.setAccessible(true);
				if (showData.get(v.getName())!=null) {
					Object va = v.get(obj);
					if(v.getType().toString().equals("class java.util.Date")){
						Date dte = (Date) va;
						va = DateUtils.convertDateToStr(dte, DateUtils.DEFAULT_LONG_DATE_FORMAT);
					}
					if (va == null) {
						va = "";
					}
					sheet.addCell(new Label(showData.get(v.getName()), i, va.toString(), wcf_left));
					j++;
				}
			}
			i++;
		}
	}

	
}