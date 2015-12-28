/**
 * 项目名: java-code-tutorials-design-pattern-visitor
 * 包名:  net.fantesy84.domain
 * 文件名: BasePOJO.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.domain;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class BasePOJO {
	private double base;
	private double exponent;
	
	/**
	 * @param base
	 * @param exponent
	 */
	public BasePOJO(double base, double exponent) {
		super();
		this.base = base;
		this.exponent = exponent;
	}
	/**
	 * @return the exponent
	 */
	public double getExponent() {
		return exponent;
	}
	/**
	 * @param exponent the exponent to set
	 */
	public void setExponent(double exponent) {
		this.exponent = exponent;
	}
	/**
	 * @return the base
	 */
	public double getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(double base) {
		this.base = base;
	}
	
}
