package com.homecoo.smarthome.util;

import org.h2.util.StringUtils;
/**
 * 
 * @author bingo
 *
 */
public class BasicProcess {
	/**
	 * 16进制byte[]转String类型
	 */
	public static String toHexString(byte[] byteArray) {
		if (byteArray == null || byteArray.length < 1)
			throw new IllegalArgumentException(
					"this byteArray must not be null or empty");
		final StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if ((byteArray[i] & 0xff) < 0x10)// 0~F前面不零
				hexString.append("0");
			hexString.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return hexString.toString().toLowerCase();
	}

	
	/**
	 * String 转换成byte[]形式，注意这个不改变实际内容 “00 01 30 02” 转  00 01 30 02
	 */
	public static byte[] toByteArray(String hexString) {
		if (StringUtils.isNullOrEmpty(hexString))
			throw new IllegalArgumentException(
					"this hexString must not be empty");
		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {
			// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
			byteArray[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return byteArray;
	}

	
	/**
	 * byte[]原版打印 测试使用,这里只是byte[] 的String形式的输出； 01 00 02 30  转 “01 00 02 30”
	 */
	public static String printHexString(byte[] b) {
		StringBuffer returnValue = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			returnValue.append(hex.toUpperCase() + " ");
		}
		return "[" + returnValue.toString() + "]";
	}
	
	
	
	/**
	 * byte[]转16进制int型，这里是为了转short 
	 * 这里写了两个不同的编码方式，供两种不同效果
	 * 
	 * */
	public static int byte2int(byte[] res) {  //前大后小，为datalength使用
		// res = InversionByte(res);
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
		int targets = (res[1] & 0xff) | ((res[0] << 8) & 0xff00); // | 表示安位或
		// int targets = ((res[0] & 0xff) |(res[1] << 0xff00) );
		return targets;
	}

	
	/** 
	 * byte[]转16进制int型，这里是为了转short 
	 * 这里写了两个不同的编码方式，供两种不同效果
	 * 
	 * */
	public static int bytetoint(byte[] res) {//正常转变，前小后大,为datatype，devType使用
		// res = InversionByte(res);
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
//		int targets = (res[1] & 0xff) | ((res[0] << 8) & 0xff00); // | 表示安位或
		 int targets = ((res[0] & 0xff) |(res[1] << 0xff00) );
		return targets;
	}
	
	
	/** 
	 * 
	 * short转byte[] 
	 * 
	 * */
	public static byte[] shortToByteArray(short s) {
		byte[] targets = new byte[2];
		for (int i = 0; i < 2; i++) {
			int offset = (targets.length - 1 - i) * 8;
			targets[i] = (byte) ((s >>> offset) & 0xff);
		}
		return targets;
	}
	
	
	
	//高位在前（dataLen定制）
	public static byte[] shortToByteArray2(short s) {
		byte[] targets = new byte[2];
		int offset1 = (targets.length - 1 - 1) * 8;	
		int offset = (targets.length - 1 - 0) * 8;
		targets[0] = (byte) ((s >>> offset1) & 0xff);
		targets[1] = (byte) ((s >>> offset) & 0xff);
		return targets;
	}
	
	
	/** 
	 * 基于位移的32位int转化成byte[] 
	 * @param int  number 
	 * @return byte[] 
	 */    
	public static byte[] intToByte(int number) {  
	    byte[] abyte = new byte[4];  
	    // "&" 与（AND），对两个整型操作数中对应位执行布尔代数，两个位都为1时输出1，否则0。  
	    abyte[0] = (byte) (0xff & number);  
	    // ">>"右移位，若为正数则高位补0，若为负数则高位补1  
	    abyte[1] = (byte) ((0xff00 & number) >> 8);  
	    abyte[2] = (byte) ((0xff0000 & number) >> 16);  
	    abyte[3] = (byte) ((0xff000000 & number) >> 24);  
	    return abyte;  
	}  
	  
	/** 
	 *基于位移的 byte[]转化成32位int 
	 * @param byte[] bytes 
	 * @return int  number 
	 */   
	public static int bytesToInt(byte[] bytes) {  
	    int number = bytes[0] & 0xFF;  
	    // "|="按位或赋值。  
	    number |= ((bytes[1] << 8) & 0xFF00);  
	    number |= ((bytes[2] << 16) & 0xFF0000);  
	    number |= ((bytes[3] << 24) & 0xFF000000);  
	    return number;  
	} 
	
	
	
	public static int bytesToIntTest(byte[] bytes) {   //2015.8.24 
	    int number = bytes[0] & 0xFF;  
	    // "|="按位或赋值。  
	    number |= ((bytes[1] << 8) & 0xFF00);  
	     
	    return number;  
	} 
	
	
}
