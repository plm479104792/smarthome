package com.homecoo.smarthome.trigger;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.service.ISchedule;
import com.homecoo.smarthome.util.DateUtil;
import com.homecoo.smarthome.util.NeedConstant;
import com.homecoo.smarthome.util.ScheduleHandler;
import com.homecoo.smarthome.util.ScheduleTimeUtil;
import com.homecoo.smarthome.util.SpringUtil;

/**
 * Quartz 定时任务
 * */
public class LoadTodaySchedule extends QuartzJobBean{
	
		/*Quartz中,直接使用 spring的注解 是没有用的，spring 和 quartz 是两个不同的容器。没法
		Spring注入的mapper接口类在Quartz中不能通过@Autowired或者@Resource获取到的
		@Autowired
		private ISchedule scheduleService;
		 */		
		/**
		 * 静态的定时任务(每天凌晨0点的时候获取当天需要执行的定时任务)
		 * */
		@Override
		protected void executeInternal(JobExecutionContext context)
				throws JobExecutionException {
			Date date=new Date();
			String riqi=DateUtil.ToYMD(date);
			ISchedule scheduleService=SpringUtil.getObject(ISchedule.class);
			//根据  年月日 去查询当天需要执行的定时任务   1代表需要执行的任务   2:代表已经执行的单次定时任务
			List<Schedule> list= scheduleService.getTodayAllSchedule(riqi,"2");
//			List<Schedule> listMusic=scheduleService.getTodayAllSchedule(date, state);
			//根据 星期去查询当天需要执行的 重复的定时任务
			String xingqi=ScheduleTimeUtil.getWeek(new Date());
			List<Schedule> listrepeat=scheduleService.getTodayScheduleByWeek(xingqi);
			Iterator<Schedule> iter =listrepeat.iterator();
			Iterator<Schedule> iterator=list.iterator();
			System.out.println("一次性定时任务有:   "+list.size()+"     周期性定时任务有:	"+listrepeat.size());
			while (iterator.hasNext()) {
				Schedule schedule=iterator.next();
				System.out.println(schedule.getScheduleId()+"	加载一次性定时任务		"+schedule.toString());
					ScheduleHandler.SendMessage(schedule);
			}
			//添加星期重复性的  定时任务
			while (iter.hasNext()) {
				Schedule schedule=iter.next();
				System.out.println(schedule.getScheduleId()+"	加载周期性定时任务		"+schedule.toString());
					ScheduleHandler.SendMessage(schedule);
			}
			
		}
}
