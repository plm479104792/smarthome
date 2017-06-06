package com.homecoo.smarthome.util;

import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.SmsCode;

/**
 * 验证码工具类
 * @author xiaobai
 * */
public class SmsCodeUtil {

	/**
	 * 得到用户发送过来的验证码
	 * @param phonenum 手机号
	 * @param identifyCode 验证码
	 * @param smsCodeType	验证码类型
	 * */
	public static SmsCode GetSmsCodeByInterface(String phonenum,String identifyCode,String smsCodeType){
		SmsCode sms=new SmsCode();
		sms.setPhonenum(phonenum);
		sms.setSmscodeType(smsCodeType);
		sms.setSmscode(identifyCode);
		return sms;
	}
	
	/**
	 * 用户重置密码的验证码
	 * */
	public static SmsCode GetRepasswordSmsCode(String phonenum,String identifyCode){
		Message msg=new Message();
		SmsCode smsCode = new SmsCode();
		smsCode.setPhonenum(phonenum);
		smsCode.setSmscode(identifyCode);
		smsCode.setSmscodeType("2");  //2 代表重置密码的验证码
		return smsCode;
		
	}
	
	/**
	 * 用户注册验证码
	 * @param phonenum
	 * @param idendifyCode
	 * */
	public static SmsCode registerappSmsCode(String phonenum,String idendifyCode){
		SmsCode smsCode = new SmsCode();
		smsCode.setPhonenum(phonenum);
		smsCode.setSmscode(idendifyCode);
		smsCode.setSmscodeType("1");
		return smsCode;
	}
	
	
}
