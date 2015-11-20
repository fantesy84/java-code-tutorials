/**
 * 项目名: java-code-tutorials-design-pattern-factory
 * 包名:  net.fantesy84.computer.mbsub
 * 文件名: MSI_Z170A_MotherBoard.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.computer.mbsub;

import net.fantesy84.computer.MotherBoard;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class MSI_B150M_MotherBoard extends MotherBoard {
	private static final long serialVersionUID = 6587056113596027144L;
	
	/**
	 * 
	 */
	public MSI_B150M_MotherBoard() {
		super();
		super.setBrand("微星");
		super.setFamily("B");
		super.setModel("B150M PRO-VDH");
		super.setMainChipsetName("Intel B150");
		super.setPrice(799.00d);
	}
}
