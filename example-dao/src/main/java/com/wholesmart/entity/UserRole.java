package com.wholesmart.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 角色用户中间表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="角色用户中间表")
@TableName("t_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色用户中间表id
     */
    @ApiModelProperty(value="角色用户中间表id",name="id")
	private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",name="userId")
	@TableField("user_id")
	private Long userId;
    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",name="roleId")
	@TableField("role_id")
	private Long roleId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserRole{" +
			"id=" + id +
			", userId=" + userId +
			", roleId=" + roleId +
			"}";
	}
}
