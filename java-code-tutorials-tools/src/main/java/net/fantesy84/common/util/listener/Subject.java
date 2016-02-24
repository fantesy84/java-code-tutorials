package net.fantesy84.common.util.listener;

import java.util.Set;

public interface Subject {
	void addListener(Listener<?> listener);
	void removeListener(Listener<?> listener);
	void notifySpecialListeners(String...names);
	<T> void notifySpecialListeners(T arg, String...names);
	void notifyListeners();
	<T> void notifyListeners(T arg);
	void clearListeners();
	void setChanged();
	void clearChanged();
	boolean hasChanged();
	int countListeners();
	Set<String> getListenerNames();
}
