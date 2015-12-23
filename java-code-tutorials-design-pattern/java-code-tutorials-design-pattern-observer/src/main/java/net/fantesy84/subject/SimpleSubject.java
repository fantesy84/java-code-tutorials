/**
 * 项目名: java-code-tutorials-design-pattern-observer
 * 包名:  net.fantesy84.subject
 * 文件名: DefaultSubjectImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.subject;

import java.util.List;

import net.fantesy84.listener.Listener;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class SimpleSubject extends AbstractSubject {

	public void setListeners(List<Listener> listeners) {
		for (Listener listener : listeners) {
			super.add(listener);
		}
	}
	
}
