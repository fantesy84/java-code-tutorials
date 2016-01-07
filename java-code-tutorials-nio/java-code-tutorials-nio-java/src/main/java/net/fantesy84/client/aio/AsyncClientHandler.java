/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.aio
 * 文件名: AsyncClientHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class AsyncClientHandler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(AsyncClientHandler.class);
	private String host;
	private int port;
	private AsynchronousSocketChannel client;
	private CountDownLatch latch;
	
	/**
	 * @param host
	 * @param port
	 */
	public AsyncClientHandler(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		try {
			client = AsynchronousSocketChannel.open();
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
		client.connect(new InetSocketAddress(host, port), this, new ConnectCompletionHandler());
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			client.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @return the client
	 */
	public AsynchronousSocketChannel getClient() {
		return client;
	}

	/**
	 * @return the latch
	 */
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
