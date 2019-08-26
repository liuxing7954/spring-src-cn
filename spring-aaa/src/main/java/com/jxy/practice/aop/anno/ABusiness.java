package com.jxy.practice.aop.anno;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface ABusiness {
	//todo: 子注解的value会默认传递给上级注解的value??????
	String value() default "";
	String name() default "";
}
