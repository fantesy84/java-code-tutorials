/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.aio
 * 文件名: SendReqeustCompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class SendReqeustCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel client;
	private CountDownLatch latch;
	
	/**
	 * @param client
	 */
	public SendReqeustCompletionHandler(AsynchronousSocketChannel client, CountDownLatch latch) {
		super();
		this.client = client;
		this.latch = latch;
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		if (attachment.hasRemaining()) {
			client.write(attachment, attachment, this);
		} else {
			ByteBuffer readBuf = ByteBuffer.allocate(1024);
			client.read(readBuf, readBuf, new ResponseCompletionHandler(client, latch));
		}
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		
	}

}
