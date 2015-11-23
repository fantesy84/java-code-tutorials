/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.factory.impl
 * 文件名: Package_A_FeastFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.factory.impl;

import net.fantesy84.builder.factory.IDrinkFactory;
import net.fantesy84.builder.factory.IFoodAndDrinkFactory;
import net.fantesy84.builder.factory.IFoodFactory;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class FoodAndDrinkFactoryImpl implements IFoodAndDrinkFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFeastFactory#makeFood()
	 */
	@Override
	public IFoodFactory makeFood() {
		return new FoodFactoryImpl();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFeastFactory#makeDrink()
	 */
	@Override
	public IDrinkFactory makeDrink() {
		return new DrinkFactoryImpl();
	}

}
