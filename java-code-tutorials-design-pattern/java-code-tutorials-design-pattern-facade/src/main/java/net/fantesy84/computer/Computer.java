/**
 * 项目名: java-code-tutorials-design-pattern-facade
 * 包名:  net.fantesy84.computer
 * 文件名: Computer.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class Computer {
	private final static Logger logger = LoggerFactory.getLogger(Computer.class);
	private CPU cpu;
	private Memory memory;
	private Disk disk;
	
	/**
	 * 
	 */
	public Computer() {
		super();
		cpu = new CPU();
		memory = new Memory();
		disk = new Disk();
	}

	public void startup(){
		logger.debug("computer starting...");
		cpu.startup();
		memory.startup();
		disk.startup();
		logger.debug("computer started!");
	}
	
	public void shutdown(){
		logger.debug("computer shutingdown...");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		logger.debug("computer shutdown!");
	}
}
