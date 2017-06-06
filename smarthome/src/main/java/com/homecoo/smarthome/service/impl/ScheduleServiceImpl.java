package com.homecoo.smarthome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.persistence.ScheduleMapper;
import com.homecoo.smarthome.service.ISchedule;

@Service
public class ScheduleServiceImpl implements ISchedule{

	@Autowired
	private ScheduleMapper scheduleMapper;
	
	@Override
	public List<Schedule> getScheduleByGatewayNo(String gatewayNo) {
		List<Schedule> list=scheduleMapper.selectScheduleBygatewayNo(gatewayNo);
		return list;
	}

	@Override
	public List<Schedule> getScheduleByPhoneNum(String phoneNum) {
		List<Schedule> list=scheduleMapper.selectScheduleByphoneNum(phoneNum);
		return list;
	}

	@Override
	public int deleteScheduleByPhoneNum(String phoneNum) {
		int row=scheduleMapper.deleteScheduleByPhoneNum(phoneNum);
		return row;
	}

	@Override
	public int deleteScheduleByGatewayNo(String gatewayNo) {
		int row=scheduleMapper.deleteScheduleByGatewayNo(gatewayNo);
		return row;
	}


	@Override
	public List<Schedule> getTodayAllSchedule(String date,String state) {
		Schedule schedule=new Schedule();
		schedule.setRiqi(date);
		schedule.setState(state);
		List<Schedule> list=scheduleMapper.selectTodayAllScheduleByRiqi(schedule);
		return list;
	}

	@Override
	public List<Schedule> getSchedule(Schedule schedule) {
		List<Schedule> list=new ArrayList();
		try {
			if (schedule.getStrategy().equals("1")) {
				
				if (schedule.getDeviceNo()!=null) {
					list=scheduleMapper.selectScheduleByRiQiShiJDeviceNo(schedule);
				}else{
					list=scheduleMapper.selectScheduleByRiQiShiJThemeNo(schedule);
				}
			}else if (schedule.getStrategy().equals("2")) {
				System.out.println("  星期    "+schedule.getXingqi());
				if (schedule.getDeviceNo()!=null) {
					list=scheduleMapper.selectScheduleByXingQiShiJDeviceNo(schedule);
				}else{
					list=scheduleMapper.selectScheduleByXingQiShiJThemeNo(schedule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addShedlue(Schedule schedule) {
		int row= scheduleMapper.insertSchedule(schedule);
		return row;
	}

	@Override
	public List<Schedule> getScheduleByDeviceNoAndPhoneNum(String deviceNo,
			String phoneNum) {
		Schedule schedule=new Schedule();
		schedule.setDeviceNo(deviceNo);
		schedule.setPhoneNum(phoneNum);
		List<Schedule> list=scheduleMapper.getScheduleByDeviceNoAndPhoenNum(schedule);
		return list;
	}

	@Override
	public List<Schedule> getScheduleByThemeNoAndPhoneNum(String themeNo,
			String phoneNum) {
		Schedule schedule=new Schedule();
		schedule.setThemeNo(themeNo);
		schedule.setPhoneNum(phoneNum);
		List<Schedule> list=scheduleMapper.getScheduleByThemeNoAndPhoenNum(schedule);
		return list;
	}

	/**
	 * type  1:设备   2:情景
	 * */
	@Override
	public int updateSchedule(Schedule schedule) {
			int row= scheduleMapper.updateSchedule(schedule);
			return row;
	}

	
	@Override
	public int deleteScheduleByScheduleId(Integer scheduleId) {
		int row=scheduleMapper.deleteScheduleByScheduleId(scheduleId);
		return row;
	}

	@Override
	public List<Schedule> getScheduleByScheduleId(String scheduleId) {
		List<Schedule> schedule=scheduleMapper.getScheduleByScheduleId(Integer.valueOf(scheduleId));
		return schedule;
	}

	@Override
	public List<Schedule> getTodayScheduleByWeek(String xingqi) {
		List<Schedule> list=scheduleMapper.selectTodayAllScheduleByWeek(xingqi);
		return list;
	}

	@Override
	public int updateScheduleState(String schedule_id) {
		List<Schedule> list=scheduleMapper.getScheduleByScheduleId(Integer.valueOf(schedule_id));
		System.out.println("-============================================================	"+list.get(0).toString());
		Schedule schedule=list.get(0);
		schedule.setState("2");
		int row=0;
		if (schedule.getStrategy().equals("1")) {
			//表示一次性的定时任务
			//单次的定时任务  ，发送出去之后就更新state 变为2(已经触发)
			 row=scheduleMapper.updateSchedule(schedule);
//			//删出已经执行过的一次性定时任务
//			 row=scheduleMapper.deleteScheduleByScheduleId(Integer.valueOf(schedule_id));
		}
//		row=scheduleMapper.updateSchedule(schedule);
//		System.out.println("===============================================================");
		return row;
	}
	
	@Override
	public List<Schedule> getScheduleMusic(String phoneNum,String scheduleName) {
		Schedule schedule=new Schedule();
		schedule.setPhoneNum(phoneNum);
		schedule.setScheduleName(scheduleName);
		List<Schedule> list = scheduleMapper.getScheduleMusic(schedule);
		return list;
	}

	@Override
	public List<Schedule> getScheduleByStateAndPhoneNum(String state,
			String phoneNum) {
		Schedule schedule=new Schedule();
		schedule.setState(state);
		schedule.setPhoneNum(phoneNum);
		List<Schedule> list=scheduleMapper.getScheduleByStateAndPhoneNum(schedule);
		return list;
	}
	

}
