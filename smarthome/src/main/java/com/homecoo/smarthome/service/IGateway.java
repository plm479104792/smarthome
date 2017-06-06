package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.Gateway;

public interface IGateway {

	/**
	 * insert 新的网关信息
	 * */
	void asyncGatewayInfo(Gateway gateway);
	
	/**
	 * 根据gatewayNo 判断是否有同一个网关信息
	 * */
	List<Gateway> getGatewayInfo(String gatewayNo);
	
	/**
	 * 根据gatewayNo 更新网关信息
	 * */
	int updateGatewayBygatewayNo(Gateway gateway);
}
