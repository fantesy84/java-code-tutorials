/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.factory
 * 文件名: IFeastFactory.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.factory;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public interface IFoodAndDrinkFactory {
	IFoodFactory makeFood();
	IDrinkFactory makeDrink();
}
