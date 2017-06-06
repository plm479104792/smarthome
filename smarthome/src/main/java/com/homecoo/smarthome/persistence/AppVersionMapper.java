package com.homecoo.smarthome.persistence;

import com.homecoo.smarthome.domain.AppVersion;

public interface AppVersionMapper {
	
	//通过手机和版本类型获取
	AppVersion getAppVersionByPhoneNumVersionType(AppVersion appVersion);
	
	//通过网关和版本类型获取
	AppVersion getAppVersionByGatewayNoVersionType(AppVersion appVersion);
	
	//更新版本表
	int updateAppVersion(AppVersion appVersion);

	//添加版本表
	int addAppVersion(AppVersion appVersion);
	
	//根据手机号 和  版本类型    删除版本信息
	int deleteAppVersion(AppVersion appVersion);
}