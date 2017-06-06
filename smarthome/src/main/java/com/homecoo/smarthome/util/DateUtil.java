package com.homecoo.smarthome.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式化工具类
 * @author xiaobai
 * 
 * */
public class DateUtil {
	
	public static Date StringToDate(String dateStr, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date 转 年月日 时分秒
	 * */
	public static String ToYMDhms(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-DD hh:mm:ss");
		String date2 = sdf.format(date);
		return date2;
	}

	/**
	 * Date 转 年月日
	 * */
	public static String ToYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date2 = sdf.format(date);
		return date2;
	}

	/**
	 * Date 转时分秒
	 * */
	public static String Tohms(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String date2 = sdf.format(date);
		return date2;
	}
	
	
	/**
	 * 带有英文字母的时间转换
	 * @time:2017-04-24
	 * PM2.5超过30分钟没有更新，默认该PM2.5传感器已经失效。
	 * */
	public static boolean PM25(Date date){
		Long currentTime=System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		try {
			Long last=sdf.parse(sdf.format(date)).getTime();
			last+=30*60*1000;					//最近获取PM2.5后30分钟的 时间
			Long c=currentTime-last;
			if (c>0) {
				return true;
			}else {
				return false;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	

}
