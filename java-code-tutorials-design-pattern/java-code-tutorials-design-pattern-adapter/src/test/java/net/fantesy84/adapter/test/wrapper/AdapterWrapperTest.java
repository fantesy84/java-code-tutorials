/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.test.wrapper
 * 文件名: AdapterWrapperTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.test.wrapper;

import org.junit.Test;

import net.fantesy84.adapter.wrapper.Source;
import net.fantesy84.adapter.wrapper.Target;
import net.fantesy84.adapter.wrapper.Wrapper;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class AdapterWrapperTest {
	@Test
	public void test1(){
		Source source = new Source();
		source.setName("admin");
		Target t = new Wrapper(source);
		t.method1();
		t.method2();
	}
}
