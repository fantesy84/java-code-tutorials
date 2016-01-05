/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.bio
 * 文件名: Client.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.client.poolbio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2016年1月5日
 */
public class Client {
	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	public static void main(String[] args) {
		int port = 18080;
		Socket client = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			client = new Socket("127.0.0.1", port);
			if (client.isConnected()) {
				String request = "query_time";
				writer = new PrintWriter(client.getOutputStream(), true);
				writer.println(request);
				logger.info("Send request:[{}] to server.", request);
				reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
				char[] part = new char[1024];
				StringBuilder builder = new StringBuilder();
				while (reader.read(part) > 0) {
					builder.append(part);
				}
				String response = builder.toString();
				logger.info("Now is: {}", response);
			} else {
				logger.warn("未连接到服务器!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), new IllegalStateException(e));
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), new IllegalStateException(e));
			}
		}
	}
}
