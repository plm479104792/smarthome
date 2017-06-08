package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Device;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer deviceId);
    
    int deleteByDeviceNo(@Param("deviceNo")String deviceNo);
    
    int insert(Device record);

    int insertSelective(Device record);

    int updateByPrimaryKeySelective(Device record);
    
    List<Device> getAllDeviceByPhoneNum(@Param("phoneNum")String phoneNum);
    
    List<Device> getALLDeviceByPhoneNumCategoryId(Device device);
    
    List<Device> getAllDeviceByGatewayNo(@Param("gatewayNo")String gatewayNo);
    
    List<Device> getDeviceByDeviceNo(Device device);
    
    int updateByPrimaryKey(Device record);
    
    
}