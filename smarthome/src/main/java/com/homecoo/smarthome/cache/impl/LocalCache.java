package com.homecoo.smarthome.cache.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.homecoo.smarthome.cache.ILocalCache;



/**
 * @Project: homecoo
 * @Title: LocalCacheImpl
 * @Description 本地缓存，就是包裹了一个{@link ConcurrentHashMap},实现{@link ILocalCache}接口
 * @author: lijz
 * @date: 2015年1月28日 下午9:49:11
 * @company: SpringMVC开发的自学项目
 * @Copyright: Copyright (c) 2014
 * @version V2.0
 */
public class LocalCache implements ILocalCache {
	// 创建日志
	private static Log log = LogFactory.getLog(LocalCache.class);
	// 缓存的实际map对象
	private ConcurrentHashMap<Object, Object> mapCache = new ConcurrentHashMap<Object, Object>();

	/**
	 * @Description 缓存大小
	 * @return 缓存大小
	 */
	@Override
	public int size() {
		return this.mapCache.size();
	}

	/**
	 * @Description 判断空
	 * @return 是否空
	 */
	@Override
	public boolean isEmpty() {
		return this.mapCache.isEmpty();
	}

	/**
	 * @Description 判断键包含
	 * @param key
	 *            - 键
	 * @return 是否包含
	 */
	@Override
	public boolean containsKey(Object key) {
		return this.mapCache.containsKey(key);
	}

	/**
	 * @Description 判断值包含
	 * @param value
	 *            - 值
	 * @return 是否包含
	 */
	@Override
	public boolean containsValue(Object value) {
		return this.mapCache.containsValue(value);
	}

	/**
	 * @Description 按键取值
	 * @param key
	 *            - 键
	 * @return 值
	 */
	@Override
	public Object get(Object key) {
		return this.mapCache.get(key);
	}

	/**
	 * @Description 键值存储
	 * @param key
	 *            - 键
	 * @param value
	 *            - 值
	 * @return 前一个key映射的值,没有则返空
	 */
	@Override
	public Object put(Object key, Object value) {
		Object result = null;
		synchronized (this.mapCache) {
			if (null != value) {
				result = this.mapCache.put(key, value);
			} else {
				log.debug(key + " value is null");
			}
		}
		return result;
	}

	/**
	 * @Description 按键删值
	 * @param key
	 *            - 键
	 * @return 值
	 */
	@Override
	public Object remove(Object key) {
		Object result;
		synchronized (this.mapCache) {
			result = this.mapCache.remove(key);
		}
		return result;
	}

	/**
	 * @Description 批量存储,迭代实现比较慢
	 * @param m
	 *            - map集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map<? extends Object, ? extends Object> m) {
		Set<Object> set = (Set<Object>) m.keySet();
		for (Object object : set) {
			this.mapCache.put(object, m.get(object));
		}
	}

	/**
	 * @Description 清空缓存
	 */
	@Override
	public void clear() {
		synchronized (this.mapCache) {
			this.mapCache.clear(); // 清空list数据
		}
	}

	/**
	 * @Description 获取键集
	 * @return 键的set集合
	 */
	@Override
	public Set<Object> keySet() {
		return this.mapCache.keySet();
	}

	/**
	 * @Description 获取值集
	 * @return 值的集合
	 */
	@Override
	public Collection<Object> values() {
		return this.mapCache.values();
	}

	/**
	 * @Description 获取键值对象集
	 * @return 键值对象集合
	 */
	@Override
	public Set<java.util.Map.Entry<Object, Object>> entrySet() {
		return this.mapCache.entrySet();
	}

	/**
	 * map 的存储形式不是 key value键值对的形式吗 那么你就遍历map把数组取出再遍历数组取得数组的每一个值
	 */

	// map的遍历方法 推荐两个
	// 一、最常规的遍历方法
	// public static void work(Map<String, Student> map) {
	// Collection<Student> c = map.values();
	// Iterator it = c.iterator();
	// for (; it.hasNext();) {
	// System.out.println(it.next());
	// }
	// }
	// 二、利用keyset遍历
	// public static void workByKeySet(Map<String, Student> map) {
	// Set<String> key = map.keySet();
	// for (Iterator it = key.iterator(); it.hasNext();) {
	// String s = (String) it.next();
	// System.out.println(map.get(s));
	// }
	// }

	// 补充：如果你细心的话 ，应该发现map和set是有着很紧密的联系的。其实map的key单独拿出来看就是个set， 所以map也可以看成是 key
	// ，value形式的set。
	
	/**
	 * @Description:获取某一本地缓存里面的所有key和value的方法
	 * @param :args
	 * @return
	 * @throws Exception
	 */
	public void obtain_All_Key_Value_ToLocalMapCache(ConcurrentHashMap<String, Object> localMapCache) {
		for (Map.Entry<String, Object> e : localMapCache.entrySet() ) {
			System.out.println("键:" + e.getKey() + ", 值:" + e.getValue());
		}
	}

	public LocalCache(ConcurrentHashMap<Object, Object> newCache) {
		this.mapCache = newCache;
	}

	/**
	 * @Description 构造方法
	 */
	public LocalCache() {
	}
}
