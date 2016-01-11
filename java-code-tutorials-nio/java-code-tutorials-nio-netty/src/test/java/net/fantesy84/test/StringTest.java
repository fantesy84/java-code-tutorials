/**
 * 项目名: java-code-tutorials-nio-netty
 * 包名:  net.fantesy84.test
 * 文件名: StringTest.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月11日
 */
package net.fantesy84.test;

import org.junit.Test;

/**
 * @author Andronicus
 * @since 2016年1月11日
 */
public class StringTest {
	@Test
	public void test1(){
		String str = "query_time" + System.lineSeparator();
		System.out.println(str);
		System.out.println(str.length());
		System.out.println(System.getProperty("line.separator"));
		System.out.println(System.getProperty("line.separator").length());
		str = str.substring(0, (str.length() - System.getProperty("line.separator").length()));
		System.out.println(str);
	}
}
