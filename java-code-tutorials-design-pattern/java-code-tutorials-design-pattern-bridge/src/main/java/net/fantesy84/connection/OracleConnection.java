/**
 * 项目名: java-code-tutorials-design-pattern-bridge
 * 包名:  net.fantesy84.source
 * 文件名: SourceImpl1.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.connection;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class OracleConnection implements Connection {

	/* (non-Javadoc)
	 * @see net.fantesy84.source.ISourceable#sourceMethod()
	 */
	@Override
	public void sourceMethod() {
		System.out.println("Quiet! You has an oracle connection!");
	}

}
