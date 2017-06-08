package com.homecoo.smarthome.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.ResponseWrapper;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.RecalcIdRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceDto;
import com.homecoo.smarthome.domain.DeviceDtoApp;
import com.homecoo.smarthome.domain.DeviceStateRecord;
import com.homecoo.smarthome.domain.Infrared;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.UserDeviceSpace;
import com.homecoo.smarthome.service.IDevice;
import com.homecoo.smarthome.service.IDeviceState;
import com.homecoo.smarthome.service.IInfrared;
import com.homecoo.smarthome.service.ITheme;
import com.homecoo.smarthome.service.IUser;
import com.homecoo.smarthome.service.IUserDeviceSpaceService;
import com.homecoo.smarthome.util.AppVersionUtil;
import com.homecoo.smarthome.util.BasicProcess;
import com.homecoo.smarthome.util.DeviceUtil;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.NeedConstant;
import com.homecoo.smarthome.util.SimpleSocketClient;

@Controller 
public class AppDeviceController {

	@Autowired
	public IUser userService;
	@Autowired
	public IDevice deviceService;
	@Autowired
	private ITheme themeService;
	@Autowired
	public IUserDeviceSpaceService userSpaceDeviceService;
	@Autowired
	private IDeviceState deviceStateService;
	@Autowired
	private IInfrared infraredService;

