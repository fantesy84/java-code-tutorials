/**
 * 项目名: java-code-tutorials-design-pattern-command
 * 包名:  net.fantesy84.invoker.impl
 * 文件名: ProjectManagerInvoker.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.invoker.impl;

import net.fantesy84.command.ICommand;
import net.fantesy84.invoker.Invoker;
import net.fantesy84.receiver.ICommandProcesser;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class ProjectManagerInvoker implements Invoker {
	private ICommand<?> command;
	
	/**
	 * @param command
	 */
	public ProjectManagerInvoker(ICommand<?> command) {
		super();
		this.command = command;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.invoker.Invoker#action()
	 */
	@Override
	public void action(ICommandProcesser processer) {
		try {
			command.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		command = new RebuildProjectCode(processer);
	}

}
