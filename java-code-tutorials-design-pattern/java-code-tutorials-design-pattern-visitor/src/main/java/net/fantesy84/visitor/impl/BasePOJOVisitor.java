/**
 * 项目名: java-code-tutorials-design-pattern-visitor
 * 包名:  net.fantesy84.visitor.impl
 * 文件名: BasePOJOVisitor.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.visitor.impl;

import net.fantesy84.subject.ISubject;
import net.fantesy84.visitor.IVisitor;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class BasePOJOVisitor implements IVisitor {

	/* (non-Javadoc)
	 * @see net.fantesy84.visitor.IVisitor#visit(net.fantesy84.subject.ISubject)
	 */
	@Override
	public void visit(ISubject subject) {
		System.out.println(subject.calculate());
	}

}
