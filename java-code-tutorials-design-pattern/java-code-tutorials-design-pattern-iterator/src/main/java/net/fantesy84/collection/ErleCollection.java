/**
 * 项目名: java-code-tutorials-design-pattern-iterator
 * 包名:  net.fantesy84.collection
 * 文件名: MyCollections.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.collection;

import net.fantesy84.iterator.ErleIterator;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public interface ErleCollection<E> {
	int size();
	void add(E e);
	E get(int index);
	E remove(int index);
	ErleIterator<E> iterator();
}
