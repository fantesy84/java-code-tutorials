/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory
 * 文件名: CakeBuilder.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.normal.cake;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.factory.normal.IFoodBuilder;
import net.fantesy84.food.Cake;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class CakeBuilderImpl implements IFoodBuilder {
	private static final Logger logger = LoggerFactory.getLogger(CakeBuilderImpl.class);
	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFoodBuilder#build()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T build() {
		logger.debug("生产[蛋糕]...");
		Cake cake = new Cake();
		cake.setName("奶油蛋糕");
		cake.setSerialNumber("QS440124011234");
		cake.setDesc("美味的奶油蛋糕!");
		cake.setProductionDate(new Date());
		return (T) cake;
	}

}
