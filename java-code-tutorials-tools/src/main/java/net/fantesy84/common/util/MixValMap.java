/**
 * Project commons-utils
 * File: MixValMap.java
 * CreateTime: 2016年4月11日
 * Creator: junjie.ge
 * Copyright ©2016 葛俊杰
 */
package net.fantesy84.common.util;

import java.util.List;
import java.util.Map;

/**
 * TypeName: MixValMap
 * <P>TODO
 * 
 * <P>CreateTime: 2016年4月11日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public interface MixValMap<K, V> extends Map<K, List<V>> {
	/**
	 * Return the first value for the given key.
	 * @param key the key
	 * @return the first value for the specified key, or {@code null}
	 */
	V getFirst(K key);

	/**
	 * Add the given single value to the current list of values for the given key.
	 * @param key the key
	 * @param value the value to be added
	 */
	void add(K key, V value);

	/**
	 * Set the given single value under the given key.
	 * @param key the key
	 * @param value the value to set
	 */
	void set(K key, V value);

	/**
	 * Set the given values under.
	 * @param values the values.
	 */
	void setAll(Map<K, V> values);

	/**
	 * Returns the first values contained in this {@code MultiValueMap}.
	 * @return a single value representation of this map
	 */
	Map<K, V> toSingleValueMap();
}
