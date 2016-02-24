package net.fantesy84.common.util.listener;

public interface Listener<T> {
	String getName();
	void update(Subject subject, T arg);
}
