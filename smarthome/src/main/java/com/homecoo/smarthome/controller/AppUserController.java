package com.homecoo.smarthome.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.SmsCode;
import com.homecoo.smarthome.domain.User;
import com.homecoo.smarthome.service.ISmsCode;
import com.homecoo.smarthome.service.ISpace;
import com.homecoo.smarthome.service.ITheme;
import com.homecoo.smarthome.service.IUser;
import com.homecoo.smarthome.service.IUserDeviceSpaceService;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.NeedConstant;
import com.homecoo.smarthome.util.SendSms;
import com.homecoo.smarthome.util.SmsCodeUtil;

@Controller
public class AppUserController {

	@Autowired
	private ISmsCode smsCodeService;
	@Autowired
	private IUser userService;
	@Autowired
	private ITheme themeService;
	@Autowired
	private IUserDeviceSpaceService userDeviceSpaceService;
	@Autowired
	private ISpace spaceService;

	/**
	 * 手机App用户注册发送手机验证码,注册的时候需要判断该手机号码是否已经存在(1.不存在，允许注册；2.存在,不允许注册.让去重置密码操作)
	 * @param phonenum 手机号码
	 * @return respJson
	 * @throws Exception
	 * @test by xiaobai 2016-4-16 OK 测试使用的 这个需要去掉
	 */
	@ResponseBody
	@RequestMapping(value = "/appSendRegitserCode", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendRegitserCode(String phonenum) {
		Message msg = new Message();
		// 首先查询验证码表上是否已经有该手机号，如果有请更换手机号！，或者重置密码，如果没有允许注册 row=0没有；row=其他 有
		int row = smsCodeService.getPhoneNum(phonenum);
		int row1=userService.getUserByPhone(phonenum);
		try {
			if (row1 == 0) {
				String idendifyCode = SendSms.sendMessage(phonenum, 1);
				SmsCode smsCode = SmsCodeUtil.registerappSmsCode(phonenum,idendifyCode);
				int a = smsCodeService.addSmsCode(smsCode);
				logger.debug(this.getClass().getName() + "手机号码 :" + phonenum+ ", 随机生成验证码 : " + idendifyCode + "注册验证码");
				if (a != -1) {
					msg = MessageUtils.appSendRegitserCodeResp(NeedConstant.SUCCESS_MESSAGE);
				} else {
					msg = MessageUtils.appSendRegitserCodeResp(NeedConstant.ERROR_MESSAGE);
				}
			} else {
				msg = MessageUtils.appSendRegitserCodeResp("aa");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtils.appSendRegitserCodeResp(NeedConstant.ERROR_MESSAGE);
		}
		String respJson = JSONObject.toJSONString(msg);
		return respJson;
	}

	/**
	 * 手机App用户重置密码发送手机验证码
	 * 
	 * @param phonenum
	 *            手机号
	 * @return respjsonString
	 * @throws Exception
	 * @test by xiaobai 2016-4-16 OK
	 */
	@ResponseBody
	@RequestMapping(value = "/appSendRePwdCode", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendRePwdCode(String phonenum) {
		String idendifyCode = SendSms.sendMessage(phonenum, 2);
		Message msg = new Message();
		SmsCode smsCode = SmsCodeUtil.GetRepasswordSmsCode(phonenum,
				idendifyCode);
		try {
			smsCodeService.updateSmsCode(smsCode);
			msg = MessageUtils
					.appSendRePwdCodeResp(NeedConstant.SUCCESS_MESSAGE);
			logger.debug(this.getClass().getName() + "手机号码 :" + phonenum
					+ ", 随机生成验证码 : " + idendifyCode + "密码验证码");
		} catch (Exception e) {
			msg = MessageUtils.appSendRePwdCodeResp(NeedConstant.ERROR_MESSAGE);
			e.printStackTrace();
		}
		String respjsonString = JSONObject.toJSONString(msg);
		return respjsonString;
	}

	/**
	 * 手机App用户发送手机验证码是否成功与失败
	 * @param phonenum 手机号
	 * @param identifyCode 验证码
	 * @param smsCodeType 验证码类型
	 * @return messageJson
	 * @throws Exception
	 * @test by xiaobai 2016-4-16 OK
	 */
	@ResponseBody
	@RequestMapping(value = "/appPhoneCheckCode", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appPhoneCheckCode(String phonenum, String identifyCode,
			String smsCodeType) {
		Message message = new Message();
		SmsCode sms = SmsCodeUtil.GetSmsCodeByInterface(phonenum, identifyCode,
				smsCodeType);
		String oldIdentifyCode = smsCodeService.findIdentifyCodeByPhone(sms);
		if (identifyCode.equals(oldIdentifyCode)) {
			message = MessageUtils
					.appPhoneCheckCodeResp(NeedConstant.SUCCESS_MESSAGE);
		} else {
			message = MessageUtils
					.appPhoneCheckCodeResp(NeedConstant.ERROR_MESSAGE);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}

	/**
	 * 手机App用户注册
	 * @param userJson
	 * @return messageJson
	 * @throws Exception
	 * @test by xiaobai 2016-4-16 OK
	 */
	@ResponseBody
	@RequestMapping(value = "/appUserRegister", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appUserRegister(String userJson) throws Exception {
		Message message = new Message();
		User user = new User();
		user = JSON.parseObject(userJson, User.class);
		user.setPassword(user.getPassword());
		int userId = userService.addUser(user);
		if (userId == 1) {
			message = MessageUtils
					.appUserRegisterResp(NeedConstant.SUCCESS_MESSAGE);
		} else if (userId == 2) {
			message = MessageUtils.appUserRegisterResp("aa");
		} else {
			message = MessageUtils
					.appUserRegisterResp(NeedConstant.ERROR_MESSAGE);
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}

	/**
	 * 用户重置密码
	 * @param phonenum 手机号
	 * @param newPassword  新密码
	 * @return msgjson
	 * @throws Exception
	 * @test by xiaobai 2016-4-16 OK
	 * @Date 2016-4-16
	 * */
	@ResponseBody
	@RequestMapping(value = "/appUpdatePassword", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appUpdatePassword(String phonenum, String newPassword) {
		Message msg = new Message();
		User user = userService.loadUserByPhonenum(phonenum);
		try {
			user.setPassword(newPassword);
			int row = userService.updateUser(user);
			if (row > 0) {
				msg = MessageUtils
						.appUpdatePasswordResp(NeedConstant.SUCCESS_MESSAGE);
			} else {
				msg = MessageUtils
						.appUpdatePasswordResp(NeedConstant.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtils.appUpdatePasswordResp("aa");
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 手机App用户登录
	 * @param phonenum  手机号
	 * @param password  加密后的密码
	 * @return messageJson
	 * @throws Exception
	 * @Date 2016-4-10
	 * @test by xiaobai 2016-4-16 OK
	 */
	@ResponseBody
	@RequestMapping(value = "/appLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String appLogin(String phonenum, String password) {
		User user = new User();
		Message message = new Message();
		if (phonenum.isEmpty() || password.isEmpty()) {
			message = MessageUtils.appLoginResp(phonenum, user);
		} else {
			user.setPhonenum(phonenum);
			user.setPassword(password);
			User record = userService.SelectUser(user);
			if (record != null) {
				message = MessageUtils.appLoginResp(
						NeedConstant.SUCCESS_MESSAGE, record);
				System.out.println("用户登录！"); // 之后需要注销这行代码 System.out占用资源
			} else {
				message = MessageUtils.appLoginResp(NeedConstant.ERROR_MESSAGE,user);
			}
		}
		String messageJson = JSONObject.toJSONString(message);
		return messageJson;
	}

	/**
	 * 将手机号与网关解绑
	 * @author xiaobai
	 * @param phoneNum   手机号
	 * @param versionJson   版本JSON
	 * @return respjson MessageJson
	 * */
	@ResponseBody
	@RequestMapping(value = "/appUnbindGatewayNo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String appUnbindGatewayNo(String phoneNum, String versionJson) {
		Message msg = new Message();
		AppVersion appVersion = JSON.parseObject(versionJson, AppVersion.class);
		try {
			User user = userService.loadUserByPhonenum(phoneNum);
			user.setGatewayNo(null);
			userService.updateUser(user);
			themeService.updateVersion(appVersion);
			msg = MessageUtils.UbindGatewayNoResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtils.UbindGatewayNoResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}
	
	/**
	 * 注销用户 
	 * */
	@ResponseBody
	@RequestMapping(value = "/appCancelUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String appCancelUser(String phonenum){
		Message msg=new Message();
		try {
		userService.CancelUser(phonenum);
		smsCodeService.deleteByPhonenum(phonenum);
		userDeviceSpaceService.CancelUserSpaceDevice(phonenum);
		spaceService.deleteSpaceByPhonenum(phonenum);
		msg=MessageUtils.appCancelUserResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appCancelUserResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson=JSONObject.toJSONString(msg);
		return respjson;
	}

	private static Logger logger = Logger.getLogger(AppUserController.class);
}
