package com.wholesmart.common.shiro.entity;

/**
 * shiro用户实体类
 * @author kangming.ning
 * @since 2017-09-14
 * */
public class ShiroUser {
	
	private String id; 
	
	private String account;//账号
	
	private String realName;
	
	private String password;
	
	private String credentialsSalt;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCredentialsSalt() {
		return credentialsSalt;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	

}
