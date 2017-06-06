package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.UserDeviceSpace;
import com.homecoo.smarthome.persistence.UserDeviceSpaceMapper;
import com.homecoo.smarthome.service.IUserDeviceSpaceService;

@Service
public class UserDeviceSpaceServiceImpl implements IUserDeviceSpaceService{

	@Autowired
	private UserDeviceSpaceMapper deviceSpaceMapper;
	
	@Override
	public int addUserSpaceDevice(UserDeviceSpace userDeviceSpace) {
		int row=deviceSpaceMapper.insertUserDeviceSpace(userDeviceSpace);
		return row;
	}

	@Override
	public List<UserDeviceSpace> getAllDevice(String phoneNum) {
		List<UserDeviceSpace> list=deviceSpaceMapper.selectUserDeviceSpaceByPhoneNum(phoneNum);
		return list;
	}

	@Override
	public boolean getUserDeviceSpace(String spaceNo) {
		List<UserDeviceSpace> list=deviceSpaceMapper.selectUserDeviceSpaceBySpaceNo(spaceNo);
		if (list.size()>0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int updateUserDeviceSpace(UserDeviceSpace userDeviceSpace) {
		int row=deviceSpaceMapper.updateUserDeviceSpace(userDeviceSpace);
		return row;
	}

	@Override
	public boolean getUserDeviceSpaceByDeviceno(UserDeviceSpace userDeviceSpace) {
		List<UserDeviceSpace> list=deviceSpaceMapper.selectUserDeviceSpaceByDeviceNo(userDeviceSpace);
		if (list.size()>0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int CancelUserSpaceDevice(String phonenum) {
		int row=deviceSpaceMapper.deleteUserDeviceSpaceByPhoneNum(phonenum);
		return row;
	}

}
