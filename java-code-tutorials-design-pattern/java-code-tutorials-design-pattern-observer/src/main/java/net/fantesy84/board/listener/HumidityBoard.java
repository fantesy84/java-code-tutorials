/**
 * 项目名: java-code-tutorials-design-pattern-observer
 * 包名:  net.fantesy84.broad.listener
 * 文件名: HumidityBoard.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.board.listener;

import net.fantesy84.listener.Listener;

/**
 * @author Andronicus
 * @since 2015年12月23日
 */
public class HumidityBoard implements Listener {

	/* (non-Javadoc)
	 * @see net.fantesy84.listener.Listener#getName()
	 */
	@Override
	public String getName() {
		return HumidityBoard.class.getSimpleName();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.listener.Listener#update()
	 */
	@Override
	public void update() {
		System.out.println("Humidity will update");
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.listener.Listener#execute(java.lang.Object)
	 */
	@Override
	public void execute(Object data) {
		System.out.println("Humidity data is updating!" + " " + data);
	}

}
