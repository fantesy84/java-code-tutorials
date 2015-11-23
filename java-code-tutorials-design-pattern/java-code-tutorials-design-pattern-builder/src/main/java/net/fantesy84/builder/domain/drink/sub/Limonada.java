/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.builder.domain.drink.sub
 * 文件名: Limonada.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.domain.drink.sub;

import net.fantesy84.builder.domain.Drink;

/**
 * 柠檬水<br/>
 * CODE: 020002
 * @author Andronicus
 * @since 2015年11月23日
 */
public class Limonada extends Drink {
	private static final long serialVersionUID = -7139564574894582621L;
	private static Double initPrice = new Double(8.00);
	
	/**
	 * 
	 */
	public Limonada() {
		super();
		super.setBrand("微山");
		super.setName("柠檬水");
		super.setPrice(initPrice);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.builder.domain.Drink#disaccount(java.lang.Integer)
	 */
	@Override
	protected Double disaccount(Integer integral) {
		return initPrice;
	}

}
