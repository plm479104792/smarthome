package com.homecoo.smarthome.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.homecoo.smarthome.domain.APPThemeMusic;
import com.homecoo.smarthome.domain.ThemeMusic;


/**
 * 自定义的音乐工具类
 * */
public class MusicUtils {

	public static List<ThemeMusic> AppthememusicListToThememusicList(List<APPThemeMusic> appThemeMusics){
		List<ThemeMusic> list=new ArrayList<ThemeMusic>();
		Iterator<APPThemeMusic> iterator=appThemeMusics.iterator();
		while (iterator.hasNext()) {
			APPThemeMusic appThemeMusic = (APPThemeMusic) iterator.next();
			ThemeMusic themeMusic= new ThemeMusic();
			themeMusic.setBz(appThemeMusic.getBz());
			themeMusic.setDeviceNo(appThemeMusic.getDeviceNo());
			themeMusic.setDeviceState(appThemeMusic.getDeviceState());
			themeMusic.setGatewayNo(appThemeMusic.getGatewayNo());
			themeMusic.setSongName(appThemeMusic.getSongName());
			themeMusic.setSpace(appThemeMusic.getSpace());
			themeMusic.setStyle(appThemeMusic.getStyle());
			themeMusic.setThemeName(appThemeMusic.getThemeName());
			themeMusic.setThemeNo(appThemeMusic.getThemeNo());
			list.add(themeMusic);
		}
		
		return list;
		
	}
			
}
