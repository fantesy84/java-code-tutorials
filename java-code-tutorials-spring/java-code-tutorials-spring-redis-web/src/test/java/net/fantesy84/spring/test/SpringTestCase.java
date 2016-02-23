/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.spring.test
 * 文件名: SpringTestCase.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月23日
 */
package net.fantesy84.spring.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Andronicus
 * @since 2016年2月23日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public class SpringTestCase {

}
