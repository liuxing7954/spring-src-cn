package com.jxy.practice.aop.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AService {
	//AlisaFor必须双向配置
	@AliasFor("name")
	String value() default "";

	@AliasFor("value")
	String name() default "";
}
