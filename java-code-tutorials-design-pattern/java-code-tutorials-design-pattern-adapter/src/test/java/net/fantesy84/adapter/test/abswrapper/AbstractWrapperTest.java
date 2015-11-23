/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.test.abswrapper
 * 文件名: AbstractWrapperTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.test.abswrapper;

import org.junit.Test;

import net.fantesy84.adapter.abs.wrapper.ISource;
import net.fantesy84.adapter.abs.wrapper.Method1Wrapper;
import net.fantesy84.adapter.abs.wrapper.Method2Wrapper;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class AbstractWrapperTest {
	@Test
	public void test1(){
		ISource s = new Method1Wrapper();
		s.method1();
		s.method2();
	}
	@Test
	public void test2(){
		ISource s = new Method2Wrapper();
		s.method1();
		s.method2();
	}
}
