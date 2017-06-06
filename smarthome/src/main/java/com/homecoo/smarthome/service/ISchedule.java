package com.homecoo.smarthome.service;

import java.util.List;

import com.homecoo.smarthome.domain.Schedule;

public interface ISchedule {
	
	int addShedlue(Schedule schedule);
	
	int deleteScheduleByScheduleId(Integer scheduleId);
	
	int deleteScheduleByPhoneNum(String phoneNum);

	int deleteScheduleByGatewayNo(String gatewayNo);
	
	int updateSchedule(Schedule schedule);
	
	int updateScheduleState(String schedule_id);
	
	List<Schedule> getScheduleByGatewayNo(String gatewayNo);

	List<Schedule> getScheduleByPhoneNum(String phoneNum);
	
	
	/**
	 * 获取定时歌曲
	 * */
	List<Schedule> getScheduleMusic(String phoneNum,String scheduleName);
	
	
	/**
	 * 根据 日期和 state(1 表示一次重复的任务 ) 获取当天要执行的定时任务
	 * */
	List<Schedule> getTodayAllSchedule(String date,String state);
	
	/**
	 * 根据 list.size()的  大小  判断同一手机号下   该设备定时   情景定时 在同一时间 是否已经存在 
	 * */
	List<Schedule> getSchedule(Schedule schedule);
	
	List<Schedule> getScheduleByDeviceNoAndPhoneNum(String deviceNo,String phoneNum);

	List<Schedule> getScheduleByThemeNoAndPhoneNum(String themeNo,String phoneNum);
	
	List<Schedule> getScheduleByStateAndPhoneNum(String state,String phoneNum);
	
	List<Schedule> getScheduleByScheduleId(String scheduleId);
	/**
	 * 根据当天是礼拜几 获取需要重复的定时任务0
	 * */
	List<Schedule> getTodayScheduleByWeek(String xingqi);
	
}
