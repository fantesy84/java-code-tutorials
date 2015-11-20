/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.test.factory.normal
 * 文件名: INormalFactoryTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.test.factory.normal;

import org.junit.Test;

import net.fantesy84.common.util.json.jackson.JsonUtils;
import net.fantesy84.factory.normal.IFoodBuilder;
import net.fantesy84.factory.normal.IFoodFactory;
import net.fantesy84.factory.normal.bread.BreadFactoryImpl;
import net.fantesy84.factory.normal.cake.CakeFactoryImpl;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class INormalFactoryTest {
	private IFoodFactory factory;
	private IFoodBuilder builder;
	@Test
	public void test1(){
		//生产面包...
		factory = new BreadFactoryImpl();
		builder = factory.create();
		System.out.println(JsonUtils.getInstance().toJson(builder.build()));
		//生产蛋糕...
		factory = new CakeFactoryImpl();
		builder = factory.create();
		System.out.println(JsonUtils.getInstance().toJson(builder.build()));
	}
}
