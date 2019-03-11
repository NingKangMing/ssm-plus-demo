package com.wholesmart.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="角色表")
@TableName("t_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("role_name")
	private String roleName;
    /**
     * 角色key
     */
    @ApiModelProperty(value="角色key",name="roleKey")
	@TableField("role_key")
	private String roleKey;
    /**
     * 角色状态,0：正常；1：删除
     */
    @ApiModelProperty(value="角色状态,0：正常；1：删除",name="status")
	private Integer status;
    /**
     * 角色描述
     */
    @ApiModelProperty(value="角色描述",name="description")
	private String description;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",name="updateTime")
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Role{" +
			"id=" + id +
			", roleName=" + roleName +
			", roleKey=" + roleKey +
			", status=" + status +
			", description=" + description +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
