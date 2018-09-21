package org.gongjian;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class Task implements Runnable {

	private RedisTemplate<String, Serializable> redisTempalte;
	private CyclicBarrier cyclicBarrier;
	private Set<Long> set;
	private RedisAtomicLong ral;

	public Task(CyclicBarrier cyclicBarrier, Set<Long> set, RedisTemplate<String, Serializable> redisTempalte, RedisAtomicLong ral) {
		this.cyclicBarrier = cyclicBarrier;
		this.set = set;
		this.redisTempalte = redisTempalte;
		this.ral = ral;
	}

	@Override
	public void run() {
		try {
			cyclicBarrier.await();

			//long key = ShardingUtil.generate("OOXX",null, redisTempalte);
			long key = ShardingUtil.generate(ral);
			System.out.print(key + ", ");
			set.add(key);
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
