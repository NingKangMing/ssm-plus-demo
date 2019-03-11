package com.wholesmart.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wholesmart.entity.Log;
import com.wholesmart.mapper.LogMapper;
import com.wholesmart.service.ILogService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
	
}
