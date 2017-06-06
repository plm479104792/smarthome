package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Infrared;


public interface InfraredMapper {

	/**
	 * 根据网关号获取所有的红外控制设备
	 * */
	List<Infrared> selectInfraredByGatewayNo(@Param("gatewayNo")String gatewayNo);
	
	
	/**
	 * 根据设备ID 删除
	 * */
	void deleteInfraredByDeviceNo(@Param("deviceNo")String deviceNo);
	
	
	/**
	 * 根据设备ID和类型ID删除
	 * */
	void deleteInfraredByTypeIdAndthemeNo(Infrared infrared);
	
	/**
	 * 根据infraredId删除红外控制设备
	 * */
	void deleteInfraredBygatewayNo(Infrared infrared);
	
	
	
	/**
	 * 根据设备id和类型id更新
	 * */
	void updateBydeviceNoAndTypeId(Infrared infrared);
	
	/**
	 * 插入
	 * */
	void insertInfrared(Infrared infrared);
	
	
}
