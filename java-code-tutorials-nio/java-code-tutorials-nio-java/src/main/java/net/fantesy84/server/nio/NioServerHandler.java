/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.nio
 * 文件名: NioServerHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.server.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月5日
 */
public class NioServerHandler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(NioServerHandler.class);
	private int port;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	/**
	 * @param port
	 */
	public NioServerHandler(int port) {
		super();
		this.port = port;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1024);
			selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			logger.info("Server has been started at port:[{}]", port);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
			try {
				while (true) {
					int activeKeys = selector.selectNow();
					if (activeKeys > 0) {
						Set<SelectionKey> keys = selector.selectedKeys();
						Iterator<SelectionKey> itt = keys.iterator();
						while (itt.hasNext()) {
							SelectionKey selectionKey = itt.next();
							itt.remove();
							handleInput(selectionKey);
						}
					}
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					selector.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
	}

	/**
	 * @param selectionKey
	 */
	private void handleInput(SelectionKey selectionKey) throws IOException {
		if (selectionKey.isValid()) {
			if (selectionKey.isAcceptable()) {
				//已连接,为下一个新连接重置状态
				ServerSocketChannel ssc = ServerSocketChannel.open();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if (selectionKey.isReadable()) {
				SocketChannel channel = (SocketChannel) selectionKey.channel();
				ByteBuffer readBuf = ByteBuffer.allocate(1024);
				int read = channel.read(readBuf);
				if (read < 0) {
					selectionKey.cancel();
					channel.close();
					return;
				}
				String reqBody = null;
				while ((read = channel.read(readBuf)) > 0) {
					readBuf.flip();
					byte[] data = new byte[readBuf.remaining()];
					readBuf.get(data);
					reqBody = new String(data, "UTF-8");
				}
				String response = null;
				if (reqBody != null && reqBody.trim().length() > 0){
					logger.debug("Server receive request body:{}", reqBody);
					response = "query_time".equalsIgnoreCase(reqBody) ? new Date().toString() : "Bad request body!";
				}
				if (response != null && response.length() > 0) {
					Write2Client(channel, response);
				} else {
					logger.info("等待请求消息...");
				}
			}
		}
	}

	/**
	 * @param response
	 */
	private void Write2Client(SocketChannel socketChannel, String response) throws IOException{
		byte[] data = response.getBytes();
		ByteBuffer writeBuf = ByteBuffer.allocate(data.length);
		writeBuf.put(data);
		writeBuf.flip();
		socketChannel.write(writeBuf);
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
}
