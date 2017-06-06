package com.homecoo.smarthome.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homecoo.smarthome.domain.Theme;

public interface ThemeMapper {
    int insertTheme(Theme record);
    
    int deleteTheme(@Param("gatewayNo")String gatewayNo);

    int updateTheme(Theme theme);
    
    List<Theme> selectAllThemeByPhoneNum(@Param("gatewayNo")String gatewayNo);
    
    List<Theme> selectThemeByThemeNoGatewayNo(@Param("themeNo")String themeNo);
}