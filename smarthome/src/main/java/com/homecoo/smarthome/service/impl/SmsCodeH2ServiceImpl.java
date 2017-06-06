package com.homecoo.smarthome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.SmsCode;
import com.homecoo.smarthome.persistence.SmsCodeMapper;
import com.homecoo.smarthome.service.ISmsCodeH2;

@Service
public class SmsCodeH2ServiceImpl implements ISmsCodeH2{

	@Autowired
	private SmsCodeMapper smsCodeMapper;
	@Override
	public int addSmsCodeH2(SmsCode smsCode) {
		int row=smsCodeMapper.insertSmsCodeH2(smsCode);
		return row;
	}

}
