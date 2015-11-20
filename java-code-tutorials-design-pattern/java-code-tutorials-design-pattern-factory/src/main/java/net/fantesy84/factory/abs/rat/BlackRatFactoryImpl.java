/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory.abs.rat
 * 文件名: WhiteRatFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.abs.rat;

import net.fantesy84.factory.abs.IRatFactory;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class BlackRatFactoryImpl implements IRatFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.IRatFactory#ratName()
	 */
	@Override
	public String ratName() {
		return "黑色的老鼠";
	}

}
