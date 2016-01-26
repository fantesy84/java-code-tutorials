/**
 * 项目名: java-code-tutorials-event-framework
 * 包名:  net.fantesy84.util.event.listener
 * 文件名: EventListener.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月26日
 */
package net.fantesy84.util.event.listener;

import net.fantesy84.util.event.Event;

/**
 * @author Andronicus
 * @since 2016年1月26日
 */
public interface EventListener<T extends Event> {
	void handleEvent(T event);
}
