package com.homecoo.smarthome.controller;

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
import com.homecoo.smarthome.domain.APPThemeMusic;
import com.homecoo.smarthome.domain.Jpush;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.Music;
import com.homecoo.smarthome.domain.ThemeMusic;
import com.homecoo.smarthome.domain.Volume;
import com.homecoo.smarthome.service.IMusicService;
import com.homecoo.smarthome.service.IVolume;
import com.homecoo.smarthome.util.JPushimpl;
import com.homecoo.smarthome.util.MessageUtils;
import com.homecoo.smarthome.util.MusicUtils;
import com.homecoo.smarthome.util.NeedConstant;

@Controller
public class AppMusicContorller {

	@Autowired
	private IMusicService musicService;
	@Autowired
	private IVolume volumeService;

	/**
	 * 七寸屏向服务器发送歌曲列表
	 * @author xiaobai
	 * @Date 2016-4-13
	 * @param musicJson 音乐Json
	 * @param gatewayNo 网关编号
	 * @return respjson 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/appSendMusicList", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendMusicList(String musicJson, String gatewayNo) {
		Message msg = new Message();
		List<Music> listMusic = JSON.parseArray(musicJson, Music.class);
		try {
			musicService.deletdMusic(gatewayNo);
			Iterator<Music> iterator = listMusic.iterator();
			while (iterator.hasNext()) {
				Music music = iterator.next();
				musicService.addMusic(music);
			}
			msg=MessageUtils.appSendMusicListResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appSendMusicListResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 手机App用户获取七寸屏上歌曲
	 * @Date 2016-4-13
	 * @return respjson 
	 * @param gatewayNo 网关编号 
	 */
	@ResponseBody
	@RequestMapping(value = "/appGetMusic", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetMusic(String gatewayNo) {
		Message msg = new Message();
		try {
			List<Music> list = musicService.selecctAllMusic(gatewayNo);
			msg=MessageUtils.appGetMusicResp(NeedConstant.SUCCESS_MESSAGE, list);
		} catch (Exception e) {
			msg=MessageUtils.appGetMusicResp(NeedConstant.ERROR_MESSAGE,null);
		}
		String respjson = JSONArray.toJSONString(msg);
		return respjson;
	}

	/**
	 * 手机控制歌曲播放指令，Jpush推送
	 * @author xiaobai
	 * @Date 2016-4-13
	 * @param jpushJson
	 * @return respjson
	 */
	@ResponseBody
	@RequestMapping(value = "/appSendMusicOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendMusicOrder(String jpushJson) {
		Message msg = new Message();
		System.out.println("收到需要推送到七寸屏的控制指令"+jpushJson);
		try {
			JPushimpl jpush = new JPushimpl();
			jpush.sendPush(JSON.parseObject(jpushJson, Jpush.class));
			msg=MessageUtils.appSendMusicOrderResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			msg=MessageUtils.appSendMusicOrderResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * Jpush推送 情景音乐设置到七寸屏上
	 * @author xiaobai
	 * @Date 2016-06-24
	 * @param jpushJson 
	 * @return respjson
	 */
	@ResponseBody
	@RequestMapping(value = "/appSendThemeMusicOrder", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendThemeMusicOrder(String jpushJson) {
		Message msg = new Message();
		try {
			JPushimpl jpush = new JPushimpl();
			jpush.sendPush(JSONObject.parseObject(jpushJson, Jpush.class));
			//TODO  这里JPUSH推送的menssage有大小限制。需要分段 
			msg=MessageUtils.appSendThemeMusicOrderResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			msg=MessageUtils.appSendThemeMusicOrderResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}
	
	
	
	
	/**
	 * (单个设置)
	 * 情景音乐设置
	 * @author xiaobai
	 * @param:themeid,songName,wgid,style //style 1:单曲循环 2:列表循环 3:随机循环 4暂停
	 * 如果该themeid有了就是update 没有添加
	 * @Date 2016-4-13
	 * @test 2016-4-20 OK 情景音乐模式 点进去之后，调用接口查询是否设置了歌曲(弹出框
	 * 没有你还没有设置该情景音乐，有当前情景音乐设置为 彩虹(确认 取消键：是否删除该情景音乐)) 选取了歌曲 退出的时候跳出一个弹出框，是否设置：否
	 * 不做判断(不调用服务器接口) 是：发送该themeMusic(歌曲 thmemid wgid==) 过来 根据themeMusic的
	 * themeid wgid 查询该情景下之前是否设置 之前没有设置，添加情景音乐 之前有设置：update 离家 睡眠模式默认暂停音乐 怎么弄
	 * 这种情况在安卓端设置好添加一个控件
	 */
	@ResponseBody
	@RequestMapping(value = "/setThemeMusic", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String setThemeMusic(String thememusic) {
		System.out.println(thememusic.toString());
		Message msg = new Message();
		ThemeMusic themeMusic = JSON.parseObject(thememusic,
				ThemeMusic.class);
		ThemeMusic getmusic = musicService.getThemeMusic(themeMusic);
		int row = 0;
		if (getmusic != null) {
			row = musicService.updateThemeMusic(themeMusic);
		} else {
			row = musicService.setThemeMusic(themeMusic);
		}
		if (row > 0) {
			msg=MessageUtils.setThemeMusicResp(NeedConstant.SUCCESS_MESSAGE);
		} else {
			msg=MessageUtils.setThemeMusicResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 *  删除情景音乐设置
	 * @author xiaobai OK
	 * @Date 2016-4-13
	 * @param themeNo 情景id
	 * @param gatewayNo 网关编号
	 * @return respjson
	 */
	@ResponseBody
	@RequestMapping(value = "/DeleteThemeMusic", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String DeleteThemeMusic(String themeNo, String gatewayNo) {
		Message msg = new Message();
		int row = musicService.deleteThemeMuisc(themeNo, gatewayNo);
		if (row > 0) {
			msg=MessageUtils.DeleteThemeMusicResp(NeedConstant.SUCCESS_MESSAGE);
		} else {
			msg=MessageUtils.DeleteThemeMusicResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;
	}

	/**
	 * 查询单个情景音乐
	 * @author xiaobai 
	 * @Date 2016-4-13
	 * @praram themeNo 情景id
	 * @param gatewayNo 网关编号
	 * @return respjson
	 */
	@ResponseBody
	@RequestMapping(value = "/appGetThemeMusic", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetThemeMusic(String themeNo, String gatewayNo) {
		Message msg = new Message();
		ThemeMusic getmusic = musicService.getThemeMusicByphoneNumgatewayNo(
				themeNo, gatewayNo);
		if (getmusic != null) {
			msg=MessageUtils.appGetThemeMusicResp(NeedConstant.SUCCESS_MESSAGE, getmusic);
		} else {
			msg=MessageUtils.appGetThemeMusicResp(NeedConstant.ERROR_MESSAGE, null);
		}
		String respjson = JSONObject.toJSONString(msg);
		System.out.println("appGetThemeMusic "+respjson);
		return respjson;
	}

	/**
	 * 用户从服务器上同步 所有的情景 音乐也要同步
	 * @author xiaobai
	 * @param gatewayNo
	 * @return respjson
	 * @test 2016-4-21 OK
	 * */
	@ResponseBody
	@RequestMapping(value = "appGetAllThemeMusic", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetAllThemeMusic(String gatewayNo) {
		Message msg = new Message();
		try {
			List<ThemeMusic> themeMusicList = musicService
					.getAllThemeMusic(gatewayNo);
			msg=MessageUtils.appGetAllThemeMusicResp(NeedConstant.SUCCESS_MESSAGE, themeMusicList);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetAllThemeMusicResp(NeedConstant.ERROR_MESSAGE,null);
		}
		String respjson = JSONObject.toJSONString(msg);
		System.out.println(respjson);
		return respjson;

	}

	/**
	 * 手机同步所有的情景音乐给服务器
	 * @author xiaobai
	 * @Date 2016-4-20
	 * @param:themeMusicList 情景音乐listJson
	 * @return respjson
	 * @test 2016-4-21 OK
	 * */
	@ResponseBody
	@RequestMapping(value = "appSetThemeMusic", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSetThemeMusic(String themeMusicList) {
		Message msg = new Message();
		List<ThemeMusic> list = JSON.parseArray(themeMusicList,
				ThemeMusic.class);
		try {
			Iterator<ThemeMusic> iterator = list.iterator();
			while (iterator.hasNext()) {
				ThemeMusic themeMusic = iterator.next();
				boolean b = musicService.getThemeMusicByThemeNo(themeMusic
						.getThemeNo());
				if (b) {
					musicService.updateThemeMusic(themeMusic);
				} else {
					musicService.setThemeMusic(themeMusic);
				}
			}
			msg=MessageUtils.appSetThemeMusicResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appSetThemeMusicResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson = JSONObject.toJSONString(msg);
		return respjson;

	}

	/**
	 * 内网情景音乐同步到 服务器
	 * @param appthemeMusicJson
	 * @return respjson
	 * @author xiaobai
	 * */
	@ResponseBody
	@RequestMapping(value = "appSendThemeMusicList", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSendThemeMusicList(String appthemeMusicJson) {
		System.out.println("内网同步到服务器上的情景联动音乐 ："+appthemeMusicJson);
		Message msg = new Message();
		try {
			List<APPThemeMusic> list = JSON.parseArray(appthemeMusicJson,
					APPThemeMusic.class);
			List<ThemeMusic> themeMusics = MusicUtils
					.AppthememusicListToThememusicList(list);
			Iterator<ThemeMusic> iterator = themeMusics.iterator();
			while (iterator.hasNext()) {
				ThemeMusic themeMusic = iterator.next();
				/**
				 * update 2016-10-11 本地同步外网的情况  歌曲乱码
				 * */
				String songName=new String(themeMusic.getSongName());
				themeMusic.setSongName(songName);
				
				boolean b = musicService.getThemeMusicByThemeNo(themeMusic
						.getThemeNo());
				if (b) {
					musicService.updateThemeMusic(themeMusic);
				} else {
					musicService.setThemeMusic(themeMusic);
				}
			}
			msg=MessageUtils.appSetThemeMusicResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			System.err.println(e);
			msg=MessageUtils.appSetThemeMusicResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson=JSON.toJSONString(msg);
		return respjson;

	}
	
	/**
	 * APP设置音量控制
	 * @author xiaobai 
	 * @param volumeJson 音量Json
	 * @return respjson
	 * @Date 2016-07-21
	 * */
	@ResponseBody
	@RequestMapping(value = "appSetVolume", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appSetVolume(String volumeJson){
		Message msg=new Message();
		try {
		Volume volume=JSON.parseObject(volumeJson, Volume.class);
		volumeService.insertVolume(volume);
		msg=MessageUtils.appSetVolumeResp(NeedConstant.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appSetVolumeResp(NeedConstant.ERROR_MESSAGE);
		}
		String respjson=JSONObject.toJSONString(msg);
		return respjson;
		
	}
	
	/**
	 * APP获取音量控制
	 * @author xiaobai 
	 * @Date 2016-07-21
	 * @param gatewayNo 网关编号
	 * @return respjson
	 * */
	@ResponseBody
	@RequestMapping(value = "appGetVolume", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String appGetVolume(String gatewayNo){
		Message msg=new Message();
		try {
		Volume volume=volumeService.getVolume(gatewayNo);
		msg=MessageUtils.appGetVolumeResp(NeedConstant.SUCCESS_MESSAGE, volume);
		} catch (Exception e) {
			e.printStackTrace();
			msg=MessageUtils.appGetVolumeResp(NeedConstant.ERROR_MESSAGE,null);
		}
		String respjson=JSONObject.toJSONString(msg);
		return respjson;
		
	}
	
	// private static Logger logger = Logger.getLogger(AppMusicContorller.class);
}
