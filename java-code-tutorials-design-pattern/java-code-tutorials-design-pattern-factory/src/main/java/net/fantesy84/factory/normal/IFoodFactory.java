/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory
 * 文件名: IFoodFactory.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.normal;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public interface IFoodFactory {
	IFoodBuilder create();
}
