package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Packet;
import com.homecoo.smarthome.persistence.PacketMapper;
import com.homecoo.smarthome.service.IPacket;
@Service
public class PacketServiceImpl implements IPacket{

	@Autowired
	private PacketMapper packetMapper;
	
	
	@Override
	public int addPacket(Packet packet) {
		int row=packetMapper.insertPacket(packet);
		return row;
	}


	@Override
	public List<Packet> selectPacket(String gatewayNo) {
		List<Packet> list=packetMapper.selectAllPacket(gatewayNo);
		return list;
	}
	

}
