package org.gongjian.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/chat")
	public String publishMsg(@RequestParam(name="info", defaultValue="1111")String info) {
		stringRedisTemplate.convertAndSend("chat", info);
		
		return info;
	}

}
