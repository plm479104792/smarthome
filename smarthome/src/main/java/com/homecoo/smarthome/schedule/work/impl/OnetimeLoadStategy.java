package com.homecoo.smarthome.schedule.work.impl;

import java.text.SimpleDateFormat;
import java.util.List;


import com.homecoo.smarthome.domain.WorkContext;
import com.homecoo.smarthome.schedule.work.LoadWorkStrategy;


/**
 * 这个是一次加载的执行任务策略实现你要写如何load这些任务
 * 
 * 这里每天0点获取当天需要执行的定时任务,这里要获取数据库一次
 * @author hsj
 *
 */
public class OnetimeLoadStategy implements LoadWorkStrategy {

	@Override
	public List<WorkContext> loadWork(long time, int type) {
		System.out.println("当前执行了load操作 type 是"+type+"time" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//		ISchedule scheduleService =SpringUtil.getObject(ISchedule.class);
//		List<Schedule> list=scheduleService.getTodayAllSchedule(DateUtil.ToYMD(new Date(time)));
//		System.out.println(list.size());
//		Iterator<Schedule> iterator=list.iterator();
//		List<WorkContext> listWorkContexts=new ArrayList<WorkContext>();
//		while (iterator.hasNext()) {
//			Schedule schedule=new Schedule();
//			schedule=iterator.next();
//			WorkContext workContext=ScheduleToWorkContext.ToWorkContext(schedule);
//			listWorkContexts.add(workContext);
//		}
		return null;
	}

}
