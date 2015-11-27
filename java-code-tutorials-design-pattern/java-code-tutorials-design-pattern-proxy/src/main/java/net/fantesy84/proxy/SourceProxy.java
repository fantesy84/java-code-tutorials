/**
 * 项目名: java-code-tutorials-design-pattern-proxy
 * 包名:  net.fantesy84.proxy
 * 文件名: SourceProxy.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.source.Source;
import net.fantesy84.source.Sourceable;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class SourceProxy implements Sourceable {
	private final static Logger logger = LoggerFactory.getLogger(SourceProxy.class);
	private Sourceable source;
	private Object realObj;
	/**
	 * 
	 */
	public SourceProxy() {
		super();
		source = new Source();
		realObj = source;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.source.Sourceable#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String name) throws Exception {
		before();
		String result = source.sayHello(name);
		logger.debug("Proxy message is: {}", result);
		after();
		return result;
	}

	private void before(){
		System.out.println("This is before sayHello method execute!");
	}
	
	private void after(){
		System.out.println("This is after sayHello method executed!");
	}

	/**
	 * @return the realObj
	 */
	public Object getRealObj() {
		return realObj;
	}
	
}
