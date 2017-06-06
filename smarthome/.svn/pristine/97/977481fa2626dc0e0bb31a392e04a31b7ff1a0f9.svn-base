package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Packet;

public interface PacketMapper {
	
	int insertPacket(Packet packet);
	
	List<Packet> selectAllPacket(@Param("gatewayNo")String gatewayNo);
}
