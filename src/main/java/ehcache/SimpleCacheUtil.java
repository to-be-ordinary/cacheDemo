package ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;

/**
 * �򵥵�cacheManager demo������
 * @author smj
 *
 */
public class SimpleCacheUtil {

	private static CacheManager cacheManager = null;
	
	private SimpleCacheUtil() {}
	
	/**
	 * ����Ĭ�ϵ�cacheManager
	 * @return
	 */
	public static CacheManager getCacheManager() {
		
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				.withCache("default", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder()
							.heap(10, EntryUnit.ENTRIES)//���ڴ������洢10��
						))
				.build(true);//Ĭ�ϵ���init������ʼ��cacheManager
		
		return cacheManager;
	}
	
	/**
	 * ��ȡĬ�ϵ�Cache
	 * @return
	 */
	public static Cache<String,String> getCache() {
		
		
		return getCacheManager().getCache("default", String.class, String.class);
	}
	
	/**
	 * ����ָ��������cache
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
	 * �����������ǿգ���رջ���
	 */
	public static void close() {
		
		if(cacheManager != null)
			cacheManager.close();
	}
}
