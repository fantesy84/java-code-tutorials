/**
 * 项目名: java-code-tutorials-design-pattern-memento
 * 包名:  net.fantesy84.memento
 * 文件名: Storage.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.memento;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class Storage {
	private Memento memento;
	
	/**
	 * @param memento
	 */
	public Storage(Memento memento) {
		super();
		this.memento = memento;
	}

	/**
	 * @return the memento
	 */
	public Memento getMemento() {
		return memento;
	}

	/**
	 * @param memento the memento to set
	 */
	public void setMemento(Memento memento) {
		this.memento = memento;
	}
	
}
