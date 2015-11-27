/**
 * 项目名: java-code-tutorials-design-pattern-facade
 * 包名:  net.fantesy84.computer
 * 文件名: Memory.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.computer;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class Disk {
	public void startup(){
		System.out.println("Disk: disk starting...");
	}
	
	public void shutdown(){
		System.out.println("Disk: disk shutingdown...");
	}
}
