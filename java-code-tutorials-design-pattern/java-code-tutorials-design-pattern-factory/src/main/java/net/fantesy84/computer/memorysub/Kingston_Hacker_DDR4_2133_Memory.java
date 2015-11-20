/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer.memorysub
 * 文件名: Kingston_DDR3_1600_Memory.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer.memorysub;

import net.fantesy84.computer.Memory;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class Kingston_Hacker_DDR4_2133_Memory extends Memory {
	private static final long serialVersionUID = -4098853636776141848L;

	/**
	 * 
	 */
	public Kingston_Hacker_DDR4_2133_Memory() {
		super();
		super.setBrand("金士顿");
		super.setFamily("骇客神条FURY");
		super.setGeneration("DDR4");
		super.setModel("KFY2133C9D4X1K2");
		super.setMainFrequency("2133GHz");
		super.setCapacity("8G");
		super.setPrice(389.00d);
	}
	
}
