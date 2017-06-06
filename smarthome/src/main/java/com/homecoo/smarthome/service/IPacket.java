package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.Packet;

public interface IPacket {
	
	List<Packet> selectPacket(String gatewayNo);
	
	int addPacket(Packet packet);
	
}
