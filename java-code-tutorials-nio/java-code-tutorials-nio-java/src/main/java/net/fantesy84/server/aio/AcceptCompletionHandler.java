/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.aio
 * 文件名: AcceptCompletionHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.server.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void completed(AsynchronousSocketChannel result, AsyncServerHandler attachment) {
		attachment.getAsyncServerSocketChannel().accept(attachment, this);
		ByteBuffer readBuf = ByteBuffer.allocate(1024);
		result.read(readBuf, 60l, TimeUnit.SECONDS, readBuf, new RequestReadCompletionHandler(result));
	}

	/* (non-Javadoc)
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, AsyncServerHandler attachment) {
		attachment.getLatch().countDown();
	}

}
