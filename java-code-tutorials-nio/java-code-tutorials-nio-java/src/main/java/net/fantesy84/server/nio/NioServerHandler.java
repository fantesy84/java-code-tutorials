/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.nio
 * 文件名: NioServerHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.server.nio;

import java.io.IOException;
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
			serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", port), 1024);
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
							try {
								handleInput(selectionKey);
							} catch (Exception e) {
								if (selectionKey != null) {
									selectionKey.cancel();
									if (selectionKey.channel() != null) {
										selectionKey.channel().close();
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} 
			if (selector != null) {
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
				logger.debug("发现连接操作位!将进行TCP握手,之后建立新管道等待新的客户端接入...");
				ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if (selectionKey.isReadable()) {
				logger.debug("发现读取操作位!接收已连接的客户端发送的请求消息...");
				SocketChannel sc = (SocketChannel) selectionKey.channel();
				ByteBuffer readBuf = ByteBuffer.allocate(1024);
				int read = sc.read(readBuf);
				String reqBody = null;
				if (read > 0) {
					logger.debug("发现数据!开始读取...");
					readBuf.flip();
					byte[] data = new byte[readBuf.remaining()];
					readBuf.get(data);
					reqBody = new String(data, "UTF-8");
				} else if (read < 0) {
					logger.warn("客户端已断开连接!");
					selectionKey.cancel();
					sc.close();
				}
				logger.debug("Server receive request body:{}", reqBody);
				String response = null;
				if (reqBody != null && reqBody.trim().length() > 0){
					response = "query_time".equalsIgnoreCase(reqBody) ? new Date().toString() : "Bad request body!";
				}
				if (response != null && response.length() > 0) {
					Write2Client(sc, response);
				} else {
					logger.info("无响应消息,等待新的请求消息...");
				}
			}
		}
	}

	/**
	 * @param response
	 */
	private void Write2Client(SocketChannel socketChannel, String response) throws IOException{
		logger.debug("将发送响应消息:{}", response);
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
