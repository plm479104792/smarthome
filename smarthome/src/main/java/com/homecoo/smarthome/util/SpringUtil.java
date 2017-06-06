package com.homecoo.smarthome.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil  implements ApplicationContextAware,InitializingBean{
	private static ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		this.applicationContext = applicationContext;
	}
	
	public static <T> T  getObject(String name , Class<T> clsType) throws BeansException{
		return applicationContext.getBean(name, clsType);
	}
	
	public static <T> T  getObject(Class<T> clsType) throws BeansException{
		return applicationContext.getBean(clsType);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

}