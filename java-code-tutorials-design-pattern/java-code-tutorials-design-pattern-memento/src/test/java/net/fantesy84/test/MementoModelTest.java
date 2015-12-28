/**
 * 项目名: java-code-tutorials-design-pattern-memento
 * 包名:  net.fantesy84.test
 * 文件名: MementoModelTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.test;

import org.junit.Test;

import net.fantesy84.memento.Original;
import net.fantesy84.memento.Storage;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class MementoModelTest {
	@Test
	public void test1(){
		Original o = new Original("Test");
		//创建备忘录
		Storage s = new Storage(o.createMemento());
		//修改原始数据
		System.out.println("原始数据为:" + o.getStr());
		o.setStr("Hello!");
		System.out.println("更新后的数据为: " + o.getStr());
		//恢复数据
		o.restoreMemento(s.getMemento());
		System.out.println("恢复后的数据为: " + o.getStr());
	}
}
