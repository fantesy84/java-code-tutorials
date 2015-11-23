/**
 * 项目名: java-code-tutorials-design-pattern-decorator
 * 包名:  net.fantesy84.decorator
 * 文件名: SourceDecoratorImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.decorator;

import net.fantesy84.source.ISource;
import net.fantesy84.source.SourceDefaultImpl;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class SourceDecoratorImpl implements ISource {
	private ISource source;
	/**
	 * 
	 */
	public SourceDecoratorImpl() {
		source = new SourceDefaultImpl();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.decorator.ISource#method1()
	 */
	@Override
	public void method1() {
		//before default implements method
		System.out.println("Before Method! say: hello!");
		source.method1();
		System.out.println("After method! say: good bye!");
	}

}
