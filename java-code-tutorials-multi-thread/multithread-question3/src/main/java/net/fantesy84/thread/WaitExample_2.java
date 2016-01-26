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
public class WaitExample_2 implements Runnable {
	private Integer counter;
	
	/**
	 * @param counter
	 */
	public WaitExample_2(Integer counter) {
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
				System.out.println("Thread-2 begin...");
				System.out.println("Thread-2 will sleep 5000ms ...");
				Runner.class.notify();
				counter = counter + 10;
				Thread.sleep(5000l);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Thread-2 go on...");
			System.out.println("Thread-2 over!");
		}
	}

}
