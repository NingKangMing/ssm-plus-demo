package com.wholesmart.service.impl.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feilong.core.bean.ConvertUtil;
import com.wholesmart.commom.tree.TreeUtil;
import com.wholesmart.entity.Resource;
import com.wholesmart.mapper.ResourceMapper;
import com.wholesmart.mapper.RoleResourcesMapper;
import com.wholesmart.service.api.sys.IResourceService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

	@Autowired
	private RoleResourcesMapper roleResourcesMapper;
	
	@Override
	public List<Resource> findResourcesByUserId(Long userId) {
		List<Resource> list = baseMapper.findResourcesByUserId(userId);
		return list;
	}

	@Override
	public List<Resource> findResourcesMenuByUserId(Long userId) {
		List<Resource> resources = baseMapper.findResourcesMenuByUserId(userId);
		List<Resource> treeMenuList =new TreeUtil().treeMenuList(resources,Long.valueOf(0));
		return treeMenuList;
	}

	@Override
	public List<Resource> queryResourceList(Map<String, Object> map) {
		List<Resource> list = baseMapper.queryResourceList(map);
		return list;
	}


	@Override
	public void deleteRoleResource(Long resourceId) {
		roleResourcesMapper.deleteByMap(ConvertUtil.toMap("resource_id",(Object)resourceId));
		baseMapper.deleteById(resourceId);
		
	}
	
	
	@Override
	public Page<Resource> selectResourcePage(Page<Resource> page, Wrapper<Resource> wrapper) {
		page.setRecords(baseMapper.selectResourcePage(page, wrapper));
		return page;
	}
	
}
