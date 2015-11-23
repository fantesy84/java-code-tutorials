/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.builder.domain
 * 文件名: BrownSauceBraisedPork.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.domain.food.sub;

import net.fantesy84.builder.domain.Food;

/**
 * 红烧肉<br/>
 * CODE:010001
 * @author Andronicus
 * @since 2015年11月23日
 */
public class BrownSauceBraisedPork extends Food {
	private static final long serialVersionUID = 6492383321005822981L;
	private static Double initPrice = new Double(68.00);
	/**
	 * 
	 */
	public BrownSauceBraisedPork() {
		super();
		super.setName("红烧肉");
		super.setPrice(initPrice);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.builder.domain.Food#disaccount(java.lang.Integer)
	 */
	@Override
	protected Double disaccount(Integer integral) {
		if (integral == null || integral == 0) {
			return initPrice;
		} else if (integral > 0 && integral <=1000) {
			return initPrice*0.9;
		} else if (integral > 1000 && integral <= 3000) {
			return initPrice*0.85;
		} else if (integral > 3000 && integral <= 5000) {
			return initPrice*0.8;
		} else if (integral > 5000 && integral <= 10000) {
			return initPrice*0.7;
		} else if (integral > 10000){
			return initPrice*0.5;
		}
		return null;
	}

}
