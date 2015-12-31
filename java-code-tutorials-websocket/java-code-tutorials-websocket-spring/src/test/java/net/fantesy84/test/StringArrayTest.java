/**
 * 项目名: java-code-tutorials-websocket-spring
 * 包名:  net.fantesy84.test
 * 文件名: StringArrayTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月31日
 */
package net.fantesy84.test;

import org.junit.Test;

/**
 * @author Andronicus
 * @since 2015年12月31日
 */
public class StringArrayTest {
	@Test
	public void test1(){
		String str = "Hello";
		char[] cache = new char[102400];
		char[] chars = str.toCharArray();
		System.out.println(cache.length);
		System.out.println(chars.length);
		//System.arraycopy(src, srcPos, dest, destPos, length);
	}
}
