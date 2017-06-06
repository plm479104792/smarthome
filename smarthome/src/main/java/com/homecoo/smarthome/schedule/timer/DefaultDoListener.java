package com.homecoo.smarthome.schedule.timer;

import java.io.IOException;
import java.net.UnknownHostException;

import com.homecoo.smarthome.domain.Job;
import com.homecoo.smarthome.schedule.exception.ExecutorTimerJobException;
import com.homecoo.smarthome.util.SimpleSocketClient;

/**
 * 将控制报文发送到netty,由netty转发到对应的网关  (没有用上了)
 * */
public class DefaultDoListener implements TimerListener{
	
	@Override
	public void doWorkBussiness(Job job) throws ExecutorTimerJobException{
		SimpleSocketClient simpleSocketClient=new SimpleSocketClient();
		try {
			simpleSocketClient.sendMessage(job.getData());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
