package com.jxy.practice.aop.service;

import com.jxy.practice.aop.anno.ABusiness;
import com.jxy.practice.aop.anno.AService;

@ABusiness(value = "为什么传递给了service的value,最后注册成了beanName?",name = "notBeanName")
public class UserService {

	@AService("testService")
	public void service(){
		System.out.println("执行了service方法");
	}


}
