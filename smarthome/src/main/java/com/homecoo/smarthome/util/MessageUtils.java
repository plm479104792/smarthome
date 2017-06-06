package com.homecoo.smarthome.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.Device;
import com.homecoo.smarthome.domain.DeviceDtoApp;
import com.homecoo.smarthome.domain.Gateway;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.Music;
import com.homecoo.smarthome.domain.Packet;
import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.domain.Space;
import com.homecoo.smarthome.domain.Theme;
import com.homecoo.smarthome.domain.ThemeDevice;
import com.homecoo.smarthome.domain.ThemeMusic;
import com.homecoo.smarthome.domain.User;
import com.homecoo.smarthome.domain.UserDeviceSpace;
import com.homecoo.smarthome.domain.Volume;

/**
 * 返回Json参数的工具类
 * 
 * @author xiaobai
 * */
public class MessageUtils {

	/**
	 * 解绑网关Message方法
	 * 
	 * @param type
	 *            success或者failed
	 * @return msg Message
	 * */
	public static Message UbindGatewayNoResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("网关解绑成功!");
		} else if (type.equals(NeedConstant.ERROR_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("网关解绑失败!");
		}
		return msg;
	}

	/**
	 * 用户登录Message方法
	 * 
	 * @author Administrator
	 * @return msg Message
	 * */
	public static Message appLoginResp(String type, User user) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setObject(JSONObject.toJSONString(user));
			msg.setUser(user);
		} else if (type.equals(NeedConstant.ERROR_MESSAGE)) {
			msg.setMessageInfo("密码错误或者用户名不存在");
			msg.setResult(type);
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("请输入 用户名  密码!");
		}
		return msg;
	}

	/**
	 * 用户更新密码Message方法
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appUpdatePasswordResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("重置密码成功!");
			msg.setResult(type);
		} else if (type.equals(NeedConstant.ERROR_MESSAGE)) {
			msg.setMessageInfo("重置密码失败!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("用户不存在!,请输入正确的账号！");
			msg.setResult(NeedConstant.ERROR_MESSAGE);
		}
		return msg;
	}

	/**
	 * 用户注册
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appUserRegisterResp(String type) {
		Message msg = new Message();
		if (NeedConstant.SUCCESS_MESSAGE.equals(type)) {
			msg.setMessageInfo("注册成功");
			msg.setResult(type);
		} else if (NeedConstant.ERROR_MESSAGE.equals(type)) {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("注册失败");
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("注册失败，用户名已经存在");
		}
		return msg;
	}

	/**
	 * 手机App用户发送手机验证码是否成功与失败
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appPhoneCheckCodeResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("验证成功");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("验证失败");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 手机App用户重置密码发送手机验证码
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSendRePwdCodeResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("发送验证码到对应手机成功!");
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("发送验证码到对应手机失败!");
		}
		return msg;
	}

	/**
	 * 手机App用户注册发送手机验证码,注册的时候需要判断该手机号码是否已经存在
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSendRegitserCodeResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("发送验证码成功");
			msg.setResult(type);
		} else if (type.equals(NeedConstant.ERROR_MESSAGE)) {
			msg.setMessageInfo("发送验证码失败");
			msg.setResult(type);
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("该手机号已经被注册");
		}
		return msg;
	}

	/**
	 * 手机从服务器获取情景
	 * 
	 * @param type
	 * @param appVersion
	 * @param themeDevicelist
	 * @param themelist
	 * @return msg
	 * */
	public static Message appGetThemeDeviceResp(String type,
			AppVersion appVersion, List<ThemeDevice> themeDevicelist,
			List<Theme> themelist) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setDescription(JSONObject.toJSONString(appVersion));
			msg.setResult(type);
			msg.setObject(JSONArray.toJSONString(themeDevicelist));
			msg.setMessageInfo(JSONArray.toJSONString(themelist));
			// IOS JSON解析
			msg.setAppVersion(appVersion);
			msg.setThemeDeviceList(themeDevicelist);
			msg.setThemeList(themelist);
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("获取情景失败!");
		}
		return msg;
	}

	/**
	 * 手机从服务器获取所有的情景报文
	 * 
	 * @param type
	 * @param list
	 * @return msg
	 * */
	public static Message appGetAllThemeResp(String type, List<Packet> list) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("获取情景报文成功!");
			msg.setResult(type);
			msg.setObject(JSONArray.toJSONString(list));
			msg.setPacketList(list);
		} else {
			msg.setMessageInfo("获取情景报文失败!");
			msg.setResult(NeedConstant.ERROR_MESSAGE);
		}
		return msg;
	}

	/**
	 * 服务器对比手机提交的Version
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appCompareVersionResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("版本检查成功!");
			msg.setResult(type);
			msg.setObject(NeedConstant.VERSION_SERVER_GTR_APP);
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("版本检查失败!");
		}
		return msg;
	}

	/**
	 * 添加空间
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appAddSpaceResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("添加成功");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("添加失败");
		}
		return msg;
	}

	/**
	 * 批量处理空间名称
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSetSpaceListResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("设置成功!");
			msg.setResult(type);
		} else {
			msg.setResult(type);
			msg.setMessageInfo("设置失败!");
		}
		return msg;
	}

	/**
	 * 
	 * 更新空间
	 * 
	 * @param type
	 * @return message
	 * */
	public static Message appUpdateSpaceResp(String type) {
		Message message = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			message.setResult(type);
			message.setMessageInfo("修改成功");
		} else {
			message.setResult(type);
			message.setMessageInfo("修改失败");
		}
		return message;
	}

	/**
	 * 删除空间
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appDeleteSpaceResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("删除成功");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("删除失败");
		}
		return msg;
	}

	/**
	 * 获取用户所有的空间
	 * 
	 * @param type
	 * @param spaceList
	 * @param appVersion
	 * @return msg
	 * */
	public static Message appGetALLSpaceResp(String type,
			List<Space> spaceList, AppVersion appVersion) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setObject(JSONArray.toJSONString(spaceList));
			msg.setDescription(JSONObject.toJSONString(appVersion));
			// ios JSON
			msg.setSpaceList(spaceList);
			msg.setAppVersion(appVersion);
			msg.setResult(NeedConstant.SUCCESS_MESSAGE);
			msg.setMessageInfo("获取空间列表成功!");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("获取空间列表失败");
		}
		return msg;
	}

	/**
	 * 用户获取单个空间
	 * 
	 * @param type
	 * @param space
	 * @return msg
	 * */
	public static Message appGetSingerSpaceResp(String type, Space space) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setObject(JSONObject.toJSONString(space));
			msg.setMessageInfo("获取空间成功!");
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("获取空间失败");
		}

		return msg;

	}

	/**
	 * 添加单个定时任务
	 * 
	 * @param type
	 * @param ScheduleId
	 *            定时任务ID
	 * @param aa
	 *            1 定时有误 设置的定时为过去时间 2 该设备或者情景在同一时间已经设置了定时任务 3 程序出错
	 * @return msg
	 * */
	public static Message appAddScheduleResp(String type, int ScheduleId, int aa) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("添加定时任务成功!");
			msg.setObject(ScheduleId);
			msg.setScheduleId(ScheduleId);
			msg.setResult(type);
		} else {
			msg.setResult(type);
			if (aa == 1) {
				msg.setMessageInfo("定时任务设置有误，请重新设置定时!");
			} else if (aa == 2) {
				msg.setMessageInfo("该设备或者情景，您已经添加了定时任务,请重新编辑！");
			} else {
				msg.setMessageInfo("设置定时任务失败!");
			}
		}
		return msg;
	}

	/**
	 * 获取该网关下的所有定时任务 (留着备用 可能不需要)
	 * 
	 * @param type
	 * @param scheduleList
	 * @return msg
	 * */
	public static Message appGetScheduleByGatewayNoResp(String type,
			List<Schedule> scheduleList) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("获取定时任务成功!");
			msg.setObject(JSONArray.toJSONString(scheduleList));
			msg.setScheduleList(scheduleList);
		} else {
			msg.setResult(type);
			msg.setMessageInfo("获取定时任务失败!");
		}
		return msg;
	}

	/**
	 * 获取 手机号 该用户下的所有定时任务
	 * 
	 * @param type
	 * @param scheduleList
	 * @return msg
	 * */
	public static Message appGetScheduleByPhoneNumResp(String type,
			List<Schedule> scheduleList) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(NeedConstant.SUCCESS_MESSAGE);
			msg.setMessageInfo("获取定时任务成功!");
			msg.setObject(JSONArray.toJSONString(scheduleList));
			msg.setScheduleList(scheduleList);
		} else {
			msg.setResult(NeedConstant.ERROR_MESSAGE);
			msg.setMessageInfo("获取定时任务失败!");
		}
		return msg;
	}

	/**
	 * 删除单个定时任务
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appDeleteScheduleByScheduleIdResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("删除定时任务成功!");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("删除定时任务失败!");
		}
		return msg;
	}

	/**
	 * 删除该手机号下的所有定时任务
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appDeleteScheduleByPhoneNumResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("删除定时任务成功!");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("删除定时任务失败!");
		}
		return msg;
	}

	/**
	 * 删除该网关下的所有定时任务 (可能用不上，留着备用)
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appDeleteScheduleByGatewayNoResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("删除定时任务成功!");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("删除定时任务失败!");
		}
		return msg;
	}

	/**
	 * 用户根据 手机号 和 设备id 查询 已经设置的定时(默认下 已经执行的定时任务 不显示)
	 * 
	 * @param type
	 *            1:表示设备 2： 表示情景 3：表示定时音乐 scheduleName字段放songName 4：表示 红外
	 * @return msg
	 * */
	public static Message appGetScheduleResp(String type,
			List<Schedule> schedules) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("获取定时任务成功!");
			msg.setObject(JSONArray.toJSONString(schedules));
			msg.setScheduleList(schedules);
		} else {
			msg.setResult(type);
			msg.setMessageInfo("获取定时任务失败！");
		}
		return msg;
	}

	/**
	 * 根据手机号，情景ID 或者deviceID 更新定时
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appUpdateScheduleResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setResult(type);
			msg.setMessageInfo("更新定时任务成功!");
		} else {
			msg.setResult(type);
			msg.setMessageInfo("更新定时任务失败!");
		}
		return msg;
	}

	/**
	 * 七寸屏向服务器发送歌曲列表
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSendMusicListResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("添加歌曲成功!");
			msg.setResult(type);
		} else {
			msg.setResult(type);
			msg.setMessageInfo("添加歌曲失败!");
		}
		return msg;
	}

	/**
	 * 手机App用户获取七寸屏上歌曲
	 * 
	 * @param type
	 * @param list
	 * @return msg
	 * */
	public static Message appGetMusicResp(String type, List<Music> list) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("获取音乐成功!");
			msg.setResult(type);
			msg.setObject(JSONArray.toJSONString(list));
			msg.setMusicList(list);
		} else {
			msg.setResult(type);
			msg.setMessageInfo("获取音乐失败!");
		}
		return msg;
	}

	/**
	 * 手机控制歌曲播放指令，Jpush推送
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSendMusicOrderResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("控制成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("控制失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * Jpush推送 情景音乐设置到七寸屏上
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSendThemeMusicOrderResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("发送OK!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("发送失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 情景音乐设置
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message setThemeMusicResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("设置成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("设置失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 删除情景音乐设置
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message DeleteThemeMusicResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("删除情景歌曲成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("删除情景歌曲失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 查询单个情景音乐
	 * 
	 * @param type
	 * @return getmusic
	 * @return msg
	 * */
	public static Message appGetThemeMusicResp(String type, ThemeMusic getmusic) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("获取情景歌曲成功!");
			msg.setResult(type);
			msg.setObject(JSONObject.toJSONString(getmusic));
			msg.setThemeMusic(getmusic);
		} else {
			msg.setMessageInfo("该情景您之前未设置情景音乐");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 用户从服务器上同步 所有的情景 音乐也要同步
	 * 
	 * @param type
	 * @return themeMusicList
	 * @return msg
	 * */
	public static Message appGetAllThemeMusicResp(String type,
			List<ThemeMusic> themeMusicList) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("同步情景音乐成功!");
			msg.setResult(type);
			msg.setObject(JSONArray.toJSONString(themeMusicList));
			msg.setThemeMusicList(themeMusicList);
		} else {
			msg.setMessageInfo("同步情景音乐失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 手机同步所有的情景音乐给服务器
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSetThemeMusicResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("同步情景音乐成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("同步情景音乐失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * APP设置音量控制
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSetVolumeResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("音量设置成功！");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("音量设置失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * APP获取音量控制
	 * 
	 * @param type
	 * @param volume
	 * @return msg
	 * */
	public static Message appGetVolumeResp(String type, Volume volume) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("音量获取成功！");
			msg.setResult(type);
			msg.setObject(JSONObject.toJSONString(volume));
			msg.setVolume(volume);
		} else {
			msg.setMessageInfo("音量获取失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 同步网关 由手机发送网关信息到服务器
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appAsyncGatewayInfoResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("同步成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("同步失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 同步网关,手机从服务器获取网关信息
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appGetGatewayInfoResp(String type,
			AppVersion version, List<Gateway> gateway) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("成功获取网关信息!");
			msg.setResult(type);
			msg.setDescription(JSONObject.toJSONString(version));
			msg.setObject(JSONArray.toJSONString(gateway));
			// IOS JSON
			msg.setAppVersion(version);
			msg.setGatewayList(gateway);
		} else {
			msg.setMessageInfo("获取网关信息失败!");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * APP同步网关信息给服务器
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appSyncGatewayInfoResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("同步网关信息成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("同步网关信息失败!");
			msg.setResult(type);
		}
		return msg;

	}

	/**
	 * 增加单个设备
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message wgAddDeviceResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("添加成功");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("添加失败");
			msg.setResult(type);
		}
		return msg;

	}

	/**
	 * 手机发送控制报文到服务器
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appDeviceControllerResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("发送命令成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("发送命令失败！");
			msg.setResult(type);
		}
		return msg;

	}

	/**
	 * 下面设备退网了，手机没有打开。手机上还是有该设备，这个时候需要手机删除设备
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appDeleteDeviceResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("删除成功");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("删除失败");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * 用户获取设备大类 设备列表
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appGetDeviceListByCategoryResp(String type,
			List<Device> deviceList) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("获取设备列表成功");
			msg.setResult(type);
			msg.setObject(JSON.toJSONString(deviceList));
			msg.setDeviceList(deviceList);
		} else {
			msg.setMessageInfo("获取设备列表失败");
			msg.setResult(type);
		}
		return msg;
	}

	/**
	 * APP从服务器获取所有设备信息
	 * 
	 * @param type
	 * @return msg
	 * */
	public static Message appGetDeviceInfoResp(String type,
			AppVersion appVersion, List<DeviceDtoApp> deviceDtoAppList,
			List<UserDeviceSpace> userDeviceSpacesList) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("APP获取设备成功!");
			msg.setResult(type);
			msg.setMessageInfo(JSONArray.toJSONString(deviceDtoAppList));
			msg.setObject(JSONArray.toJSONString(userDeviceSpacesList));
			msg.setDescription(JSONObject.toJSONString(appVersion));
			
			// 2016-07-17 添加IOS返回json
			msg.setAppVersion(appVersion);
			msg.setDeviceDtoAppList(deviceDtoAppList);
			msg.setUserDeviceSpaceList(userDeviceSpacesList);
		} else {
			msg.setMessageInfo("APP获取设备失败!");
			msg.setResult(type);
		}
		return msg;
	}
	
	
	/**
	 * APP同步所有设备信息到服务器
	 * @param type
	 * @return msg
	 * */
	public static Message appSyncDeviceInfoResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("同步成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("同步失败!");
			msg.setResult(type);
		}
		return msg;
	}
	
	/**
	 * 注销用户 
	 * @param type
	 * @return msg
	 * */
	public static Message appCancelUserResp(String type) {
		Message msg = new Message();
		if (type.equals(NeedConstant.SUCCESS_MESSAGE)) {
			msg.setMessageInfo("注销用户成功!");
			msg.setResult(type);
		} else {
			msg.setMessageInfo("注销用户失败!");
			msg.setResult(type);
		}
		return msg;
	}
	
	
	
	
	
	
	
	
	
	
	

}
