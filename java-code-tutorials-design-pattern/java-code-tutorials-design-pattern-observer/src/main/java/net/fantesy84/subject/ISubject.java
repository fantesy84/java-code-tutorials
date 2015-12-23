/**
 * 项目名: java-code-tutorials-design-pattern-observer
 * 包名:  net.fantesy84.subject
 * 文件名: WeatherCenter.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.subject;

import net.fantesy84.listener.Listener;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface ISubject {
	void add(Listener listener);
	void del(Listener listener);
	void notifyRegistedListeners();
	void notifyRegistedListeners(Object data);
	void notifySpecialListener(String name);
	void notifySpecialListener(String name, Object data);
}
