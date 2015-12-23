/**
 * 项目名: java-code-tutorials-design-pattern-chain-of-responsibility
 * 包名:  net.fantesy84.responsibility
 * 文件名: Chain_1.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.responsibility;

import net.fantesy84.handler.AbstractHandler;
import net.fantesy84.handler.IHandler;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class Chain_1 extends AbstractHandler implements IHandler {
	private String name;
	
	/**
	 * @param name
	 */
	public Chain_1(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.handler.IHandler#operator()
	 */
	@Override
	public void operator() {
		System.out.println("Welcome! Mr. " + name);
		if (super.getHandler() != null) {
			super.getHandler().operator();
		}
	}

}
