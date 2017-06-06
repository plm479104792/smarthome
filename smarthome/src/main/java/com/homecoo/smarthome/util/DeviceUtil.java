package com.homecoo.smarthome.util;

import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceDto;
import com.homecoo.smarthome.domain.DeviceDtoApp;
import com.homecoo.smarthome.domain.DeviceStateRecord;

/**
 * @Description:设备工具类
 * @author xiaobai
 * */
public class DeviceUtil {
	
	/**
	 * @author xiaobai
	 * @Description:APP传过来的 device 实体类  转换为  服务器的deviceDto实体类
	 * @param:devive
	 * @return:DeviceDto
	 * */
	public static DeviceDto DeviceDtoappToDeviceDto(DeviceDtoApp deviceDtoApp){
		
		DeviceDto deviceDto=new DeviceDto();
		Device device=new Device();
		try {
		device.setDeviceId(null);
		device.setDeviceNo(deviceDtoApp.getDeviceNo());
		device.setDeviceTypeId(deviceDtoApp.getDeviceTypeId());
		device.setDeviceTypeName(deviceDtoApp.getDeviceTypeName());
		device.setDeviceCategoryId(deviceDtoApp.getDeviceCategoryId());
		device.setDeviceCategoryName(deviceDtoApp.getDeviceCategoryName());
		device.setDeviceName(deviceDtoApp.getDeviceName());
		device.setGatewayNo(deviceDtoApp.getGatewayNo());
		device.setSpaceNo(deviceDtoApp.getSpaceNo());
		if (deviceDtoApp.getSpaceTypeId()==null || deviceDtoApp.getSpaceTypeId().equals("")) {
			deviceDtoApp.setSpaceTypeId(0);
		}
		device.setSpaceTypeId(String.valueOf(deviceDtoApp.getSpaceTypeId()));
		device.setPhoneNum(deviceDtoApp.getPhoneNum());
		device.setCreateTime(null);
		device.setCreateBy(null);
		device.setUpdateBy(null);
		device.setUpdateBy(null);
		deviceDto.setDevice(device);
		deviceDto.setDeviceState(deviceDtoApp.getDeviceStateCmd());
		} catch (Exception e) {
			System.err.println("dto转换错误 "+e);
			e.printStackTrace();
		}
		return deviceDto;
		
	}

	/**
	 * @Description:device  deviceState   转App 需要的DeviceDtoApp
	 * @author xiaobai
	 * @param DEVICE  deviceState
	 * @return:DeviceDtoApp
	 * */
	public static DeviceDtoApp GetDeviceDtoApp(Device device,DeviceStateRecord record){
		
		DeviceDtoApp deviceDtoApp=new DeviceDtoApp();
		try {
		if (device.getDeviceTypeId().equals(109)) {
			boolean b=DateUtil.PM25(record.getLastUpdateTime());
			if (b) {
				deviceDtoApp.setDeviceStateCmd("");
			}else {
				deviceDtoApp.setDeviceStateCmd(record.getDeviceStateCmd());
			}
		}else {
			deviceDtoApp.setDeviceStateCmd(record.getDeviceStateCmd());
		}
		deviceDtoApp.setDeviceId(device.getDeviceId());
		deviceDtoApp.setDeviceNo(device.getDeviceNo());
		deviceDtoApp.setDeviceTypeId(device.getDeviceTypeId());
		deviceDtoApp.setDeviceTypeName(device.getDeviceTypeName());
		deviceDtoApp.setDeviceCategoryId(device.getDeviceCategoryId());
		deviceDtoApp.setDeviceCategoryName(device.getDeviceCategoryName());
		deviceDtoApp.setDeviceName(device.getDeviceName());
		deviceDtoApp.setGatewayNo(device.getGatewayNo());
		deviceDtoApp.setSpaceNo(device.getSpaceNo());
		if (device.getSpaceTypeId()==null) {
			deviceDtoApp.setSpaceTypeId(0);
		}else {
			deviceDtoApp.setSpaceTypeId(Integer.valueOf(device.getSpaceTypeId()));
		}
		if (device.getSpaceNo()==null) {
			deviceDtoApp.setSpaceNo("0");
		}else {
			deviceDtoApp.setPhoneNum(device.getPhoneNum());
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceDtoApp;
	}
	
	
}
