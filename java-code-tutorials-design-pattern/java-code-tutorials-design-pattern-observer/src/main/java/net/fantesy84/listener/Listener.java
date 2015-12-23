/**
 * 项目名: java-code-tutorials-design-pattern-observer
 * 包名:  net.fantesy84.listener
 * 文件名: Listener.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.listener;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface Listener {
	String getName();
	void update();
	void execute(Object data);
}
