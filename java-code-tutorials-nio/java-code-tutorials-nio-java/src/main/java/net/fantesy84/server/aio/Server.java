/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.server.aio
 * 文件名: Server.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.server.aio;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class Server {
	public static void main(String[] args) {
		int port = 18080;
		AsyncServerHandler target = new AsyncServerHandler(port);
		new Thread(target, "AIO-SERVER-001").start();
	}
}
