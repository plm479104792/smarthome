package com.homecoo.smarthome.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homecoo.smarthome.domain.Job;
import com.homecoo.smarthome.domain.WorkContext;
import com.homecoo.smarthome.schedule.exception.ExecutorTimerJobException;
import com.homecoo.smarthome.schedule.timer.ListenerManager;
import com.homecoo.smarthome.schedule.timer.TimerListener;
import com.homecoo.smarthome.schedule.work.factory.LoadWorkStrategyManager;
import com.homecoo.smarthome.schedule.work.impl.OnetimeLoadStategy;
import com.homecoo.smarthome.util.ServiceThread;

public class TimeManagerService extends ServiceThread {
	private ListenerManager listenerManager = new ListenerManager();
	private LoadWorkStrategyManager loadWorkStrategyManager = new LoadWorkStrategyManager();
	private Logger logger = LoggerFactory.getLogger(TimeManagerService.class);
	private TreeMap<Long,WorkContext> treeWorkContext = new TreeMap<Long,WorkContext>();
	/**
	 * 开启一个线程每天凌晨加载一次需要发送的列表
	 */
	public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	public void run() {
		while(!this.isStoped()){
			if(!treeWorkContext.isEmpty()){
//				long executorTime = treeWorkContext.lastKey();
				long executorTime = treeWorkContext.firstKey();
				long currentTime = System.currentTimeMillis() ;
				if(executorTime <= currentTime){
					try{
						WorkContext workContext = treeWorkContext.get(executorTime);
						if(workContext != null){
							listenerManager.activeListener(workContext);
							treeWorkContext.remove(executorTime);
						}
					}catch(ExecutorTimerJobException e){
						logger.error("executor job error"+executorTime);
					}
				}
			}
		}
		
	}
	@Override
	public void start(){
		super.start();
		loadDataToTimerManager();
	}
	private void loadDataToTimerManager() {
		long t = System.currentTimeMillis();
		loadWorkStrategyManager.regesitorStargegy(1, new OnetimeLoadStategy());
		List<WorkContext> workContextList =  loadWorkStrategyManager.loadAll(t);
		for(WorkContext workContext : workContextList){
			putSendRequest(workContext);
		}
	}
	public void putSendRequest(WorkContext workContext){
		Map<String, Job> jobMap = workContext.getJobMap();
		long executeTime = workContext.getExecuteTime();
 		for(String jobId: jobMap.keySet()){
			Job job = jobMap.get(jobId);
			if(job != null){
				putSendRequest(executeTime,job);
			}
		}
	}
	
	public void putSendRequest(Long executeTime,Job job){
		putSendRequest(executeTime,job,null);
	}

	
	//每天0点的时候，会加载当天要完成的定时任务， 当天里新加的定时任务需要调用这个函数
	public void putSendRequest(Long executeTime,Job job,TimerListener timerListener){
		boolean matched = treeWorkContext.containsKey(executeTime);
		WorkContext newWorkContext = null;
		WorkContext existWorkContext = null;
		if(matched){
			 existWorkContext = treeWorkContext.get(executeTime);
			 newWorkContext = existWorkContext;
		}else{
			newWorkContext = new WorkContext();
			newWorkContext.setExecuteTime(executeTime);
		}
		
		if(timerListener != null){
			job.setTimerListener(timerListener);
		}
		newWorkContext.addJob(job);
		treeWorkContext.put(executeTime,newWorkContext);
		listenerManager.regeisterListener(newWorkContext,timerListener);
	}
	/**
	 * 取消时间列表的网关任务
	 * @param executeTime
	 * @param gateWayNos
	 * @return
	 */
	public int cancleJob(Long executeTime, String jobIds){
		boolean matched = treeWorkContext.containsKey(executeTime); 
		if(StringUtils.isBlank(jobIds)){
			return -1;
		}
		if(matched){
			WorkContext existWorkContext = treeWorkContext.get(executeTime);
			for(String jobId : jobIds.split(",")){
				existWorkContext.getJobMap().remove(jobId);
			}
		}
		return 0;
	}
	


	@Override
	public String getServiceName() {
		return TimeManagerService.class.getName();
	}

}
