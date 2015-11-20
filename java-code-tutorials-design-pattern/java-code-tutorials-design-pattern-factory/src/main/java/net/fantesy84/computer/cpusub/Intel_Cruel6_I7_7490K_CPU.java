/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer.cpusub
 * 文件名: IntelCPU.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer.cpusub;

import net.fantesy84.computer.CPU;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class Intel_Cruel6_I7_7490K_CPU extends CPU {
	private static final long serialVersionUID = -2897152643142155360L;
	
	/**
	 * 
	 */
	public Intel_Cruel6_I7_7490K_CPU() {
		super();
		super.setBrand("英特尔");
		super.setFamily("酷睿");
		super.setGeneration("第六代");
		super.setModel("i7 7490K");
		super.setCoreNumber(8);
		super.setMainFrequency("3.2GHz");
		super.setThreadNumber(16);
		super.setPrice(3980.00d);
	}
}
