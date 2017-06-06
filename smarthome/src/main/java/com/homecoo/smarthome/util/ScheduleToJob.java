package com.homecoo.smarthome.util;


import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.Job;
import com.homecoo.smarthome.domain.Jpush;
import com.homecoo.smarthome.domain.MusicOrder;
import com.homecoo.smarthome.domain.Schedule;


/**
 * @author xiaobai
 * @Description:数据库查询的记录转换为  workcontext的job
 * */
public class ScheduleToJob {
	
	/**
	 * 转为 workcontext的 job
	 * */
	public  static Job ToJob(Schedule schedule){
		Job job=new Job();
		if (schedule.getState().equals(NeedConstant.SCHEDULESTATEMUSIC)) {
			String data=schedule.toString();
			byte[] data1=data.getBytes();
			job.setData(data1);
		}else{
			job.setData(BasicProcess.toByteArray(schedule.getPacketData()));
		}
		
		job.setJobId(String.valueOf(schedule.getScheduleId()));
		return job;
		
	}
	
	/**
	 * @Description:schedule 转  MusicOrder  定时
	 * @param:Schedule
	 * @return:JPush
	 * */
	public static Jpush toMusicOrder(Schedule schedule){
		
		Jpush jpush=new Jpush();
		MusicOrder order=new MusicOrder();
		order.setBz("");
		order.setOrder(NeedConstant.JPUSH_MUSIC_ORDE_PLAY);
		order.setSongName(schedule.getScheduleName());
		// TODO   2016-08-30
		if (schedule.getScheduleName().equals("暂停音乐")) {
			order.setBz("00");  //七寸屏音乐播放  暂停音乐
			order.setOrder(NeedConstant.JPUSH_MUSIC_ORDE_PAUSE);
		}
		order.setStyle(NeedConstant.JPUSH_MUSIC_STYLE_SINGER);
		order.setWgid(schedule.getGatewayNo());
		
		jpush.setMesssageType(NeedConstant.JPUSH_MUSIC);
		jpush.setGatewayNo(order.getWgid());
		jpush.setObject(JSONObject.toJSONString(order));
		jpush.setTime(0L);
		
		return jpush;
		
	}
	

}
