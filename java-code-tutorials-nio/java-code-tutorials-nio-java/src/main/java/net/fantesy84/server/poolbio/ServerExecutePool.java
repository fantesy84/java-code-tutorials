/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.poolbio
 * 文件名: ServerExecutePool.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.server.poolbio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Andronicus
 * @since 2016年1月5日
 */
public class ServerExecutePool {
	private ExecutorService executor;

	/**
	 * 
	 */
	public ServerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 300,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize, true));
	}
	
	public void execute(Runnable task) {
		executor.execute(task);
	}
}
