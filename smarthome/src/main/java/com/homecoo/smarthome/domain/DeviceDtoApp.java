package com.homecoo.smarthome.domain;


public class DeviceDtoApp {
	
	private Integer deviceId;    //本地数据库使用的主键 
	private String deviceNo; 
	private String  deviceName; 
	private Integer  deviceTypeId; 
	private String  deviceTypeName; 
	private Integer  deviceCategoryId; 
	private String  deviceCategoryName; 
	private String  gatewayNo; 
	private Integer  spaceTypeId; 
	private String  spaceNo; 
	private String  phoneNum; 
	private String  deviceStateCmd; 
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	public Integer getDeviceCategoryId() {
		return deviceCategoryId;
	}
	public void setDeviceCategoryId(Integer deviceCategoryId) {
		this.deviceCategoryId = deviceCategoryId;
	}
	public String getDeviceCategoryName() {
		return deviceCategoryName;
	}
	public void setDeviceCategoryName(String deviceCategoryName) {
		this.deviceCategoryName = deviceCategoryName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	public Integer getSpaceTypeId() {
		return spaceTypeId;
	}
	public void setSpaceTypeId(Integer spaceTypeId) {
		this.spaceTypeId = spaceTypeId;
	}
	public String getSpaceNo() {
		return spaceNo;
	}
	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getDeviceStateCmd() {
		return deviceStateCmd;
	}
	public void setDeviceStateCmd(String deviceStateCmd) {
		this.deviceStateCmd = deviceStateCmd;
	}
	public DeviceDtoApp() {
		super();
	}
	@Override
	public String toString() {
		return "DeviceDtoApp [deviceId=" + deviceId + ", deviceNo=" + deviceNo
				+ ", deviceName=" + deviceName + ", deviceTypeId="
				+ deviceTypeId + ", deviceTypeName=" + deviceTypeName
				+ ", deviceCategoryId=" + deviceCategoryId
				+ ", deviceCategoryName=" + deviceCategoryName + ", gatewayNo="
				+ gatewayNo + ", spaceTypeId=" + spaceTypeId + ", spaceNo="
				+ spaceNo + ", phoneNum=" + phoneNum + ", deviceStateCmd="
				+ deviceStateCmd + "]";
	}
	

}
