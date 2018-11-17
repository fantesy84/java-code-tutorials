/**
 * Created: 2016-12-06
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.reacter.notify;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.reacter.event.Event;
import net.fantesy84.reacter.listener.Listener;
import net.fantesy84.reacter.listener.ListenerWapper;

/**
 * @author junjie.ge
 *
 */
public class NotifyServiceImpl implements NotifyService {
	private static final Logger LOG = LoggerFactory.getLogger(NotifyServiceImpl.class);
	private ConcurrentMap<String, Listener> listenerMap;

	public NotifyServiceImpl() {
		this.listenerMap = new ConcurrentHashMap<>(0);
	}

	public NotifyServiceImpl(ConcurrentMap<String, Listener> listenerMap) {
		super();
		this.listenerMap = listenerMap;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.event.NotifyService#subscribe(net.fantesy84.event.Listener)
	 */
	@Override
	public void subscribe(Object listener) {
		ListenerWapper wapper = new ListenerWapper(listener);
		if (!listenerMap.containsKey(wapper.getEventListenerName())) {
			LOG.info("将事件监听器{}加入监听器队列", wapper.getEventListenerName());
			listenerMap.put(wapper.getEventListenerName(), wapper);
		} else {
			throw new IllegalStateException("队列中已存在名为:{"+wapper.getEventListenerName()+"}的监听器");
		}
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.event.NotifyService#unsubscribe(net.fantesy84.event.Listener)
	 */
	@Override
	public void unsubscribe(Object listener) {
		ListenerWapper wapper = new ListenerWapper(listener);
		LOG.info("将事件监听器{}移出监听器队列", wapper.getEventListenerName());
		listenerMap.remove(wapper.getEventListenerName(), wapper);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.reacter.notify.NotifyService#getListenerMap()
	 */
	@Override
	public Map<String, Listener> getListenerMap() {
		return listenerMap;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.event.NotifyService#doNotify(net.fantesy84.event.Event)
	 */
	@Override
	public void doNotify(Event e) {
		String targetListenerName = e.getTargetName();
		if (targetListenerName == null || targetListenerName.trim().length() == 0) {
			targetListenerName = "";
		}
		if (listenerMap != null && !listenerMap.isEmpty()) {
			Listener listener = listenerMap.get(targetListenerName);
			if (listener != null) {
				LOG.info("已在监听器队列中找到名为:{}的监听器,通知该监听器", targetListenerName);
				listener.onEvent(e);
			} else {
				LOG.info("在监听器队列中未找到名为:{}的监听器", targetListenerName);
			}
		} else {
			LOG.warn("监听器队列为空队列,忽略通知");
		}
	}

}
