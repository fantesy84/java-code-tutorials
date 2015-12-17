/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.array.handler
 * 文件名: IArrayHandler.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.discount.strategy;

import java.math.BigDecimal;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public interface IDiscountHandler {
	BigDecimal processing(BigDecimal rawPrice, Integer points) throws Exception;
}
