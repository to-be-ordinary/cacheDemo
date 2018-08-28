package ehcache;

import java.io.File;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

/**
 * �־û����湤����
 * @author smj
 *
 */
public class PersistentCacheUtil {

	private PersistentCacheUtil() {}
	
	private static PersistentCacheManager persistentCacheManager = null;
	
	/**
	 * �����־û���CacheManager
	 * @return
	 */
	public static PersistentCacheManager getPersistentCacheManager() {
		
		if(persistentCacheManager == null)
			persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
					.with(CacheManagerBuilder.persistence(new File("D:/cache/ehcacheUtil")))
					.withCache("defaultPersistent", CacheConfigurationBuilder.newCacheConfigurationBuilder(
							String.class, String.class, 
							ResourcePoolsBuilder.newResourcePoolsBuilder()
								.heap(10, EntryUnit.ENTRIES)
								.disk(500, MemoryUnit.MB,true)
							))
					.build(true);
		
		return persistentCacheManager;
	}
	
	/**
	 * ����Ĭ�ϵĳ־û���cache
	 * @return
	 */
	public static Cache<String,String> getCache(){
		
		return getPersistentCacheManager().getCache("defaultPersistent", 
				String.class, 
				String.class);
	}
	
	public static void close() {
		
		if(persistentCacheManager != null)
			persistentCacheManager.close();
	}
	
	
}
