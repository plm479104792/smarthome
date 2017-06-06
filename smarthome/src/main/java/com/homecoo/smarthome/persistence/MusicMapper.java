package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Music;

public interface MusicMapper {
	
	int addMusaic(Music music);
	
	Music selectSingleMusic(@Param("uuid")String uuid);
	
	List<Music> selectAllMusic(@Param("gatewayNo")String gatewayNo);

	void deleteMusic(@Param("gatewayNo")String gatewayNo);
}
