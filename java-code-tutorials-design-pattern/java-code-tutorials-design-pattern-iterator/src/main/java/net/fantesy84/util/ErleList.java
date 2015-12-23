/**
 * 项目名: java-code-tutorials-design-pattern-iterator
 * 包名:  net.fantesy84.util
 * 文件名: ErleList.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.util;

import net.fantesy84.collection.ErleCollection;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface ErleList<E> extends ErleCollection<E> {
	Object[] toArray();
	
	<T> T[] toArray(T[] a);
	
	int indexOf(Object o);
	
	int lastIndexOf(Object o);
}
