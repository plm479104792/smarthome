package com.homecoo.smarthome.util;


import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.annotations.JsonAdapter;
import com.homecoo.smarthome.domain.Jpush;


public class JPushimpl{
	private static final String appKey="f01169052651936b1a139e18"; 				//极光推送应用的KEY
	private static final String masterSecret="8604eec283004285f913daf0";		//极光推送应用KEY对应的Secret
	public void sendPush(Jpush jpush){
		String alias=jpush.getGatewayNo();
		String jpushString=JSONObject.toJSONString(jpush);
//		System.out.println("根据设备别名推送是的别名："+alias);
		//默认离线消息存活一天
		JPushClient jPushClient=new JPushClient(masterSecret, appKey);
		//默认离线消息存活半天
//		JPushClient jPushClient=new JPushClient(masterSecret, appKey, false, 43200);
		
		PushPayload payload=PushPayload.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias))
							//		.addAudienceTarget(AudienceTarget.tag("",""))
							//		.addAudienceTarget(AudienceTarget.registrationId("",""))
									.build())
				.setMessage(Message.newBuilder()
						.setMsgContent(jpushString)
						.addExtra("", "")
						.build())
				.build();
		try {
			System.out.println("情景音乐推送："+jpushString);
			PushResult result=jPushClient.sendPush(payload);
//			System.out.println(result.toString());
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.err.println(e);
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			System.err.println("JPush推送别名的网关未连接到netty");
//			e.printStackTrace();
		}
				
	}
	
	//推送各种报警通知
	public void sendNotificationAllSmoke(Jpush jpush) throws APIConnectionException, APIRequestException{
		JPushClient jPushClient=new JPushClient(masterSecret, appKey,false,43200);
		PushResult result=jPushClient.sendAndroidNotificationWithAlias("请及时打开APP查看报警信息！", "烟感报警", null, jpush.getGatewayNo());
	}
	public void sendNotificationAllHumi(Jpush jpush) throws APIConnectionException, APIRequestException{
		JPushClient jPushClient=new JPushClient(masterSecret, appKey,false,43200);
		PushResult result=jPushClient.sendAndroidNotificationWithAlias("请及时打开APP查看报警信息！", "燃气报警", null, jpush.getGatewayNo());
	}
	public void sendNotificationAllInfra(Jpush jpush) throws APIConnectionException, APIRequestException{
		JPushClient jPushClient=new JPushClient(masterSecret, appKey,false,43200);
		PushResult result=jPushClient.sendAndroidNotificationWithAlias("请及时打开APP查看报警信息！", "红外报警", null, jpush.getGatewayNo());
	}
	public void sendNotificationAllMaglock(Jpush jpush) throws APIConnectionException, APIRequestException{
		JPushClient jPushClient=new JPushClient(masterSecret, appKey,false,43200);
		PushResult result=jPushClient.sendAndroidNotificationWithAlias("请及时打开APP查看报警信息！", "门磁报警", null, jpush.getGatewayNo());
	}
	
}
