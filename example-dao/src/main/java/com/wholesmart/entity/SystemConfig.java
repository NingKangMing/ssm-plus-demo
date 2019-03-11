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
 * 系统配置表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="系统配置表")
@TableName("t_system_config")
public class SystemConfig extends Model<SystemConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统配置表id
     */
    @ApiModelProperty(value="系统配置表id",name="id")
	private Long id;
    /**
     * 配置key
     */
    @ApiModelProperty(value="配置key",name="key1")
	private String key1;
    /**
     * 配置value
     */
    @ApiModelProperty(value="配置value",name="value1")
	private String value1;
    
    @ApiModelProperty(value="是否是系统属性，系统属性将不允许删除",name="isSys")
    @TableField("is_sys")
    private Integer isSys;
    
    @ApiModelProperty(value="参数说明",name="memo")
    private String memo;
    
    
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
	@TableField("user_id")
	private Long userId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SystemConfig{" +
			"id=" + id +
			", key1=" + key1 +
			", value1=" + value1 +
			", inputUser=" + inputUser +
			", inputTime=" + inputTime +
			", userId=" + userId +
			"}";
	}

	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
