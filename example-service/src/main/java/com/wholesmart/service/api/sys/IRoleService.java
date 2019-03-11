package com.wholesmart.service.api.sys;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wholesmart.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-08-29
 */
public interface IRoleService extends IService<Role> {
	
    boolean savePermission(Long roleId,List<Long> resourceIds);
}
