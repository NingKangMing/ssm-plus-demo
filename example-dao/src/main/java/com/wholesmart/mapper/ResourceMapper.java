package com.wholesmart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wholesmart.entity.Resource;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
public interface ResourceMapper extends BaseMapper<Resource> {
	
	List<Resource> findResourcesByUserId(@Param(value="userId") Long userId);
	
	List<Resource> findResourcesMenuByUserId(@Param(value="userId") Long userId);
	
	List<Resource> queryResourceList(Map<String, Object> parameter);
	
	List<Resource> selectResourcePage(Pagination page,@Param("ew") Wrapper<Resource> wrapper);

}