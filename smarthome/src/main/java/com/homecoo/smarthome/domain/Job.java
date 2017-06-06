package com.homecoo.smarthome.domain;

import com.homecoo.smarthome.schedule.timer.TimerListener;


public class Job {
	
	private String jobId;
	private TimerListener timerListener;
	private byte[] data;
	
	public Job(){
		
	}
	public Job(String jobId,byte[]datas ){
		this(jobId,datas,null);
	}
	public Job(String jobId, byte[] datas, TimerListener timerListener) {
		setJobId(jobId);
		setData(datas);
		setTimerListener(timerListener);
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public TimerListener getTimerListener() {
		return timerListener;
	}
	public void setTimerListener(TimerListener timerListener) {
		this.timerListener = timerListener;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
