/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer.hdsub
 * 文件名: WD_S3_2T_7200_HardDisk.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer.hdsub;

import net.fantesy84.computer.HardDisk;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class WD_S3_2T_7200_HardDisk extends HardDisk {
	private static final long serialVersionUID = 7898238975193494708L;

	/**
	 * 
	 */
	public WD_S3_2T_7200_HardDisk() {
		super();
		super.setBrand("西部数据");
		super.setGeneration("SATA 3.0");
		super.setModel("WD2002FAEX");
		super.setCapacity("2TB");
		super.setSpeed(7200);
		super.setPrice(870.00d);
	}
	
}
