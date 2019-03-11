package com.wholesmart.service.impl.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feilong.core.bean.ConvertUtil;
import com.wholesmart.commom.config.Constants;
import com.wholesmart.entity.User;
import com.wholesmart.entity.UserRole;
import com.wholesmart.mapper.UserMapper;
import com.wholesmart.mapper.UserRoleMapper;
import com.wholesmart.service.api.sys.IUserService;

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

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public User selectUserRole(Map<String, Object> map) {
		User user = baseMapper.selectUserRole(map);
		return user;
	}
	
	/**
	 * 插入用户和用户角色信息
	 * */
	@Override
	public boolean insert(User entity) {
		boolean b = super.insert(entity);//插入后id会写到entity
		if(b) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(entity.getRole().getId());
			userRole.setUserId(entity.getId());
			Integer insert = userRoleMapper.insert(userRole);
			if(insert>0) return true;
		}
		return false;
		
	}
	
	@Override
	public boolean deleteById(Serializable id) {
		userRoleMapper.deleteByMap(ConvertUtil.toMap("user_id",id));//删除用户角色
		User user = baseMapper.selectById(id);
		user.setStatus(Constants.STATUSDELETE);//状态更新为删除
		user.setUpdateTime(new Date(System.currentTimeMillis()));
		Integer count = baseMapper.updateById(user);
		if(count>0) return true;
		return false;
		
	}

	@Override
	public Page<User> selectUserPage(Page<User> page, Wrapper<User> wrapper) {
		page.setRecords(baseMapper.selectUserPage(page, wrapper));
	    return page;
	}
	
}
