package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Gateway;
import com.homecoo.smarthome.persistence.GatewayMapper;
import com.homecoo.smarthome.service.IGateway;

@Service
public class GatewayServiceImpl implements IGateway{
	
	@Autowired
	private GatewayMapper gatewayMapper;

	@Override
	public void asyncGatewayInfo(Gateway gateway) {
		gatewayMapper.insert(gateway);
	}

	@Override
	public List<Gateway> getGatewayInfo(String gatewayNo) {
		List<Gateway> list=gatewayMapper.selectByGatewayNo(gatewayNo);
		return list;
	}

	@Override
	public int updateGatewayBygatewayNo(Gateway gateway) {
		int row=gatewayMapper.updateByGatewayNo(gateway);
		return row;
	}

}
