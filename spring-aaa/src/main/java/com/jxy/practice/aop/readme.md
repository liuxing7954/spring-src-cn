### 这是对于公司框架一个不足点展开的研究
#### 对于一个被注解标记的类,如果被AOP后,注解是如何被处理的

- 如果注解本身没有被@Inherited标注,则说明这是一个不可继承的注解,那代理类就获取不到
- 特殊情况就是继承只是在类层级的,也就是注解如果是作用于方法等,就仍然不被继承
> 而公司的扫描微服务接口就是针对于方法的,所以切面之后丢失了注解,不被识别为一个微服务接口

这次实验学习了几个牛逼的方法,分别是

- AnnotatedElementUtils.findMergedAnnotation();//牛
- AnnotatedElementUtils.getMergedAnnotation();
- AnnotationUtils.findAnnotation();//牛
- AnnotationUtils.getAnnotation();

> 牛的那两个方法直接不管是不是代理类,管你什么子类,重写方法,就是拿到注解