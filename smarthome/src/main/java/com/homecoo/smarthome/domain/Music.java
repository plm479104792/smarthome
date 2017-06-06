package com.homecoo.smarthome.domain;

public class Music {
	private Integer id;					//数据库表主键，七寸屏上传音乐列表到服务器，这个ID是空的
	private String uuid;				//uuid   没有用上
	private String gatewayNo;			//网关id
	private String familyName;		//家庭
	private String songName;		//歌曲名称
	private String space;			//空间名称(多个七寸屏)
	private String bz;				//备用
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getGatewayNo() {
		return gatewayNo;
	}
	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
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
		return "Music [id=" + id + ", uuid=" + uuid + ", gatewayNo="
				+ gatewayNo + ", familyName=" + familyName + ", songName="
				+ songName + ", space=" + space + ", bz=" + bz + "]";
	}
	
	
}
