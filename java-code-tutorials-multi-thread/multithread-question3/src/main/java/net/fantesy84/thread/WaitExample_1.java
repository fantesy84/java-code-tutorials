/**
 * 项目名: multithread-question3
 * 包名:  net.fantesy84.thread
 * 文件名: WaitExample.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月26日
 */
package net.fantesy84.thread;

import net.fantesy84.runner.Runner;

/**
 * @author Andronicus
 * @since 2016年1月26日
 */
public class WaitExample_1 implements Runnable {
	private Integer counter;
	
	/**
	 * @param counter
	 * @param lock
	 */
	public WaitExample_1(Integer counter) {
		super();
		this.counter = counter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		synchronized (Runner.class) {
			try {
				System.out.println("Thread-1 begin...");
				System.out.println("Now, counter's value: " + counter);
				while (counter < 5) {
					counter++;
				}
				System.out.println("Increament finished! counter's value: " + counter);
				Runner.class.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("At last, counter's value: " + counter);
			System.out.println("Thread-1 over!");
		}
	}
	
}
