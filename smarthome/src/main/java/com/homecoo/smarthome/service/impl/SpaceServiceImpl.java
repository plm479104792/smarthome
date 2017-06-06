package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Space;
import com.homecoo.smarthome.persistence.SpaceMapper;
import com.homecoo.smarthome.service.ISpace;

@Service
public class SpaceServiceImpl implements ISpace{
	
	@Autowired
	private SpaceMapper spaceMapper;

	@Override
	public int addSpace(Space space) {
		int row=spaceMapper.addSpace(space);
		return row;
	}

	@Override
	public int updateSapce(Space space) {
		int row=spaceMapper.updateSapce(space);
		return row;
	}

	@Override
	public void deleteSpace(String SpaceNo, String phoneNum) {
		Space space=new Space();
		space.setSpaceNo(SpaceNo);
		space.setPhoneNum(phoneNum);
		spaceMapper.deleteSpace(space);
	}

	@Override
	public List<Space> getAllSpaceByPhoneNum(String phoneNum) {
		List<Space> list=spaceMapper.getAllSpaceByPhoneNum(phoneNum);
		return list;
	}

	@Override
	public Space getSpaceBySpaceNoPhoneNum(String phoneNum, String spaceNo) {
			Space space=spaceMapper.getSpaceBySpaceNoPhoneNum(phoneNum, spaceNo);
			return space;
	}

	@Override
	public void deleteSpaceByPhonenum(String phonenum) {
		spaceMapper.deleteSpaceByPhonenum(phonenum);
		
	}


}
