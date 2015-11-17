/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.test.util
 * 文件名: StringUtilsTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月17日
 */
package net.fantesy84.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Andronicus
 * @since 2015年11月17日
 */
public class StringUtilsTest {
	@Test
	public void test1(){
		String str = "甲方${userDomain.user.name}保证$aa.aa总${kk.aa是$bb.bb}在{aa.aa}三个月${9user.aa}内${_student.name}完成${project.name}的开发工作";
		String regex = "\\$\\{{1,1}[a-zA-Z_]+[a-zA-Z0-9_\\.]*\\}{1,1}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		List<String> strings = new ArrayList<String>(0);
		while (m.find()) {
			String temp = m.group();
			System.out.println(temp);
			temp = temp.substring(2, temp.indexOf("}"));
			strings.add(temp);
		}
		System.out.println(strings);
	}
	
	@Test
	public void test2(){
		String str = "user1";
		String regex = "^[a-zA-Z$][a-zA-Z0-9]*";
		System.out.println(str.matches(regex));
	}
}
