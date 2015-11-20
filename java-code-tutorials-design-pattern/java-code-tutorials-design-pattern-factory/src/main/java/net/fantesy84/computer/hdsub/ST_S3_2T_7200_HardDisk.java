/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer.hdsub
 * 文件名: ST_S3_2TB_7200_HardDisk.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer.hdsub;

import net.fantesy84.computer.HardDisk;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class ST_S3_2T_7200_HardDisk extends HardDisk {
	private static final long serialVersionUID = -8241261793116321023L;

	/**
	 * 
	 */
	public ST_S3_2T_7200_HardDisk() {
		super();
		super.setBrand("希捷");
		super.setGeneration("SATA 3.0");
		super.setModel("ST2000DX001");
		super.setCapacity("2TB");
		super.setSpeed(7200);
		super.setPrice(550.00d);
	}
	
}
