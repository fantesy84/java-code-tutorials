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
public class Kingston_HyperX_DDR3_1600_Memory extends Memory {
	private static final long serialVersionUID = -4098853636776141848L;

	/**
	 * 
	 */
	public Kingston_HyperX_DDR3_1600_Memory() {
		super();
		super.setBrand("金士顿");
		super.setFamily("HyperX");
		super.setGeneration("DDR3");
		super.setModel("KHX1600C9D3X2K2");
		super.setMainFrequency("1600GHz");
		super.setCapacity("4G");
		super.setPrice(150.00d);
	}
	
}
