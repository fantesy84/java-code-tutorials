/**
 * 项目名: java-code-tutorials-design-pattern-proxy
 * 包名:  net.fantesy84.source
 * 文件名: Source.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class Source implements Sourceable {
	private final static Logger logger = LoggerFactory.getLogger(Source.class);
	/* (non-Javadoc)
	 * @see net.fantesy84.source.Sourceable#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String name) throws Exception {
		String str = "Hi, " + name + "! How are you!";
		logger.debug("Message is: {}", str);
		return str;
	}

}
