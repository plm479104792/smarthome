package com.homecoo.smarthome.domain;

public class Volume {
	private Integer id;				//数据库主键，你传过来的参数设置为空
	private String gatewayNo;		//网关号
	private String volume;			//音量大小 String类型的 数字   0-7
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	@Override
	public String toString() {
		return "Volume [id=" + id + ", gatewayNo=" + gatewayNo + ", volume="
				+ volume + "]";
	}
	
}
