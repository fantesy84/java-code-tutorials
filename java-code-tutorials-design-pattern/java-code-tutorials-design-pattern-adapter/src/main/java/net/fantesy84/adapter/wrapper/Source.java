/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.clazz
 * 文件名: Source.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.wrapper;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class Source {
	private String name;
	
	public void method1(){
		System.out.println(this.name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
