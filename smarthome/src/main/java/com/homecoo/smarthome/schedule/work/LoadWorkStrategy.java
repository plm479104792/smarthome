package com.homecoo.smarthome.schedule.work;

import java.util.List;

import com.homecoo.smarthome.domain.WorkContext;

public interface LoadWorkStrategy {
	
	/**
	 * 类型,load的时间
	 * @param time
	 * @param type
	 * @return
	 */
	public List<WorkContext> loadWork(long time , int type); 
}
