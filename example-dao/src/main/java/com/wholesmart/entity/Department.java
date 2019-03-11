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
 * 部门表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="部门表")
@TableName("t_department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门表id
     */
    @ApiModelProperty(value="部门表id",name="id")
	private Long id;
    /**
     * 部门名称
     */
    @ApiModelProperty(value="部门名称",name="name")
	private String name;
    /**
     * 编号
     */
    @ApiModelProperty(value="编号",name="code")
	private String code;
    /**
     * 父级id
     */
    @ApiModelProperty(value="父级id",name="parentId")
	@TableField("parent_id")
	private String parentId;
    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="remark")
	private String remark;
    /**
     * 录入人
     */
    @ApiModelProperty(value="录入人",name="inputUser")
	@TableField(value = "input_user", fill = FieldFill.INSERT)
	private String inputUser;
    /**
     * 录入时间
     */
    @ApiModelProperty(value="录入时间",name="inputTime")
	@TableField(value = "input_time", fill = FieldFill.INSERT)
	private Date inputTime;
    /**
     * 树深度
     */
    @ApiModelProperty(value="树深度",name="level")
	private Integer level;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",name="updateTime")
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 备用字段
     */
    @ApiModelProperty(value="备用字段",name="spare0")
	private String spare0;
	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInputUser() {
		return inputUser;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSpare0() {
		return spare0;
	}

	public void setSpare0(String spare0) {
		this.spare0 = spare0;
	}

	public String getSpare1() {
		return spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	public String getSpare2() {
		return spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

	public String getSpare3() {
		return spare3;
	}

	public void setSpare3(String spare3) {
		this.spare3 = spare3;
	}

	public String getSpare4() {
		return spare4;
	}

	public void setSpare4(String spare4) {
		this.spare4 = spare4;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Department{" +
			"id=" + id +
			", name=" + name +
			", code=" + code +
			", parentId=" + parentId +
			", remark=" + remark +
			", inputUser=" + inputUser +
			", inputTime=" + inputTime +
			", level=" + level +
			", updateTime=" + updateTime +
			", spare0=" + spare0 +
			", spare1=" + spare1 +
			", spare2=" + spare2 +
			", spare3=" + spare3 +
			", spare4=" + spare4 +
			"}";
	}
}
