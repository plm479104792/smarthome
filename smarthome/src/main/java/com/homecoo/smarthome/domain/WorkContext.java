package com.homecoo.smarthome.domain;

import java.util.HashMap;
import java.util.Map;

import com.homecoo.smarthome.util.BasicProcess;


/**
 * 定时任务的实体类
 * */
public class WorkContext {
	/**
	 * 执行时间
	 */
	private Long executeTime;
	
	private Map<String/*jobid*/,Job> jobMap = new HashMap<String, Job>();
	
	public Long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}
	
	public void addJob(Job job){
		String jobId = job.getJobId();
		jobMap.put(jobId, job);
	}
	

	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("executeTime="+executeTime);
		
		for(String jobId : jobMap.keySet()){
			Job job = jobMap.get(jobId);
			stringBuffer.append("jobId = "+ jobId+"datas = "+ BasicProcess.toHexString(job.getData()));
		}
		return stringBuffer.toString();
	}

	public Map<String, Job> getJobMap() {
		return jobMap;
	}

	public void setJobMap(Map<String, Job> jobMap) {
		this.jobMap = jobMap;
	}
}
