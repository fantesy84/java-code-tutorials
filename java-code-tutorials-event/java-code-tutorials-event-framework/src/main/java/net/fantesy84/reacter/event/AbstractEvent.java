/**
 * Created: 2016-12-06
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.reacter.event;

/**
 * @author junjie.ge
 *
 */
public class AbstractEvent implements Event {
	private String listenerName;
	private String listenerMethodMapping;
	private Object source;

	public AbstractEvent(String listenerName, String listenerMethodMapping, Object source) {
		super();
		this.listenerName = listenerName;
		this.listenerMethodMapping = listenerMethodMapping;
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.event.Event#getTarget()
	 */
	@Override
	public String getTargetName() {
		return listenerName;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.reacter.event.Event#getTargetMapping()
	 */
	@Override
	public String getTargetMapping() {
		return listenerMethodMapping;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.event.Event#getValue()
	 */
	@Override
	public Object getResources() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Object source) {
		this.source = source;
	}

}
