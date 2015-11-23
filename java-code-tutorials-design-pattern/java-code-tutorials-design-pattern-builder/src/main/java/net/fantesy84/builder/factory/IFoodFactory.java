/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.factory
 * 文件名: IFoodFactory.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.factory;

import net.fantesy84.builder.domain.Food;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public interface IFoodFactory {
	Food cooked(String foodCodes);
}
