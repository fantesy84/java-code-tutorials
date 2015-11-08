/**
 * Project java-code-tutorials-tools
 * File: JavaLoggerTest.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.test.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * TypeName: JavaLoggerTest
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月8日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class JavaLoggerTest {
	private static final Logger logger = Logger.getLogger(JavaLoggerTest.class.getName());
	@Test
	public void test1(){
		logger.log(Level.INFO, "test log informations:{}", "aaaa");
	}
	
}
