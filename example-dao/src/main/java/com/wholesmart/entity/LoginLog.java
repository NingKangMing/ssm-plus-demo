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
 * 登录日志表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-21
 */
@ApiModel(value="登录日志表")
@TableName("t_login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志id
     */
    @ApiModelProperty(value="登录日志id",name="id")
	private Long id;
    /**
     * 登录账户名
     */
    @ApiModelProperty(value="登录账户名",name="accountName")
	@TableField("account_name")
	private String accountName;
    /**
     * 登录IP
     */
    @ApiModelProperty(value="登录IP",name="ip")
	private String ip;
    /**
     * 登录时间
     */
    @ApiModelProperty(value="登录时间",name="createTime")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 登录信息
     */
    @ApiModelProperty(value="登录信息",name="remark")
	private String remark;
    /**
     * 登录状态
     */
    @ApiModelProperty(value="登录状态",name="status")
	private String status;
    
    @ApiModelProperty(value="查询字段",name="beginTime")
	@TableField(exist=false)
	private Date beginTime;
	
	@ApiModelProperty(value="查询字段",name="endTime")
	@TableField(exist=false)
	private Date endTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LoginLog{" +
			"id=" + id +
			", accountName=" + accountName +
			", ip=" + ip +
			", createTime=" + createTime +
			", remark=" + remark +
			", status=" + status +
			"}";
	}
}
