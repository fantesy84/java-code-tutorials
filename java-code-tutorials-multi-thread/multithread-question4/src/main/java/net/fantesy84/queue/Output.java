/**
 * 项目名: multithread-question4
 * 包名:  net.fantesy84.queue
 * 文件名: Output.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月27日
 */
package net.fantesy84.queue;

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
			while (true) {
				System.out.println("当前队列情况: " + queue.getQueue().toString());
				String result = queue.dequeue();
				if (result != null) {
					System.out.println(result + " 已处理完成!");
				} else {
					break;
				}
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
