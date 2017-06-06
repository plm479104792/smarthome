package com.homecoo.smarthome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Theme;
import com.homecoo.smarthome.persistence.ThemeMapper;
import com.homecoo.smarthome.service.IThemeH2;

@Service
public class ThemeServiceH2Impl implements IThemeH2{
	
	@Autowired
	private ThemeMapper themeMapper;

	@Override
	public int addThemeH2(Theme theme) {
		int row=themeMapper.insertTheme(theme);
		return row;
	}

	@Override
	public int updateThemeH2(Theme theme) {
		int row=themeMapper.updateTheme(theme);
		return row;
	}

	@Override
	public int deleteThemeH2(String gatewayNo) {
		int row=themeMapper.deleteTheme(gatewayNo);
		return row;
	}

}
