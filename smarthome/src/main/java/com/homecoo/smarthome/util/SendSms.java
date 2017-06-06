package com.homecoo.smarthome.util;

import java.net.URLEncoder;

import com.homecoo.smarthome.util.ym.RandomNumeric;
import com.homecoo.smarthome.util.ym.SDKHttpClient;

public class SendSms {

	public static String sendMessage(String phoneNum,int smsCodeType ){
		String sn = "8SDK-EMY-6699-RIQQN";// 软件序列号,请通过亿美销售人员获取
		String key = "043261";// 序列号首次激活时自己设定
		String password = "043261";// 密码,请通过亿美销售人员获取
		String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
		String message = "";
		String idendifyCode = "";
		idendifyCode = RandomNumeric.random();
		
		try {
			String mdn = phoneNum;
			
			switch (smsCodeType){
			case 1:
				message = "【HomeCoo】您的注册验证码是" + idendifyCode
				+ ",请在60秒内提交验证码，切勿将验证码泄露于他人" + System.currentTimeMillis();
				break;
			case 2:
				message = "【HomeCoo】您正在重置密码,验证码" + idendifyCode
				+ ",请在60秒内提交验证码，切勿将验证码泄露于他人" + System.currentTimeMillis();	
				break;
			 default :
			       System.out.println("错误的选择！请输入１～2的数字做出选择。");
			}
		
			message = URLEncoder.encode(message, "UTF-8");
			String code = "";
			long seqId = System.currentTimeMillis();
			String param = "cdkey=" + sn + "&password=" + key + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
			String url = baseUrl + "sendsms.action";
			String ret = SDKHttpClient.sendSMS(url, param);
			System.out.println("ret:" + ret);
		} catch (Exception e ) {
			e.printStackTrace();
		}finally{
		}
		
		return idendifyCode;
		}
	}