/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.checkout.service
 * 文件名: CheckoutServiceImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.checkout.service;

import java.math.BigDecimal;

import net.fantesy84.discount.strategy.IDiscountHandler;
import net.fantesy84.domain.Customer;
import net.fantesy84.domain.Order;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class CheckoutServiceImpl implements ICheckoutService {

	/* (non-Javadoc)
	 * @see net.fantesy84.checkout.service.ICheckoutService#checkout(net.fantesy84.domain.Order)
	 */
	@Override
	public void checkout(Order order, IDiscountHandler discountHandler) throws Exception {
		Customer customer = order.getCustomer();
		Integer points = customer.getPoints();
		BigDecimal price = null;
		if (discountHandler != null) {
			price = discountHandler.processing(order.getTotalAmount(), points);
		} else {
			price = order.getTotalAmount();
		}
		System.out.println("订单价格 总计:" + price);
	}

}
