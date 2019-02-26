package com.wholesmart.service;

import com.baomidou.mybatisplus.service.IService;
import com.wholesmart.common.web.BasePageDTO;
import com.wholesmart.common.web.PageInfo;
import com.wholesmart.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-22
 */
public interface IUserService extends IService<User> {
	
	/**
	 * 获取分页数据
	 * */
	BasePageDTO<User> getListPage(PageInfo pageInfo);
	
}
