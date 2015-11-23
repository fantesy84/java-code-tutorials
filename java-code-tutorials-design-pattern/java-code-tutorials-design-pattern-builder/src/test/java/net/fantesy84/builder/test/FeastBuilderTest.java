/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.builder.test
 * 文件名: FeastBuilderTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.test;

import org.junit.Test;

import net.fantesy84.builder.FeastBuilder;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class FeastBuilderTest {
	@Test
	public void test1(){
		FeastBuilder builder = new FeastBuilder();
		builder.setCodes(new String[]{"010001","010002","020001"});
		builder.createFeast();
	}
}
