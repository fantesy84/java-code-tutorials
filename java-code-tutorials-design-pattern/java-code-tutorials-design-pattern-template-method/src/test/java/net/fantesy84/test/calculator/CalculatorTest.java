/**
 * 项目名: java-code-tutorials-design-pattern-template-method
 * 包名:  net.fantesy84.test.calculator
 * 文件名: CalculatorTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.test.calculator;

import org.junit.Test;

import net.fantesy84.calculator.Calculator;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class CalculatorTest {
	@Test
	public void test1(){
		Calculator c = new Calculator();
		System.out.println(c._subtract("5*4"));
	}
}
