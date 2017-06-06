package com.homecoo.smarthome.domain;


public class APPThemeMusic {

	private String songName;			//歌曲名称
	private String gatewayNo;          //网关号
	private String themeNo;				//情景ID
	private String deviceNo;		//硬件情景面板  设备ID
	private String deviceState;     //情景面板上的设置状态   设置状态
	private String themeName;		//情景名称
	private String style;			//音乐控制       参考Style
	private String space;			//空间
	private String bz;				//备注
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	public String getThemeNo() {
		return themeNo;
	}
	public void setThemeNo(String themeNo) {
		this.themeNo = themeNo;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceState() {
		return deviceState;
	}
	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Override
	public String toString() {
		return "APPThemeMusic [songName=" + songName + ", gatewayNo="
				+ gatewayNo + ", themeNo=" + themeNo + ", deviceNo=" + deviceNo
				+ ", deviceState=" + deviceState + ", themeName=" + themeName
				+ ", style=" + style + ", space=" + space + ", bz=" + bz + "]";
	}
	
}
