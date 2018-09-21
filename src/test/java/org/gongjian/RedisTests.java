package org.gongjian;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

	@Autowired
	private RedisTemplate<String, String> redisTempalte;

	@Autowired
	private JedisPool jedisPool;

	/*
	 * @Test public void test() {
	 * 
	 * ValueOperations<String, String> ops = redisTempalte.opsForValue();
	 * ops.set("a", "c");
	 * 
	 * HashOperations<String, String, String> hops = redisTempalte.opsForHash();
	 * hops.put("map1", "field1", "111111"); hops.put("map1", "field2", "222222"); }
	 */

	@Test
	public void testJedisPool() {
		Jedis jedis = jedisPool.getResource();

		//jedis.hset("map2", "field1", "AAAAAA");	
		/*String retVal = jedis.set("aaa", "bbbb", "NX", "EX", 100);
		
		if(retVal == null) {
			System.out.println("null xxxxx");
		} else {
			System.out.println("not null");
		}*/
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		//cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
		//cal.set(Calendar.HOUR_OF_DAY, 0);
		//cal.set(Calendar.MINUTE, 10);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)+10);
		
		
		jedis.set("aaa", "bbb");
		jedis.expireAt("aaa", cal.getTimeInMillis()/1000);
		
		System.out.println(cal.getTimeInMillis());

		if (jedis != null) {
			jedis.close();
		}

	}

}
