package com.wholesmart.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-08
 */
@ApiModel(value="日志表")
@TableName("t_log")
public class Log extends Model<Log> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志编号
     */
    @ApiModelProperty(value="日志编号",name="id")
	private Long id;
    /**
     * 操作用户id
     */
    @ApiModelProperty(value="操作用户id",name="userId")
	@TableField("user_id")
	private Long userId;
    /**
     * 操作用户姓名
     */
    @ApiModelProperty(value="操作用户姓名",name="relname")
	private String relname;
    /**
     * 操作用户账号
     */
    @ApiModelProperty(value="操作用户账号",name="username")
	private String username;
    /**
     * 操作时间
     */
    @ApiModelProperty(value="操作时间",name="operaTime")
	@TableField("opera_time")
	private Date operaTime;
    /**
     * 日志类型
     */
    @ApiModelProperty(value="日志类型",name="operaStlye")
	@TableField("opera_stlye")
	private String operaStlye;
    
    /**
     * 操作模块
     */
    @ApiModelProperty(value="日志类型",name="operaStlye")
	@TableField("opera_model")
	private String operaModel;
    
    
    /**
     * 操作信息
     */
    @ApiModelProperty(value="操作信息",name="operaRecord")
	@TableField("opera_record")
	private String operaRecord;
    /**
     * 操作结果
     */
    @ApiModelProperty(value="操作结果",name="operaResult")
	@TableField("opera_result")
	private String operaResult;
    /**
     * 操作IP
     */
    @ApiModelProperty(value="操作IP",name="userIp")
	@TableField("user_ip")
	private String userIp;
    /**
     * 备用字段
     */
    @ApiModelProperty(value="备用字段",name="spare0")
	private String spare0;
	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;
	
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOperaTime() {
		return operaTime;
	}

	public void setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
	}

	public String getOperaStlye() {
		return operaStlye;
	}

	public void setOperaStlye(String operaStlye) {
		this.operaStlye = operaStlye;
	}

	public String getOperaRecord() {
		return operaRecord;
	}

	public void setOperaRecord(String operaRecord) {
		this.operaRecord = operaRecord;
	}
	
	

	public String getOperaModel() {
		return operaModel;
	}

	public void setOperaModel(String operaModel) {
		this.operaModel = operaModel;
	}

	public String getOperaResult() {
		return operaResult;
	}

	public void setOperaResult(String operaResult) {
		this.operaResult = operaResult;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
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
		return "Log{" +
			"id=" + id +
			", userId=" + userId +
			", relname=" + relname +
			", username=" + username +
			", operaTime=" + operaTime +
			", operaStlye=" + operaStlye +
			", operaRecord=" + operaRecord +
			", operaResult=" + operaResult +
			", userIp=" + userIp +
			", spare0=" + spare0 +
			", spare1=" + spare1 +
			", spare2=" + spare2 +
			", spare3=" + spare3 +
			", spare4=" + spare4 +
			"}";
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
}
