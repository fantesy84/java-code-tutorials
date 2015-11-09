/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.test.commendline
 * 文件名: CommendLineUtilsTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月9日
 */
package net.fantesy84.test.commendline;

import org.junit.Test;

import net.fantesy84.common.util.commendline.CommendLineUtils;

/**
 * @author Andronicus
 * @since 2015年11月9日
 */
public class CommendLineUtilsTest {
	@Test
	public void test1(){
		String ipconfig = CommendLineUtils.run(new String[]{"ipconfig","-all"}, "GBK");
		System.out.println(ipconfig);
	}
	@Test
	public void test2(){
		String ipconfig = CommendLineUtils.run(new String[]{"D:\\test.bat"}, "GBK");
		System.out.println(ipconfig);
	}
}
