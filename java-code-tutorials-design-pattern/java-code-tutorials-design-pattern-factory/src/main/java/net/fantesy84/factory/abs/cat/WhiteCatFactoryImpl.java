/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.factory.abs.cat
 * 文件名: WhiteCatFactoryImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.factory.abs.cat;

import net.fantesy84.factory.abs.ICatFactory;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class WhiteCatFactoryImpl implements ICatFactory {

	/* (non-Javadoc)
	 * @see net.fantesy84.factory.abs.ICatFactory#catchingRat()
	 */
	@Override
	public String catchingRat() {
		return "白猫正在抓";
	}

}
