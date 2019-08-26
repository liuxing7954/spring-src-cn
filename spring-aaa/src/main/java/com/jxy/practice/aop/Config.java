package com.jxy.practice.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.jxy.practice.aop")
@EnableAspectJAutoProxy
public class Config {
}
