package com.wholesmart.common.web;

/**
 * 通用常量接口
 * @author KangMing.Ning
 * @since 2017-12-01
 * 
 */
public interface IConstants {
	
	public interface Result {
		JSONMessage InternalException = new JSONMessage(1020101,
				"接口内部异常");
		JSONMessage ParamsAuthFail = new JSONMessage(1010101,
				"请求参数验证失败，缺少必填参数或参数错误");
	}


}
