package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.Infrared;

public interface IInfrared {
	
	/**
	 * 更新/增加 红外控制设备
	 * */
	public void SaveOrUpdateInfrared(Infrared infrared);
	
	
	/**
	 * 根据网关号删除红外控制设备
	 * */
	public void deleteInfraredBydeviceNo(String deviceNo);
	
	
	/**
	 * 根据网关号和 红外控制设备类型ID删除红外控制设备
	 * */
	public  void deleteInfraredBydeviceNoAndTypeId(Infrared infrared);
	
	/**
	 * 根据网关号和 红外控制设备类型ID删除红外控制设备
	 * */
	public  void deleteInfraredBygatewayNo(Infrared infrared); 
	
	/**
	 * 根据网关号获取所有的红外控制设备
	 * */
	public List<Infrared> getAllInfraredBygatewayNo(String gatewayNo);
}
