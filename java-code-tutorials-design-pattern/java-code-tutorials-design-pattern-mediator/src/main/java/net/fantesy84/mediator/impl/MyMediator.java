/**
 * 项目名: java-code-tutorials-design-pattern-mediator
 * 包名:  net.fantesy84.mediator.impl
 * 文件名: MyMediator.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月29日
 */
package net.fantesy84.mediator.impl;

import net.fantesy84.domain.Person;
import net.fantesy84.domain.sub.Student;
import net.fantesy84.domain.sub.Worker;
import net.fantesy84.mediator.Mediator;

/**
 * @author Andronicus
 * @since 2015年12月29日
 */
public class MyMediator implements Mediator {
	private Person student;
	private Person worker;
	/* (non-Javadoc)
	 * @see net.fantesy84.mediator.Mediator#createMediator()
	 */
	@Override
	public void createMediator() {
		student = new Student(this);
		worker = new Worker(this);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.mediator.Mediator#eat()
	 */
	@Override
	public void eat() {
		student.eat();
		worker.eat();
	}
	
	/* (non-Javadoc)
	 * @see net.fantesy84.mediator.Mediator#workAll()
	 */
	@Override
	public void work() {
		student.work();
		worker.work();
	}

	/**
	 * @return the student
	 */
	public Person getStudent() {
		return student;
	}

}
