/**
 * 项目名: java-code-tutorials-webservice-axis
 * 包名:  net.fantesy84.ws.service
 * 文件名: AxisHelloWS.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月18日
 */
package net.fantesy84.ws.service;

/**
 * @author Andronicus
 * @since 2015年11月18日
 */
public class AxisHelloWS {
	
	public String hello(String name) {
		return name + " say Hello!";
	}
	
	public Integer add(Integer x, Integer y) {
		return x + y;
	}
}
