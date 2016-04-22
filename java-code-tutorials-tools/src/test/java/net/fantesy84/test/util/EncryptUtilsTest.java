/**
 * Project java-code-tutorials-tools
 * CreateTime: 2016年4月22日
 * Creator: junjie.ge
 * Copyright ©2016 葛俊杰
 */
package net.fantesy84.test.util;

import org.junit.Assert;
import org.junit.Test;

import net.fantesy84.common.util.EncryptUtils;

/**
 * TypeName: EncryptUtilsTest
 * 
 * <P>CreateTime: 2016年4月22日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class EncryptUtilsTest {
	@Test
	public void test1(){
		String str = "hello";
		String pwd = EncryptUtils.encryptWithMD5(str);
		Assert.assertEquals(32, pwd.length());
		System.out.println(pwd);
	}
}
