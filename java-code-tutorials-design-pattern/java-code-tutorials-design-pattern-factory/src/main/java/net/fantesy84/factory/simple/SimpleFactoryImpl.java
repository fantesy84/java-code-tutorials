/**
 * 项目名: java-code-tutorials-design-pattern-simplefactory
 * 包名:  net.fantesy84.factory
 * 文件名: FoodFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.simple;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.food.Bread;
import net.fantesy84.food.Cake;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class SimpleFactoryImpl implements ISimpleFactory {
	private static final Logger logger = LoggerFactory.getLogger(SimpleFactoryImpl.class);
	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFoodFactory#make(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T make(String foodName) {
		logger.debug("简单工厂得到命令: 生产{}", foodName);
		if ("cake".equalsIgnoreCase(foodName)) {
			Cake cake = new Cake();
			cake.setName("奶油蛋糕");
			cake.setSerialNumber("QS440124011234");
			cake.setDesc("美味的奶油蛋糕!");
			cake.setProductionDate(new Date());
			return (T) cake;
		} else if ("bread".equalsIgnoreCase(foodName)) {
			Bread bread = new Bread();
			bread.setName("椰蓉面包");
			bread.setTaste("清香椰蓉味");
			bread.setPrice(6.00d);
			bread.setProductionDate(new Date());
			return (T) bread;
		} else {
			IllegalStateException e = new IllegalStateException("未知的食品!无法生产!");
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}
