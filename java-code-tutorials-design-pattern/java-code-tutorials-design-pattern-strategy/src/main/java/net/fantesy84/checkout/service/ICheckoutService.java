/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.checkout.service
 * 文件名: ICheckoutService.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.checkout.service;

import net.fantesy84.discount.strategy.IDiscountHandler;
import net.fantesy84.domain.Order;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public interface ICheckoutService {
	void checkout(Order order, IDiscountHandler discountHandler) throws Exception;
}
