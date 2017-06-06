package com.homecoo.smarthome.schedule.timer;


import com.homecoo.smarthome.domain.Job;
import com.homecoo.smarthome.schedule.exception.ExecutorTimerJobException;


public interface TimerListener {
	
	/**
	 * 业务处理
	 * @param workContext
	 * @throws ExecutorTimerJobException
	 */
	public void doWorkBussiness(Job job) throws ExecutorTimerJobException;
}	
