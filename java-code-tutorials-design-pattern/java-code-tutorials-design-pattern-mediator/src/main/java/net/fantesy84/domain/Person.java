/**
 * 项目名: java-code-tutorials-design-pattern-mediator
 * 包名:  net.fantesy84.domain
 * 文件名: Person.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月29日
 */
package net.fantesy84.domain;

import net.fantesy84.mediator.Mediator;

/**
 * @author Andronicus
 * @since 2015年12月29日
 */
public abstract class Person {
	private String name;
	private String sex;
	private Mediator mediator;
	
	/**
	 * 
	 */
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param mediator
	 */
	public Person(Mediator mediator) {
		super();
		this.mediator = mediator;
	}
	
	public abstract void eat();
	
	public abstract void work();
	
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
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the mediator
	 */
	public Mediator getMediator() {
		return mediator;
	}
	/**
	 * @param mediator the mediator to set
	 */
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	
}
