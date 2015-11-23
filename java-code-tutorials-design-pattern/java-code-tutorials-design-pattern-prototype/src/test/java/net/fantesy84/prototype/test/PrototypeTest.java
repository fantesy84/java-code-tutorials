/**
 * 项目名: java-code-tutorials-design-pattern-prototype
 * 包名:  net.fantesy84.prototype.test
 * 文件名: PrototypeTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.prototype.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import net.fantesy84.prototype.domain.User;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class PrototypeTest {
	private User user;
	@Before
	public void init(){
		user = new User();
		user.setId(1);
		user.setSort(100);
		user.setAccount("admin");
		user.setPassword("admin");
		user.setRealName("管理员");
		user.setNickName("final");
		user.setBirthday(new Date());
		user.setDeleteFlag(Boolean.FALSE);
	}
	
	@Test
	public void test1() throws CloneNotSupportedException{
		User temp = user.clone();
		System.out.println(temp.getAccount());
		System.out.println(temp.getSort());
	}
}
