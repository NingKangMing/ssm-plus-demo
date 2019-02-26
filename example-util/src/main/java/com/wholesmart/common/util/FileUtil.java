package com.wholesmart.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *  操作文件的工具类
 * 
 */
public class FileUtil {

	/**
	 * 判断文件图片类型
	 * */
	public static String getFormatInFile(File f) {
		return getFormatName(f);
	}

	/**
	 * */
	public static void checkOrMakeDir(String filePath) {
		File aimFile = new File(filePath);
		if (!aimFile.exists() || !aimFile.isDirectory()) {
			aimFile.mkdirs();
		}
	}

	// Returns the format name of the image in the object 'o'.
	// Returns null if the format is not known.
	public static String getFormatName(Object o) {
		try {
			// Create an image input stream on the image
			ImageInputStream iis = ImageIO.createImageInputStream(o);

			// Find all image readers that recognize the image format
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				// No readers found
				return null;
			}

			// Use the first reader
			ImageReader reader = iter.next();
			// Close stream
			iis.close();

			// Return the format name
			return reader.getFormatName();
		} catch (IOException e) {
			//
		}

		// The image could not be read
		return null;
	}

	/**
	 * 功能：将指定文件里的内容读成一个Sring对象
	 * 
	 * @param file
	 * @return
	 */
	public String readFile(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream sis = new ByteArrayOutputStream();

		int c = 0;
		while ((c = fis.read()) != -1) {
			sis.write(c);
		}
		sis.flush();
		fis.close();
		return sis.toString();
	}

	/**
	 *功能：将字符串的内容写入到文件中
	 * 
	 * @param file
	 * @param content
	 */
	public void writeFile(File file, String content) throws Exception {
		FileOutputStream fos = new FileOutputStream(file);

		byte[] s = content.getBytes();
		for (int i = 0; i < s.length; i++) {
			fos.write(s[i]);
		}
		fos.flush();
		fos.close();
	}

	/**
	 * 根据文件名自动创建目录和文件
	 * */
	public static boolean createFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return false;
		}
		if (filePath.endsWith(File.separator)) {
			return false;
		}
		//判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			//如果目标文件所在的目录不存在，则创建父目录
			if (!file.getParentFile().mkdirs()) {
				return false;
			}
		}
		//创建目标文件
		try {
			if (file.createNewFile()) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 得到文件名 根据yyyyMMddHHmmss+随机数格式
	 * 
	 * @param name
	 * @return
	 */
	public static String getFileDateName() {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;
		return fileName;
	}

	/**
	 * 得到文件名 根据yyyyMMddHHmmss+随机数格式
	 * 
	 * @param name
	 * @return
	 */
	public static String getFileDateName(String name) {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;

		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}

		return fileName;
	}

	/**
	 * 返回yyyy/mm/dd/
	 * */
	public static String getFileDateTimeNowDir() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH) + 1) + "";//Gregorian and Julian第一个月是0月 +1符合中国习惯
		if (month.length() < 2) {
			month = "0" + month;
		}
		String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
		if (day.length() < 2) {
			day = "0" + day;
		}
		return year + "/" + month + "/" + day + "/";
	}


	/**
	 * 从输入流中获取byte数据
	 * */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {//把数据读到buffer里面
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}

}
