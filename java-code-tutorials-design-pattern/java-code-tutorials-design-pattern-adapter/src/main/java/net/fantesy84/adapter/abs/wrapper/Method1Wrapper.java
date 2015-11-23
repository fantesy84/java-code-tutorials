/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.abs.wrapper
 * 文件名: Method1Wrapper.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.abs.wrapper;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class Method1Wrapper extends AbstractWrapper {

	/* (non-Javadoc)
	 * @see net.fantesy84.adapter.abs.wrapper.AbstractWrapper#method1()
	 */
	@Override
	public void method1() {
		System.out.println("Oh! yes! " + this.getClass().getName() + " - method1 running!");
	}
	
}
