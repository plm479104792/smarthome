package com.homecoo.smarthome.util;

/**
 * 常量工具类
 * */
public class NeedConstant {
	
	//================app版本信息常量=================
	public static final int VERSION_APP=1;				// app版本
	public static final int VERSION_DEVICE=2;			// 设备版本
	public static final int VERSION_SPACE=3;			// 空间版本
	public static final int VERSION_THEME=4;			// 情景版本
	public static final int VERSION_MUSIC=5;			// 音乐版本
	public static final int VERSION_GATEWAY=6;			// 网关版本
			
	//Message 回复消息常量
	public static final String SUCCESS_MESSAGE = "success";		//	返回成功
	public static final String ERROR_MESSAGE = "failed";		//  返回失败
	
	
	//版本信息
	public static final String VERSION_APP_EQUAL_SERVER="0";			//APP  服务器的一致；
	public static final String VERSION_APP_GTR_SERVER="1";			//服务器 最新
	public static final String VERSION_SERVER_GTR_APP="2";			//APP 最新
	
	public static final String SCHEDULESTATEMUSIC="3";		//音乐定时
	public static final String SCHEDULESTATEPAST="2";		//已经执行了
	public static final String SCHEDULESTATEDEVICE="1";		//除去音乐  一次性的定时任务
	public static final String SCHEDULESTATECYCLE="0";		//周期性定时
	
	public static final int JPUSH_DEVICESTATE_UPDATE=1;		//1:设备状态更新
	public static final int JPUSH_SECURITH_ALERT=2;			//2:安防报警
	public static final int JPUSH_MUSIC=3;					//3:音乐
	public static final int JPUSH_THEME=4;					// 4:情景类
	
	public static final String JPUSH_MUSIC_ORDE_PAUSE="1";		//1:暂停 
	public static final String JPUSH_MUSIC_ORDE_PLAY="2";		//2:播放
	public static final String JPUSH_MUSIC_ORDE_FORMER="3";		//3:上一首
	public static final String JPUSH_MUSIC_ORDE_NEXT="4";		//4:下一首
	
	public static final String JPUSH_MUSIC_STYLE_SINGER="1";	//1单曲循环
	public static final String JPUSH_MUSIC_STYLE_LIST="1";	//2单曲循环
	public static final String JPUSH_MUSIC_STYLE_RANDOM="1";	//3单曲循环
	
}
