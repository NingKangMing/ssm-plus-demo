package com.wholesmart.common.util;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

	public static void main(String[] args) {
		String a="abcd\n";
		String str = JSON.toJSONString(a);
		System.out.println(a);
		System.out.println(str);
	}
}
