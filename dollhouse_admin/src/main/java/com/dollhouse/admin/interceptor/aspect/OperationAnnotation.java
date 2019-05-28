package com.dollhouse.admin.interceptor.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationAnnotation {
	 //模块名  
	public abstract String moduleName() default "";  
    //操作内容  
	public abstract String operation() default "";  
}