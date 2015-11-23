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
 * 橙汁<br/>
 * CODE: 020004
 * @author Andronicus
 * @since 2015年11月23日
 */
public class Orangeade extends Drink {
	private static final long serialVersionUID = -7139564574894582621L;
	private static Double initPrice = new Double(15.00);
	
	/**
	 * 
	 */
	public Orangeade() {
		super();
		super.setBrand("乐百氏");
		super.setName("橙汁");
		super.setPrice(initPrice);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.builder.domain.Drink#disaccount(java.lang.Integer)
	 */
	@Override
	protected Double disaccount(Integer integral) {
		if (integral == null || integral == 0) {
			return initPrice;
		} else if (integral > 0 && integral <=3000) {
			return initPrice*0.9;
		} else if (integral > 3000 && integral <= 5000) {
			return initPrice*0.8;
		} else if (integral > 5000 && integral <= 10000) {
			return initPrice*0.7;
		} else if (integral > 10000){
			return initPrice*0.6;
		}
		return null;
	}

}
