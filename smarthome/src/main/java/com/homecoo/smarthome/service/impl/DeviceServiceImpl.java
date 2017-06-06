package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceStateRecord;
import com.homecoo.smarthome.persistence.DeviceMapper;
import com.homecoo.smarthome.service.IDevice;

@Service
public class DeviceServiceImpl implements IDevice{

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public int addDevice(Device device) {
		int row=deviceMapper.insert(device);
		return row;
	}

	@Override
	public int updateDevice(Device device) {
		// TODO Auto-generated method stub
		int row=deviceMapper.updateByPrimaryKey(device);
		return 0;
	}

	@Override
	public void deleteDevice(String deviceNo) {
		deviceMapper.deleteByDeviceNo(deviceNo);
	}

	@Override
	public List<Device> getAllDevice(String gatewayNo) {
		List<Device> list=deviceMapper.getAllDeviceByGatewayNo(gatewayNo);
		return list;
	}

	@Override
	public List<Device> getDeviceListByCategory(String phonenum, int categoryId) {
		Device device=new Device();
		device.setPhoneNum(phonenum);
		device.setDeviceCategoryId(categoryId);
		List<Device> list=deviceMapper.getALLDeviceByPhoneNumCategoryId(device);
		return list;
	}

	@Override
	public List<Device> getDeviceListByPhoneNum(String phoneNum) {
		List<Device> list=deviceMapper.getAllDeviceByPhoneNum(phoneNum);
		return list;
	}

	@Override
	public Device controlDevice(String packetJson, String gatewayNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDeviceStateRecord(DeviceStateRecord deviceStateRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getDeviceByDeviceNo(String deviceNo) {
		List<Device> list=deviceMapper.getDeviceByDeviceNo(deviceNo);
		if (list.size()>0) {
			return true;
		}else{
			return false;
		}
	}
}
