/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.test.factory.abs
 * 文件名: StoryFactoryTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.test.factory.abs;

import org.junit.Test;

import net.fantesy84.factory.abs.IStoryFactory;
import net.fantesy84.factory.abs.story.BalckStoryFactoryImpl;
import net.fantesy84.factory.abs.story.BlackWhiteStoryFactoryImpl;
import net.fantesy84.factory.abs.story.WhiteBlackStoryFactoryImpl;
import net.fantesy84.factory.abs.story.WhiteStoryFactoryImpl;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class StoryFactoryTest {
	private IStoryFactory factory;
	@Test
	public void test1(){
		factory = new BalckStoryFactoryImpl();
		factory.story();
		factory = new BlackWhiteStoryFactoryImpl();
		factory.story();
		factory = new WhiteStoryFactoryImpl();
		factory.story();
		factory = new WhiteBlackStoryFactoryImpl();
		factory.story();
	}
}
