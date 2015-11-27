/**
 * 项目名: java-code-tutorials-design-pattern-proxy
 * 包名:  net.fantesy84.proxy.test
 * 文件名: ProxyTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.proxy.test;

import org.junit.Test;

import net.fantesy84.proxy.SourceProxy;
import net.fantesy84.source.Source;
import net.fantesy84.source.Sourceable;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class ProxyTest {
	@Test
	public void test1() throws Exception{
		Sourceable source = new Source();
		source.sayHello("John");
	}
	
	@Test
	public void test2() throws Exception{
		Sourceable source = new SourceProxy();
		source.sayHello("John");
	}
}
