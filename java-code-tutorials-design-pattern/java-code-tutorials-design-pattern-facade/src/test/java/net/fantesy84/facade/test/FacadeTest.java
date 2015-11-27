/**
 * 项目名: java-code-tutorials-design-pattern-facade
 * 包名:  net.fantesy84.facade.test
 * 文件名: FacadeTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.facade.test;

import org.junit.Test;

import net.fantesy84.computer.Computer;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class FacadeTest {
	@Test
	public void test1(){
		Computer computer = new Computer();
		computer.startup();
		computer.shutdown();
	}
}
