/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.bio
 * 文件名: Server.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.server.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月5日
 */
public class Server {
	private static final Logger logger = LoggerFactory.getLogger(Server.class);
	public static void main(String[] args) {
		int port = 18080;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			logger.info("服务器启动完毕!IP: {};端口: {}","127.0.0.1", port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				if (socket.isConnected()) {
					logger.debug("客户端已连接");
					new Thread(new ServerMsgHandler(socket), "BIO-SERVER-001").start();
				} else {
					logger.warn("客户端未成功连接!");
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} finally {
			try {
				if (!server.isClosed()) {
					server.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), new IllegalStateException(e));
			}
		}
	}
	
}
