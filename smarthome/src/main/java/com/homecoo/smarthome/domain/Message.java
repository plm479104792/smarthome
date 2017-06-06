package com.homecoo.smarthome.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: YCIG_EXTRONLINE从业人员考试系统后台管理
 * @Title: Message
 * @Description: 消息对象类
 * @author: lijz
 * @date :2016年2月19日 上午11:29:49
 * @Copyright: Copyright (c) 2016
 * @version V1.0
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2991603577177353117L;           //序列ID
	private String phoneNum;					//手机号
	private int generatedId;					//没有用到
	private String messageInfo;					//消息   比如：设置成功   设置失败 
	private String result = "success";			//返回结果  success   error两种
	private Object object;						//安卓与服务器之间的传参都放在这个属性里面
	private String description;					//描述   安卓这边有些参数也是放在这里面
	private String note;						//没有用上      安卓有些参数放在这里面
	
	//207-1-6 新增   只用于IOS 红外
	private List<Infrared> infraredList;
	
	
	//2016-07-17  配合IOS开发 新加
	
	/**
	 * 接口 ： appGetAllSpaceDevice(String phonenum)
	 * */
	private List<UserDeviceSpace> userDeviceSpaceList;
	
	/**
	 * 接口：getDeviceListByCategory(String phonenum, int categoryId)
	 * */
	private List<Device> deviceList;
	
	/**
	 * 接口：appGetDeviceListByPhoneNum(String phoneNum)
	 * */
	private List<DeviceDto> deviceDtoList;
	
	/**
	 * 接口：appGetDeviceInfo(String phonenum)
	 * */
	private AppVersion appVersion;
	private List<DeviceDtoApp> deviceDtoAppList;
	
	/**
	 * 接口：getGatewayInfo(String phoneNum)
	 * */
	private List<Gateway> gatewayList; 
	
	/**
	 * 接口：appGetMusic(String gatewayNo)
	 * */
	private List<Music> musicList; 			//IOS 音乐list
	
	/**
	 * 接口：appGetThemeMusic(String themeNo, String gatewayNo)
	 * */
	private ThemeMusic themeMusic;			//IOS  情景联动音乐
	
	/**
	 * 接口：appGetAllThemeMusic(String gatewayNo)
	 * */
	private List<ThemeMusic> themeMusicList;		//IOS  情景联动音乐list
	
	/**
	 * 接口：appAddSchedule(String scheduleJson)
	 * */
	private Integer scheduleId;
	
	/**
	 * 接口：appGetScheduleByGatewayNo(String gatewayNo)
	 * */
	private List<Schedule> scheduleList;
	
	/**
	 * 接口：appGetALLSpace(String phonenum)
	 * */
	private List<Space> spaceList;
	
	/**
	 * 接口：appGetThemeDevice(String gatewayNo)
	 * */
	private List<ThemeDevice> themeDeviceList;
	private List<Theme> themeList;
	
	
	/**
	 * 接口：appCompareVersion(String versionJson)
	 * 		 (1)如果服务器的时间和手机的时间戳相同则ResultMessage.object=0
	 *     	 (2)如果服务器的时间最新则返回ResultMessage.object=1
	 *       (3)如果手机端的手机最新则返回ResultMessage.object=2
	 * */
	
	
	/**
	 * 接口：appGetAllTheme(String gatewayNo)
	 * */
	private List<Packet> packetList;
	
	/**
	 * 接口：appLogin(String phonenum, String password)
	 * */
	private User user;
	
	/**
	 * 接口:appGetVolume(String gatewayNo)
	 * */
	private Volume volume;       //IOS  音量
	 
	
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getGeneratedId() {
		return generatedId;
	}

	public void setGeneratedId(int generatedId) {
		this.generatedId = generatedId;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<UserDeviceSpace> getUserDeviceSpaceList() {
		return userDeviceSpaceList;
	}

	public void setUserDeviceSpaceList(List<UserDeviceSpace> userDeviceSpaceList) {
		this.userDeviceSpaceList = userDeviceSpaceList;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public List<DeviceDto> getDeviceDtoList() {
		return deviceDtoList;
	}

	public void setDeviceDtoList(List<DeviceDto> deviceDtoList) {
		this.deviceDtoList = deviceDtoList;
	}

	public AppVersion getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(AppVersion appVersion) {
		this.appVersion = appVersion;
	}

	public List<DeviceDtoApp> getDeviceDtoAppList() {
		return deviceDtoAppList;
	}

	public void setDeviceDtoAppList(List<DeviceDtoApp> deviceDtoAppList) {
		this.deviceDtoAppList = deviceDtoAppList;
	}

	public List<Gateway> getGatewayList() {
		return gatewayList;
	}

	public void setGatewayList(List<Gateway> gatewayList) {
		this.gatewayList = gatewayList;
	}

	public List<Music> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<Music> musicList) {
		this.musicList = musicList;
	}

	public ThemeMusic getThemeMusic() {
		return themeMusic;
	}

	public void setThemeMusic(ThemeMusic themeMusic) {
		this.themeMusic = themeMusic;
	}

	public List<ThemeMusic> getThemeMusicList() {
		return themeMusicList;
	}

	public void setThemeMusicList(List<ThemeMusic> themeMusicList) {
		this.themeMusicList = themeMusicList;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

	public List<ThemeDevice> getThemeDeviceList() {
		return themeDeviceList;
	}

	public void setThemeDeviceList(List<ThemeDevice> themeDeviceList) {
		this.themeDeviceList = themeDeviceList;
	}

	public List<Theme> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}

	public List<Packet> getPacketList() {
		return packetList;
	}

	public void setPacketList(List<Packet> packetList) {
		this.packetList = packetList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Infrared> getInfraredList() {
		return infraredList;
	}

	public void setInfraredList(List<Infrared> infraredList) {
		this.infraredList = infraredList;
	}

	@Override
	public String toString() {
		return "Message [phoneNum=" + phoneNum + ", generatedId=" + generatedId
				+ ", messageInfo=" + messageInfo + ", result=" + result
				+ ", object=" + object + ", description=" + description
				+ ", note=" + note + ", infraredList=" + infraredList
				+ ", userDeviceSpaceList=" + userDeviceSpaceList
				+ ", deviceList=" + deviceList + ", deviceDtoList="
				+ deviceDtoList + ", appVersion=" + appVersion
				+ ", deviceDtoAppList=" + deviceDtoAppList + ", gatewayList="
				+ gatewayList + ", musicList=" + musicList + ", themeMusic="
				+ themeMusic + ", themeMusicList=" + themeMusicList
				+ ", scheduleId=" + scheduleId + ", scheduleList="
				+ scheduleList + ", spaceList=" + spaceList
				+ ", themeDeviceList=" + themeDeviceList + ", themeList="
				+ themeList + ", packetList=" + packetList + ", user=" + user
				+ ", volume=" + volume + "]";
	}
	

}
