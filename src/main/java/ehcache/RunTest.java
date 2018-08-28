package ehcache;

import org.ehcache.Cache;
import org.junit.Test;

public class RunTest {

	@Test
	public void cacheTest() {
		
		Cache<String, String> cache = XMLCacheUtil.getCache();
		
		cache.put("name", "sun");
		
		String name = XMLCacheUtil.getCache().get("name");
		
		System.err.println(name);

		XMLCacheUtil.close();
	}
	
	@Test
	public void read() {
		
		String name = XMLCacheUtil.getCache().get("name");
		
		System.err.println(name);
		
		XMLCacheUtil.close();
	}
}
