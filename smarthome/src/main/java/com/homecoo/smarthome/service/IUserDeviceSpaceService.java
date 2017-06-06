package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.UserDeviceSpace;

public interface IUserDeviceSpaceService {

	int addUserSpaceDevice(UserDeviceSpace userDeviceSpace);

	List<UserDeviceSpace> getAllDevice(String phoneNum);
	
	boolean getUserDeviceSpace(String spaceNo);
	
	boolean getUserDeviceSpaceByDeviceno(UserDeviceSpace deviceSpace);
	
	int updateUserDeviceSpace(UserDeviceSpace userDeviceSpace);

	int CancelUserSpaceDevice(String phonenum);
}
