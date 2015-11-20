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
public class MSI_Z170A_MotherBoard extends MotherBoard {
	private static final long serialVersionUID = 6587056113596027144L;
	
	/**
	 * 
	 */
	public MSI_Z170A_MotherBoard() {
		super();
		super.setBrand("微星");
		super.setFamily("Z");
		super.setModel("Z170A GAMING M3");
		super.setMainChipsetName("Intel Z170");
		super.setPrice(1599.00d);
	}
}
