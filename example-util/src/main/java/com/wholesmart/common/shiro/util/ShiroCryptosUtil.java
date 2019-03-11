package com.wholesmart.common.shiro.util;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.wholesmart.common.shiro.entity.ShiroUser;

/**
 * Shiro加密类
 * @since 2017-08-31
 * @author kangming.ning
 * */
public class ShiroCryptosUtil {

	/** 
	 * base64进制加密 
	 * 
	 * @param password 
	 * @return 
	 */ 
	public static String encrytBase64(String password) { 
		byte[] bytes = password.getBytes(); 
		return Base64.encodeToString(bytes); 
	} 
	/** 
	 * base64进制解密 
	 * @param cipherText 
	 * @return 
	 */ 
	public static String decryptBase64(String cipherText) { 
		return Base64.decodeToString(cipherText); 
	} 
	/** 
	 * 16进制加密 
	 * 
	 * @param password 
	 * @return 
	 */ 
	public static String encrytHex(String password) { 
		byte[] bytes = password.getBytes(); 
		return Hex.encodeToString(bytes); 
	} 
	/** 
	 * 16进制解密 
	 * @param cipherText 
	 * @return 
	 */ 
	public static String decryptHex(String cipherText) { 
		return new String(Hex.decode(cipherText)); 
	} 

	public static String generateKey() 
	{ 
		AesCipherService aesCipherService=new AesCipherService(); 
		Key key=aesCipherService.generateNewKey(); 
		return Base64.encodeToString(key.getEncoded()); 
	} 


	/** 
	 * 对密码进行md5加密,并返回密文和salt，包含在ShiroUser对象中 
	 * @param username 用户名 
	 * @param password 密码
	 * @param hashIterations 迭代次数 
	 * @return UserEntity对象，包含密文和salt 
	 */ 
	public static ShiroUser md5Password(String username,String password,int hashIterations){ 
		SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
		String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
		//组合username,两次迭代，对密码进行加密 
		Md5Hash hash =new Md5Hash(password,username+salt,hashIterations);
		String password_cryto =hash.toHex();//16进制进行编码
		ShiroUser user=new ShiroUser(); 
		user.setPassword(password_cryto); 
		user.setCredentialsSalt(salt); 
		user.setAccount(username);
		return user; 
	} 

	public static void main(String[] args) { 
		SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
		String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
		System.out.println(salt);
		//组合username,两次迭代，对密码进行加密 
		Md5Hash hash = new Md5Hash("admin","admin"+salt,2); 
		String password_cryto = hash.toHex();
		System.out.println(password_cryto);
	} 

}
