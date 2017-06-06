package com.homecoo.smarthome.service;

import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceDto;
import com.homecoo.smarthome.domain.DeviceStateRecord;

public interface IDeviceState {
	
	DeviceStateRecord getDeviceState(Device device);
	
	int updateDeviceState(DeviceStateRecord deviceStateRecord);
	
	boolean selectDeviceStateByDeviceNo(String DeviceNo);
	
	int AddDeviceState(DeviceDto deviceDto);
}
