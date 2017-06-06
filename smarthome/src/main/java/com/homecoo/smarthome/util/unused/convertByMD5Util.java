package com.homecoo.smarthome.util.unused;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class convertByMD5Util {

	public static String convertByMD5(String str) {
		MessageDigest instance;
		String strmd5 = "";
		byte[] digest = null;
		try {
			instance = MessageDigest.getInstance("MD5");
			digest = instance.digest(str.getBytes());
			
			for (int i = 0; i < digest.length; i++) {
				String hex = Integer.toHexString(digest[i] & 0xFF);
				strmd5+=hex;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return strmd5;
	}

	
}
