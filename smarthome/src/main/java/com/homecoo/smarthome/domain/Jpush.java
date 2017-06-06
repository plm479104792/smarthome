package com.homecoo.smarthome.domain;

import java.util.Date;

/**
 * 极光推送消息类
 * */
public class Jpush {
	private String gatewayNo;			//JPush推送别名  JPush根据gatewayNo推送到对应的网关下的所有手机   网关号
	private Object object;				//属性   一般需要传的参数都放在这个属性里面
	private int messsageType;			//1:设备状态更新  2:安防报警   3:音乐   4:情景类   5：情景音乐
	private Long time;					//时间
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public int getMesssageType() {
		return messsageType;
	}
	public void setMesssageType(int messsageType) {
		this.messsageType = messsageType;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Jpush [gatewayNo=" + gatewayNo + ", object=" + object
				+ ", messsageType=" + messsageType + ", time=" + time + "]";
	}
	
}
