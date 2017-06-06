package com.homecoo.smarthome.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.service.ISchedule;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.NeedConstant;
import com.homecoo.smarthome.util.ScheduleHandler;
import com.homecoo.smarthome.util.ScheduleTimeUtil;

@Controller
public class AppScheduleController {


	@Autowired
	private ISchedule scheduleService;
	
	/**
	 * 添加单个定时任务
	 * 1:现根据deviceNo 或者themeNo 去数据库中查询 该设备或者情景是否已经做了定时。
	 * 2:查看传过来的schedule是否有seheduleid
	 * 3:如果没有直接添加定时任务；如果有，判断该定时任务的时间是否一致,时间一致 提醒用户已经在同一时间设置了定时任务
	 * @param scheduleJson 定时Json
	 * @return respjson MessageJson
	 * */
	@ResponseBody
	@RequestMapping(value = "/appAddSchedule", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appAddSchedule(String scheduleJson) {
		System.out.println(scheduleJson);
		Schedule schedule = JSON
				.parseObject(scheduleJson, Schedule.class);
		Message msg = new Message();
		try {
		int type=ScheduleTimeUtil.Differ(schedule);
		int row=scheduleService.getSchedule(schedule).size();
		//设置的定时时间 是 过去的时间   获数据库有该定时任务
		if (type==0) {
			msg=MessageUtils.appAddScheduleResp(NeedConstant.ERROR_MESSAGE, 0, 1);
		}else if (type > 0) {
			//设置的定时任务  是当天需要执行的定时任务  且数据库没有该定时任务
			if (row > 0) {
				msg=MessageUtils.appAddScheduleResp(NeedConstant.ERROR_MESSAGE, 0, 2);
			}else{
				scheduleService.addShedlue(schedule);
				final List<Schedule> list=scheduleService.getSchedule(schedule);
				msg.setMessageInfo("添加定时任务成功!");
				msg=MessageUtils.appAddScheduleResp(NeedConstant.SUCCESS_MESSAGE, Integer.valueOf(list.get(0).getScheduleId()), 0);
				if (type==2) {
					//添加的任务为当天需要执行的定时任务
					ScheduleHandler.SendMessage(list.get(0));
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appAddScheduleResp(NeedConstant.ERROR_MESSAGE, 0, 3);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 获取该网关下的所有定时任务 (留着备用 可能不需要)
	 * *//*
	@ResponseBody
	@RequestMapping(value = "/appGetScheduleByGatewayNo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetScheduleByGatewayNo(String gatewayNo) {
		Message msg = new Message();
		try {
			List<Schedule> scheduleList = scheduleService
					.getScheduleByGatewayNo(gatewayNo);
			msg=MessageUtils.appGetScheduleByGatewayNoResp(NeedConstant.SUCCESS_MESSAGE, scheduleList);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetScheduleByGatewayNoResp(NeedConstant.ERROR_MESSAGE,null);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}*/

	/**
	 * 获取 手机号 该用户下的所有定时任务
	 * @Deparam:phoneNum     默认已经执行了的 单次定时任务不显示
	 * */
	@ResponseBody
	@RequestMapping(value = "/appGetScheduleByPhoneNum", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetScheduleByPhoneNum(String phoneNum) {
		Message msg = new Message();
		try {
			List<Schedule> scheduleList = scheduleService.getScheduleByPhoneNum(phoneNum);
			Iterator<Schedule> iterator=scheduleList.iterator();
			Schedule schedule=null;
			List<Schedule> list=new ArrayList<Schedule>();
			while (iterator.hasNext()) {
				schedule=iterator.next();
				if (Integer.valueOf(schedule.getState()) != 2) {
					System.out.println(schedule.toString());
					list.add(schedule);
				}
			}
//			msg=MessageUtils.appGetScheduleByPhoneNumResp(NeedConstant.SUCCESS_MESSAGE, scheduleList);
			msg=MessageUtils.appGetScheduleByPhoneNumResp(NeedConstant.SUCCESS_MESSAGE, list);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetScheduleByPhoneNumResp(NeedConstant.ERROR_MESSAGE, null);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}
	
	
	/**
	 * 删除单个定时任务 或者直接传个scheduleId 过来
	 * @param scheduleId 定时任务ID
	 * @return respjson
	 * */
	@ResponseBody
	@RequestMapping(value = "/appDeleteScheduleByScheduleId", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appDeleteScheduleByScheduleId(String scheduleId){
		  Message msg=new Message(); 
		  List<Schedule> schedules=scheduleService.getScheduleByScheduleId(scheduleId);
		  //是否需要在treeMap上取消定时
		  ScheduleHandler.DeleteSchedule(schedules);
		  try {
			  scheduleService.deleteScheduleByScheduleId(Integer.valueOf(scheduleId)); 
			  msg=MessageUtils.appDeleteScheduleByScheduleIdResp(NeedConstant.SUCCESS_MESSAGE);
		  } catch(Exception e) { 
			  e.printStackTrace(); 
			  msg=MessageUtils.appDeleteScheduleByScheduleIdResp(NeedConstant.ERROR_MESSAGE); 
		     }
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 删除该手机号下的所有定时任务
	 * @param phoneNum 手机号
	 * @return respjson MessageJson
	 * *//*
	@ResponseBody
	@RequestMapping(value = "/appDeleteScheduleByPhoneNum", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appDeleteScheduleByPhoneNum(String phoneNum) {
		Message msg = new Message();
		//删除之前去 treeMap上把当天要执行的定时任务都取消掉
		List<Schedule> schedules=scheduleService.getScheduleByPhoneNum(phoneNum);
		ScheduleHandler.DeleteSchedule(schedules);
		try {
			scheduleService.deleteScheduleByPhoneNum(phoneNum);
			msg=MessageUtils.appDeleteScheduleByPhoneNumResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appDeleteScheduleByPhoneNumResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	*//**
	 * 删除该网关下的所有定时任务 (可能用不上，留着备用)
	 * *//*
	@ResponseBody
	@RequestMapping(value = "/appDeleteScheduleByGatewayNo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appDeleteScheduleByGatewayNo(String gatewayNo) {
		Message msg = new Message();
		//去掉 treeMap 上当天需要执行的定时
		List<Schedule> schedules=scheduleService.getScheduleByGatewayNo(gatewayNo);
		ScheduleHandler.DeleteSchedule(schedules);
		try {
			scheduleService.deleteScheduleByGatewayNo(gatewayNo);
			msg=MessageUtils.appDeleteScheduleByGatewayNoResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appDeleteScheduleByGatewayNoResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}*/
	/**
	 * 用户根据 手机号 和 设备id 查询 已经设置的定时(默认下 已经执行的定时任务 不显示)
	 * @author xiaobai
	 * @param:type 1:表示设备   2： 表示情景  3：表示定时音乐   scheduleName字段放songName   4：表示 红外
	 * @param phoneNum 手机号
	 * @param paramNo 
	 * @return respjson
	 * */ 
	@ResponseBody
	@RequestMapping(value = "/appGetSchedule", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetSchedule(String paramNo,String phoneNum,String type){
		Message msg = new Message();
		try {
			//这里可以放到service里面处理
			if (Integer.valueOf(type)==1) {
				List<Schedule> list=scheduleService.getScheduleByDeviceNoAndPhoneNum(paramNo, phoneNum);
				List<Schedule> schedules=ScheduleHandler.DeleteOutTimeSchedule(list);
				msg=MessageUtils.appGetScheduleResp(NeedConstant.SUCCESS_MESSAGE, schedules);
			}else if (Integer.valueOf(type)==2) {
				List<Schedule> list=scheduleService.getScheduleByThemeNoAndPhoneNum(paramNo, phoneNum);
				List<Schedule> schedules=ScheduleHandler.DeleteOutTimeSchedule(list);
				msg=MessageUtils.appGetScheduleResp(NeedConstant.SUCCESS_MESSAGE, schedules);
			}else if (Integer.valueOf(type)==3) {
				List<Schedule> list=scheduleService.getScheduleMusic(phoneNum, paramNo);
				List<Schedule> schedules=ScheduleHandler.DeleteOutTimeSchedule(list);
				msg=MessageUtils.appGetScheduleResp(NeedConstant.SUCCESS_MESSAGE, schedules);
			}else if (Integer.valueOf(type)==4) {
				List<Schedule> list=scheduleService.getScheduleByStateAndPhoneNum(type, phoneNum);
				List<Schedule> schedules=ScheduleHandler.DeleteOutTimeSchedule(list);
				msg=MessageUtils.appGetScheduleResp(NeedConstant.SUCCESS_MESSAGE, schedules);
			}else{
				msg=MessageUtils.appGetScheduleResp(NeedConstant.ERROR_MESSAGE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetScheduleResp(NeedConstant.ERROR_MESSAGE, null);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}
	
	/**
	 * 根据手机号，情景ID 或者deviceID 更新定时   
	 * 如果更新的时间为过去时间  直接回复 更新设置失败
	 * @param scheduleJson n定时Json
	 * @return respjson
	 * */
	@ResponseBody
	@RequestMapping(value = "/appUpdateSchedule", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appUpdateSchedule(String scheduleJson){
		Schedule schedule=JSON.parseObject(scheduleJson, Schedule.class);
		Message msg = new Message();
		List<Schedule> list=scheduleService.getScheduleByScheduleId(String.valueOf(schedule.getScheduleId()));
		int type=ScheduleTimeUtil.Differ(schedule);
		if (type > 0) {
			try {
				scheduleService.updateSchedule(schedule);
				ScheduleHandler.UpdateSchedule(schedule, list.get(0));
				msg=MessageUtils.appUpdateScheduleResp(NeedConstant.SUCCESS_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				msg=MessageUtils.appUpdateScheduleResp(NeedConstant.ERROR_MESSAGE);
			}
		}else {
			msg=MessageUtils.appUpdateScheduleResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}
	
}
