/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.factory.impl
 * 文件名: DrinkFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.factory.impl;

import net.fantesy84.builder.domain.Drink;
import net.fantesy84.builder.domain.drink.sub.Bordeaux;
import net.fantesy84.builder.domain.drink.sub.Limonada;
import net.fantesy84.builder.domain.drink.sub.Moutai;
import net.fantesy84.builder.domain.drink.sub.Orangeade;
import net.fantesy84.builder.domain.drink.sub.Wuliangye;
import net.fantesy84.builder.factory.IDrinkFactory;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class DrinkFactoryImpl implements IDrinkFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IDrinkFactory#take(java.lang.String[])
	 */
	@Override
	public Drink take(String drinkCodes) {
		if ("020001".equals(drinkCodes)) {
			return new Bordeaux();
		} else if ("020002".equals(drinkCodes)) {
			return new Limonada();
		} else if ("020003".equals(drinkCodes)) {
			return new Moutai();
		} else if ("020004".equals(drinkCodes)) {
			return new Orangeade();
		} else if ("020005".equals(drinkCodes)) {
			return new Wuliangye();
		}
		return null;
	}

}
