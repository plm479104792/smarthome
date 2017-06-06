package com.homecoo.smarthome.util;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.Job;
import com.homecoo.smarthome.domain.Jpush;
import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.schedule.exception.ExecutorTimerJobException;
import com.homecoo.smarthome.schedule.timer.TimerListener;
import com.homecoo.smarthome.service.ISchedule;
import com.homecoo.smarthome.service.TimeManagerService;

/**
 * @Description:定时任务的处理工具类
 * @author xiaobai
 * */
public class ScheduleHandler {
	
	private static String state="";
	private static Schedule scheduleShort=null;
	
	/**
	 * 把当天的定时任务放到 TreeMap 上
	 * */
	public static void SendMessage(Schedule schedule){
//		System.out.println("   触发定时任务      "+	schedule.getScheduleId());
		state="";
		scheduleShort=null;
		TimeManagerService managerService=SpringUtil.getObject(TimeManagerService.class);
		if (schedule.getState().equals(NeedConstant.SCHEDULESTATEMUSIC)) {
			state=NeedConstant.SCHEDULESTATEMUSIC;
			scheduleShort=schedule;
		}
		Job job=ScheduleToJob.ToJob(schedule);
		Long time = ScheduleTimeUtil.getScheduleTime(schedule);
		managerService.putSendRequest(time, job, new TimerListener() {
			
			@Override
			public void doWorkBussiness(Job job) throws ExecutorTimerJobException {
				SimpleSocketClient socketClient=new SimpleSocketClient();
				ISchedule scheduleService =SpringUtil.getObject(ISchedule.class);
				try {
					if (state.equals(NeedConstant.SCHEDULESTATEMUSIC)) {
						
//						System.out.println("															");
						System.out.println("                  JPUSH   推送音乐定时");
						PushScheduleMusic(scheduleShort);
					}else{
						socketClient.sendMessage(job.getData());
					}
					System.out.println("执行了定时任务,定时任务ID : "+job.getJobId());
					//每次 执行定时任务之后，判断下该定时任务是否是一次性的定时任务,如果是:更新数据库;不是不做处理
					scheduleService.updateScheduleState(job.getJobId());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	/**
	 * 取消定时任务
	 * */
	public static void CancelSchedule(Long time,String scheduleId){
//		TimeManagerService timeManagerService=new TimeManagerService();
		TimeManagerService timeManagerService=SpringUtil.getObject(TimeManagerService.class);
		timeManagerService.cancleJob(time, scheduleId);
	}
	
	/**
	 * 判断被删除的定时任务是否需要在treeMap上取消 定时
	 * */
	public static void DeleteSchedule(List<Schedule> schedule){
		TimeManagerService timeManagerService=SpringUtil.getObject(TimeManagerService.class);
		for (int i = 0; i < schedule.size(); i++) {
			int type=ScheduleTimeUtil.Differ(schedule.get(i));
			//时间差 大于当时系统时间且小于第二天的时间  表示是当天需要执行的定时任务  需要在treeMap的定时任务中取消
			if (type==2) {
				Long time = ScheduleTimeUtil.getScheduleTime(schedule.get(i));
				timeManagerService.cancleJob(time, String.valueOf(schedule.get(i).getScheduleId()));
			}
			
		}
	}
	
	/**
	 * 根据相应的条件查询数据库，获取到的所有定时任务  并把过时的定时任务从list集合上去掉 再返回给APP。
	 * 不从sql语句上处理的原因是 : sql语句可以处理但是效率极差
	 * */
	public static List<Schedule> DeleteOutTimeSchedule(List<Schedule> list){
		List<Schedule> schedules=new ArrayList();
		Iterator<Schedule> iterator=list.iterator();
		while (iterator.hasNext()) {
			Schedule schedule=iterator.next();
			if (!schedule.getState().equals("2")) {
				schedules.add(schedule);
			}
		}
		return schedules;
		
	}
	
	/**
	 * @Description:3种情况       1:之前的定时任务是未来时间，更新后的时间为当天的定时任务，我们需要添加到treeMap 上,   
	 * 						 2:之前的定时任务就是当天   未  执行的，我们需要在treeMap上取消 之前 定时任务，在添加更新的定时任务
	 * 						 3:之前的定时任务就是当天  已经  执行的，我们只需直接在treeMap上添加定时任务
	 * 				0:过去的定时任务   1:未来的定时任务   2:当天的定时任务
	 * */							
	public static void UpdateSchedule(Schedule newSchedule,Schedule oldSchedule){
		//之前定时任务的定时时间
		Long timeold=ScheduleTimeUtil.getScheduleTime(oldSchedule);
		//获取更新后的定时任务的定时时间  
		int olddiffer=ScheduleTimeUtil.Differ(oldSchedule);
		int newdiffer=ScheduleTimeUtil.Differ(newSchedule);
		if (olddiffer==1) {
			//未来的定时任务   
			if (newdiffer==1) {
				//不做处理
			}else if (newdiffer==2) {
				//情况 1
				ScheduleHandler.SendMessage(newSchedule);
			}
			
		}else if (olddiffer==2) {
			//当天的定时任务
			if (newdiffer==2) {
				//情况2
				ScheduleHandler.CancelSchedule(timeold, String.valueOf(oldSchedule.getScheduleId()));
				ScheduleHandler.SendMessage(newSchedule);
			}else if (newdiffer==1) {
				//不处理
			}else if (newdiffer==0) {
				//情况3
				ScheduleHandler.SendMessage(newSchedule);
			}
			
		}
		
	}
	
	/**
	 * 定时音乐   JPush推送
	 * */
	public static void PushScheduleMusic(Schedule schedule){
		JPushimpl jPushimpl=new JPushimpl();
		Jpush jpush=ScheduleToJob.toMusicOrder(schedule);
		jPushimpl.sendPush(jpush);
	}
	
}
