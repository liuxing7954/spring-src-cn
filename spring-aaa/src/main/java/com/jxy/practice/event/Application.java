package com.jxy.practice.event;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);

	}
}
