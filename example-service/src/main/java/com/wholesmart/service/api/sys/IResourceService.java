package com.wholesmart.service.api.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wholesmart.entity.Resource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
public interface IResourceService extends IService<Resource> {

	List<Resource> findResourcesByUserId(Long userId);

	List<Resource> findResourcesMenuByUserId(Long userId);

	List<Resource> queryResourceList(Map<String, Object> map);

	Page<Resource> selectResourcePage(Page<Resource> page, Wrapper<Resource> wrapper);

	void deleteRoleResource(Long resourceId);

}
