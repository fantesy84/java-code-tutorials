/**
 * 项目名: java-code-tutorials-design-pattern-visitor
 * 包名:  net.fantesy84.subject.impl
 * 文件名: BasePOJOSubject.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.subject.impl;

import net.fantesy84.domain.BasePOJO;
import net.fantesy84.subject.ISubject;
import net.fantesy84.visitor.IVisitor;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class BasePOJOSubject implements ISubject{
	
	private BasePOJO pojo;
	
	/**
	 * @param pojo
	 */
	public BasePOJOSubject(BasePOJO pojo) {
		super();
		this.pojo = pojo;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#accept(net.fantesy84.visitor.IVisitor)
	 */
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.subject.ISubject#calculate()
	 */
	@Override
	public Object calculate() {
		if (pojo == null) {
			return null;
		}
		if (pojo.getBase() == 0 && pojo.getBase() == pojo.getExponent()) {
			return null;
		}
		return Math.pow(pojo.getBase(), pojo.getExponent());
	}

}
