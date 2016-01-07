/**
 * 项目名: java-code-tutorials-nio-java
 * 包名:  net.fantesy84.client.aio
 * 文件名: Client.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.aio;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class Client {
	public static void main(String[] args) {
		int port = 18080;
		AsyncClientHandler target = new AsyncClientHandler("127.0.0.1",port);
		new Thread(target, "AIO-CLIENT-001").start();
	}
}
