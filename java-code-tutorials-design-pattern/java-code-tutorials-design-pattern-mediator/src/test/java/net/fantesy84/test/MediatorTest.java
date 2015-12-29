/**
 * 项目名: java-code-tutorials-design-pattern-mediator
 * 包名:  net.fantesy84.test
 * 文件名: MediatorTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月29日
 */
package net.fantesy84.test;

import org.junit.Test;

import net.fantesy84.mediator.Mediator;
import net.fantesy84.mediator.impl.MyMediator;

/**
 * @author Andronicus
 * @since 2015年12月29日
 */
public class MediatorTest {
	@Test
	public void test1(){
		Mediator m = new MyMediator();
		m.createMediator();
		m.eat();
		m.work();
	}
}
