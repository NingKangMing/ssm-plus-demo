package com.wholesmart.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/** 
 * EHCache工具类
 * cacheName全部配置到ehcache.xml
 * @author kangming.ning 
 * @since 2017-09-16
 */
public class EHCacheUtil {

	private static CacheManager manager =CacheManager.create();



	/**
	 * 返回当前缓存名称下key对应的value
	 * */
	public static Object get(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
		}
		return null;
	}

	/**
	 * 返回当前缓存名称下key对应的value
	 * */
	public static Cache getCache(String cacheName) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			return cache;
		}
		return null;
	}

	/**
	 * 返回当前缓存名称下的key集合对应的元素Map
	 * @return Map of key and elements value for the provided keys, value will be null for the keys which do not exist
	 * */
	public static Map<Object, Object> getMap(String cacheName,Collection<?> collection) {
		Cache cache = manager.getCache(cacheName);
		Map<Object, Object> maps=new ConcurrentHashMap<Object, Object>();
		if (cache != null) {
			Map<Object, Element> map = cache.getAll(collection);
			if (!map.isEmpty()) {
				for (Object obj : map.keySet()) {
					Element element = map.get(obj);
					if (null!=element) {
						Object objectValue = element.getObjectValue();
						maps.put(obj, objectValue);
					}
				}
			}
			return maps;
		}
		return null;
	}

	/**
	 * 根据cacheName得到当前cacheName下缓存的所有key
	 * */
	@SuppressWarnings("rawtypes")
	public static List getKeys(String cacheName) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			return cache.getKeys();
		}
		return null;
	}

	public static Element getElement(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element;
			}
		}
		return null;
	}

	public static void put(String cacheName, Element element) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(element);
		}
	}

	public static void put(String cacheName, Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value));
		}
	}

	public static void put(String cacheName, Object key, Object value,final Integer timeToLiveSeconds) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value,false,0,timeToLiveSeconds));
		}
	}

	public static void put(String cacheName, Object key, Object value,final Integer timeToIdleSeconds, final Integer timeToLiveSeconds) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value,false,timeToIdleSeconds,timeToLiveSeconds));
		}
	}

	public static void put(String cacheName, Object key, Object value,final Boolean eternal,final Integer timeToIdleSeconds, final Integer timeToLiveSeconds) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value,eternal,timeToIdleSeconds,timeToLiveSeconds));
		}
	}

	public static boolean remove(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			return cache.remove(key);
		}
		return false;
	}


}
