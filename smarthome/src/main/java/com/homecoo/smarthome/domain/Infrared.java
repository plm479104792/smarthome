package com.homecoo.smarthome.domain;

public class Infrared {
	
	private Integer infraredId;   //主键
	private String deviceNo;	//设备ID
	private String deviceStateCmd;	//设备状态
	private String gatewayNo;		//网关号
	private String infraCrlName;	//红外控制设备名称
	private Integer infraTypeId;	//红外控制设备类型
	private String themeNo;		
	public Integer getInfraredId() {
		return infraredId;
	}
	public void setInfraredId(Integer infraredId) {
		this.infraredId = infraredId;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceStateCmd() {
		return deviceStateCmd;
	}
	public void setDeviceStateCmd(String deviceStateCmd) {
		this.deviceStateCmd = deviceStateCmd;
	}
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	public String getInfraCrlName() {
		return infraCrlName;
	}
	public void setInfraCrlName(String infraCrlName) {
		this.infraCrlName = infraCrlName;
	}
	
	public Integer getInfraTypeId() {
		return infraTypeId;
	}
	public void setInfraTypeId(Integer infraTypeId) {
		this.infraTypeId = infraTypeId;
	}
	public String getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}
	@Override
	public String toString() {
		return "Infrared [infraredId=" + infraredId + ", deviceNo=" + deviceNo
				+ ", deviceStateCmd=" + deviceStateCmd + ", gatewayNo="
				+ gatewayNo + ", infraCrlName=" + infraCrlName
				+ ", infraTypeId=" + infraTypeId + ", themeNo=" + themeNo + "]";
	}
	
	
}
