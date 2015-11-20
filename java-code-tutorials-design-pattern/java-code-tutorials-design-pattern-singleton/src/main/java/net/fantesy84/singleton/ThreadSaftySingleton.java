/**
 * 项目名: java-code-tutorials-design-pattern-singleton
 * 包名:  net.fantesy84.singleton
 * 文件名: ThreadSaftySingleton.java
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
public class ThreadSaftySingleton {
	private static final Logger logger = LoggerFactory.getLogger(ThreadSaftySingleton.class);
	private static String str = "通过单例对象的方法获取到的数据";
	private static ThreadSaftySingleton instance;
	
	private ThreadSaftySingleton() {
		logger.debug("使用构造器创建了一个对象");
	}
	
	public static ThreadSaftySingleton getInstance(){
		if (instance == null) {
			synchronized (ThreadSaftySingleton.class) {
				if (instance == null) {
					instance = new ThreadSaftySingleton();
				}
			}
		}
		logger.debug("当前懒加载单例对象的hash为:{}", instance.hashCode());
		return instance;
	}
	
	public String getStr(){
		return str;
	}
}
