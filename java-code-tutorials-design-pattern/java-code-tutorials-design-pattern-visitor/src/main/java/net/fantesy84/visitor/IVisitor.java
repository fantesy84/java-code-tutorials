/**
 * 项目名: java-code-tutorials-design-pattern-visitor
 * 包名:  net.fantesy84.visitor
 * 文件名: IVisitor.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.visitor;

import net.fantesy84.subject.ISubject;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public interface IVisitor {
	void visit(ISubject subject);
}
