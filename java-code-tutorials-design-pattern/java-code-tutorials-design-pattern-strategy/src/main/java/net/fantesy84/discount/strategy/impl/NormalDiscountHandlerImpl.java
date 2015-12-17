/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.discount.strategy.impl
 * 文件名: VipDiscountHandlerImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.discount.strategy.impl;

import java.math.BigDecimal;

import net.fantesy84.discount.strategy.IDiscountHandler;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class NormalDiscountHandlerImpl implements IDiscountHandler {

	/* (non-Javadoc)
	 * @see net.fantesy84.discount.strategy.IDiscountHandler#processing(java.math.BigDecimal, int)
	 */
	@Override
	public BigDecimal processing(BigDecimal rawPrice, Integer points) throws Exception {
		if (points < 2000) {
			throw new IllegalArgumentException("会员积分未达到普通会员的最低积分!请检查数据!");
		}
		double newPrice = 0.00;
		if (points < 5000) {
			newPrice = rawPrice.doubleValue()*0.95;
		} else if (points >= 5000 && points < 10000) {
			newPrice = rawPrice.doubleValue()*0.8;
		} else {
			newPrice = rawPrice.doubleValue()*0.7;
		}
		return new BigDecimal(newPrice);
	}

}
