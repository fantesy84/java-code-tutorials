/**
 * Project java-code-tutorials-design-pattern-singleton
 * File: EnclosingFactorySingleton.java
 * CreateTime: 2015年11月21日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TypeName: EnclosingFactorySingleton
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月21日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class EnclosingFactorySingleton {
	private static final Logger logger = LoggerFactory.getLogger(EnclosingFactorySingleton.class);
	private String str = "通过单例对象的方法获取到的数据";
	
	private EnclosingFactorySingleton(){
		logger.debug("使用构造器创建了一个对象");
	}
	
	public static EnclosingFactorySingleton getInstance(){
		return EnclosingFactory.newInstance;
	}
	
	public String getStr(){
		return this.str;
	}
	private static class EnclosingFactory{
		private static EnclosingFactorySingleton newInstance = new EnclosingFactorySingleton();
	}
}
