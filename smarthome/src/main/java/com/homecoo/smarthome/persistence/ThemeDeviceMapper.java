package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.ThemeDevice;

public interface ThemeDeviceMapper {
	
	List<ThemeDevice> getThemeDevice(@Param("themeNo")String themeNo);

	int addThemeDevice(ThemeDevice themeDevice);
	
	int updateThemeDevice(ThemeDevice themeDevice);
	
	List<ThemeDevice> getAllThemeDeviceByGatewayNo(@Param("gatewayNo")String gatewayNo);
}
