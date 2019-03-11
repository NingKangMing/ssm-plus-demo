package com.wholesmart.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wholesmart.entity.LoginLog;
import com.wholesmart.mapper.LoginLogMapper;
import com.wholesmart.service.ILoginLogService;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-21
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {
	
}
