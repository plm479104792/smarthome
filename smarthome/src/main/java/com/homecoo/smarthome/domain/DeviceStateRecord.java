package com.homecoo.smarthome.domain;

import java.util.Date;

public class DeviceStateRecord {
    private Integer recordId;

    private String deviceNo;

    private String deviceStateCmd;
    
    private String gatewayNo;

    private Date lastUpdateTime;

    private Integer lastUpdateBy;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getGatewayNo() {
		return gatewayNo;
	}

	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	@Override
	public String toString() {
		return "DeviceStateRecord [recordId=" + recordId + ", deviceNo="
				+ deviceNo + ", deviceStateCmd=" + deviceStateCmd
				+ ", gatewayNo=" + gatewayNo + ", lastUpdateTime="
				+ lastUpdateTime + ", lastUpdateBy=" + lastUpdateBy + "]";
	}

	
    
}