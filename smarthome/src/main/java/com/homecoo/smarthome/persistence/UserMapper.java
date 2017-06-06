package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Role;
import com.homecoo.smarthome.domain.User;

public interface UserMapper {
	
	//注册后查询，还不够完善
	List<User> getUserByPhonenumReg(String phonenum);
	
	//根据phonenum 获取User
	User getUserByPhonenum(@Param("phonenum")String phonenum);
	
	//注册
	public int insertUser(User user);

	//重置账号
    int updateUser(User record);
    
    //登录
    User selectUser(User user);
	
    //没有用到
    List<Role> getRoleListByUserId(Integer userId, Object object);
    
    //注销用户 2016-07-31
    int CancelUser(@Param("phonenum")String phonenum);
    
}