/**
 * 项目名: java-code-tutorials-design-pattern-decorator
 * 包名:  net.fantesy84.decorator
 * 文件名: SourceDefaultImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.source;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class SourceDefaultImpl implements ISource {

	/* (non-Javadoc)
	 * @see net.fantesy84.decorator.ISource#method1()
	 */
	@Override
	public void method1() {
		System.out.println("This is default implements!");
	}

}
