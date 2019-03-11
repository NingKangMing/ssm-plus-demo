package com.wholesmart.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="角色资源表")
@TableName("t_role_resources")
public class RoleResources extends Model<RoleResources> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限表id
     */
    @ApiModelProperty(value="角色权限表id",name="id")
	private Long id;
    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",name="roleId")
	@TableField("role_id")
	private Long roleId;
    /**
     * 权限id
     */
    @ApiModelProperty(value="权限id",name="resourceId")
	@TableField("resource_id")
	private Long resourceId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "RoleResources{" +
			"id=" + id +
			", roleId=" + roleId +
			", resourceId=" + resourceId +
			"}";
	}
}
