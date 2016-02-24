package net.fantesy84.common.util.listener;

import java.util.concurrent.ConcurrentHashMap;

public class StandardSubject extends AbstractSubject {

	public StandardSubject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StandardSubject(ConcurrentHashMap<String, Listener<?>> listenerMap) {
		super(listenerMap);
		// TODO Auto-generated constructor stub
	}
	
}
