package com.wholesmart.common.web;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据传输类类
 * @author KangMing.Ning
 * */
public class BasePageDTO<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 分页信息
	 */
	private PageInfo pageInfo;
	
	/**
	 * 分页实体列表
	 */
	private List<T> list;
	
	public BasePageDTO(PageInfo pageInfo, List<T> list) {
		super();
		this.pageInfo = pageInfo;
		this.list = list;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
