/**
 * Project java-code-tutorials-spring-commit-token
 * File: TokenHelperTest.java
 * CreateTime: 2015年12月24日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.test.token;

import org.junit.Test;

import net.fantesy84.sys.util.TokenHelper;

/**
 * TypeName: TokenHelperTest
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月24日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class TokenHelperTest {
	@Test
	public void test1(){
		String token = TokenHelper.getInstance().generateToken();
		System.out.println(token);
	}
}
