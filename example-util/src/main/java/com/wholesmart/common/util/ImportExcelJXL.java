package com.wholesmart.common.util;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.web.multipart.MultipartFile;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
 

 

public class ImportExcelJXL {
	
    
    public static final String READER_BASE_ROOT = "io";
    public static final String START_INDEX = "startRow";    
    public static final String TITLES = "titles";
 
    
    public static Map<String, String> dataTitlle=new HashMap<String, String>();
    
    
    
    /**
     * 将excel表数据添加转换为实体集合
     * @param <T>
     * @param zmFile
     */
    public static  <T> List<T> importExcelData(MultipartFile bmFile,Class<T> clz,Map<String, String> title,String[] colums) throws Exception{
        dataTitlle=title;
	    List<T> list = transToObject(bmFile.getInputStream(),clz);
	    return list;
    }

    public static Workbook loadWorkBookByPath(InputStream in) {  
    	 Workbook wb =null; 
        try {  
               wb = Workbook.getWorkbook(in);
        } catch (Exception e) {  
             e.printStackTrace();
        } 
        return wb;
    }  
    

    @SuppressWarnings("unchecked")
    public static <T> List<T> transToObject( InputStream is,Class<T> clz) throws Exception{
        try {
        	
            Workbook wb = loadWorkBookByPath(is);
            Sheet sheet = wb.getSheet(0);
            Map<String,Object> map = readExcelTitle(sheet);
            int start = Integer.parseInt(map.get(START_INDEX).toString());
            List<String> titles = (List<String>)map.get(TITLES);
            int last = sheet.getRows();
            List<T> lst = new ArrayList<T>();
            for(int i = (start+1);i<last;i++){
                List<String> values = getRowValues(sheet, i);
                T t = transToObject(titles, values, clz);
                lst.add(t);
            }
            return lst;
        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        }finally{
            if(is!=null){
                is.close();
            }
        }
    }
     
    private static <T> T transToObject(List<String> titles,List<String> values,Class<T> clz) throws Exception{
        T t = clz.newInstance();
        int size = titles.size();
        for(int i = 0;i<size;i++){
            if(values.size()<=i){
                break;
            }
            String title = titles.get(i);
            String value = values.get(i);
            setValue(t,clz,title,value);
        }
        return t;
    }
     
    private static void setValue(Object o,Class<?>clz,String title,String value) throws Exception{
        Method m = null;
        if(title.indexOf(".")!=-1){
            String[] titleSplit = title.split("\\.");
            m = getSetMethod(titleSplit.toString(), clz);
        }else{
            m = getSetMethod(title, clz);  //..........
        }
        if(m == null){
            return;
        }
        setValue(o, m, title,value);
    }
     
    private static void setValue(Object o,Method method,String title,String value) throws Exception{
        Class<?>[] clazz = method.getParameterTypes();  
        String type = clazz[0].getName();
        if(StringUtils.isNullOrBlank(value)){
            return;
        }
        if("java.lang.String".equals(type)){
            method.invoke(o, value);
        }else if("java.util.Date".equals(type)){
            Date d = null;
            if(value.length()>10){
                d = DateUtils.convertStrToDate(value,DateUtils.TIME_FORMAT_TIMES_SHOT);
            }else{
                d =  DateUtils.convertStrToDate(value,DateUtils.DEFAULT_SHORT_DATE_FORMAT);
            }
            method.invoke(o, d);
        }else if("java.sql.Timestamp".equals(type)){
            Timestamp d = Timestamp.valueOf(value);
            method.invoke(o, d);
        }else if("java.lang.Integer".equals(type)||"int".equals(type)){
            Integer i =Integer.valueOf(value);
            method.invoke(o, i);
        }else if("java.lang.Long".equals(type)||"long".equals(type)){
            Long l =Long.valueOf(value);
            method.invoke(o, l);
        }else if("java.lang.Short".equals(type)||"short".equals(type)){
            Short s =Short.valueOf(value);
            method.invoke(o, s);
        }else if("java.lang.Boolean".equals(type)||"boolean".equals(type)){
            Boolean b = Boolean.valueOf(value);
            method.invoke(o, b);
        }else if("java.math.BigDecimal".equals(type)){
            BigDecimal b = BigDecimal.valueOf(Double.valueOf(value));
            method.invoke(o, b);
        }else if("java.lang.Double".equals(type)||"double".equals(type)){
        	 double b = Double.valueOf(value);
             method.invoke(o, b);
        }else{
            Method getMethodName = o.getClass().getMethod(method.getName().replace("set", "get"));
            Object returnValue = getMethodName.invoke(o);
            Class<?> returnClass = Class.forName(type);
            if(returnValue == null){
                returnValue = returnClass.newInstance();
                method.invoke(o, returnValue);
            }
            title = title.substring(title.indexOf(".")+1);
            setValue(returnValue, returnClass, title, value);
        }
    }
     
