/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.aio
 * 文件名: ResponseWriteCompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.server.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class ResponseWriteCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private static final Logger logger = LoggerFactory.getLogger(RequestReadCompletionHandler.class);
	private AsynchronousSocketChannel asyncSocketChannel;
	
	/**
	 * @param asyncSocketChannel
	 */
	public ResponseWriteCompletionHandler(AsynchronousSocketChannel asyncSocketChannel) {
		super();
		this.asyncSocketChannel = asyncSocketChannel;
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		if (attachment.hasRemaining()) {
			asyncSocketChannel.write(attachment, attachment, this);
		}
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			asyncSocketChannel.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
