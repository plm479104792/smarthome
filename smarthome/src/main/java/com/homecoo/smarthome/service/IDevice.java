package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceStateRecord;

public interface IDevice {

	int addDevice(Device device);

	int updateDevice(Device device);

	void deleteDevice(String deviceNo);

	List<Device> getAllDevice(String gatewayNo);

	List<Device> getDeviceListByCategory(String phonenum, int categoryId);

	List<Device> getDeviceListByPhoneNum(String phonenum);

	Device controlDevice(String packetJson, String gatewayNo);

	void updateDeviceStateRecord(DeviceStateRecord deviceStateRecord);

	boolean getDeviceByDeviceNo(Device device);
}
