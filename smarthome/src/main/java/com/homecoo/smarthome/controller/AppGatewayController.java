package com.homecoo.smarthome.controller;


import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.Gateway;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.User;
import com.homecoo.smarthome.service.IGateway;
import com.homecoo.smarthome.service.ITheme;
import com.homecoo.smarthome.service.IUser;
import com.homecoo.smarthome.util.AppVersionUtil;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.NeedConstant;

@Controller
public class AppGatewayController {

	@Autowired
	public IGateway gatewayService;
	@Autowired
	private IUser userService;
	@Autowired
	private ITheme themeService;
	
	/**
	 * 同步网关      由手机发送网关信息到服务器
	 * @author xiaobai
	 * @param gatewayJson 网关Json
	 * @param versionJson 版本Json
	 * @return messageJson
	 * @test by xiaobai 2016-4-16 OK
	 */
	@ResponseBody
	@RequestMapping(value = "/appAsyncGatewayInfo", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String asyncGatewayInfo(String gatewayJson,String versionJson){
		Message message = new Message();
		AppVersion appVersion=JSON.parseObject(versionJson, AppVersion.class);
		try{
			Gateway gateway = JSON.parseObject(gatewayJson,Gateway.class);
			gatewayService.asyncGatewayInfo(gateway);
			AppVersion aa=AppVersionUtil.getGatewayNoAppVersion(appVersion.getGatewayNo());
			AppVersion version=themeService.getAppVersionByGatewayNoVersionType(aa);
			if (version!=null) {
				themeService.updateVersion(appVersion);
			}else{
				themeService.addAppVersion(appVersion);
			}
			message=MessageUtils.appAsyncGatewayInfoResp(NeedConstant.SUCCESS_MESSAGE);
		}catch(Exception e){
			logger.error("同步网关失败", e);
			message=MessageUtils.appAsyncGatewayInfoResp(NeedConstant.ERROR_MESSAGE);
		}
		String	messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	/**
	 * 同步网关,手机从服务器获取网关信息
	 * @param phoneNum 手机号
	 * @return messageJson
	 * @test by xiaobai 2016-4-16 OK
	 */
	@ResponseBody
	@RequestMapping(value = "/appGetGatewayInfo", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String getGatewayInfo(String phoneNum){
		Message message = new Message();
		try{
			String gatewayNo=userService.loadUserByPhonenum(phoneNum).getGatewayNo();
			List<Gateway> gateway = gatewayService.getGatewayInfo(gatewayNo);
			AppVersion appVersion=AppVersionUtil.getPhoneNumAppVersion(phoneNum);
			AppVersion version=themeService.getAppVersionByPhoneNumVersionType(appVersion);
			message=MessageUtils.appGetGatewayInfoResp(NeedConstant.SUCCESS_MESSAGE, version, gateway);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("同步网关失败", e);
			message=MessageUtils.appGetGatewayInfoResp(NeedConstant.ERROR_MESSAGE,null,null);
		}
		String	messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	/**
	 * APP同步网关信息给服务器
	 * @param gatewayJson 网关Json
	 * @param versionJson 版本Json
	 * @param phonenum 手机号
	 * @return respjson
	 * */
	@ResponseBody
	@RequestMapping(value = "/appSyncGatewayInfo", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appSyncGatewayInfo(String gatewayJson,String versionJson,String phonenum){
		System.out.println("gatewaylist"+gatewayJson);
		System.out.println(phonenum+"   version  "+versionJson);
		List<Gateway> list=JSON.parseArray(gatewayJson, Gateway.class);
		AppVersion version=JSON.parseObject(versionJson,AppVersion.class);
		Message msg=new Message();
		msg=MessageUtils.appAsyncGatewayInfoResp(NeedConstant.SUCCESS_MESSAGE);
		if (list.size()==0) {
			themeService.deleteAppVersion(phonenum, NeedConstant.VERSION_GATEWAY);
		}else {
			try {
				//把网关信息 插入到对应的  t_user表 用户下
				String gatewayNo = list.get(0).getGatewayNo();
				User user=userService.loadUserByPhonenum(phonenum);
				user.setGatewayNo(gatewayNo);
				userService.updateUser(user);
				
				AppVersion appVersion = themeService
						.getAppVersionByPhoneNumVersionType(version);
				if (appVersion != null) {
					themeService.updateVersion(version);
				} else {
					themeService.addAppVersion(version);
				}
				
				Iterator<Gateway> iterator=list.iterator();
				while (iterator.hasNext()) {
					Gateway gateway=iterator.next();
					List<Gateway> record=gatewayService.getGatewayInfo(gateway.getGatewayNo());
					if (record.size() != 0) {
						//数据库中存在该网关的信息，更新数据库
						gatewayService.updateGatewayBygatewayNo(gateway);
					}else {
						//数据库中没有该网关的信息，直接insert
						gatewayService.asyncGatewayInfo(gateway);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg=MessageUtils.appAsyncGatewayInfoResp(NeedConstant.ERROR_MESSAGE);
			}
		}
		String respjson =  JSONObject.toJSONString(msg);
		return respjson;
	}
	
	
	private static Logger logger = Logger.getLogger(AppGatewayController.class);
}
