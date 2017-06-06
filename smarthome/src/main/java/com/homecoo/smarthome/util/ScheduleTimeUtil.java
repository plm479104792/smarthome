package com.homecoo.smarthome.util;

import java.util.Calendar;
import java.util.Date;

import com.homecoo.smarthome.domain.Schedule;

/**
 * @Description:定时任务 时间处理工具类
 * @author xiaobai
 * */
public class ScheduleTimeUtil {

		/**
		 * 0:过时的定时任务   1:未来的定时任务   2:当天的定时任务
		 * 比较  app定时任务的时间 与当前系统的时间的大小   86400000是相差一天的毫秒数
		 * @return : 0:过时的定时任务     1:未来的定时任务    2:当天的定时任务
		 * */
		public static int Differ(Schedule schedule){
			int a=0;
			Long scheduleTime=ScheduleTimeUtil.getScheduleTime(schedule);
			Long systemTime=System.currentTimeMillis();
			Long differ = scheduleTime-systemTime;
			System.out.println("differ "+differ+" systemTime	"+systemTime+"	scheduleTime	"+scheduleTime);
			if (differ<0) {
				if (schedule.getState().equals("0") || schedule.getStrategy().equals("2")) {
					// 重复性的定时任务
					a=1;
				}else {
					//定时的任务时间小于 当前系统时间
					a=0;
				}
			}else if(differ>=0 && differ<86400000){
				//定时时间 是当天的定时任务
				a=2;
			}else if (differ>86400000) {
				//未来的定时任务
				a=1;
			}
			return a;
		}
		
		/**
		 * 获取app设置的定时任务 定时任务的定时时间  
		 * @param:Schedule
		 * @return:time(Long) 定时任务触发时间
		 * */
		public static Long getScheduleTime(Schedule schedule){
			String date=null;
			if (schedule.getRiqi().isEmpty() || schedule.getRiqi()==null) {
				//重复性定时任务的   年月日
				date=DateUtil.ToYMD(new Date());
			}else{
				//一次性定时任务的   年月日
				date=schedule.getRiqi();
			}
			String shij=schedule.getShij();
			String a=date+" "+shij;
			Long time=DateUtil.StringToDate(a, "yyyy-MM-dd hh:mm:ss").getTime();
			System.out.println("	getScheduleTime	"+time);
			return time;
			
		}
		
		/**
		 * 获取当天 礼拜几   日历是  礼拜日(1)  礼拜一(2)  礼拜二(3)  礼拜三(4)  礼拜四(5)  礼拜五(6)  礼拜六(7)
		 * 当天是礼拜几则显示  _____1_ (礼拜5)
		 * @param:Date
		 * @return:星期
		 * */
		public static String getWeek(Date date){
			String riqi=null;
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			int i=calendar.get(Calendar.DAY_OF_WEEK);
			switch (i) {
			case 1:
				riqi="______1";
				break;
			case 2:
				riqi="1______";
				break;
			case 3:
				riqi="_1_____";
				break;
			case 4:
				//礼拜3
				riqi="__1____";
				break;
			case 5:
				//礼拜4
				riqi="___1___";
				break;
			case 6:
				//礼拜5
				riqi="____1__";
				break;
			case 7:
				//礼拜六
				riqi="_____1_";
				break;
			default:
				break;
			}
			return riqi;
		}
}
