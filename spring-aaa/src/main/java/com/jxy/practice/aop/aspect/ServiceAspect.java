package com.jxy.practice.aop.aspect;

import com.jxy.practice.aop.anno.ABusiness;
import com.jxy.practice.aop.anno.AService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class ServiceAspect {

	@Pointcut("@annotation(com.jxy.practice.aop.anno.AService)")//连接点是@AService
	private void servicePointcut() {
	}

	@Around(value = "servicePointcut()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		joinPoint.getThis();//获取到代理后生成的心类
		joinPoint.getTarget();//获取到被代理的原类,如果是cglib两者则是继承关系

		//关于获取注解的方式,因为此时ABusiness没有添加可继承属性,所以子类是获取不到ABusiness注解的值的
		//顺便说以后注解尽量用这种方式取,将注解托管与spring控制,像AliasFor这种注解才能生效
		// todo: 具体get和find的命名差别下 ,逻辑有什么不同还待研究,目前推测是取低级和高级的注解区别? 但是这个语义太模糊了,还牵扯出一个
		// 问题,如果类有多个父类都有这个注解,取的是哪一个? 最近的还是最远的?属性会被覆盖成最低的吗? 得看源码了....
		ABusiness a1 = AnnotatedElementUtils.findMergedAnnotation(joinPoint.getThis().getClass(), ABusiness.class);//但是这种方式能获取到
		ABusiness a2 = AnnotatedElementUtils.getMergedAnnotation(joinPoint.getThis().getClass(), ABusiness.class);//
		ABusiness a3 = AnnotationUtils.findAnnotation(joinPoint.getThis().getClass(), ABusiness.class);//也能
		ABusiness a4 = AnnotationUtils.getAnnotation(joinPoint.getThis().getClass(), ABusiness.class);//
		ABusiness a5 = joinPoint.getThis().getClass().getAnnotation(ABusiness.class);

		//这里获取到的直接就是真实类的方法对象,所以方法级的注解什么的都是有的,使用起来也没什么困难
		Method var = ((MethodSignature) joinPoint.getSignature()).getMethod();
		var.getAnnotation(AService.class);//没问题
		//todo: 这个 Signature 是个什么抽象目前还不了解.

		//如果去获取代理后的类的方法再取注解,因为方法级注解无法继承所以会获取不到,现在来试一下上面那些牛逼的方法
		Method method = joinPoint.getThis().getClass().getMethod(var.getName(), var.getParameterTypes());
		AService b1 = AnnotatedElementUtils.findMergedAnnotation(method, AService.class);//这种方式还是能获取到
		AService b2 = AnnotatedElementUtils.getMergedAnnotation(method, AService.class);//
		AService b3 = AnnotationUtils.findAnnotation(method, AService.class);//也能
		AService b4 = AnnotationUtils.getAnnotation(method, AService.class);//
		AService b5 = method.getAnnotation(AService.class);

		System.out.println("增强service方法-------aop");
		return joinPoint.proceed();
	}

}
