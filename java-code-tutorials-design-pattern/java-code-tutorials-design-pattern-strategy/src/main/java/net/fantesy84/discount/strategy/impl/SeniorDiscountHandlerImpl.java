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
public class SeniorDiscountHandlerImpl implements IDiscountHandler {

	/* (non-Javadoc)
	 * @see net.fantesy84.discount.strategy.IDiscountHandler#processing(java.math.BigDecimal, int)
	 */
	@Override
	public BigDecimal processing(BigDecimal rawPrice, Integer points) throws Exception {
		if (points < 100000) {
			throw new IllegalArgumentException("会员积分未达到高级会员的最低积分!请检查数据!");
		}
		double newPrice = 0.00;
		if (points < 200000) {
			newPrice = rawPrice.doubleValue()*0.65;
		} else if (points >= 200000 && points < 350000) {
			newPrice = rawPrice.doubleValue()*0.6;
		} else {
			newPrice = rawPrice.doubleValue()*0.55;
		}
		return new BigDecimal(newPrice);
	}

}
