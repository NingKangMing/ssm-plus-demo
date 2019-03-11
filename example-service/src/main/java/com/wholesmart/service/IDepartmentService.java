package com.wholesmart.service;


import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wholesmart.entity.Department;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
public interface IDepartmentService extends IService<Department> {
	Page<Department> selectDepartmentPage(Page<Department> page, Wrapper<Department> wrapper);
}
