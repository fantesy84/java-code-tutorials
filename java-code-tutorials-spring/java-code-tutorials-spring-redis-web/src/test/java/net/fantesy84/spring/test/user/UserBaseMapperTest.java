/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.spring.test.user
 * 文件名: UserBaseMapperTest.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月23日
 */
package net.fantesy84.spring.test.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import net.fantesy84.spring.test.SpringTestCase;
import net.fantesy84.user.domain.UserBase;
import net.fantesy84.user.mapper.UserBaseMapper;

/**
 * @author Andronicus
 * @since 2016年2月23日
 */
public class UserBaseMapperTest extends SpringTestCase {
	@Autowired
	private UserBaseMapper baseMapper;
	
	@Test
	@Transactional
	@Rollback(false)
	public void test1(){
		UserBase record = new UserBase();
		record.setAccount("admin");
		record.setPassword("admin");
		record.setEmail("admin@fantesy84.net");
		record.setTelphone("010-88591123");
		baseMapper.insert(record);
	}
}
