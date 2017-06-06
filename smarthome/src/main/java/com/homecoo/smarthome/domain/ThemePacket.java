package com.homecoo.smarthome.domain;

import java.util.Date;

public class ThemePacket {

	private Integer themepacketid;

	private String themempacket;

	private String gatewayNo;

	private Date createTime;

	public Integer getThemepacketid() {
		return themepacketid;
	}

	public void setThemepacketid(Integer themepacketid) {
		this.themepacketid = themepacketid;
	}

	public String getThemempacket() {
		return themempacket;
	}

	public void setThemempacket(String themempacket) {
		this.themempacket = themempacket;
	}

	public String getGatewayNo() {
		return gatewayNo;
	}

	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
