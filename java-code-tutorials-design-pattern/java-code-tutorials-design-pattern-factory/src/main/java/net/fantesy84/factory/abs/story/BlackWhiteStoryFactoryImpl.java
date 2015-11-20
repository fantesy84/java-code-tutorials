/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory.abs.story
 * 文件名: BlackWhiteStoryFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.abs.story;

import net.fantesy84.factory.abs.ICatFactory;
import net.fantesy84.factory.abs.IRatFactory;
import net.fantesy84.factory.abs.IStoryFactory;
import net.fantesy84.factory.abs.cat.BlackCatFactoryImpl;
import net.fantesy84.factory.abs.rat.WhiteRatFactoryImpl;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class BlackWhiteStoryFactoryImpl implements IStoryFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.IStoryFactory#getCat()
	 */
	@Override
	public ICatFactory getCat() {
		return new BlackCatFactoryImpl();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.IStoryFactory#getRat()
	 */
	@Override
	public IRatFactory getRat() {
		return new WhiteRatFactoryImpl();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.IStoryFactory#story()
	 */
	@Override
	public void story() {
		System.out.println(getCat().catchingRat() + getRat().ratName());
	}

}
