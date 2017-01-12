/**
 * Created: 2017-01-06
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.event;

import net.fantesy84.reacter.event.AbstractEvent;

/**
 * @author junjie.ge
 *
 */
public class TestEvent extends AbstractEvent {

	private long lastTrigger;

	/**
	 * @param listenerName
	 * @param listenerMethod
	 * @param source
	 */
	public TestEvent(String listenerName, String listenerMethod, Object source) {
		super(listenerName, listenerMethod, source);
		this.lastTrigger = System.currentTimeMillis();
	}

	/**
	 * @return the lastTrigger
	 */
	public long getLastTrigger() {
		return lastTrigger;
	}

}
