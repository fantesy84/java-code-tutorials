/**
 * 项目名: java-code-tutorials-design-pattern-mediator
 * 包名:  net.fantesy84.domain.sub
 * 文件名: User.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月29日
 */
package net.fantesy84.domain.sub;

import net.fantesy84.domain.Person;
import net.fantesy84.mediator.Mediator;

/**
 * @author Andronicus
 * @since 2015年12月29日
 */
public class Student extends Person {

	/**
	 * 
	 */
	public Student() {
		super();
	}

	/**
	 * @param mediator
	 */
	public Student(Mediator mediator) {
		super(mediator);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.domain.Person#eat()
	 */
	@Override
	public void eat() {
		System.out.println("学生吃东西总是很挑剔!");
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.domain.Person#work()
	 */
	@Override
	public void work() {
		System.out.println("学生暂时还不需要外出工作!");
	}

}
