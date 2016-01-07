/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.aio
 * 文件名: AsyncServerHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.server.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class AsyncServerHandler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(AsyncServerHandler.class);
	private AsynchronousServerSocketChannel asyncServerSocketChannel;
	private CountDownLatch latch;
	/**
	 * @param port
	 */
	public AsyncServerHandler(int port) {
		super();
		try {
			asyncServerSocketChannel = AsynchronousServerSocketChannel.open();
			asyncServerSocketChannel.bind(new InetSocketAddress("127.0.0.1", port), 1024);
			logger.info("服务器已启动,占用端口:{}", port);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	private void doAccept() {
		asyncServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}

	/**
	 * @return the asyncServerSocketChannel
	 */
	public AsynchronousServerSocketChannel getAsyncServerSocketChannel() {
		return asyncServerSocketChannel;
	}

	/**
	 * @return the latch
	 */
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
