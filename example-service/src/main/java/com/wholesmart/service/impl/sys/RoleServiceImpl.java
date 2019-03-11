package com.wholesmart.service.impl.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feilong.core.bean.ConvertUtil;
import com.feilong.core.util.CollectionsUtil;
import com.wholesmart.commom.config.Constants;
import com.wholesmart.entity.Role;
import com.wholesmart.entity.RoleResources;
import com.wholesmart.mapper.RoleMapper;
import com.wholesmart.mapper.RoleResourcesMapper;
import com.wholesmart.service.api.sys.IRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private RoleResourcesMapper roleResourceMappper;

	public boolean deleteById(Serializable id) {
		//1,删除角色资源中间表数据
		roleResourceMappper.deleteByMap(ConvertUtil.toMap("role_id",id));
		//2,设置角色的状态为删除
		Role role = baseMapper.selectById(id);
		role.setStatus(Constants.STATUSDELETE);
		role.setUpdateTime(new Date(System.currentTimeMillis()));
		Integer count = baseMapper.updateById(role);

		return true;

	}

	@Override
	public boolean savePermission(Long roleId, List<Long> resourceIds) {
		try {

			List<RoleResources> roleResources = roleResourceMappper.selectByMap(ConvertUtil.toMap("role_id",(Object)roleId));
			List<RoleResources> newRes = new ArrayList<>();
			for(Long sid : resourceIds){
				RoleResources rr = new RoleResources();
				rr.setRoleId(roleId);
				rr.setResourceId(sid);
				newRes.add(rr);
			}

			//查找出需要删除的
			List<RoleResources> removeRes = CollectionsUtil.selectRejected(roleResources,"resourceId",resourceIds);
			//查找需要新增的
			List<RoleResources> addRes = CollectionsUtil.selectRejected(newRes,"resourceId",CollectionsUtil.getPropertyValueList(roleResources, "resourceId"));
			for(RoleResources r:removeRes){
				roleResourceMappper.deleteById(r.getId());
			}
			for(RoleResources r:addRes){
				roleResourceMappper.insert(r);
			}

		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
}
