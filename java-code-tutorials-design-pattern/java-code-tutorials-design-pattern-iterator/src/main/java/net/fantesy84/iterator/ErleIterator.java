/**
 * 项目名: java-code-tutorials-design-pattern-iterator
 * 包名:  net.fantesy84.iterator
 * 文件名: MyIterator.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.iterator;

/**
 * 
 * @author Andronicus
 * @since 2015年12月23日
 * @param <E> 集合中被迭代对象
 */
public interface ErleIterator<E> {
	/**
	 * 前移
	 * @return {@linkplain E} 迭代对象
	 */
	E previous();
	/**
	 * 后移
	 * @return {@linkplain E} 迭代对象
	 */
	E next();
	/**
	 * 是否还有下一个元素
	 * @return <code>true</code>表示还能后移,<code>false</code>表示不能后移(即已经移动到最后一个下标处)
	 */
	boolean hasNext();
}
