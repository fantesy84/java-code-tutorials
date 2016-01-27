/**
 * 项目名: multithread-question4
 * 包名:  net.fantesy84.queue
 * 文件名: Output.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月27日
 */
package net.fantesy84.queue;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Andronicus
 * @since 2016年1月27日
 */
public class Output implements Runnable {
	private BlockingQueue<String> queue;
	
	/**
	 * @param queue
	 */
	public Output(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			Lock lock = new ReentrantLock();
			while (true) {
				lock.lock();
				List<String> list = queue.getQueue();
				System.out.print("当前队列长度: " + list.size() + " ");
				//System.out.println("当前队列情况: " + list.toString());
				lock.unlock();
				String result = queue.dequeue();
				if (result != null) {
					System.out.println(result + " 已处理完成!");
				} else {
					break;
				}
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
