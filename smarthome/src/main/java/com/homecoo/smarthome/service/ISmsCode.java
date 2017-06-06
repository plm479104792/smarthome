package com.homecoo.smarthome.service;

import com.homecoo.smarthome.domain.SmsCode;

public interface ISmsCode {

	int addSmsCode(SmsCode smsCode);
	
	int updateSmsCode(SmsCode smsCode);
	
	String findIdentifyCodeByPhone(SmsCode smsCode);
	
	int getPhoneNum(String phoneNum);
	
	int deleteByPhonenum(String phonenum);
}