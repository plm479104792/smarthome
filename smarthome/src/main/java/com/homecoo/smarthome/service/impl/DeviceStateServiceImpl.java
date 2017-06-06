package com.homecoo.smarthome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceDto;
import com.homecoo.smarthome.domain.DeviceStateRecord;
import com.homecoo.smarthome.persistence.DeviceStateRecordMapper;
import com.homecoo.smarthome.service.IDeviceState;

@Service
public class DeviceStateServiceImpl implements IDeviceState{
	
	@Autowired
	private DeviceStateRecordMapper stateRecordMapper;

	@Override
	public DeviceStateRecord getDeviceState(Device device) {
		DeviceStateRecord record=stateRecordMapper.selectByDeviceNoGatewayNo(device);
		if (record==null) {
			DeviceStateRecord a=new DeviceStateRecord();
			a.setDeviceStateCmd("");
			return a;
		}else{
			return record;
		}
		
	}

	@Override
	public int updateDeviceState(DeviceStateRecord deviceStateRecord) {
		return 0;
	}

	@Override
	public boolean selectDeviceStateByDeviceNo(String DeviceNo) {
		boolean b;
		DeviceStateRecord record=stateRecordMapper.selectByDeviceNo(DeviceNo);
		if (record==null) {
			b=true;
		}else{
			b=false;
		}
		return b;
	}

	@Override
	public int AddDeviceState(DeviceDto deviceDto) {
		DeviceStateRecord stateRecord=new DeviceStateRecord();
		stateRecord.setDeviceNo(deviceDto.getDevice().getDeviceNo());
		stateRecord.setDeviceStateCmd(deviceDto.getDeviceState());
		int record=stateRecordMapper.insert(stateRecord);
		return record;
	}

}
