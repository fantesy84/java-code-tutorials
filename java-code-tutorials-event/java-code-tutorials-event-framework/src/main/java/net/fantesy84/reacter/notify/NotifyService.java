/**
 * Created: 2016-12-06
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.reacter.notify;

import java.util.Map;

import net.fantesy84.reacter.event.Event;
import net.fantesy84.reacter.listener.Listener;

/**
 *
 * @author junjie.ge
 *
 */
public interface NotifyService {
	void subscribe(Object listener);
	void unsubscribe(Object listener);
	Map<String, Listener> getListenerMap();
	void doNotify(Event e);
}
