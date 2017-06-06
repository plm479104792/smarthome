package com.homecoo.smarthome.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*
import com.extr.domain.Application;
import com.extr.domain.Subscribe;
import com.extr.domain.User;
import com.extr.domain.Version;
import com.extr.persistence.UserMapper;
import com.extr.service.IUser;
import com.extr.util.Page;*/
import com.homecoo.smarthome.domain.User;
import com.homecoo.smarthome.persistence.UserMapper;
import com.homecoo.smarthome.service.IUser;

/**
 * @author Ocelot
 * @date 2014年6月8日 下午8:21:31
 */
@Service
public class UserServiceImpl implements IUser {

	@Autowired
	public UserMapper userMapper;
	


	@Override
	public int addUser(User user) {
			int userId = -1;
			List<User> list =userMapper.getUserByPhonenumReg(user.getPhonenum());
			if (list.size()>0) {
				userId=2;
				return userId;
			}else{
				userMapper.insertUser(user);
				List<User> list1 =userMapper.getUserByPhonenumReg(user.getPhonenum());
				return list1.size();
			}
	}

	

	@Override
	public User loadUserByPhonenum(String phonenum) {
		User user=userMapper.getUserByPhonenum(phonenum);
		return user;
	}



	@Override
	public User SelectUser(User user) {
		User record=userMapper.selectUser(user);
		return record;
	}



	@Override
	public int updateUser(User record) {
		int row=userMapper.updateUser(record);
		return row;
	}



	@Override
	public int getUserByPhone(String phonenum) {
		User user=userMapper.getUserByPhonenum(phonenum);
		if (user==null) {
			return 0;
		}else{
			return -1;
		}
	}



	@Override
	public int CancelUser(String phonenum) {
		int row=userMapper.CancelUser(phonenum);
		return row;
	}

}
