package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.UserDeviceSpace;

public interface UserDeviceSpaceMapper {
	
	//根据手机号获取空间名称
	List<UserDeviceSpace> selectUserDeviceSpaceByPhoneNum(@Param("phoneNum")String phoneNum);
	
	//根据spaceNo获取空间名称
	List<UserDeviceSpace> selectUserDeviceSpaceBySpaceNo(@Param("spaceNo")String spaceNo);
	
	//根据设备id查询空间名称
	List<UserDeviceSpace> selectUserDeviceSpaceByDeviceNo(UserDeviceSpace deviceSpace);
	
	//根据spaceNo判断是否存在
	int getUserDeviceSpace(@Param("spaceNo")String spaceNo);
	
	//添加
	int insertUserDeviceSpace(UserDeviceSpace userDeviceSpace);
	
	//根据手机号删除
	int deleteUserDeviceSpaceByPhoneNum(@Param("phoneNum")String phoneNum);
	
	//根据设备ID删除
	int deleteUserDeviceSpaceByDeviceNo(@Param("deviceNo")String deviceNo);
	
	//更新
	int updateUserDeviceSpace(UserDeviceSpace userDeviceSpace);
	
}
