/**
 * Project multithread-join
 * File: ThreadOne.java
 * CreateTime: 2016年1月25日
 * Creator: junjie.ge
 * copy right ©2016 葛俊杰
 */
package net.fantesy84.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TypeName: ThreadOne
 * <P>TODO
 * 
 * <P>CreateTime: 2016年1月25日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class RunnableImpl implements Runnable {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(RunnableImpl.class);
	private int num;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Now, the number is: " + num);
		num++;
		System.out.println("num has add itself. now: " + num);
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
}
