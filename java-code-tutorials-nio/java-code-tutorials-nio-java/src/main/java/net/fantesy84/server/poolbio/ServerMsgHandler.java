/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.bio
 * 文件名: ServerMsgHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.server.poolbio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月5日
 */
public class ServerMsgHandler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ServerMsgHandler.class);
	private Socket socket;
	public ServerMsgHandler(Socket socket) {
		this.socket = socket;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String request = reader.readLine();
			logger.debug("Server receive request body: {}", request);
			String response = "query_time".equalsIgnoreCase(request) ? new Date().toString() : "Bad request!";
			logger.debug("Response body: {}", response);
			writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(response);
		} catch (IOException e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), new IllegalStateException(e));
			}
		}
	}

}
