/**
 * 项目名: java-code-tutorials-event-framework
 * 包名:  net.fantesy84.util.event.source
 * 文件名: EventSource.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月26日
 */
package net.fantesy84.util.event.source;

import net.fantesy84.util.event.Event;
import net.fantesy84.util.event.listener.EventListener;

/**
 * @author Andronicus
 * @since 2016年1月26日
 */
public interface EventSource {
	void addEventListener(EventListener<? extends Event> listener);
	void removeEventListener(EventListener<? extends Event> listener);
	void notifyListeners(Event event);
}
