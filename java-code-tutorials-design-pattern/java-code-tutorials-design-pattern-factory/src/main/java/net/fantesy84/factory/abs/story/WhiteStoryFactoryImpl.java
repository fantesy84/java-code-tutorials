/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory.abs.story
 * 文件名: WhiteStoryFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.abs.story;

import net.fantesy84.factory.abs.ICatFactory;
import net.fantesy84.factory.abs.IRatFactory;
import net.fantesy84.factory.abs.IStoryFactory;
import net.fantesy84.factory.abs.cat.WhiteCatFactoryImpl;
import net.fantesy84.factory.abs.rat.WhiteRatFactoryImpl;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class WhiteStoryFactoryImpl implements IStoryFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.IWhiteStoryFactory#getWhiteCat()
	 */
	@Override
	public ICatFactory getCat() {
		return new WhiteCatFactoryImpl();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.IWhiteStoryFactory#getWhiteRat()
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
