package com.homecoo.smarthome.domain;

import java.util.Date;

public class DeviceCategory {
    private Integer deviceCategoryId;

    private String deviceCategoryName;

    private Date createTime;

    private Integer createBy;

    private Date updateTime;

    private Integer updateBy;


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
		return "DeviceCategory [deviceCategoryId=" + deviceCategoryId
				+ ", deviceCategoryName=" + deviceCategoryName
				+ ", createTime=" + createTime + ", createBy=" + createBy
				+ ", updateTime=" + updateTime + ", updateBy=" + updateBy + "]";
	}

}