    private static Method getSetMethod(String propName,Class<?> clz){
        Method[]methods = clz.getMethods();
        for(Method method : methods){
        	String  col = dataTitlle.get(propName);
        	if (col==null  || col.equals("")) {
				continue;
			}
            if(method.getName().toLowerCase().equals("set"+col.toLowerCase())){
                Class<?>[] clazz = method.getParameterTypes();
                if(clazz.length == 1){
                    return method;
                }
            }
        }
        return null;
    }  
    
    private static Map<String,Object> readExcelTitle(Sheet sheet) throws Exception{
        int m = 0;
        Map<String,Object> map = new HashMap<String,Object>();
        Cell cell = sheet.getRow(m)[0];
        if(cell!=null){
            String cellValue = cell.getContents();
            if(!cellValue.startsWith("#")){
                List<String> lstStr = getRowValues(sheet, m);
                map.put(START_INDEX, m);
                map.put(TITLES, lstStr);
                return map;
            }
        }
        throw new Exception("Excel格式不正确");
    }
     
    /**
     * 获得行数据
     * @param sheet
     * @param rowIndex
     * @return
     */
    private static List<String> getRowValues(Sheet sheet,int rowIndex){
    	Cell cell = null;
        List<String> lstStr = new ArrayList<String>();
        if(rowIndex==0){
        	//行数(表头的目录不需要，从1开始)
            for(int i=0; i<1; i++){
    	         for(int j=0; j<sheet.getColumns(); j++){
    	          //获取第i行，第j列的值
    	          cell = sheet.getCell(j,i);    
    	          lstStr.add(cell.getContents());
    	         }
            }
        }else{
        	 //行数(表头的目录不需要，从1开始)
            for(int i=rowIndex; i<sheet.getRows(); i++){
    	         //列数
    	         for(int j=0; j<sheet.getColumns(); j++){
    	          //获取第i行，第j列的值
    	          cell = sheet.getCell(j,i); 
    	          if(cell.getType().equals(CellType.DATE)){
    	        	  
    	        	  //excel日期格式转换
    	        	  DateCell dateCell = (DateCell) cell;
    	              Date date = dateCell.getDate();
    	              
    	              TimeZone gmt = TimeZone.getTimeZone("GMT");  
    	              DateFormat dateFormat = new SimpleDateFormat(DateUtils.TIME_FORMAT_TIMES_SHOT,Locale.getDefault());  
    	              dateFormat.setTimeZone(gmt);  
    	              String dateValue = dateFormat.format(date);  
//    	              String dateValue = DateUtils.convertDateToStr(date, DateUtils.TIME_FORMAT_TIMES_SHOT);
    	        	  
    	              lstStr.add(dateValue);
    	          }else{
    	        	  lstStr.add(cell.getContents());
    	          }
    	         
    	         }
            }
        }
       
        return lstStr;
    }
    
    
  
    
	
}