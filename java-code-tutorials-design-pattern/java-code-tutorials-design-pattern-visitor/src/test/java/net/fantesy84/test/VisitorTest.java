/**
 * 项目名: java-code-tutorials-design-pattern-visitor
 * 包名:  net.fantesy84.test
 * 文件名: IntegerTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.test;

import org.junit.Test;

import net.fantesy84.domain.BasePOJO;
import net.fantesy84.subject.ISubject;
import net.fantesy84.subject.impl.BasePOJOSubject;
import net.fantesy84.visitor.IVisitor;
import net.fantesy84.visitor.impl.BasePOJOVisitor;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class VisitorTest {
	@Test
	public void test1(){
		IVisitor v = new BasePOJOVisitor();
		ISubject s = new BasePOJOSubject(new BasePOJO(4.0, 1.5));
		s.accept(v);
	}
}
