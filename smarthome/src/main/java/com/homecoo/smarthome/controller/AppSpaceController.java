package com.homecoo.smarthome.controller;

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
import com.homecoo.smarthome.domain.Space;
import com.homecoo.smarthome.service.ISpace;
import com.homecoo.smarthome.service.ITheme;
import com.homecoo.smarthome.service.IUser;
import com.homecoo.smarthome.util.AppVersionUtil;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.NeedConstant;


@Controller
public class AppSpaceController {

	@Autowired
	public  ISpace spaceService;
	@Autowired
	public IUser userService;
	@Autowired
	private ITheme themeService;
	
	/**
	 * 添加空间
	 * @param spaceJson 空间Json
	 * @test by xiaobai OK
	 * @return messageJson
	 * @Date 2016-4-16
	 * */
	@ResponseBody
	@RequestMapping(value = "/appAddSpace", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appAddSpace(String spaceJson){
		Message message = new Message();
		try{
			Space space =  JSON.parseObject(spaceJson,Space.class);
			spaceService.addSpace(space);
			message=MessageUtils.appAddSpaceResp(NeedConstant.SUCCESS_MESSAGE);
		}catch(Exception e){
			message=MessageUtils.appAddSpaceResp(NeedConstant.ERROR_MESSAGE);
			logger.error("添加失败", e);
		}
		String 	messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	
	/**
	 * 批量处理空间名称 
	 * @author xiaobai
	 * @Date 2016-4-18 12:17
	 * @param spacejsonlList 空间listJson
	 * @param versionJson 版本Json
	 * @return messageJson MessageJson
	 * @test by xiaobai 2016-4-18  OK
	 * */
	@ResponseBody
	@RequestMapping(value = "/appSetSpaceList", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appSetSpaceList(String spaceJsonList,String versionJson){
		Message msg = new Message();
		AppVersion appVersion=JSON.parseObject(versionJson, AppVersion.class);
		AppVersion version=themeService.getAppVersionByPhoneNumVersionType(appVersion);
		if (version!=null) {
			themeService.updateVersion(appVersion);
		}else{
			themeService.addAppVersion(appVersion);
		}
		Space space = new Space();
		List<Space> spacelist=JSON.parseArray(spaceJsonList, Space.class);
		try {
			for (int i = 0; i < spacelist.size(); i++) {
				space=spacelist.get(i);
				spaceService.deleteSpace(space.getSpaceNo(),space.getPhoneNum());
				if (space.getSpaceId()!=null) {
					spaceService.updateSapce(space);
				}else{
					spaceService.addSpace(space);
				}
			}
			msg=MessageUtils.appSetSpaceListResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appSetSpaceListResp(NeedConstant.ERROR_MESSAGE);
		}
		String	messageJson = JSONObject.toJSONString(msg);
		return messageJson;
	}
	
	/**
	 * 更新空间
	 * @param spaceJson
	 * @return messageJson
	 * @test by xiaobai OK
	 * @Date 2016-4-16
	 * */
	@ResponseBody
	@RequestMapping(value = "/appUpdateSpace", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appUpdateSpace(String spaceJson){
		Message message = new Message();
		Space space = new Space();
		try{
			space = JSON.parseObject(spaceJson,Space.class);
			int userid=userService.loadUserByPhonenum(space.getPhoneNum()).getUserId();
			space.setUpdateBy(userid);
			spaceService.updateSapce(space);
			message=MessageUtils.appUpdateSpaceResp(NeedConstant.SUCCESS_MESSAGE);
		}catch(Exception e){
			message=MessageUtils.appUpdateSpaceResp(NeedConstant.ERROR_MESSAGE);
			logger.error("修改失败", e);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	/**
	 * 删除空间
	 * @param SpaceNo 空间id
	 * @param phoneNum 手机号
	 * @return messageJson
	 * @test by xiaobai OK
	 * @Date 2016-4-16
	 * */
	@ResponseBody
	@RequestMapping(value = "/appDeleteSpace", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appDeleteSpace(String SpaceNo,String phoneNum){
		Message message = new Message();
		try{
			spaceService.deleteSpace(SpaceNo, phoneNum);
			message=MessageUtils.appDeleteSpaceResp(NeedConstant.SUCCESS_MESSAGE);
		}catch(Exception e){
			message=MessageUtils.appDeleteSpaceResp(NeedConstant.ERROR_MESSAGE);
			logger.error("删除失败", e);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	
	/**
	 * 获取用户所有的空间
	 * @param phonenum 手机号
	 * @return messageJson
	 * @test by xiaobai OK
	 * @Date 2016-4-16
	 * */
	@ResponseBody
	@RequestMapping(value = "/appGetALLSpace", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appGetALLSpace(String phonenum){
		Message message = new Message();
		try{
			List<Space> spaceList = spaceService.getAllSpaceByPhoneNum(phonenum.trim());
			AppVersion version=AppVersionUtil.getSpaAppVersion(phonenum);
			AppVersion appVersion=themeService.getAppVersionByPhoneNumVersionType(version);
			message=MessageUtils.appGetALLSpaceResp(NeedConstant.SUCCESS_MESSAGE, spaceList, appVersion);
		}catch(Exception e){
			message=MessageUtils.appGetALLSpaceResp(NeedConstant.ERROR_MESSAGE, null, null);
			logger.error("获取空间列表失败", e);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	
	/**
	 * 用户获取单个空间
	 * @param phoneNum 手机号
	 * @param spaceNo 空间ID
	 * @return messageJson
	 *@test by xiaobai OK
	 *@Date 2016-4-16
	 * */
	@ResponseBody
	@RequestMapping(value = "/appGetSpace", method = RequestMethod.POST,produces= { "application/json;charset=UTF-8" })
	public String appGetSpace(String phoneNum,String spaceNo){
		Message message = new Message();
		Space space = spaceService.getSpaceBySpaceNoPhoneNum(phoneNum, spaceNo);
		if(space!=null){
			message=MessageUtils.appGetSingerSpaceResp(NeedConstant.SUCCESS_MESSAGE, space);
		}else{
			message=MessageUtils.appGetSingerSpaceResp(NeedConstant.ERROR_MESSAGE,null);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}
	
	private static Logger logger = Logger.getLogger(AppSpaceController.class);
}
