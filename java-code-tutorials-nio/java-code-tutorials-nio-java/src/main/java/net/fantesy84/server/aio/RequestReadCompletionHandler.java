/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.aio
 * 文件名: RequestReadCompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.server.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class RequestReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private static final Logger logger = LoggerFactory.getLogger(RequestReadCompletionHandler.class);
	private AsynchronousSocketChannel asyncSocketChannel;
	
	/**
	 * @param asyncSocketChannel
	 */
	public RequestReadCompletionHandler(AsynchronousSocketChannel asyncSocketChannel) {
		super();
		this.asyncSocketChannel = asyncSocketChannel;
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
			String requestBody = new String(data, "UTF-8");
			logger.debug("Server receive request body:[{}]", requestBody);
			String response = "query_time".equalsIgnoreCase(requestBody) ? new Date().toString() : "Bad request!";
			byte[] resBytes = response.getBytes();
			ByteBuffer writeBuf = ByteBuffer.allocate(resBytes.length);
			writeBuf.put(resBytes);
			writeBuf.flip();
			asyncSocketChannel.write(writeBuf, writeBuf, new ResponseWriteCompletionHandler(asyncSocketChannel));
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
			this.asyncSocketChannel.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
