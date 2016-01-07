/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.aio
 * 文件名: ConnectCompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class ConnectCompletionHandler implements CompletionHandler<Void, AsyncClientHandler> {

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void completed(Void result, AsyncClientHandler attachment) {
		AsynchronousSocketChannel channel = attachment.getClient();
		String order = "query_time";
		byte[] data = order.getBytes();
		ByteBuffer writeBuf = ByteBuffer.allocate(data.length);
		writeBuf.put(data);
		writeBuf.flip();
		channel.write(writeBuf, writeBuf, new SendReqeustCompletionHandler(channel, attachment.getLatch()));
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, AsyncClientHandler attachment) {
		attachment.getLatch().countDown();
	}

}
