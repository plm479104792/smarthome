package com.homecoo.smarthome.service;

import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Volume;

public interface IVolume {
	
	int updateVolume(Volume volume);
	
	int deleteVolume(String gatewayNo);
	
	int insertVolume(Volume volume);
	
	Volume getVolume(String gatewayNo);
	
}
