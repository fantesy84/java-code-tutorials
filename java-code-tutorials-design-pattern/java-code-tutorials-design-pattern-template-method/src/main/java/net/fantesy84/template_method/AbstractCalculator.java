/**
 * 项目名: java-code-tutorials-design-pattern-template-method
 * 包名:  net.fantesy84.template_method
 * 文件名: AbstractCalculator.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.template_method;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public abstract class AbstractCalculator {
	
	protected int[] splitOptStr(String exp, String optMarker) {
		String[] temps = exp.split(optMarker);
		if (temps == null || temps.length != 2) {
			return new int[]{};
		} else {
			int[] result = new int[2];
			result[0] = Integer.parseInt(temps[0]);
			result[1] = Integer.parseInt(temps[1]);
			return result;
		}
	}
	/**
	 * 加法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public final int add(String exp) {
		int[] array = splitOptStr(exp, "\\+");
		return array[0] + array[1];
	}
	/**
	 * 减法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public final int subtract(String exp) {
		int[] array = splitOptStr(exp, "\\-");
		return array[0] - array[1];
	}
	/**
	 * 乘法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public final int multiply(String exp) {
		int[] array = splitOptStr(exp, "\\*");
		return array[0] * array[1];
	}
	/**
	 * 除法
	 * @param exp 表达式
	 * @return 计算结果
	 */
	public final int divide(String exp) {
		int[] array = splitOptStr(exp, "/");
		return array[0] / array[1];
	}
}
