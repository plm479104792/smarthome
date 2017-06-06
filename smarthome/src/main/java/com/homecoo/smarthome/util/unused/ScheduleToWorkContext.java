package com.homecoo.smarthome.util.unused;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.domain.WorkContext;
import com.homecoo.smarthome.util.BasicProcess;
import com.homecoo.smarthome.util.DateUtil;

/**
 * mysql查询到的定时任务  转换为发送到netty的workcontext(没有用上了)
 * */
public class ScheduleToWorkContext {
	
	public static WorkContext ToWorkContext(Schedule schedule){
		WorkContext workContext=new WorkContext();
		String date=null;
		if (schedule.getRiqi().isEmpty()) {
			date=DateUtil.ToYMD(new Date());
		}else{
			date=schedule.getRiqi();
		}
		String shij=schedule.getShij();
		String time1=date+" "+shij;
		Long time=DateUtil.StringToDate(time1, "yyyy-MM-dd hh:mm:ss.SSS").getTime();
		workContext.setExecuteTime(time);
		Map<String,byte[]> map=new HashMap<String, byte[]>();
		map.put(String.valueOf(schedule.getScheduleId()), BasicProcess.toByteArray(schedule.getPacketData()));
//		workContext.setSendMapData(map);
		return workContext;
	} 

}
