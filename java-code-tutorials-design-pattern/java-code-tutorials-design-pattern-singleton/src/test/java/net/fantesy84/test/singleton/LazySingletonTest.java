/**
 * 项目名: java-code-tutorials-design-pattern-singleton
 * 包名:  net.fantesy84.test.singleton
 * 文件名: LazySingletonTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.test.singleton;

import org.junit.Test;

import net.fantesy84.singleton.LazySingleton;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class LazySingletonTest {
	@Test
	public void test1() throws InterruptedException{
		String str = LazySingleton.getInstance().getStr();
		System.out.println(str);
		Thread.sleep(3000l);
		String str2 = LazySingleton.getInstance().getStr();
		System.out.println(str2);
	}
}
