package com.wholesmart.common.support.spring.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

public class LongIdSerializer implements ObjectSerializer{
	
	@Override
	public void write(JSONSerializer serializer, Object object, Object arg2, Type arg3, int arg4) throws IOException {
		SerializeWriter out = serializer.getWriter();
		if (object == null) {
			serializer.getWriter().writeNull();
			return;
		}
		out.write("\"" + ((Long)object).toString() + "\"");
	}

}

