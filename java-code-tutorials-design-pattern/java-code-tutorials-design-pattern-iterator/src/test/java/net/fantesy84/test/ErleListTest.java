/**
 * 项目名: java-code-tutorials-design-pattern-iterator
 * 包名:  net.fantesy84.test
 * 文件名: ErleListTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.test;

import org.junit.Test;

import net.fantesy84.iterator.ErleIterator;
import net.fantesy84.util.ErleArrayList;
import net.fantesy84.util.ErleList;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class ErleListTest {
	@Test
	public void test1(){
		ErleList<String> list = new ErleArrayList<String>();
		for (int i = 0; i < 15; i++) {
			list.add("test String " + i);
		}
		ErleIterator<String> itr = list.iterator();
		while (itr.hasNext()) {
			String str = itr.next();
			System.out.println(str);
		}
	}
}
