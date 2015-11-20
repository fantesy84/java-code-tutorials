/**
 * 项目名: java-code-tutorials-design-pattern-simplefactory
 * 包名:  net.fantesy84.test.simple.factory
 * 文件名: IFoodFactoryTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.test.factory.simple;

import org.junit.Test;

import net.fantesy84.factory.simple.SimpleFactoryImpl;
import net.fantesy84.factory.simple.ISimpleFactory;
import net.fantesy84.food.Bread;
import net.fantesy84.food.Cake;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class ISimpleFactoryTest {
	@Test
	public void test1(){
		ISimpleFactory factory = new SimpleFactoryImpl();
		Cake cake = factory.make("cake");
		System.out.println(cake.getName() + " | " + cake.getDesc() + " | " + cake.getProductionDate().toString());
		Bread bread = factory.make("bread");
		System.out.println(bread.getName() + " | " + bread.getTaste() + " | " + bread.getProductionDate().toString());
		factory.make("apple");
	}
}
