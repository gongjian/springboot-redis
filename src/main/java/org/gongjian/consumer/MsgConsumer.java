package org.gongjian.consumer;

import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {

	public void printMsg(String message) {
		System.out.println(message);
	}
	
}
