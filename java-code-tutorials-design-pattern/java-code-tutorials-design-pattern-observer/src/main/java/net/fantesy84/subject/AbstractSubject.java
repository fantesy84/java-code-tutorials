/**
 * 项目名: java-code-tutorials-design-pattern-observer
 * 包名:  net.fantesy84.subject
 * 文件名: AbstractSubject.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.subject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import net.fantesy84.listener.Listener;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public abstract class AbstractSubject implements ISubject {
	private ConcurrentLinkedDeque<Listener> listenerQueue = new ConcurrentLinkedDeque<Listener>();
	private ConcurrentHashMap<String, Listener> listenerMap = new ConcurrentHashMap<String, Listener>(0);
	
	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#add(net.fantesy84.listener.Listener)
	 */
	@Override
	public void add(Listener listener) {
		if (listener != null) {
			listenerQueue.add(listener);
			if (listener.getName() != null) {
				listenerMap.put(listener.getName(), listener);
			} else {
				listenerMap.put(String.valueOf(listener.hashCode()), listener);
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#del(net.fantesy84.listener.Listener)
	 */
	@Override
	public void del(Listener listener) {
		if (listener == null) {
			throw new IllegalStateException();
		}
		if (listenerQueue.contains(listener)) {
			listenerQueue.remove(listener);
		}
		if (listener.getName() != null) {
			if (listenerMap.containsKey(listener.getName())) {
				listenerMap.remove(listener.getName());
			} else {
				return;
			}
		} else {
			if (listenerMap.containsKey(String.valueOf(listener.hashCode()))) {
				listenerMap.remove(String.valueOf(listener.hashCode()));
			} else {
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#notifyRegistedListeners()
	 */
	@Override
	public void notifyRegistedListeners() {
		for (Listener listener : listenerQueue) {
			listener.update();
		}
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#notifySpecialListener(java.lang.String)
	 */
	@Override
	public void notifySpecialListener(String name) {
		if (name == null) {
			throw new NullPointerException();
		}
		if (listenerMap.containsKey(name)) {
			listenerMap.get(name).update();
		}
	}
	
	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#notifyRegistedListeners(java.lang.Object)
	 */
	@Override
	public void notifyRegistedListeners(Object data) {
		for (Listener listener : listenerQueue) {
			listener.execute(data);
		}
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#notifySpecialListener(java.lang.String, java.lang.Object)
	 */
	@Override
	public void notifySpecialListener(String name, Object data) {
		if (name == null) {
			throw new NullPointerException();
		}
		if (listenerMap.containsKey(name)) {
			listenerMap.get(name).execute(data);
		}
	}

	/**
	 * @return the listenerQueue
	 */
	protected ConcurrentLinkedDeque<Listener> getListenerQueue() {
		return listenerQueue;
	}

	/**
	 * @param listenerQueue the listenerQueue to set
	 */
	protected void setListenerQueue(ConcurrentLinkedDeque<Listener> listenerQueue) {
		this.listenerQueue = listenerQueue;
	}

	/**
	 * @return the listenerMap
	 */
	protected ConcurrentHashMap<String, Listener> getListenerMap() {
		return listenerMap;
	}

	/**
	 * @param listenerMap the listenerMap to set
	 */
	protected void setListenerMap(ConcurrentHashMap<String, Listener> listenerMap) {
		this.listenerMap = listenerMap;
	}
	
}
