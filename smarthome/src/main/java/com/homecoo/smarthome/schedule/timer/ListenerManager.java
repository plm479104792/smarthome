package com.homecoo.smarthome.schedule.timer;

import java.util.HashMap;
import java.util.Map;

import com.homecoo.smarthome.domain.Job;
import com.homecoo.smarthome.domain.WorkContext;
import com.homecoo.smarthome.schedule.exception.ExecutorTimerJobException;


public class ListenerManager {
	
	public TimerListener getDefaultListener() {
		return defaultListener;
	}

	private TimerListener defaultListener = new DefaultDoListener();
	public Map<WorkContext, TimerListener> listenerManager = new HashMap<WorkContext, TimerListener>();
	public void regeisterListener(WorkContext workContext){
		regeisterListener(workContext,defaultListener);
	}
	
	public void regeisterListener(WorkContext workContext , TimerListener listener){
		if(listener != null){
			listenerManager.put(workContext, listener);
		}else{
			listenerManager.put(workContext, defaultListener);
		}
	}
	
	public void activeListener(WorkContext workContext ) throws ExecutorTimerJobException{
		
		Map<String, Job> jobMap = workContext.getJobMap();
			if(jobMap != null){
				for(String jobId : jobMap.keySet()){
					Job job = jobMap.get(jobId);
					TimerListener timerListener = job.getTimerListener();
					if(timerListener == null){
						timerListener = defaultListener;
					}
					timerListener.doWorkBussiness(job);
			
				}
			}
		
		
	}
}
