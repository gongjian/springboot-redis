package org.gongjian;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class ShardingUtil {
	

	public static Long generate(RedisAtomicLong ral) {
		return ral.incrementAndGet();
	}

	public static Long generate(String key, Long delta,
			RedisTemplate<String, Serializable> redisTemplate) {
		try {
			if (null == delta) {
				delta = 1L;
			}
			return redisTemplate.opsForValue().increment(key, delta);
			
		} catch (Exception e) {// redis宕机时采用uuid的方式生成唯一id
			int first = new Random(10).nextInt(8) + 1;
			int randNo = UUID.randomUUID().toString().hashCode();
			if (randNo < 0) {
				randNo = -randNo;
			}
			return Long.valueOf(first + String.format("%16d", randNo));
		}
	}

}
