/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.test.clazz
 * 文件名: TargetTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.test.clazz;

import org.junit.Test;

import net.fantesy84.adapter.clazz.SourceExtendsTargetAdapter;
import net.fantesy84.adapter.clazz.Target;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class TargetTest {
	@Test
	public void test1(){
		Target t = new SourceExtendsTargetAdapter();
		t.method1();
		t.method2();
	}
}
