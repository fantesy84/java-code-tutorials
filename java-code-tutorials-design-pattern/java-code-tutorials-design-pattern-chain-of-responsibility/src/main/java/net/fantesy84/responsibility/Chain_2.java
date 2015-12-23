/**
 * 项目名: java-code-tutorials-design-pattern-chain-of-responsibility
 * 包名:  net.fantesy84.responsibility
 * 文件名: Chain_2.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.responsibility;

import java.util.Date;

import net.fantesy84.handler.AbstractHandler;
import net.fantesy84.handler.IHandler;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class Chain_2 extends AbstractHandler implements IHandler {
	private Date date;
	
	/**
	 * @param date
	 */
	public Chain_2(Date date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.handler.IHandler#operator()
	 */
	@Override
	public void operator() {
		System.out.println("It is a nice time! let us remember this date: " + date.toString());
		if (super.getHandler() != null) {
			super.getHandler().operator();
		}
	}

}
