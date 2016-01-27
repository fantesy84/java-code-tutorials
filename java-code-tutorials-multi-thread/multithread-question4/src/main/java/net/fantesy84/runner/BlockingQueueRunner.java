/**
 * 项目名: multithread-question4
 * 包名:  net.fantesy84.runner
 * 文件名: BlockingQueueRunner.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月27日
 */
package net.fantesy84.runner;

import net.fantesy84.queue.BlockingQueue;
import net.fantesy84.queue.Input;
import net.fantesy84.queue.Output;

/**
 * @author Andronicus
 * @since 2016年1月27日
 */
public class BlockingQueueRunner {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new BlockingQueue<String>(8);
		new Thread(new Input(queue)).start();
		new Thread(new Output(queue)).start();
	}
}
