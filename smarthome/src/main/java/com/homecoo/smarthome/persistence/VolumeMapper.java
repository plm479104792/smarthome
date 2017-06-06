package com.homecoo.smarthome.persistence;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Volume;

public interface VolumeMapper {
	
	Volume selectVolumeByGatewayNo(@Param("gatewayNo")String gatewayNo);

	int updateVolume(Volume volume);
	
	int insertVolume(Volume volume);
	
	int deleteVolume(@Param("gatewayNo")String gatewayNo);
}
