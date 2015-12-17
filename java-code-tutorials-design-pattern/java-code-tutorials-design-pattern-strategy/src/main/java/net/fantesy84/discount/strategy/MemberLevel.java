/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.discount.strategy
 * 文件名: MemberLevel.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.discount.strategy;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public enum MemberLevel {
	NONE(0), NORMAL(1), SENIOR(2), VIP(3);
	private int value;

	private MemberLevel(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
