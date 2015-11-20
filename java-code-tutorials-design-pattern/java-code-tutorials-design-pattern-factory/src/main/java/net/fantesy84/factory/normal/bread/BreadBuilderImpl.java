/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory
 * 文件名: CakeBuilder.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.normal.bread;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.factory.normal.IFoodBuilder;
import net.fantesy84.food.Bread;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class BreadBuilderImpl implements IFoodBuilder {
	private static final Logger logger = LoggerFactory.getLogger(BreadBuilderImpl.class);
	/* (non-Javadoc)
	 * @see net.fantesy84.factory.IFoodBuilder#build()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T build() {
		logger.debug("生产[面包]...");
		Bread bread = new Bread();
		bread.setName("椰蓉面包");
		bread.setTaste("清香椰蓉味");
		bread.setPrice(6.00d);
		bread.setProductionDate(new Date());
		return (T) bread;
	}

}
