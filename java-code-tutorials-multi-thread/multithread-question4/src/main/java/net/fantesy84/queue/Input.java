/**
 * 项目名: multithread-question4
 * 包名:  net.fantesy84.queue
 * 文件名: Input.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月27日
 */
package net.fantesy84.queue;

/**
 * @author Andronicus
 * @since 2016年1月27日
 */
public class Input implements Runnable {
	private BlockingQueue<String> queue;
	
	/**
	 * @param queue
	 */
	public Input(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			for (int i = 0; i < 20; i++) {
				String str = "排队人" + i + "号";
				queue.enqueue(str);
				Thread.sleep(1000l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
