/**
 * 项目名: java-code-tutorials-spring-redis
 * 包名:  net.fantesy84.gcache.spring
 * 文件名: GcacheManager.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月11日
 */
package net.fantesy84.gcache.spring;

import java.util.Collection;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

/**
 * @author Andronicus
 * @since 2015年12月11日
 */
public class GcacheManager extends AbstractTransactionSupportingCacheManager {
	
	private Collection<Gcache> caches;
	
	/* (non-Javadoc)
	 * @see org.springframework.cache.support.AbstractCacheManager#loadCaches()
	 */
	@Override
	protected Collection<? extends Cache> loadCaches() {
		if (caches != null) {
			return caches;
		}
		return null;
	}
	/**
	 * @return the caches
	 */
	public Collection<Gcache> getCaches() {
		return caches;
	}

	/**
	 * @param caches the caches to set
	 */
	public void setCaches(Collection<Gcache> caches) {
		this.caches = caches;
	}
	
}
