package com.wholesmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wholesmart.entity.Department;
import com.wholesmart.mapper.DepartmentMapper;
import com.wholesmart.service.IDepartmentService;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
	@Autowired
	private DepartmentMapper DepartmentMapper;
	
	@Override
	public Page<Department> selectDepartmentPage(Page<Department> page, Wrapper<Department> wrapper) {
		page.setRecords(baseMapper.selectDepartmentPage(page, wrapper));
		return page;
	}
	
}
