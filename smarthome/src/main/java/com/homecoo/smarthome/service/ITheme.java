package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.Theme;

public interface ITheme {

	void addTheme(String themePacket, String gatewayNo);
	
	
	//添加情景
	int addTheme(Theme theme);
	//更新情景
	int updateTheme(Theme theme);
	//删除情景
	int deleteTheme(String gatewayNo);
	//用户获取所以情景
	List<Theme> getAllTheme(String gatewayNo);
	
	//同步情景  是不是类似与添加情景()
	boolean getTheme(String themeNo);
	
	//版本表 获取版本信息
	AppVersion getAppVersionByPhoneNumVersionType(AppVersion appVersion);

	//版本表 获取版本信息
	AppVersion getAppVersionByGatewayNoVersionType(AppVersion appVersion);
	
	//根据 phoneNum,versionType 更新版本表
	int updateVersion(AppVersion appVersion);
	
	//添加版本
	int addAppVersion(AppVersion appVersion);
	
	int deleteAppVersion(String phonenum,Integer type);
}
