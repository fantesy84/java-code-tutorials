/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.aio
 * 文件名: RequestCompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class ResponseCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private static final Logger logger = LoggerFactory.getLogger(SendReqeustCompletionHandler.class);
	private AsynchronousSocketChannel client;
	private CountDownLatch latch;
	
	/**
	 * @param client
	 * @param latch
	 */
	public ResponseCompletionHandler(AsynchronousSocketChannel client, CountDownLatch latch) {
		super();
		this.client = client;
		this.latch = latch;
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] data = new byte[attachment.remaining()];
		attachment.get(data);
		try {
			String response = new String(data, "UTF-8");
			logger.info("Now is:[{}]", response);
			latch.countDown();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			client.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
