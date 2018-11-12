package com.gkr.util.enctype;

import java.util.Base64;

/**
 * 自定义加密工具类
 * @author Nate
 *
 */
public class PasswordUtil {
	private static final String SEED = "gkrjava";//表是种子数
	private static int NE_NUM = 3;//表是密码迭代次数
	
	private static String createSeed() {//用次方法创建一个基于某种算法的种子数
		String str = SEED;
		for(int x=0;x<NE_NUM;x++) {
			str = Base64.getEncoder().encodeToString(str.getBytes());
		}
		return str;	
	}
	/**
	 * 加密方法
	 * @param password
	 * @return
	 */
	public static String getPassword(String password) {
		MD5Code md5 = new MD5Code();
		String pass = "{"+password+"."+createSeed()+"}";
		for(int x = 0;x < NE_NUM;x++) {
			pass = md5.getMD5ofStr(pass);
		}
		return pass;
	}
    
	
}