	/**
	 * 增加单个设备，用户长按硬件，网关发送上报报文到nettyServer，
	 * nettyServer接到报文后解析，判断如果是添加设备的，则将报文解析后，
	 * 存储设备信息进数据表t_device表。期间要从t_category表中取出设备大类， 增加设备由网关发送上报报文到服务器，服务器要解析报文，
	 * 生成device，存储到服务器。手机不执行 这个方法可以没有controller接口 这时候是没有space和phonenum的。
	 * @param deviceJson 
	 * @return messageJson
	 */
	@ResponseBody
	@RequestMapping(value = "/wgAddDevice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String wgAddDevice(String deviceJson) {
		Message message = new Message();
		Device device = new Device();
		try {
			device = JSON.parseObject(deviceJson, Device.class);
			device.setCreateTime(new Date());
			device.setCreateBy(userService.loadUserByPhonenum(
					device.getPhoneNum()).getUserId());
			deviceService.addDevice(device);
			message=MessageUtils.wgAddDeviceResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			logger.error("添加设备失败", e);
			message=MessageUtils.wgAddDeviceResp(NeedConstant.ERROR_MESSAGE);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}

	/**
	 * 网关一通电，服务器设备数据t_device就必须和网关设备数据进行同步， 网关会发送报文到服务器，服务器解析，判断如果是同步设备的，
	 * 对t_device表，如果服务器里没有这个设备则新增一条，如果有则update这条，如果少了就要删掉一条（这个怎么处理），
	 * 根据deviceNo，这时候也不用管t_user_space_device表 （网关如何发送设备报文过来，这个要和平安确定一下）。手机不执行
	 * 这个方法zai NETTY中实现了
	 * */
	
	
	/**
	 * 下面设备退网了，手机没有打开。手机上还是有该设备，这个时候需要手机删除设备
	 * 并根据deviceNo到t_user_space_device表删除跟其相关的数据。 手机也不要执行。
	 * @param deviceNo 设备ID
	 * @return messageJson
	 * */
	@ResponseBody
	@RequestMapping(value = "/appDeleteDevice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String deleteDevice(String deviceNo) {
		Message message = new Message();
		try {
			deviceService.deleteDevice(deviceNo);
			message=MessageUtils.appDeleteDeviceResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			logger.error("删除设备失败", e);
			message=MessageUtils.appDeleteDeviceResp(NeedConstant.ERROR_MESSAGE);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}

	/**
	 * 用户获取设备大类 设备列表
	 * @author xiaobai
	 * @param phonenum 手机号
	 * @param categoryId 设备类型Id
	 * @return messageJson
	 * @test 2016-4-19 15:31 OK
	 * */
	@ResponseBody
	@RequestMapping(value = "/appGetDeviceListByCategory", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getDeviceListByCategory(String phonenum, int categoryId) {
		Message message = new Message();
		try {
			List<Device> deviceList = deviceService.getDeviceListByCategory(
					phonenum, categoryId);
			message=MessageUtils.appGetDeviceListByCategoryResp(NeedConstant.SUCCESS_MESSAGE, deviceList);
		} catch (Exception e) {
			logger.error("获取设备列表失败", e);
			message=MessageUtils.appGetDeviceListByCategoryResp(NeedConstant.ERROR_MESSAGE,null);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}


	/**
	 * APP同步所有设备信息到服务器
	 * @author xiaobai
	 * @Date 2016-4-20
	 * @param: deviceJson 设备listJson
	 * @param deviceSpaceJson 设备空间listJson
	 * @param versionJson 版本Json
	 * */
	@ResponseBody
	@RequestMapping(value = "appSyncDeviceInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSyncDeviceInfo(String deviceJson, String deviceSpaceJson,
			String versionJson) {
//		System.out.println("设备表："+deviceJson);
//		System.out.println("用户配置表："+deviceSpaceJson);
//		System.out.println("版本信息表:"+versionJson);
		Message msg = new Message();
		try {
		List<DeviceDtoApp> deviceDtoAppList = JSON
				.parseArray(deviceJson, DeviceDtoApp.class);
		List<UserDeviceSpace> userDeviceSpacesLits = JSON.parseArray(
				deviceSpaceJson, UserDeviceSpace.class);
//		AppVersion appVersion = JSON.parseObject(versionJson,AppVersion.class);
//		AppVersion version = themeService
//				.getAppVersionByPhoneNumVersionType(appVersion);
//		System.out.println("deviceDtoappList"+deviceDtoAppList.toString());
//		System.out.println("userDeviceSpaceLits"+userDeviceSpacesLits.toString());
//		System.out.println("appversion"+appVersion.toString());
//		if (version != null) {
//			themeService.updateVersion(appVersion);
//		} else {
//			themeService.addAppVersion(appVersion);
//		}
		
			Iterator<DeviceDtoApp> iterator = deviceDtoAppList.iterator();
			Iterator<UserDeviceSpace> iter = userDeviceSpacesLits.iterator();
			while (iterator.hasNext()) {
				DeviceDtoApp deviceDtoApp = iterator.next();
				DeviceDto deviceDto=DeviceUtil.DeviceDtoappToDeviceDto(deviceDtoApp);
				boolean b = deviceService.getDeviceByDeviceNo(deviceDto.getDevice());   //需要根据网关号和deviceNo判断
				if (b) {
					deviceService.updateDevice(deviceDto.getDevice());
				} 
//				else {
//					deviceService.addDevice(deviceDto.getDevice());
//				}
//				boolean a=deviceStateService.selectDeviceStateByDeviceNo(deviceDto.getDevice().getDeviceNo());
//				if (a) {
//					deviceStateService.AddDeviceState(deviceDto);
//				}
			}
			while (iter.hasNext()) {
				UserDeviceSpace userDeviceSpace = iter.next();
				boolean b = userSpaceDeviceService.getUserDeviceSpaceByDeviceno(userDeviceSpace);
				if (b) {
					userSpaceDeviceService
							.updateUserDeviceSpace(userDeviceSpace);
				} else {
					userSpaceDeviceService.addUserSpaceDevice(userDeviceSpace);
				}
			}
			msg=MessageUtils.appSyncDeviceInfoResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appSyncDeviceInfoResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * APP从服务器获取所有设备信息
	 * @author xiaobai
	 * @Date 2016-4-20
	 * @param phonenum 手机号
	 * @param gatewayNo 网关编号
	 * @return respjson
	 * */
	@ResponseBody
	@RequestMapping(value = "appGetDeviceInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetDeviceInfo(String phonenum,String gatewayNo) {
		Message msg = new Message();
		try {
			List<Device> deviceList = deviceService.getAllDevice(gatewayNo);
			List<DeviceDtoApp> deviceDtoAppList = new ArrayList<DeviceDtoApp>();
			Iterator<Device> iterator = deviceList.iterator();
			while (iterator.hasNext()) {
				Device device = iterator.next();
				DeviceStateRecord record=deviceStateService.getDeviceState(device);
				DeviceDtoApp deviceDtoApp=DeviceUtil.GetDeviceDtoApp(device, record);
				deviceDtoAppList.add(deviceDtoApp);
			}
			List<UserDeviceSpace> userDeviceSpacesList = userSpaceDeviceService
					.getAllDevice(phonenum);
			AppVersion version = AppVersionUtil.getDeviceAppVersionByPhoneNum(phonenum);
			AppVersion appVersion = themeService
					.getAppVersionByPhoneNumVersionType(version);
			msg=MessageUtils.appGetDeviceInfoResp(NeedConstant.SUCCESS_MESSAGE, appVersion, deviceDtoAppList, userDeviceSpacesList);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetDeviceInfoResp(NeedConstant.ERROR_MESSAGE,null,null,null);
		}
		String respjson = JSONObject.toJSONString(msg);
		System.out.println(msg.getDeviceDtoAppList().toString());
//		System.out.println(JSONObject.parseObject(respjson, Message.class).getAppVersion());
		return respjson;
	}

	/**
	 * 手机发送控制报文到服务器，服务器发送数据到NettyServer,再发送给网关。 netty要接收从网关发回来的该设备最新状态报文，如果接收不到，
	 * @author xiaobai
	 * @param devicePacketJson 
	 * @Date 2016-4-23
	 * @test OK
	 * */
	@ResponseBody
	@RequestMapping(value = "appDeviceController", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appDeviceController(String devicePacketJson) {
		Message msg = new Message();
		System.out.println("发送报文到netty 		"+devicePacketJson);
		try {
			byte[] datagram = BasicProcess.toByteArray(devicePacketJson);
			SimpleSocketClient simpleSocketClient = new SimpleSocketClient();
			simpleSocketClient.sendMessage(datagram);
			msg=MessageUtils.appDeviceControllerResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			msg=MessageUtils.appDeviceControllerResp(NeedConstant.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			msg=MessageUtils.appDeviceControllerResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjsonString = JSONObject.toJSONString(msg);
		return respjsonString;

	}
	
	/**
	 * @author xiaobai
	 * by 2017-1-6
	 * 获取红外控制设备 				
	 * @param gatewayNo
	 * @return msg.json
	 * */
	@ResponseBody
	@RequestMapping(value = "appGetInfraredByGatewayNo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetInfraredByGatewayNo(String gatewayNo){
		Message msg=new Message();
//		System.out.println("appGetInfraredByGatewayNo"+gatewayNo);
		try {
			List<Infrared> list=infraredService.getAllInfraredBygatewayNo(gatewayNo);
			msg.setMessageInfo("获取红外控制设备成功!");
			msg.setInfraredList(list);
			msg.setResult(NeedConstant.SUCCESS_MESSAGE);
			msg.setObject(JSONArray.toJSONString(list));
		} catch (Exception e) {
			msg.setMessageInfo("获取红外控制设备失败!");
			msg.setResult(NeedConstant.ERROR_MESSAGE);
		}
		return JSON.toJSONString(msg);
	}
	
	/**
	 * @author xiaobai
	 * by	2017-1-6
	 * 接收到APP发送的红外控制设备  存储并更新    因为数据不多，我们先删除之前的数据然后再插入新数据  不更新
	 * @param
	 * @return
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "appSendInfrared", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendInfrared(String infraJson){
		Message msg=new Message();
//		System.out.println("appSendInfrared"+infraJson);
		try {
			List<Infrared> list=JSON.parseArray(infraJson, Infrared.class);
//			System.out.println(list.toString());
			Iterator<Infrared> iterator=list.iterator();
			infraredService.deleteInfraredBygatewayNo(list.get(0));
			while (iterator.hasNext()) {
				Infrared infrared=new Infrared();
				infrared=iterator.next();
				infraredService.SaveOrUpdateInfrared(infrared);
			}
			msg.setMessageInfo("红外控制设备更新成功！");
			msg.setResult(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			msg.setMessageInfo("红外控制设备更新失败！");
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			
		}
		return JSON.toJSONString(msg);
		
	}
	
//	/**
//	 * @author xiaobai
//	 * 2017-1-7
//	 * 删除红外控制设备
//	 * @param infrared
//	 * @return msg.json
//	 * */
//	@ResponseBody
//	@RequestMapping(value = "deleteInfra", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
//	public String deleteInfra(String infrajson){
//		Message msg=new Message();
//		try {
//			Infrared infrared=JSON.parseObject(infrajson, Infrared.class);
//			infraredService.deleteInfraredBydeviceNoAndTypeId(infrared);
//			msg.setResult(NeedConstant.SUCCESS_MESSAGE);
//			msg.setMessageInfo("删除红外控制设备成功！");
//		} catch (Exception e) {
//			logger.info(e);
//			msg.setResult(NeedConstant.ERROR_MESSAGE);
//			msg.setMessageInfo("删除红外控制设备失败!");
//		}
//		return JSON.toJSONString(msg);
//	}
	
	
	private static Logger logger = Logger.getLogger(AppSpaceController.class);
}
