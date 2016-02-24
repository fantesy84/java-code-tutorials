package net.fantesy84.common.util.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractSubject implements Subject {
	private boolean changed = false;
	private ConcurrentHashMap<String, Listener<?>> listenerMap;
	
	protected AbstractSubject() {
		super();
		listenerMap = new ConcurrentHashMap<>();
	}

	protected AbstractSubject(ConcurrentHashMap<String, Listener<?>> listenerMap) {
		super();
		this.listenerMap = listenerMap;
	}
	
	@Override
	public synchronized void addListener(Listener<?> listener) {
		if (listener == null) {
			throw new NullPointerException();
		}
		String name = listener.getName();
		if (name != null && !listenerMap.containsKey(name)) {
			listenerMap.put(name, listener);
		}
	}

	@Override
	public synchronized void removeListener(Listener<?> listener) {
		if (listener == null) {
			return;
		}
		String name = listener.getName();
		if (name != null && listenerMap.containsKey(name)) {
			listenerMap.remove(name);
		}
	}

	@Override
	public void notifySpecialListeners(String... names) {
		notifySpecialListeners(null, names);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void notifySpecialListeners(T arg, String... names) {
		if (names == null) {
			throw new NullPointerException();
		}
		Object[] temps = null;
		synchronized (this) {
			if (changed) {
				List<Listener<?>> listeners = new ArrayList<>();
				for (int i = 0; i < names.length; i++) {
					if (names[i] == null) {
						continue;
					}
					if (listenerMap.containsKey(names[i])) {
						listeners.add(listenerMap.get(names[i]));
					}
				}
				temps = listeners.toArray();
				clearChanged();
			}
		}
		for (int i = temps.length-1; i>=0; i--) {
			((Listener<T>)temps[i]).update(this, arg);
		}
	}

	@Override
	public void notifyListeners() {
		notifyListeners(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void notifyListeners(T arg) {
		Object[] temps = null;
		synchronized (this) {
			if (changed) {
				temps = listenerMap.values().toArray();
				clearChanged();
			}
		}
		for (int i = temps.length-1; i>=0; i--) {
			((Listener<T>)temps[i]).update(this, arg);
		}
	}

	@Override
	public synchronized void clearListeners() {
		listenerMap.clear();
	}

	@Override
	public void setChanged() {
		this.changed = true;
	}

	@Override
	public void clearChanged() {
		this.changed = false;
	}

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public int countListeners() {
		return listenerMap.size();
	}

	/* (non-Javadoc)
	 * @see com.shangde.school.util.listener.Subject#getListenerNames()
	 */
	@Override
	public Set<String> getListenerNames() {
		return listenerMap.keySet();
	}
	
}
