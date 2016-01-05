/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.nio
 * 文件名: Server.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月5日
 */
package net.fantesy84.server.nio;

/**
 * @author Andronicus
 * @since 2016年1月5日
 */
public class Server {
	public static void main(String[] args) {
		int port = 18080;
		NioServerHandler target = new NioServerHandler(port);
		new Thread(target, "NIO-SERVER-001").start();
	}
}
