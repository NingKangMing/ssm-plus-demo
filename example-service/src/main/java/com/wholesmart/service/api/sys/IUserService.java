package com.wholesmart.service.api.sys;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
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
	
	User selectUserRole(Map<String, Object> map);
	
	Page<User> selectUserPage(Page<User> page, Wrapper<User> wrapper);
	
}
