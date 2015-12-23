/**
 * 项目名: java-code-tutorials-design-pattern-template-method
 * 包名:  net.fantesy84.template_method.test
 * 文件名: Calculator.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.calculator;

import net.fantesy84.template_method.AbstractCalculator;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class Calculator extends AbstractCalculator {
	/**
	 * 加法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public int _add(String exp) {
		return super.add(exp);
	}
	/**
	 * 减法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public int _subtract(String exp) {
		return super.subtract(exp);
	}
	/**
	 * 乘法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public int _multiply(String exp) {
		return super.multiply(exp);
	}
	/**
	 * 除法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public int _divide(String exp) {
		return super.divide(exp);
	}
}
