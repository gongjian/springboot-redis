package org.gongjian;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingKeyTest {
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	@Test
	public void test() {
		Set<Long> set = new CopyOnWriteArraySet<>();
		CyclicBarrier barrier = new CyclicBarrier(1000);
		
		RedisAtomicLong ral = new RedisAtomicLong("OOXX", redisTemplate.getConnectionFactory());

		ExecutorService pool = Executors.newFixedThreadPool(1000);
		for (int i = 0; i < 1000; i++) {
			pool.execute(new Task(barrier, set, redisTemplate, ral));
		}

		try {
			Thread.sleep(3000);
			System.out.println();
			System.out.println(set.size());
			System.out.println(set);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
