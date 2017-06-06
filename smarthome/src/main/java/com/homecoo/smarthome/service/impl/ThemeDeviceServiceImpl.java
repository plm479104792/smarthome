package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.ThemeDevice;
import com.homecoo.smarthome.persistence.ThemeDeviceMapper;
import com.homecoo.smarthome.service.IThemeDevice;
@Service
public class ThemeDeviceServiceImpl implements IThemeDevice{

	@Autowired
	private ThemeDeviceMapper themeDeviceMapper;
	
	@Override
	public boolean getThemeDevice(String themeNo) {
		List<ThemeDevice> list=themeDeviceMapper.getThemeDevice(themeNo);
		if (list.size()>0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int addThemeDevice(ThemeDevice themeDevice) {
		int row= themeDeviceMapper.addThemeDevice(themeDevice);
		return row;
	}

	@Override
	public int updateThemeDevice(ThemeDevice themeDevice) {
		int row=themeDeviceMapper.updateThemeDevice(themeDevice);
		return row;
	}

	@Override
	public List<ThemeDevice> getALLThemeDeviceByGatewayNo(String gatewayNo) {
		List<ThemeDevice> list=themeDeviceMapper.getAllThemeDeviceByGatewayNo(gatewayNo);
		return list;
	}

}
