package com.wholesmart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wholesmart.entity.Department;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-24
 */
public interface DepartmentMapper extends BaseMapper<Department> {
	
	List<Department> selectDepartmentPage(RowBounds rowBounds, @Param("ew") Wrapper<Department> wrapper);

}