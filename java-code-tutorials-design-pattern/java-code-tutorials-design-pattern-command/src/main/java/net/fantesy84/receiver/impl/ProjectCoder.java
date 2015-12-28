/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.receiver.impl
 * 文件名: ProjectCoder.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.receiver.impl;

import net.fantesy84.receiver.ICommandProcesser;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class ProjectCoder implements ICommandProcesser {

	/* (non-Javadoc)
	 * @see net.fantesy84.receiver.ICommandProcesser#process(java.lang.Object)
	 */
	@Override
	public void process(Object obj) throws Exception {
		System.out.println(obj);
		System.out.println("这需求太操蛋了!不做!");
		obj = null;
	}

}
