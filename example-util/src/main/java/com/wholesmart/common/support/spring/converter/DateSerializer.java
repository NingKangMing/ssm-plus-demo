package com.wholesmart.common.support.spring.converter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.wholesmart.common.util.DateUtils;
/**
 * 日期自定义转换
 * @author NingKangMing
 * */
public class DateSerializer implements ObjectSerializer{

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
			throws IOException {
		SerializeWriter out = serializer.getWriter();
		if (object == null) {
			serializer.getWriter().writeNull();
			return;
		}
		String date = DateUtils.formatDate((Date)object, "yyyy-MM-dd HH:mm:ss");
		out.write("\""+date+ "\"");
		
	}

}
