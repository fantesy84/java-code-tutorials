/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.factory
 * 文件名: IDrinkFactory.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.factory;

import net.fantesy84.builder.domain.Drink;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public interface IDrinkFactory {
	Drink take(String drinkCodes);
}
