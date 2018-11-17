/**
 * Created: 2017-01-06
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.event;

import java.util.Date;

import net.fantesy84.reacter.annotations.EventListener;
import net.fantesy84.reacter.annotations.EventMapping;
import net.fantesy84.reacter.event.Event;

/**
 * @author junjie.ge
 *
 */
@EventListener(name="testListener")
public class TestListener {

	@EventMapping(value="test/trigger")
	public void onEvent() {
		System.out.println("事件已触发!监听器收到通知!");
	}

	@EventMapping(value="test/event")
	public void echoEvent(Event e) {
		System.out.println("Event对象类型:" + e.getClass());
		System.out.println(e.getResources());
	}

	@EventMapping(value="test/echo")
	public void echoEvent(TestEvent e) {
		System.out.println(e.getLastTrigger());
		System.out.println(e.getResources());
	}

	@EventMapping(value="test/date")
	public void dateEvent(Date time) {
		System.out.println(time);
	}

	@EventMapping(value="test/subObject")
	public void objEvent1(TestResourceSubBean bean) {
		System.out.println("本身属性:" + bean.getSex());
		System.out.println("父类属性:" + bean.getId() + " | " + bean.getName());
	}

	@EventMapping(value="test/parentObject")
	public void objEvent2(TestResourceBean bean) {
		if (bean instanceof TestResourceSubBean) {
			TestResourceSubBean subBean = (TestResourceSubBean) bean;
			System.out.println("本身属性:" + subBean.getSex());
		}
		System.out.println("父类属性:" + bean.getId() + " | " + bean.getName());
	}
}
