package com.homecoo.smarthome.service;

import java.util.List;


import com.homecoo.smarthome.domain.Music;
import com.homecoo.smarthome.domain.ThemeMusic;

public interface IMusicService {
	//添加音乐
	int addMusic(Music music);
	
	//根据手机号网关获取情景音乐
	ThemeMusic getThemeMusicByphoneNumgatewayNo(String themeNo,String gatewayNo);
	
	//查询单首
	Music selectSingleMusic(String uuid);
	
	//查询网关下所有
	List<Music> selecctAllMusic(String gatewayNo);
	
	//删除
	void deletdMusic(String gatewayNo);
	
	//查询情景音乐
	ThemeMusic getThemeMusic(ThemeMusic themeMusic);
	
	//删除情景音乐
	int deleteThemeMuisc(String themeNo,String gatewayNo);
	
	//设置情景音乐
	int setThemeMusic(ThemeMusic themeMusic);
	
	//更新情景音乐
	int updateThemeMusic(ThemeMusic themeMusic);
	
	//获取所有的情景音乐
	List<ThemeMusic> getAllThemeMusic(String gatewayNo);

	boolean getThemeMusicByThemeNo(String themeNo);
}
