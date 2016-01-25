/**
 * Project multithread-join
 * File: ThreadRunner.java
 * CreateTime: 2016年1月25日
 * Creator: junjie.ge
 * copy right ©2016 葛俊杰
 */
package net.fantesy84.runner;

import net.fantesy84.thread.RunnableImpl;

/**
 * TypeName: ThreadRunner
 * <P>TODO
 * 
 * <P>CreateTime: 2016年1月25日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class ThreadRunner {
	public static void main(String[] args) throws Exception {
		RunnableImpl task = new RunnableImpl();
		task.setNum(1);
		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);
		Thread t3 = new Thread(task);
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
	}
}
