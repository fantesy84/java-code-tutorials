/**
 * 项目名: java-code-tutorials-design-pattern-visitor
 * 包名:  net.fantesy84.subject
 * 文件名: ISubject.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.subject;

import net.fantesy84.visitor.IVisitor;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public interface ISubject {
	void accept(IVisitor visitor);
	Object calculate();
}
