/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.test.SystemPropUtils
 * 文件名: SystemPropUtilsTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月9日
 */
package net.fantesy84.test.systemproputils;

import org.junit.Test;

import net.fantesy84.common.util.SystemPropUtils;
import net.fantesy84.common.util.SystemPropUtils.PropKeys;

/**
 * @author Andronicus
 * @since 2015年11月9日
 */
public class SystemPropUtilsTest {
	@Test
	public void test1(){
		String version = SystemPropUtils.getProperty(PropKeys.JAVA_VERSION);
		System.out.println(version);
	}
	
	@Test
	public void test2(){
		String osName = SystemPropUtils.getProperty(PropKeys.OS_NAME);
		System.out.println(osName.startsWith("Windows"));
	}
}
