package com.jxy.practice.event;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@ComponentScan("com.jxy.practice.event")
public class Config {
	@EventListener
	public void eventListener(Object o){
		System.out.println("收到监听事件");
	}
}
