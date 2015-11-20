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
public class Intel_Cruel5_I7_4860M_CPU extends CPU {
	private static final long serialVersionUID = -2897152643142155360L;
	/**
	 * 
	 */
	public Intel_Cruel5_I7_4860M_CPU() {
		super();
		super.setBrand("英特尔");
		super.setFamily("酷睿");
		super.setGeneration("第五代");
		super.setModel("i7 4860M");
		super.setCoreNumber(4);
		super.setMainFrequency("2.5GHz");
		super.setThreadNumber(8);
		super.setPrice(1890.00d);
	}
}
