package com.homecoo.smarthome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Music;
import com.homecoo.smarthome.domain.ThemeMusic;
import com.homecoo.smarthome.persistence.MusicMapper;
import com.homecoo.smarthome.persistence.ThemeMusicMapper;
import com.homecoo.smarthome.service.IMusicService;

@Service
public class MusicServiceImpl implements IMusicService{

	@Autowired
	private MusicMapper musicMapper;
	
	@Autowired
	private ThemeMusicMapper themeMusicMapper;

	@Override
	public int addMusic(Music music) {
		int count =musicMapper.addMusaic(music);
		return count;
	}

	@Override
	public Music selectSingleMusic(String uuid) {
		Music music=musicMapper.selectSingleMusic(uuid);
		return music;
	}
	
	@Override
	public List<Music> selecctAllMusic(String gatewayNo) {
		List<Music> listMusic=musicMapper.selectAllMusic(gatewayNo);
		return listMusic;
	}

	@Override
	public void deletdMusic(String gatewayNo) {
		musicMapper.deleteMusic(gatewayNo);
	}

	@Override
	public ThemeMusic getThemeMusic(ThemeMusic thememusic) {
			ThemeMusic themeMusic=themeMusicMapper.selectThemeMusic(thememusic);
			return themeMusic;
	}
	
	@Override
	public int deleteThemeMuisc(String themeNo, String gatewayNo) {
		ThemeMusic themeMusic=new ThemeMusic();
		themeMusic.setThemeNo(themeNo);
		themeMusic.setGatewayNo(gatewayNo);
		int row=themeMusicMapper.deleteThemeMusic(themeMusic);
		return row;
	}

	@Override
	public int setThemeMusic(ThemeMusic themeMusic) {
		int row=themeMusicMapper.insertThemeMusic(themeMusic);
		return row;
	}

	@Override
	public int updateThemeMusic(ThemeMusic themeMusic) {
		int row=themeMusicMapper.updateThemeMusic(themeMusic);
		return row;
	}

	@Override
	public ThemeMusic getThemeMusicByphoneNumgatewayNo(String themeNo,String gatewayNo) {
		ThemeMusic music=new ThemeMusic();
		music.setGatewayNo(gatewayNo);
		music.setThemeNo(themeNo);
		ThemeMusic record=themeMusicMapper.selectThemeMusic(music);
		return record;
	}

	@Override
	public List<ThemeMusic> getAllThemeMusic(String gatewayNo) {
		List<ThemeMusic> list=themeMusicMapper.getAllThemeMusicByGatewayNo(gatewayNo);
		return list;
	}

	@Override
	public boolean getThemeMusicByThemeNo(String themeNo) {
		List<ThemeMusic> list=themeMusicMapper.selectThemeMusicByThemeNo(themeNo);
		if (list.size()>0) {
			return true;
		}else{
			return false;
		}
	}
}
