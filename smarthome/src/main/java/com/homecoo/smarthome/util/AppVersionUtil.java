package com.homecoo.smarthome.util;

import com.homecoo.smarthome.domain.AppVersion;

/**
 * 版本信息
 * @author xiaobai
 * */
public class AppVersionUtil {

	/**
	 * 用于获取版本信息
	 * */
	public static AppVersion GetAppVersion(String gatewayNo){
		AppVersion version=new AppVersion();
		version.setGatewayNo(gatewayNo);
		version.setVersionType(4);    //4 代表情景同步
		return version;
	}
	
	/**
	 * 用于获取空间版本信息
	 * @param phonenum
	 * @return 
	 * */
	public static AppVersion getSpaAppVersion(String phonenum){
		AppVersion version=new AppVersion();
		version.setPhoneNum(phonenum);
		version.setVersionType(3);					//3  代表空间同步
		return version;
	}

	/**
	 * 用于同步网关版本信息
	 * @param gatewayNo
	 * @return appVersion
	 * */
	public static AppVersion getGatewayNoAppVersion(String gatewayNo){
		AppVersion appVersion=new AppVersion();
		appVersion.setGatewayNo(gatewayNo);
		appVersion.setVersionType(NeedConstant.VERSION_GATEWAY);   //6  代表网关同步
		return appVersion;
	}
	
	/**
	 * 用于同步网关版本信息
	 * @param gatewayNo
	 * @return appVersion
	 * */
	public static AppVersion getPhoneNumAppVersion(String phoneNum){
		AppVersion appVersion=new AppVersion();
		appVersion.setPhoneNum(phoneNum);
		appVersion.setVersionType(NeedConstant.VERSION_GATEWAY);  //6  代表网关同步
		return appVersion;
	}
	
	
	/**
	 * 用于同步网关版本信息
	 * @param gatewayNo
	 * @return appVersion
	 * */
	public static AppVersion getDeviceAppVersionByPhoneNum(String phoneNum){
		AppVersion appVersion=new AppVersion();
		appVersion.setPhoneNum(phoneNum);
		appVersion.setVersionType(NeedConstant.VERSION_DEVICE);  // 2 代表设备同步
		return appVersion;
	}
	
	
	
}
