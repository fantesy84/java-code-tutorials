/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.factory.impl
 * 文件名: FoodFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.factory.impl;

import net.fantesy84.builder.domain.Food;
import net.fantesy84.builder.domain.food.sub.BrownSauceBraisedPork;
import net.fantesy84.builder.domain.food.sub.ChineseCabbageAndTofuSoup;
import net.fantesy84.builder.domain.food.sub.Rice;
import net.fantesy84.builder.domain.food.sub.SweetAndSourSpareRibs;
import net.fantesy84.builder.domain.food.sub.TwiceCookedPork;
import net.fantesy84.builder.factory.IFoodFactory;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class FoodFactoryImpl implements IFoodFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFoodFactory#cooked(java.lang.String[])
	 */
	@Override
	public Food cooked(String foodCodes) {
		if ("010001".equals(foodCodes)) {
			return new BrownSauceBraisedPork();
		} else if ("010002".equals(foodCodes)) {
			return new SweetAndSourSpareRibs();
		} else if ("010003".equals(foodCodes)) {
			return new TwiceCookedPork();
		} else if ("010004".equals(foodCodes)) {
			return new ChineseCabbageAndTofuSoup();
		} else if ("010005".equals(foodCodes)) {
			return new Rice();
		}
		return null;
	}

}
