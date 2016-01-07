/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.nio
 * 文件名: NioClientHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class NioClientHandler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(NioClientHandler.class);
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	/**
	 * @param host
	 * @param port
	 */
	public NioClientHandler(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
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
			connectServer();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return;
		}
		while (!stop) {
			try {
				int keys = selector.selectNow();
				if (keys > 0) {
					Set<SelectionKey> selectedKeys = selector.selectedKeys();
					Iterator<SelectionKey> itt = selectedKeys.iterator();
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
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
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
	private void handleInput(SelectionKey key) throws IOException{
		if (key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				logger.debug("发现连接操作位!");
				if (sc.finishConnect()) {
					logger.debug("完成TCP物理链路建立!");
					sc.register(selector, SelectionKey.OP_READ);
					sendRequest(sc);
				} else {
					return;
				}
			}
			if (key.isReadable()) {
				logger.debug("发现读取操作位!");
				ByteBuffer readBuf = ByteBuffer.allocate(1024);
				int read = sc.read(readBuf);
				if (read > 0) {
					readBuf.flip();
					byte[] data = new byte[readBuf.remaining()];
					readBuf.get(data);
					String response = new String(data, "UTF-8");
					logger.info("Now is:[{}]", response);
					//this.stop = true;
					sendRequest(sc);
				} else if (read < 0) {
					key.cancel();
					sc.close();
				}
			}
		}
	}

	/**
	 * @param sc
	 */
	private void sendRequest(SocketChannel sc) throws IOException{
		String order = "query_time";
		byte[] bytes = order.getBytes();
		ByteBuffer writeBuf = ByteBuffer.allocate(1024);
		writeBuf.put(bytes);
		writeBuf.flip();
		sc.write(writeBuf);
		if (!writeBuf.hasRemaining()) {
			logger.debug("Send request body '{}' to server succeed!", order);
		}
	}

	/**
	 * 
	 */
	private void connectServer() throws IOException{
		boolean isConnected = socketChannel.connect(new InetSocketAddress(host, port));
		if (isConnected) {
			logger.debug("客户端已连接!注册读取操作位,服务端将读取socket管道中的数据.");
			socketChannel.register(selector, SelectionKey.OP_READ);
			sendRequest(socketChannel);
		} else {
			logger.debug("客户端未连接!注册连接操作位,服务端将建立TCP连接.");
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

}
