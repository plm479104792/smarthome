package com.homecoo.smarthome.persistence;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.ThemeMusic;

public interface ThemeMusicMapper {
	
	ThemeMusic selectThemeMusic(ThemeMusic music);
	
	List<ThemeMusic> getAllThemeMusicByGatewayNo(@Param("gatewayNo")String gatewayNo);
	
	List<ThemeMusic> selectThemeMusicByThemeNo(@Param("themeNo")String themeNo);
	
	int deleteThemeMusic(ThemeMusic themeMusic);
	
	int insertThemeMusic(ThemeMusic themeMusic);
	
	int updateThemeMusic(ThemeMusic themeMusic);
}
