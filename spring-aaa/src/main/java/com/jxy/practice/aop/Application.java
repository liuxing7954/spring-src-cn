package com.jxy.practice.aop;


import com.jxy.practice.aop.anno.ABusiness;
import com.jxy.practice.aop.anno.AService;
import com.jxy.practice.aop.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
		//被切了,看ServiceAspect.class
		UserService u1 = app.getBean(UserService.class);
		u1.service();
		//尽管真实类注册到容器中会被代理类所替代,但是貌似基本信息还是会被保存,比如知道原类的注解,会被作为bean的搜索条件保存下来
		Map<String, Object> beans = app.getBeansWithAnnotation(ABusiness.class);//拿到代理类的对象
		UserService u2 = (UserService) beans.get("为什么传递给了service的value,最后注册成了beanName?");
		ABusiness a1 = u2.getClass().getAnnotation(ABusiness.class);//正常是获取不到的,因为现在是在代理类上拿注解,而这个注解我没设置继承属性
		Class<?> userClass = ClassUtils.getUserClass(u2.getClass());//获取到被代理类,这个是cglib的时候适用
		ABusiness a2 = userClass.getAnnotation(ABusiness.class);//经过上一行方法,拿到了原类,就可以获取到了
		//上面的方法好像都一般.试试那个牛逼的方法
		ABusiness a3 = AnnotationUtils.findAnnotation(u2.getClass(), ABusiness.class);//依然牛逼

		//完全适用公司框架里微服务注册判断的最小代价修改代码!
		for (Method method : u2.getClass().getMethods()) {
			AService aService = AnnotationUtils.findAnnotation(method, AService.class);
			if (aService != null) {
				String res = String.format("在类%s中的%s方法找到了存在AService注解",u2.getClass(),method.getName());
				System.out.println(res);
			}
		}

	}
}
