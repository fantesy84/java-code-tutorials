/**
 * Created: 2017-01-06
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.event;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import net.fantesy84.reacter.event.AbstractEvent;
import net.fantesy84.reacter.event.Event;
import net.fantesy84.reacter.notify.NotifyService;
import net.fantesy84.reacter.notify.NotifyServiceImpl;

/**
 * @author junjie.ge
 *
 */
public class EventNotifyTest {
	private NotifyService ns;
	private TestListener listener1;
	private TestListener listener2;
	private TestResourceBean rb;
	private TestResourceSubBean rsb;

	@Before
	public void init() {
		ns = new NotifyServiceImpl();
		listener1 = new TestListener();
		listener2 = new TestListener();
		try {
			ns.subscribe(listener1);
			ns.subscribe(listener2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rb = new TestResourceBean();
		rb.setId("1");
		rb.setName("admin");
		rsb = new TestResourceSubBean();
		rsb.setId("2");
		rsb.setName("user");
		rsb.setSex("Male");
	}

	@Test
	public void test0() {
		TestEvent e = new TestEvent("testListener", "test/echo", "hello!world!");
		Class<?> clazz = e.getClass();
		System.out.println(e instanceof Event);
		System.out.println(Event.class.isAssignableFrom(clazz));
		System.out.println(AbstractEvent.class.isAssignableFrom(clazz));
	}

	@Test
	public void test1() {
		TestEvent e = new TestEvent("testListener", "test/trigger", "hello!world!");
		ns.doNotify(e);
	}
	@Test
	public void test2() {
		TestEvent e = new TestEvent("testListener", "test/event", 1);
		e.setSource("叫你不要乱跑来的!");
		ns.doNotify(e);
		ns.unsubscribe(listener1);
		ns.doNotify(e);
	}
	@Test
	public void test3() {
		TestEvent e = new TestEvent("testListener", "test/echo", "hello!world!");
		ns.doNotify(e);
	}
	@Test
	public void test4() {
		TestEvent e = new TestEvent("testListener", "test/date", new Date());
		ns.doNotify(e);
	}
	@Test
	public void test5() {
		//TestEvent e = new TestEvent("testListener", "test/parentObject", rsb);
		TestEvent e = new TestEvent("testListener", "test/subObject", rsb);
		ns.doNotify(e);
	}

}
