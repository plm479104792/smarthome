package com.homecoo.smarthome.schedule.exception;

/**
 * 执行任务时异常
 * @author hsj
 *
 */
public class ExecutorTimerJobException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8239243692619234507L;

	public ExecutorTimerJobException(String message){
		super(message);
	}
	
	public ExecutorTimerJobException(Throwable throwable){
		super(throwable);
	}
}
