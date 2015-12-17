/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.checkout.test
 * 文件名: CheckoutTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.checkout.test;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import net.fantesy84.checkout.service.CheckoutServiceImpl;
import net.fantesy84.checkout.service.ICheckoutService;
import net.fantesy84.discount.strategy.IDiscountHandler;
import net.fantesy84.discount.strategy.impl.NormalDiscountHandlerImpl;
import net.fantesy84.discount.strategy.impl.SeniorDiscountHandlerImpl;
import net.fantesy84.discount.strategy.impl.VipDiscountHandlerImpl;
import net.fantesy84.domain.Customer;
import net.fantesy84.domain.Order;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class CheckoutTest {
	private Order order;
	@Before
	public void init(){
		order = new Order();
		Customer customer = new Customer();
		order.setId(1l);
		order.setTotalAmount(BigDecimal.valueOf(100000.00));
		order.setSerialNo("TM20151217010010001");
		customer.setId(1l);
		customer.setCustomerNo("TMMC0311542");
		customer.setPoints(1000);
		order.setCustomer(customer);
	}
	@Test
	public void checkout() throws Exception{
		IDiscountHandler handler = null;
		Integer points = order.getCustomer().getPoints();
		if (points >= 2000 && points < 100000) {
			handler = new NormalDiscountHandlerImpl();
		} else if (points >= 100000 && points < 500000) {
			handler = new SeniorDiscountHandlerImpl();
		} else if (points >= 100000) {
			handler = new VipDiscountHandlerImpl();
		}
		ICheckoutService service = new CheckoutServiceImpl();
		service.checkout(order, handler);
	}
}
