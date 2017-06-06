package com.homecoo.smarthome.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.homecoo.smarthome.domain.APPThemeMusic;
import com.homecoo.smarthome.domain.AppVersion;
import com.homecoo.smarthome.domain.DeviceDto;
import com.homecoo.smarthome.domain.DeviceDtoApp;
import com.homecoo.smarthome.domain.Gateway;
import com.homecoo.smarthome.domain.Infrared;
import com.homecoo.smarthome.domain.Jpush;
import com.homecoo.smarthome.domain.Message;
import com.homecoo.smarthome.domain.Music;
import com.homecoo.smarthome.domain.Schedule;
import com.homecoo.smarthome.domain.Space;
import com.homecoo.smarthome.domain.ThemeMusic;
import com.homecoo.smarthome.domain.UserDeviceSpace;
import com.homecoo.smarthome.domain.Volume;
import com.homecoo.smarthome.service.IVolume;
import com.homecoo.smarthome.service.impl.VolumeServiceImpl;
import com.homecoo.smarthome.util.NeedConstant;

public class Test {

	public static void main(String[] args) {
		Infrared infrared=new Infrared();
		infrared.setInfraredId(2);
		
		System.out.println(JSON.toJSON(infrared));
		
	}
		
}
