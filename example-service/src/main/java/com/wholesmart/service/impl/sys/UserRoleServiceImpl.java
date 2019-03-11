package com.wholesmart.service.impl.sys;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wholesmart.entity.UserRole;
import com.wholesmart.mapper.UserRoleMapper;
import com.wholesmart.service.api.sys.IUserRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
	
}
