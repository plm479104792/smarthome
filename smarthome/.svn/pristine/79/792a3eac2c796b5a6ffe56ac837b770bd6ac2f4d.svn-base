package com.homecoo.smarthome.persistence;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.DeviceStateRecord;

public interface DeviceStateRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(DeviceStateRecord record);

    int insertSelective(DeviceStateRecord record);

    DeviceStateRecord selectByDeviceNo(@Param("deviceNo")String deviceNo);

    int updateByPrimaryKeySelective(DeviceStateRecord record);

    int updateByPrimaryKey(DeviceStateRecord record);
}