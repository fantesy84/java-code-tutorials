/**
 * 项目名: java-code-tutorials-design-pattern-decorator
 * 包名:  net.fantesy84.decorator.test
 * 文件名: DecoratorTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.decorator.test;

import org.junit.Test;

import net.fantesy84.decorator.SourceDecoratorImpl;
import net.fantesy84.source.ISource;

import net.fantesy84.source.SourceDefaultImpl;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class DecoratorTest {
	@Test
	public void test1(){
		ISource source = new SourceDefaultImpl();
		source.method1();
		System.out.println("==================================");
		source = new SourceDecoratorImpl();
		source.method1();
	}
}
