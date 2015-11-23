/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.builder.domain
 * 文件名: Rice.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.domain.food.sub;

import net.fantesy84.builder.domain.Food;

/**
 * 米饭<br/>
 * CODE:010005
 * @author Andronicus
 * @since 2015年11月23日
 */
public class Rice extends Food {
	private static final long serialVersionUID = -1352474082485273652L;
	private static Double initPrice = new Double(2.00);
	
	/**
	 * 
	 */
	public Rice() {
		super();
		super.setName("米饭");
		super.setPrice(initPrice);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.builder.domain.Food#disaccount(java.lang.Integer)
	 */
	@Override
	protected Double disaccount(Integer integral) {
		return initPrice;
	}

}
