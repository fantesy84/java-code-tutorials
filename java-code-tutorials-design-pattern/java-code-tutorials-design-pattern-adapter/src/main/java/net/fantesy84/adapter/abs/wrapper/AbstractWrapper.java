/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.abs.wrapper
 * 文件名: AbstractWrapper.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.abs.wrapper;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public abstract class AbstractWrapper implements ISource {
	
	/* (non-Javadoc)
	 * @see net.fantesy84.adapter.abs.wrapper.ISource#method1()
	 */
	@Override
	public void method1() {
		System.out.println("This is " + this.getClass().getName() + " - method1 running!");
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.adapter.abs.wrapper.ISource#method2()
	 */
	@Override
	public void method2() {
		System.out.println("This is " + this.getClass().getName() + " - method2 running!");
	}

}
