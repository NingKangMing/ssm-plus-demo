package com.wholesmart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wholesmart.common.web.BasePageDTO;
import com.wholesmart.common.web.PageInfo;
import com.wholesmart.entity.User;
import com.wholesmart.mapper.UserMapper;
import com.wholesmart.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	
	@Override
	public BasePageDTO<User> getListPage(PageInfo pageInfo) {
		Page<User> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
		List<User> list = baseMapper.selectPage(page, null);
		pageInfo.setTotal(page.getTotal());
		BasePageDTO<User> result = new BasePageDTO<User>(pageInfo, list);
		return result;
	}
	
}
