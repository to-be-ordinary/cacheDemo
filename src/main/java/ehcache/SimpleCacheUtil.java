package ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;

/**
 * 简单的cacheManager demo工具类
 * @author smj
 *
 */
public class SimpleCacheUtil {

	private static CacheManager cacheManager = null;
	
	private SimpleCacheUtil() {}
	
	/**
	 * 创建默认的cacheManager
	 * @return
	 */
	public static CacheManager getCacheManager() {
		
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("default", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder()
							.heap(10, EntryUnit.ENTRIES)//对内存中最多存储10条
						))
				.build(true);//默认调用init方法初始化cacheManager
		
		return cacheManager;
	}
	
	/**
	 * 获取默认的Cache
	 * @return
	 */
	public static Cache<String,String> getCache() {
		
		
		return getCacheManager().getCache("default", String.class, String.class);
	}
	
	/**
	 * 创建指定别名的cache
	 * @param alias
	 * @return
	 */
	public static Cache<String,String> createCache(String alias){
		
		ResourcePoolsBuilder.newResourcePoolsBuilder();
		return getCacheManager().createCache(alias, CacheConfigurationBuilder.newCacheConfigurationBuilder(
				String.class, 
				String.class, 
				ResourcePoolsBuilder
					.heap(10)
				));
	}
	
	/**
	 * 如果缓存管理不是空，则关闭缓存
	 */
	public static void close() {
		
		if(cacheManager != null)
			cacheManager.close();
	}
}
