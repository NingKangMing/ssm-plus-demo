package com.wholesmart.webcommon.shiro;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

/**
 * 自定义实现会话的存储 持久化或缓存会话<br>
 * 如果Dao没配置缓存管理器cacheManager，将用一个内存MapCache去管理session(可能导致{@code OutOfMemoryException}不推荐)
 * @author kangming.ning
 * */
public class ShiroSessionDao  extends CachingSessionDAO{
	
	private static final Logger logger = Logger.getLogger(ShiroSessionDao.class);

	public ShiroSessionDao(){

		setCacheManager(new AbstractCacheManager() {
			@Override
			protected Cache<Serializable, Session> createCache(String name) throws CacheException {
				return new MapCache<Serializable, Session>(name, new ConcurrentHashMap<Serializable, Session>());
			}
		});
	}

	//下面为session id 持久化需要写的代码
	@Override
	protected void doUpdate(Session session) {
	}

	@Override
	protected void doDelete(Session session) {
       logger.debug("doDelete:"+session.getId());
	}

	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// logger.info("doReadSession:"+sessionId);
		return null;
	}

}

