/**
 * 项目名: java-code-tutorials-design-pattern-singleton
 * 包名:  net.fantesy84.singleton
 * 文件名: LazySingleton.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class LazySingleton {
	private static final Logger logger = LoggerFactory.getLogger(LazySingleton.class);
	private static String str = "通过单例对象的方法获取到的数据";
	private static LazySingleton instance;
	
	private LazySingleton() {
		logger.debug("使用构造器创建了一个对象");
	}
	
	public static LazySingleton getInstance(){
		if (instance == null) {
			instance = new LazySingleton();
		}
		logger.debug("当前懒加载单例对象的hash为:{}", instance.hashCode());
		return instance;
	}
	
	public String getStr(){
		return str;
	}
}
