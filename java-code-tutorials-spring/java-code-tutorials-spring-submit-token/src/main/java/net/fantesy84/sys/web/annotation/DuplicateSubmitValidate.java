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
	 * @return 默认为<code>false</code>,不生成新的token,为<code>true</code>则会生成新的token,将用于验证下一次重复提交验证
	 */
	boolean createToken() default false;
	/**
	 * 本次令牌被使用过后,是否移除,以防止被注解的方法重复提交
	 * @return 默认为true,移除本次令牌,防止重复提交.如果设置为false,则可进行重复提交
	 */
	boolean avoidDuplicateSubmit() default true;
	/**
	 * 是否重置重复提交验证.
	 * @return 默认为true.即重置验证,需要跟<code>resetTime</code>属性配合定义验证有效期.若为false,则永远不重置重复提交,直到页面刷新
	 */
	boolean reset() default true;
	/**
	 * 重置验证时长(单位:毫秒)
	 * @return 默认为10000毫秒,即10秒后可重复提交
	 */
	long resetTime() default 10000;
}
