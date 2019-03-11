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
 * 用户表
 * </p>
 *
 * @author KangMing.Ning
 * @since 2017-09-07
 */
@ApiModel(value="用户表")
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @ApiModelProperty(value="用户表id",name="id")
	private Long id;
    /**
     * 账号
     */
    @ApiModelProperty(value="账号",name="username")
	private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码",name="password")
	private String password;
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄",name="age")
	private Integer age;
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名",name="relname")
	private String relname;
    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号",name="phone")
	private String phone;
    /**
     * 身份证
     */
    @ApiModelProperty(value="身份证",name="idcard")
	private String idcard;
    /**
     * 部门表id
     */
    @ApiModelProperty(value="部门表id",name="departmentId")
	@TableField("department_id")
	private Long departmentId;
    /**
     * 性别
     */
    @ApiModelProperty(value="性别",name="sex")
	private String sex;
    /**
     * email
     */
    @ApiModelProperty(value="email",name="email")
	private String email;
    /**
     * 职务
     */
    @ApiModelProperty(value="职务",name="duty")
	private String duty;
    /**
     * 注册IP
     */
    @ApiModelProperty(value="注册IP",name="regIp")
	@TableField("reg_ip")
	private String regIp;
    /**
     * 头像地址
     */
    @ApiModelProperty(value="头像地址",name="avatar")
	private String avatar;
    /**
     * 密码盐
     */
    @ApiModelProperty(value="密码盐",name="credentialsSalt")
	@TableField("credentials_salt")
	private String credentialsSalt;
    /**
     * 录入人
     */
    @ApiModelProperty(value="录入人",name="inputUser")
	@TableField(value="input_user",fill = FieldFill.INSERT)
	private String inputUser;
    /**
     * 录入时间
     */
    @ApiModelProperty(value="录入时间",name="createTime")
	@TableField(value="create_time",fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 数据更新时间
     */
    @ApiModelProperty(value="数据更新时间",name="updateTime")
	@TableField(value="update_time", fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 状态,0：正常；1：删除
     */
    @ApiModelProperty(value="状态,0：正常；1：删除",name="status")
	private Integer status;
    /**
     * 是否锁定
     */
    @ApiModelProperty(value="是否锁定",name="uLocked")
	@TableField("u_locked")
	private Boolean uLocked=false;
    /**
     * 备用字段
     */
    @ApiModelProperty(value="备用字段",name="spare0")
	private String spare0;
	private String spare1;
	private String spare2;
	private String spare3;
	private String spare4;
	
	@ApiModelProperty(value="角色对象",name="role",hidden=true)
	@TableField(exist=false)
	private Role role;
	
	@ApiModelProperty(value="部门名称",name="department",hidden=true)
	@TableField(exist=false)
	private String department;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

	public String getInputUser() {
		return inputUser;
	}

	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getuLocked() {
		return uLocked;
	}

	public void setuLocked(Boolean uLocked) {
		this.uLocked = uLocked;
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
		return "User{" +
			"id=" + id +
			", username=" + username +
			", password=" + password +
			", age=" + age +
			", relname=" + relname +
			", phone=" + phone +
			", idcard=" + idcard +
			", departmentId=" + departmentId +
			", sex=" + sex +
			", email=" + email +
			", duty=" + duty +
			", regIp=" + regIp +
			", avatar=" + avatar +
			", credentialsSalt=" + credentialsSalt +
			", inputUser=" + inputUser +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", status=" + status +
			", uLocked=" + uLocked +
			", spare0=" + spare0 +
			", spare1=" + spare1 +
			", spare2=" + spare2 +
			", spare3=" + spare3 +
			", spare4=" + spare4 +
			"}";
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
