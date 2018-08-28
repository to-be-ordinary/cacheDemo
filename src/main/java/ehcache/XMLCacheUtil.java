package ehcache;

import java.net.URL;

import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.core.EhcacheManager;
import org.ehcache.xml.XmlConfiguration;

public class XMLCacheUtil {

	
	
	private XMLCacheUtil() {
		super();
	}

	private static PersistentCacheManager persistentCacheManager = null;
	
	
	public static PersistentCacheManager getCacheManager() {
		
		if(persistentCacheManager == null) {

			URL url = XMLCacheUtil.class.getClass().getResource("/config/ehcache.xml");
			XmlConfiguration configuration = new XmlConfiguration(url);
			
			persistentCacheManager = new EhcacheManager(configuration);
			
			persistentCacheManager.init();
		}
		
		return persistentCacheManager;
	}
	
	/**
	 * 创建默认的持久化的cache
	 * @return
	 */
	public static Cache<String,String> getCache(){
		
		return getCacheManager().getCache("defaultXml", 
				String.class, 
				String.class);
	}
	
	public static void close() {
		
		if(persistentCacheManager != null)
			persistentCacheManager.close();
	}
}
