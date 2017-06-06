package com.homecoo.smarthome.util.unused;

import java.util.Random;
/**
 *@Description:随机生成 0----30000 的随机数  (没有用上)
 * */
public class RandomNumUtil {
	
	/**
	 * @Description:最多延迟时间为30S
	 * */
	 public static Long RandomNum(){
	        int max=30000;
	        int min=0;
	        Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;
			return Long.valueOf(s);
	    }
}
