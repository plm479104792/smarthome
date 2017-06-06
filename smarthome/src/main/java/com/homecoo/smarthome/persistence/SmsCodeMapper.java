package com.homecoo.smarthome.persistence;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.SmsCode;

public interface SmsCodeMapper {
 
    
    int updateByPhoneNum(SmsCode record);					
    
	int insertSmsCode(SmsCode smsCode);				
	
	//备用H2的
	int insertSmsCodeH2(SmsCode smsCode);	
	
	//获取最新的验证码
	List<SmsCode> findIdentifyCodeByPhone(SmsCode smsCode);			
	
	//查看该手机号是否已经注册过
	List<SmsCode> getPhoneNum(@Param("phoneNum")String phoneNum);
	
	int deleteByphoneNum(@Param("phonenum")String phonenum);
	
}