package com.homecoo.smarthome.domain;

public class UserDeviceSpace {

	private String deviceNo;
	
	private String phoneNum;
	
	private String spaceNo;
	
	private String deviceName;
	
	private Integer spaceType;
	
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSpaceNo() {
		return spaceNo;
	}

	public void setSpaceNo(String spaceNo) {
		this.spaceNo = spaceNo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public Integer getSpaceType() {
		return spaceType;
	}

	public void setSpaceType(Integer spaceType) {
		this.spaceType = spaceType;
	}

	@Override
	public String toString() {
		return "UserDeviceSpace [deviceNo=" + deviceNo + ", phoneNum="
				+ phoneNum + ", spaceNo=" + spaceNo + ", deviceName="
				+ deviceName + ", spaceType=" + spaceType + "]";
	}

	
}
