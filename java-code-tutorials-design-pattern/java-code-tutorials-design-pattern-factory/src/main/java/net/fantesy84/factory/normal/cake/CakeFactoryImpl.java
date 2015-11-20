/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory
 * 文件名: FoodFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.normal.cake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.factory.normal.IFoodBuilder;
import net.fantesy84.factory.normal.IFoodFactory;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class CakeFactoryImpl implements IFoodFactory {
	private static final Logger logger = LoggerFactory.getLogger(CakeFactoryImpl.class);
	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFoodFactory#create()
	 */
	@Override
	public IFoodBuilder create() {
		logger.debug("面包工厂开动[蛋糕]生产机...");
		return new CakeBuilderImpl();
	}

}
