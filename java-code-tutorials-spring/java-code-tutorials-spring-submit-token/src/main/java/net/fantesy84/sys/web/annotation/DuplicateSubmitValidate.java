/**
 * Project java-code-tutorials-spring-commit-token
 * File: DuplicateSubmitValidate.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.sys.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TypeName: DuplicateSubmitValidate
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateSubmitValidate {
	/**
	 * 是否新建令牌并保存,适用于使用ModelAndView作为返回值的时候
	 * @return 默认为true,保存生成的token,将用于验证下一次该方法是否重复提交
	 */
	boolean saveToken() default true;
	/**
	 * 本次令牌被使用过后,是否移除,以防止被注解的方法重复提交
	 * @return 默认为true,移除本次令牌,防止重复提交.如果设置为false,则可进行重复提交
	 */
	boolean removeToken() default true;
}
