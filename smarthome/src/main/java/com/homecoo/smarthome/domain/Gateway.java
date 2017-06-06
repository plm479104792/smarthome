package com.homecoo.smarthome.domain;

import java.util.Date;

public class Gateway {
    private String gatewayNo;

    private String gatewayIp;

    private String gatewayPwd;
    
    private Date createTime;

    private Integer createBy;

    private Date updateTime;

    private Integer updateBy;
    
    
    public String getGatewayNo() {
        return gatewayNo;
    }

    public void setGatewayNo(String gatewayNo) {
        this.gatewayNo = gatewayNo;
    }

    public String getGatewayIp() {
        return gatewayIp;
    }

    public void setGatewayIp(String gatewayIp) {
        this.gatewayIp = gatewayIp;
    }

    public String getGatewayPwd() {
        return gatewayPwd;
    }

    public void setGatewayPwd(String gatewayPwd) {
        this.gatewayPwd = gatewayPwd;
    }


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return "Gateway [gatewayNo=" + gatewayNo + ", gatewayIp=" + gatewayIp
				+ ", gatewayPwd=" + gatewayPwd + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateTime=" + updateTime
				+ ", updateBy=" + updateBy + "]";
	}

	
}