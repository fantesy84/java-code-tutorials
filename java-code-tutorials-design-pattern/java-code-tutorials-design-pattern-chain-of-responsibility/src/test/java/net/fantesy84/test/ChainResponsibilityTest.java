/**
 * 项目名: java-code-tutorials-design-pattern-chain-of-responsibility
 * 包名:  net.fantesy84.test
 * 文件名: ChainResponsibilityTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.test;

import java.util.Date;

import org.junit.Test;

import net.fantesy84.responsibility.Chain_1;
import net.fantesy84.responsibility.Chain_2;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class ChainResponsibilityTest {
	@Test
	public void test1(){
		Chain_1 c1 = new Chain_1("JunJie.Ge");
		Chain_2 c2 = new Chain_2(new Date());
		c1.setHandler(c2);
		c1.operator();
	}
}
