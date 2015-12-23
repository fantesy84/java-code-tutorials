/**
 * 项目名: java-code-tutorials-design-pattern-observer
 * 包名:  net.fantesy84.test
 * 文件名: ObserverTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.fantesy84.board.listener.HumidityBoard;
import net.fantesy84.board.listener.TempratureBoard;
import net.fantesy84.listener.Listener;
import net.fantesy84.subject.SimpleSubject;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class ObserverTest {
	@Test
	public void test1(){
		Listener listener1 = new TempratureBoard();
		Listener listener2 = new HumidityBoard();
		List<Listener> list = new ArrayList<Listener>();
		list.add(listener1);
		list.add(listener2);
		SimpleSubject subject = new SimpleSubject();
		subject.setListeners(list);
		//subject.notifyRegistedListeners();
		subject.notifyRegistedListeners("test update data");
	}
}
