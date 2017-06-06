package com.homecoo.smarthome.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.Packet;
import com.homecoo.smarthome.domain.Theme;
import com.homecoo.smarthome.domain.ThemeDevice;
import com.homecoo.smarthome.service.IPacket;
import com.homecoo.smarthome.service.ITheme;
import com.homecoo.smarthome.service.IThemeDevice;
import com.homecoo.smarthome.service.IUser;
import com.homecoo.smarthome.util.AppVersionUtil;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.NeedConstant;

@Controller
public class AppThemeController {

	@Autowired
	public ITheme themeService;
	@Autowired
	public IUser userService;
	@Autowired
	public IThemeDevice themeDeviceSerive;
	@Autowired
	private IPacket packetService;

	/**
	 * 增加情景模式由手机发送情景模式配置报文上报报文到服务器，再发送到nettyserver, 再传到网关，由网关保存。
	 * @param themePacket 情景报文
	 * @param gatewayNo 网关号
	 * @param phonenum 手机号
	 * @return messageJson MessageJson  
	 * !!! 情景不是应用服务器做的
	 */
	@ResponseBody
	@RequestMapping(value = "/appAddTheme", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String addTheme(String themePacket, String gatewayNo, String phonenum) {
		Message message = new Message();
		String messageJson = "";
		Theme theme = new Theme();

		try {
			theme.setCreateTime(new Date());
			theme.setCreateBy(userService.loadUserByPhonenum(phonenum)
					.getUserId());
			// 解析报文
			theme.setThemeState("themeStateCmd");
			themeService.addTheme(themePacket, gatewayNo);
			message.setResult(NeedConstant.SUCCESS_MESSAGE);
			message.setMessageInfo("添加成功");
		} catch (Exception e) {
			logger.error("添加设备失败", e);
			message.setResult(NeedConstant.ERROR_MESSAGE);
			message.setMessageInfo("添加失败");
		}

		messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}

	/**
	 * 手机同步情景到服务器
	 * @param themejson 情景Json
	 * @param themedevicejson 情景设备Json
	 * @param versionJson 版本Json
	 * @return respjson MessageJson
	 * @Date 2016-4-20
	 * 手机同步情景到服务器 也不是应用服务器的事情
	 * */
	@ResponseBody
	@RequestMapping(value = "appSyncThemeInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSyncThemeInfo(String themeJson, String themeDeviceJson,String versionJson) {
		Message msg = new Message();
		List<Theme> themelist = JSON.parseArray(themeJson, Theme.class);
		List<ThemeDevice> themeDevicelist = JSON.parseArray(themeDeviceJson, ThemeDevice.class);
		AppVersion appVersion=JSON.parseObject(versionJson,AppVersion.class);
		AppVersion version=themeService.getAppVersionByPhoneNumVersionType(appVersion);
		if (version!=null) {
			themeService.updateVersion(appVersion);
		}else{
			themeService.addAppVersion(appVersion);
		}
		Iterator<Theme> iterator = themelist.iterator();
		Iterator<ThemeDevice> iter = themeDevicelist.iterator();
		try {
			while (iterator.hasNext()) {
				Theme theme = iterator.next();
				// 判断网关下的情景是否存在，存在update 不存在 insert
				Boolean b = themeService.getTheme(theme.getThemeNo());
				if (b) {
					themeService.updateTheme(theme);
				} else {
					themeService.addTheme(theme);
				}
			}
			while (iter.hasNext()) {
				ThemeDevice themeDevice = iter.next();
				// 判断t_themedevice是否有该themeNo 的记录，存在 update;不存在 inert
//				System.out.println("==============	" + themeDevice.toString());
				Boolean b = themeDeviceSerive.getThemeDevice(themeDevice
						.getThemeNo());
				if (b) {
					themeDeviceSerive.updateThemeDevice(themeDevice);
				} else {
					themeDeviceSerive.addThemeDevice(themeDevice);
				}
			}
			msg.setMessageInfo("同步情景成功!");
			msg.setResult(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			msg.setMessageInfo("同步情景失败!");
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			e.printStackTrace();
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 手机从服务器获取情景
	 * @author xiaobai
	 * @param gatewayNo 网关号
	 * @return respjson MessageJson
	 * @Date 2016-4-20
	 * @test 2016-4-10 OK
	 * */
	@ResponseBody
	@RequestMapping(value = "appGetThemeDevice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetThemeDevice(String gatewayNo) {
		Message msg = new Message();
		try {
			List<Theme> themelist = themeService.getAllTheme(gatewayNo);
			List<ThemeDevice> themeDevicelist = themeDeviceSerive.getALLThemeDeviceByGatewayNo(gatewayNo);
			AppVersion version=AppVersionUtil.GetAppVersion(gatewayNo);
			AppVersion appVersion=themeService.getAppVersionByGatewayNoVersionType(version);
			msg=MessageUtils.appGetThemeDeviceResp(NeedConstant.SUCCESS_MESSAGE, appVersion, themeDevicelist, themelist);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetThemeDeviceResp(NeedConstant.ERROR_MESSAGE, null, null, null);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 服务器对比手机提交的Version串中phonenum、versiontype、updatetime 
	 *       (1)如果服务器的时间和手机的时间戳相同则ResultMessage.object=0
	 *     	 (2)如果服务器的时间最新则返回ResultMessage.object=1
	 *       (3)如果手机端的手机最新则返回ResultMessage.object=2
	 * @param versionJson 版本Json
	 * @return respjson MessageJson
	 * @Date 2016-4-21
	 * */
	@ResponseBody
	@RequestMapping(value="appCompareVersion",method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	public String appCompareVersion(String versionJson) {
		Message msg = new Message();
		AppVersion appVersion = JSON.parseObject(versionJson,AppVersion.class);
		try {
			AppVersion version = themeService.getAppVersionByPhoneNumVersionType(appVersion);
			if (version!=null) {
				Long i = appVersion.getUpdateTime()-version.getUpdateTime();
				System.out.println("APP 与服务器比较 版本的时间  判断哪个是最新的 0：一样   2：手机       1：服务器   i="+i);
				if (i > 0) { 							// i>0 表示手机端发送的时间最新
					msg.setObject(NeedConstant.VERSION_SERVER_GTR_APP);
				} else if (i == 0) {					// i==0表示手机服务器一致
					msg.setObject(NeedConstant.VERSION_APP_EQUAL_SERVER);
				} else {								//i<0 服务器最新
					msg.setObject(NeedConstant.VERSION_APP_GTR_SERVER);
				}
				msg.setMessageInfo("版本检查成功!");
				msg.setResult(NeedConstant.SUCCESS_MESSAGE);
			}else{
				msg=MessageUtils.appCompareVersionResp(NeedConstant.SUCCESS_MESSAGE);
			}
		} catch (Exception e) {
			msg=MessageUtils.appCompareVersionResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}
	
	
	/**
	 * 手机从服务器获取所有的情景报文
	 * @Date 2016-4-30
	 * @param gatewayNo 网关号
	 * @return respjson MessageJson
	 * */
	@ResponseBody
	@RequestMapping(value="appGetAllTheme",method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	public String appGetAllTheme(String gatewayNo){
		Message msg=new Message();
		try {
			List<Packet> list=packetService.selectPacket(gatewayNo);
			msg=MessageUtils.appGetAllThemeResp(NeedConstant.SUCCESS_MESSAGE, list);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetAllThemeResp(NeedConstant.ERROR_MESSAGE, null);
		}
		String respjson = JSONArray.toJSONString(msg);
		return respjson;
	}
	
	private static Logger logger = Logger.getLogger(AppSpaceController.class);

}
